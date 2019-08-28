package com.linkpets.cms.adopt.resource;


import com.alibaba.fastjson.JSONObject;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IUserService;
import com.linkpets.core.model.CmsAdoptAttention;
import com.linkpets.core.model.CmsUser;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.DateUtils;
import com.linkpets.util.UserAnalyseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(value = "领养平台用户接口",tags = "领养平台-用户接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/users")
public class UserResource {

    @Resource
    private IUserService userService;

    @ApiOperation("获取用户详情接口")
    @GetMapping("/user")
    public PlatformResult getUserInfo(@RequestParam("userId") String userId) {
        CmsUser userInfo = userService.getUserInfoByUserId(userId);
        return PlatformResult.success(userInfo);
    }

    @ApiOperation("修改用户信息接口")
    @PutMapping("/user")
    public PlatformResult updateUserInfo(@RequestParam("userId") String userId,
                                         @RequestParam("nickName") String nickName,
                                         @RequestParam("gender") String gender,
                                         @RequestParam("location") String location,
                                         @RequestParam("intro") String intro,
                                         @RequestParam("birthday") String birthday,
                                         @RequestParam("portrait") String portrait) {
        CmsUser user = new CmsUser();
        user.setUserId(userId);
        user.setPortrait(portrait);
        user.setNickName(nickName);
        user.setGender(gender);
        user.setLocation(location);
        user.setIntro(intro);
        user.setBirthday(DateUtils.getFormatDate(birthday));
        userService.updateUserInfo(user);
        return PlatformResult.success();
    }

    @ApiOperation("获取领养发布用户信息接口")
    @GetMapping("/adoptUser")
    public PlatformResult getAdoptUserInfo(@RequestParam("userId") String userId) {
        CmsUser userInfo = userService.getUserInfoByUserId(userId);
        String birthday = DateUtils.getFormatDateStr(userInfo.getBirthday());
        String starSign = UserAnalyseUtil.getStarSignName(birthday);
        userInfo.setStarSign(starSign);
        userInfo.setAgeFrom(UserAnalyseUtil.getAgeFrom(birthday));
        String lastLoginTime = userService.getLastLoginTime(userId);
        userInfo.setLastLoginTime(lastLoginTime);
        return PlatformResult.success(userInfo);
    }

    @ApiOperation(value = "我关注的人列表", notes = "分页获取我关注的人列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "{targetUserId}/attentTo")
    PlatformResult getUserListAttentTo(@PathVariable("targetUserId") String targetUserId,
                                       @RequestParam("userId") String userId,
                                       @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                                       @RequestParam(value = "sortCol", required = false, defaultValue = "attentTime") String sortCol,
                                       @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("targetUserId", targetUserId);
        JSONObject data = userService.getUserListAttentTo(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(data);
    }

    @PostMapping("attention")
    PlatformResult setAttention(@RequestBody CmsAdoptAttention record) {
        userService.crtAttention(record);
        return PlatformResult.success(true);
    }

    @ApiOperation(value = "取消关注", notes = "取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "被+关注人的用户id", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "attentBy", value = "去+关注的人", required = true)})
    @DeleteMapping("attention")
    PlatformResult delAttention(@RequestParam("userId") String userId, @RequestParam("attentBy") String attentBy) {
        userService.delAttention(userId, attentBy);
        return PlatformResult.success(null);
    }


    @ApiOperation(value = "关注我的人列表", notes = "分页获取关注我的人列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "{targetUserId}/attentBy")
    PlatformResult getUserListAttentBy(@PathVariable("targetUserId") String targetUserId,
                                       @RequestParam("userId") String userId,
                                       @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                                       @RequestParam(value = "sortCol", required = false, defaultValue = "attentTime") String sortCol,
                                       @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("targetUserId", targetUserId);
        param.put("userId", userId);
        JSONObject data = userService.getUserListAttentBy(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(data);
    }

    @GetMapping("attention")
    PlatformResult getAttention(@RequestParam("userId") String userId,
                                @RequestParam("targetUserId") String targetUserId) {
        int followed = userService.getAttentionStatus(userId, targetUserId);
        if (followed > 0) {
            return PlatformResult.success(true);
        }
        return PlatformResult.success(false);
    }


}
