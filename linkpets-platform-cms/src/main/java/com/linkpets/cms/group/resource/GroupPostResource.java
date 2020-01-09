package com.linkpets.cms.group.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.group.reqEntity.GroupPostReq;
import com.linkpets.cms.group.service.IGroupPostService;
import com.linkpets.core.respEntity.RespGroupPost;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Edie
 */
@Api(value = "圈子模块-圈子发帖接口", tags = "圈子模块-圈子发帖接口")
@ResponseResult
@RestController
@RequestMapping("/group/post")
public class GroupPostResource {

    @Resource
    private IGroupPostService groupPostService;

    @ApiOperation("分页获取圈子帖子")
    @GetMapping("page")
    public PlatformResult getGroupPostPage(@RequestParam(value = "groupId", required = false) String groupId,
                                           @ApiParam(name = "isValid", value = "查询帖子是否有效 0：无效 1：有效")
                                           @RequestParam(value = "isValid", required = false) Integer isValid,
                                           @ApiParam(name = "nickName", value = "用户昵称")
                                           @RequestParam(value = "nickName", required = false) String nickName,
                                           @RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RespGroupPost> postId = groupPostService.getGroupPostPage(groupId, isValid, nickName, pageNum, pageSize);
        return PlatformResult.success(postId);
    }

    @ApiOperation("创建圈子帖子")
    @PostMapping("")
    public PlatformResult crtGroupPost(@RequestBody GroupPostReq groupPostReq) {
        String postId = groupPostService.crtGroupPost(groupPostReq.getCmsGroupPost(), groupPostReq.getCmsGroupPostImgList());
        return PlatformResult.success(postId);
    }

    @ApiOperation("更新圈子帖子")
    @PutMapping("")
    public PlatformResult uptGroupPost(@RequestBody GroupPostReq groupPostReq) {
        groupPostService.uptGroupPost(groupPostReq.getCmsGroupPost(), groupPostReq.getCmsGroupPostImgList());
        return PlatformResult.success();
    }

    @ApiOperation("删除圈子帖子")
    @DeleteMapping("")
    public PlatformResult delGroupPost(@RequestParam("postId") String postId,
                                       @RequestParam("memo") String memo) {
        groupPostService.delGroupPost(postId, memo);
        return PlatformResult.success();
    }

    @ApiOperation("点赞圈子帖子")
    @PostMapping("/like")
    public PlatformResult crtGroupPostLike(@RequestParam("postId") String postId,
                                           @RequestParam("userId") String userId,
                                           @ApiParam(name = "type", value = "0：取消点赞 1：点赞")
                                           @RequestParam("type") String type) {
        groupPostService.crtGroupPostLike(postId, userId, type);
        return PlatformResult.success();
    }

}
