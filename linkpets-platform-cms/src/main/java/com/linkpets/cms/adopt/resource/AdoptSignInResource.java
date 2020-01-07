package com.linkpets.cms.adopt.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.ISignInService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "领养平台-签到接口", tags = "领养平台-签到接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/signIn")
public class AdoptSignInResource {

    @Resource
    private ISignInService signInService;

    @ApiOperation("用户签到返回积分:返回0代表今日已签到")
    @PostMapping
    public PlatformResult signIn(@RequestParam("userId") String userId) {
        int points = signInService.crtSignInRecord(userId);
        return PlatformResult.success(points);
    }


    @ApiOperation("获取用户连续签到天数")
    @GetMapping
    public PlatformResult getSignInTimes(@RequestParam("userId") String userId) {
        int times = signInService.getSignInTimes(userId);
        return PlatformResult.success(times);
    }
}
