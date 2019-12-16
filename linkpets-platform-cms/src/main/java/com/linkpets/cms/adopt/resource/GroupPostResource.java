package com.linkpets.cms.adopt.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.reqEntity.GroupPostReq;
import com.linkpets.core.respEntity.RespGroupPost;
import com.linkpets.cms.adopt.service.IGroupPostService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Edie
 */
@Api(value = "领养平台-圈子发帖接口", tags = "领养平台-圈子发帖接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/group/post")
public class GroupPostResource {

    @Resource
    private IGroupPostService groupPostService;

    @ApiOperation("分页获取圈子帖子")
    @GetMapping("page")
    public PlatformResult getGroupPostPage(@RequestParam(value = "groupId") String groupId,
                                           @RequestParam("pageNum") Integer pageNum,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageInfo<RespGroupPost> postId = groupPostService.getGroupPostPage(groupId, pageNum, pageSize);
        return PlatformResult.success(postId);
    }


    @ApiOperation("创建圈子帖子")
    @PostMapping("")
    public PlatformResult crtGroupPost(@RequestBody GroupPostReq groupPostReq) {
        String postId = groupPostService.crtGroupPost(groupPostReq.getCmsAdoptGroupPost(), groupPostReq.getCmsAdoptGroupPostImgList());
        return PlatformResult.success(postId);
    }

    @ApiOperation("更新圈子帖子")
    @PutMapping("")
    public PlatformResult uptGroupPost(@RequestBody GroupPostReq groupPostReq) {
        groupPostService.uptGroupPost(groupPostReq.getCmsAdoptGroupPost(), groupPostReq.getCmsAdoptGroupPostImgList());
        return PlatformResult.success();
    }

    @ApiOperation("删除圈子帖子")
    @DeleteMapping("")
    public PlatformResult delGroupPost(@RequestParam("postId") String postId) {
        groupPostService.delGroupPost(postId);
        return PlatformResult.success();
    }
}
