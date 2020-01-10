package com.linkpets.cms.group.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.group.service.IPointStatementService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "圈子模块-用户积分接口", tags = "圈子模块-用户积分接口")
@ResponseResult
@RestController
@RequestMapping("/pointStatement")
public class PointStatementResource {

    @Resource
    private IPointStatementService pointStatementService;

    @ApiOperation("添加用户积分:返回0表示积分已添加或已超过上限，前端不显示积分提示")
    @PostMapping
    public PlatformResult crtPointStatement(@ApiParam(name = "userId", value = "", required = true)
                                            @RequestParam("userId") String userId,
                                            @ApiParam(name = "channel", value = "4:领养信息转发获得积分;5:领养信息浏览获得积分;6:活动分享获得积分;7:参加活动获得积分;8:圈内发帖获得积分;10:系统奖励积分", required = true)
                                            @RequestParam("channel") int channel,
                                            @ApiParam(name = "targetId", value = "领养信息浏览及转发传petId,活动转发传activityId", required = true)
                                            @RequestParam(value = "targetId", required = false) String targetId,
                                            @ApiParam(name = "points", value = "积分", required = true)
                                            @RequestParam(value = "points",required = false) int rewardPoints) {
        Integer points = pointStatementService.crtPointStatementByChannel(userId, targetId, channel, rewardPoints);
        return PlatformResult.success(points);
    }

}
