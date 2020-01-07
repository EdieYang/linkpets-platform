package com.linkpets.cms.adopt.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IGroupActivityRegisterService;
import com.linkpets.cms.adopt.service.IGroupActivityService;
import com.linkpets.core.model.CmsAdoptGroupActivity;
import com.linkpets.core.model.CmsAdoptGroupActivityRegister;
import com.linkpets.core.respEntity.RespActivityRegister;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @ApiOperation("分页查询圈子活动报名列表")
    @GetMapping("page")
    public PlatformResult getGroupActivityRegisterPage(@RequestParam(value = "activityId") String activityId,
                                                       @RequestParam("pageNum") Integer pageNum,
                                                       @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RespActivityRegister> pageInfo = activityRegisterService.getGroupActivityRegisterPage(activityId, pageNum, pageSize);
        return PlatformResult.success(pageInfo);
    }


    @ApiOperation("活动报名")
    @PostMapping("")
    public PlatformResult crtGroupActivityRegister(@RequestParam(value = "activityId") String activityId,
                                                   @RequestParam(value = "userId") String userId) {
        //TODO 判断活动必备条件是否满足
        List<CmsAdoptGroupActivityRegister> registerList=activityRegisterService.getGroupActivityRegisterListByUserId(userId,activityId);
        if(registerList.size()>0){
            return PlatformResult.failure(ResultCode.ACTIVITY_REGISTER_DUPLICATE);
        }
        String registerId = activityRegisterService.crtGroupActivityRegister(userId, activityId);
        return PlatformResult.success(registerId);
    }


}
