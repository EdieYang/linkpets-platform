package com.linkpets.cms.lost.resource;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.lost.model.PostsInfo;
import com.linkpets.cms.lost.service.ILostPostService;
import com.linkpets.core.model.CmsLostPost;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Api(value = "寻宠发布帖子接口", tags = "寻宠接口")
@RequestMapping(value = "/lost/posts")
@RestController
public class LostPostResource {

    @Resource
    private ILostPostService lostPostService;

    private final static String city = "上海市";

    @GetMapping("")
    @ApiOperation("获取寻宠帖子列表")
    public PlatformResult getLostPosts(@RequestParam("pageNo") int pageNo,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam(value = "dayLimit",required = false,defaultValue = "-1") int dayLimit) {

        List<PostsInfo> posts = lostPostService.getLostPostsList(pageNo, pageSize, city, dayLimit);
        return PlatformResult.success(posts);

    }

    @GetMapping("/post")
    @ApiOperation("获取寻宠帖子详情")
    public PlatformResult getLostPostDetail(@RequestParam("postId") String postId) {

        PostsInfo post = lostPostService.getLostPost(postId);
        return PlatformResult.success(post);

    }

    @ApiOperation("新增寻宠帖子信息")
    @PostMapping(value = "/post")
    public PlatformResult createPost(@RequestBody JSONObject data) {
        String userId = data.getString("userId");
        String content = data.getString("content");
        String images = data.getString("images");
        String location = data.getString("location");
        String detailLocation = data.getString("detailLocation");
        String lat = data.getString("lat");
        String lng = data.getString("lng");
        String city=data.getString("city");

        CmsLostPost post = new CmsLostPost();
        String postId = UUIDUtils.getUUID();
        post.setPostId(postId);
        post.setContent(content);
        post.setImages(images);
        post.setUserId(userId);
        post.setLocation(location);
        post.setDetailLocation(detailLocation);
        post.setLat(lat);
        post.setLng(lng);
        post.setCity(city);
        post.setCreateDate(new Date());
        lostPostService.insertPost(post);
        return PlatformResult.success(postId);
    }

    @ApiOperation("修改寻宠帖子信息")
    @PutMapping(value = "/post")
    public PlatformResult modifyPost(@RequestBody JSONObject data) {
        String userId = data.getString("userId");
        String postId = data.getString("postId");
        String content = data.getString("content");
        String images = data.getString("images");
        String location = data.getString("location");
        String detailLocation = data.getString("detailLocation");
        String lat = data.getString("lat");
        String lng = data.getString("lng");
        String city=data.getString("city");

        CmsLostPost post = new CmsLostPost();
        post.setPostId(postId);
        post.setContent(content);
        post.setImages(images);
        post.setUserId(userId);
        post.setLocation(location);
        post.setDetailLocation(detailLocation);
        post.setLat(lat);
        post.setLng(lng);
        post.setCity(city);
        post.setUpdateDate(new Date());
        lostPostService.updatePost(post);

        return PlatformResult.success();
    }

}
