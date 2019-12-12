package com.linkpets.cms.adopt.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IGroupService;
import com.linkpets.core.model.CmsAdoptGroup;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Edie
 */
@Api(value = "平台圈子接口", tags = "平台圈子接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/group")
public class GroupResource {

    @Resource
    private IGroupService groupService;

    @ApiOperation("分页查询圈子列表")
    @GetMapping("page")
    public PlatformResult getGroupPage(@ApiParam(name = "groupType", value = "圈子类型: 1 活动圈 2 普通圈子 ")
                                       @RequestParam(value = "groupType", required = false) String groupType,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize) {
        PageInfo<CmsAdoptGroup> pageInfo = groupService.getAdoptGroupPage(groupType, pageNum, pageSize);
        return PlatformResult.success(pageInfo);
    }

    @ApiOperation("查询圈子详情")
    @GetMapping("")
    public PlatformResult getGroupInfo(@RequestParam("groupId") String groupId) {
        CmsAdoptGroup cmsAdoptGroup = groupService.getAdoptGroup(groupId);
        return PlatformResult.success(cmsAdoptGroup);
    }

    @ApiOperation("创建圈子")
    @PostMapping("")
    public PlatformResult crtGroup(@RequestBody CmsAdoptGroup cmsAdoptGroup) {
        String groupId = groupService.crtAdoptGroup(cmsAdoptGroup);
        return PlatformResult.success(groupId);
    }

    @ApiOperation("更新圈子")
    @PutMapping("")
    public PlatformResult uptGroup(@RequestBody CmsAdoptGroup cmsAdoptGroup) {
        groupService.uptAdoptGroup(cmsAdoptGroup);
        return PlatformResult.success();
    }


    @ApiOperation("用户关注的圈子列表")
    @GetMapping("follow")
    public PlatformResult getFollowGroupList(@RequestParam("userId") String userId) {
        List<CmsAdoptGroup> followGroupList = groupService.getFollowedGroupList(userId);
        return PlatformResult.success(followGroupList);
    }

    @ApiOperation("用户关注圈子")
    @PostMapping("follow")
    public PlatformResult followGroup(@RequestParam("groupId") String groupId,
                                      @RequestParam("userId") String userId) {
        groupService.followGroup(userId, groupId);
        return PlatformResult.success();
    }


    @ApiOperation("用户取消关注圈子")
    @DeleteMapping("follow")
    public PlatformResult unFollowGroup(@RequestParam("groupId") String groupId,
                                        @RequestParam("userId") String userId) {
        groupService.unFollowGroup(userId, groupId);
        return PlatformResult.success();
    }
}
