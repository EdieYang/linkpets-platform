package com.linkpets.cms.adopt.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.*;
import com.linkpets.core.model.*;
import com.linkpets.core.respEntity.RespActivityRegister;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Edie
 */
@Api(value = "领养平台-圈子活动报名接口", tags = "领养平台-圈子活动报名接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/group/activity/register")
public class GroupActivityRegisterResource {

    @Resource
    private IGroupActivityRegisterService activityRegisterService;
    @Resource
    private IGroupActivityService groupActivityService;
    @Resource
    private IQuestionnaireAnswerService questionnaireAnswerService;
    @Resource
    private IUserService userService;
    @Resource
    private ICertificationService certificationService;
    @Resource
    private IPointStatementService pointStatementService;

    @ApiOperation("分页查询圈子活动报名列表")
    @GetMapping("page")
    public PlatformResult getGroupActivityRegisterPage(@RequestParam("activityId") String activityId,
                                                       @RequestParam(value = "isValid", required = false) Integer isValid,
                                                       @RequestParam(value = "wxAccount", required = false) String wxAccount,
                                                       @RequestParam(value = "mobilePhone", required = false) String mobilePhone,
                                                       @RequestParam(value = "involvementTime", required = false) String involvementTime,
                                                       @RequestParam("pageNum") Integer pageNum,
                                                       @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RespActivityRegister> pageInfo = activityRegisterService.getGroupActivityRegisterPage(activityId, isValid, wxAccount, mobilePhone, involvementTime, pageNum, pageSize);
        return PlatformResult.success(pageInfo);
    }


    @ApiOperation("查询圈子活动报名列表")
    @GetMapping("list")
    public PlatformResult getGroupActivityRegisterList(@RequestParam("activityId") String activityId,
                                                       @RequestParam(value = "isValid", required = false) Integer isValid,
                                                       @RequestParam(value = "wxAccount", required = false) String wxAccount,
                                                       @RequestParam(value = "mobilePhone", required = false) String mobilePhone,
                                                       @RequestParam(value = "involvementTime", required = false) String involvementTime) {
        List<RespActivityRegister> pageInfo = activityRegisterService.getGroupActivityRegisterList(activityId, isValid, wxAccount, mobilePhone, involvementTime);
        return PlatformResult.success(pageInfo);
    }


    @ApiOperation("活动报名")
    @PostMapping("")
    public PlatformResult crtGroupActivityRegister(@RequestParam(value = "activityId") String activityId,
                                                   @RequestParam(value = "userId") String userId,
                                                   @RequestParam(value = "isValid", required = false) Integer isValid,
                                                   @RequestParam(value = "involvementTime") String involvementTime) {
        //判断活动必备条件是否满足
        CmsAdoptGroupActivityRegister register = activityRegisterService.getGroupActivityRegisterListByUserId(userId, activityId);
        if (register != null) {
            return PlatformResult.failure(ResultCode.ACTIVITY_REGISTER_DUPLICATE);
        }
        CmsUser user = userService.getUser(userId);
        if (user == null) {
            return PlatformResult.failure(ResultCode.USER_NOT_EXIST);
        }
        CmsAdoptGroupActivity activity = groupActivityService.getAdoptGroupActivityInfo(activityId);
        if (activity == null) {
            return PlatformResult.failure(ResultCode.GROUP_ACTIVITY_NOT_EXIST);
        }
        if (new Date().before(activity.getActivityRegisterStartTime()) || new Date().after(activity.getActivityRegisterEndTime())) {
            return PlatformResult.failure(ResultCode.GROUP_ACTIVITY_REGISTER_TIMEOUT);
        }
        if ("1".equals(activity.getActivityShouldQuestionnaire())) {
            String questionnaireId = activity.getQuestionnaireId();
            List<CmsAdoptQuestionnaireAnswer> questionnaireAnswerList = questionnaireAnswerService.getQuestionnaireAnswerListByUserId(userId, activityId, questionnaireId);
            if (questionnaireAnswerList.size() == 0) {
                return PlatformResult.failure(ResultCode.GROUP_ACTIVITY_QUESTIONNAIRE_NOT_ANSWER);
            }
        }
        if ("1".equals(activity.getActivityShouldVerify())) {
            CmsAdoptCertification certification = certificationService.getUserCertification(userId);
            if (certification == null) {
                return PlatformResult.failure(ResultCode.UNAUTHENTICATED_USER);
            }
            if (!"1".equals(user.getAuthenticated())) {
                return PlatformResult.failure(ResultCode.AUTHENTICATED_USER_PROCEEDING);
            }
        }
        //报名人数限制
        List<RespActivityRegister> activityRegisterList = activityRegisterService.getGroupActivityRegisterList(activityId, 1, "", "", "");
        if (activity.getRegisterLimit() != 0 && activityRegisterList.size() >= activity.getRegisterLimit()) {
            return PlatformResult.failure(ResultCode.GROUP_ACTIVITY_REGISTER_FULL);
        }
        Integer points = pointStatementService.getUserPoints(userId);
        if (points < activity.getActivityCost()) {
            return PlatformResult.failure(ResultCode.GROUP_ACTIVITY_REGISTER_POINT_LACK);
        }
        String registerId = activityRegisterService.crtGroupActivityRegister(userId, activityId, involvementTime);
        return PlatformResult.success(registerId);
    }


    @ApiOperation("取消用户活动报名")
    @DeleteMapping("")
    public PlatformResult delGroupActivityRegister(@RequestParam(value = "activityId") String activityId,
                                                   @RequestParam(value = "userId") String userId,
                                                   @RequestParam("memo") String memo) {
        activityRegisterService.delGroupActivityRegister(userId, activityId, memo);
        return PlatformResult.success();
    }

}
