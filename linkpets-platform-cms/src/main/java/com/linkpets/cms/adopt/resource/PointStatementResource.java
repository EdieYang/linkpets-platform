package com.linkpets.cms.adopt.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IPointStatementService;
import com.linkpets.cms.adopt.service.ISignInService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "领养平台-用户积分接口", tags = "领养平台-用户积分接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/pointStatement")
public class PointStatementResource {

    @Resource
    private IPointStatementService pointStatementService;

    @ApiOperation("添加用户积分:返回0表示积分已添加或已超过上限，前端不显示积分提示")
    @PostMapping
    public PlatformResult crtPointStatement(@ApiParam(name = "userId", value = "", required = true)
                                            @RequestParam("userId") String userId,
                                            @ApiParam(name = "channel", value = "4:领养信息转发获得积分;5:领养信息浏览获得积分;6:活动分享获得积分;7:参加活动获得积分;8:圈内发帖获得积分;10:系统奖励积分", required = true)
                                            @RequestParam("channel") String channel,
                                            @ApiParam(name = "targetId", value = "领养信息浏览及转发传petId,活动转发传activityId", required = true)
                                            @RequestParam("targetId") String targetId) {
        Integer points = pointStatementService.crtPointStatementByChannel(userId, channel, targetId);
        return PlatformResult.success(points);
    }

}
