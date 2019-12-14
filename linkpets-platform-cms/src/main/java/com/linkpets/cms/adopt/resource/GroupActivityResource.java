package com.linkpets.cms.adopt.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IGroupActivityService;
import com.linkpets.core.model.CmsAdoptGroup;
import com.linkpets.core.model.CmsAdoptGroupActivity;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Edie
 */
@Api(value = "平台圈子活动接口", tags = "平台圈子活动接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/group/activities")
public class GroupActivityResource {

    @Resource
    private IGroupActivityService groupActivityService;

    @ApiOperation("分页查询圈子活动列表")
    @GetMapping("page")
    public PlatformResult getGroupActivityPage(@RequestParam(value = "groupId") String groupType,
                                               @RequestParam(value = "isActive", required = false) Integer isActive,
                                               @RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize) {
        PageInfo<CmsAdoptGroupActivity> pageInfo = groupActivityService.getAdoptGroupActivityPage(groupType, isActive, pageNum, pageSize);
        return PlatformResult.success(pageInfo);
    }

    @ApiOperation("查询圈子详情")
    @GetMapping("")
    public PlatformResult getGroupActivityInfo(@RequestParam("activityId") String activityId) {
        CmsAdoptGroupActivity cmsAdoptGroupActivity = groupActivityService.getAdoptGroupActivityInfo(activityId);
        return PlatformResult.success(cmsAdoptGroupActivity);
    }

    @ApiOperation("创建圈子活动")
    @PostMapping("")
    public PlatformResult crtGroupActivity(@RequestBody CmsAdoptGroupActivity cmsAdoptGroupActivity) {
        String activityId = groupActivityService.crtAdoptGroupActivity(cmsAdoptGroupActivity);
        return PlatformResult.success(activityId);
    }

    @ApiOperation("更新圈子活动")
    @PutMapping("")
    public PlatformResult uptGroupActivity(@RequestBody CmsAdoptGroupActivity cmsAdoptGroupActivity) {
        groupActivityService.uptAdoptGroupActivity(cmsAdoptGroupActivity);
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

}
