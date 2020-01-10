package com.linkpets.cms.group.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.group.service.IGroupService;
import com.linkpets.core.model.CmsGroup;
import com.linkpets.core.respEntity.RespGroupInfo;
import com.linkpets.enums.ResultCode;
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
@Api(value = "圈子模块-圈子接口", tags = "圈子模块-圈子接口")
@ResponseResult
@RestController
@RequestMapping("/group")
public class GroupResource {

    @Resource
    private IGroupService groupService;

    @ApiOperation("分页查询圈子列表")
    @GetMapping("page")
    public PlatformResult getGroupPage(@ApiParam(name = "groupType", value = "圈子类型: 1 活动圈 2 普通圈子 ")
                                       @RequestParam(value = "groupType", required = false) String groupType,
                                       @ApiParam(name = "isActive", value = "是否上线: 0 下线 1 上线 ")
                                       @RequestParam(value = "isActive", required = false) Integer isActive,
                                       @ApiParam(name = "orderBy", value = "排序: 0 默认排序 1 按创建时间排序 ")
                                       @RequestParam(value = "orderBy", required = false) Integer orderBy,
                                       @RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RespGroupInfo> pageInfo = groupService.getGroupPage(groupType, isActive, orderBy, pageNum, pageSize);
        return PlatformResult.success(pageInfo);
    }

    @ApiOperation("查询圈子列表")
    @GetMapping("list")
    public PlatformResult getGroupPage(@ApiParam(name = "groupType", value = "圈子类型: 1 活动圈 2 普通圈子 ")
                                       @RequestParam(value = "groupType", required = false) String groupType,
                                       @ApiParam(name = "isActive", value = "是否上线: 0 下线 1 上线 ")
                                       @RequestParam(value = "isActive", required = false) Integer isActive,
                                       @ApiParam(name = "orderBy", value = "排序: 0 默认排序 1 按创建时间排序 ")
                                       @RequestParam(value = "orderBy", required = false) Integer orderBy) {
        List<RespGroupInfo> groupList = groupService.getGroupList(groupType, isActive, orderBy);
        return PlatformResult.success(groupList);
    }

    @ApiOperation("查询圈子详情")
    @GetMapping("")
    public PlatformResult getGroupInfo(@RequestParam("groupId") String groupId,
                                       @RequestParam(value = "userId", required = false) String userId) {
        RespGroupInfo cmsGroup = groupService.getGroup(groupId, userId);
        return PlatformResult.success(cmsGroup);
    }

    @ApiOperation("创建圈子：重复创建活动圈返回错误码【ACTIVITY_GROUP_DUPLICATE】：30010")
    @PostMapping("")
    public PlatformResult crtGroup(@RequestBody CmsGroup cmsGroup) {
        String groupType = cmsGroup.getGroupType();
        if (groupType.equals("1")) {
            List<CmsGroup> groupList = groupService.getActivityGroupList();
            if (groupList != null && groupList.size() > 0) {
                return PlatformResult.failure(ResultCode.ACTIVITY_GROUP_DUPLICATE);
            }
        }
        String groupId = groupService.crtGroup(cmsGroup);
        return PlatformResult.success(groupId);
    }

    @ApiOperation("更新圈子")
    @PutMapping("")
    public PlatformResult uptGroup(@RequestBody CmsGroup cmsGroup) {
        groupService.uptGroup(cmsGroup);
        return PlatformResult.success();
    }


    @ApiOperation("用户关注的圈子列表")
    @GetMapping("follow")
    public PlatformResult getFollowGroupList(@RequestParam("userId") String userId) {
        List<CmsGroup> followGroupList = groupService.getFollowedGroupList(userId);
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
