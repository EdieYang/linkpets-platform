package com.linkpets.cms.group.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.ICertificationService;
import com.linkpets.cms.group.service.IGroupActivityService;
import com.linkpets.cms.group.service.IQuestionnaireAnswerService;
import com.linkpets.core.model.CmsGroupActivity;
import com.linkpets.core.respEntity.RespGroupActivity;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Edie
 */
@Api(value = "圈子模块-圈子活动接口", tags = "圈子模块-圈子活动接口")
@ResponseResult
@RestController
@RequestMapping("/group/activities")
public class GroupActivityResource {

    @Resource
    private IGroupActivityService groupActivityService;
    @Resource
    private ICertificationService certificationService;
    @Resource
    private IQuestionnaireAnswerService answerService;

    @ApiOperation("分页查询圈子活动列表")
    @GetMapping("page")
    public PlatformResult getGroupActivityPage(@RequestParam(value = "activityType", required = false) Integer activityType,
                                               @RequestParam(value = "isActive", required = false) Integer isActive,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RespGroupActivity> pageInfo = groupActivityService.getGroupActivityPage(activityType, isActive, pageNum, pageSize);
        return PlatformResult.success(pageInfo);
    }

    @ApiOperation("查询圈子活动详情")
    @GetMapping("")
    public PlatformResult getGroupActivityInfo(@RequestParam("activityId") String activityId,
                                               @RequestParam(value = "userId", required = false) String userId) {
        CmsGroupActivity cmsGroupActivity = new CmsGroupActivity();
        if (StringUtils.isNotEmpty(userId)) {
            cmsGroupActivity = groupActivityService.getGroupActivityInfoWithUserId(activityId, userId);
            if (cmsGroupActivity != null) {
                cmsGroupActivity.setIsAuthenticated(certificationService.isAuthenticated(userId));
                if (cmsGroupActivity.getActivityShouldQuestionnaire() == 1) {
                    cmsGroupActivity.setIsAnswered(answerService.getQuestionnaireAnswerInfo("", userId, activityId) == null ? 0 : 1);
                }
            }
        } else {
            cmsGroupActivity = groupActivityService.getGroupActivityInfo(activityId);
        }
        return PlatformResult.success(cmsGroupActivity);
    }

    @ApiOperation("创建圈子活动")
    @PostMapping("")
    public PlatformResult crtGroupActivity(@RequestBody CmsGroupActivity cmsGroupActivity) {
        String activityId = groupActivityService.crtGroupActivity(cmsGroupActivity);
        return PlatformResult.success(activityId);
    }

    @ApiOperation("更新圈子活动")
    @PutMapping("")
    public PlatformResult uptGroupActivity(@RequestBody CmsGroupActivity cmsGroupActivity) {
        groupActivityService.uptGroupActivity(cmsGroupActivity);
        return PlatformResult.success();
    }


    @ApiOperation("删除圈子活动")
    @DeleteMapping("")
    public PlatformResult delGroupActivity(@RequestParam("activityId") String activityId) {
        groupActivityService.delGroupActivity(activityId);
        return PlatformResult.success();
    }

    @ApiOperation("关注圈子活动")
    @PostMapping("/follow")
    public PlatformResult crtGroupActivityFollow(@RequestParam("userId") String userId,
                                                 @RequestParam("activityId") String activityId) {
        groupActivityService.crtGroupActivityFollow(userId, activityId);
        return PlatformResult.success();
    }


    @ApiOperation("取消关注圈子活动")
    @DeleteMapping("/follow")
    public PlatformResult delGroupActivityFollow(@RequestParam("userId") String userId,
                                                 @RequestParam("activityId") String activityId) {
        groupActivityService.delGroupActivityFollow(userId, activityId);
        return PlatformResult.success();
    }


    @ApiOperation("查询用户关注的圈子活动列表")
    @GetMapping("/follow/list")
    public PlatformResult getUserGroupActivityList(@RequestParam("userId") String userId) {
        List<RespGroupActivity> userActivityList = groupActivityService.getGroupActivityListByUserId(userId);
        return PlatformResult.success(userActivityList);
    }
}
