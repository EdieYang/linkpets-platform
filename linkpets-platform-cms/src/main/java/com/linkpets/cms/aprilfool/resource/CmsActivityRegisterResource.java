package com.linkpets.cms.aprilfool.resource;


import com.alibaba.fastjson.JSONObject;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.aprilfool.service.IActivityRegisterService;
import com.linkpets.core.model.CmsActivityRegistry;
import com.linkpets.core.model.CmsActivityRegistryInfo;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "活动报名注册接口", tags = "活动报名接口")
@ResponseResult
@RestController
@RequestMapping(value = "/activity/register")
public class CmsActivityRegisterResource {

    @Autowired
    private IActivityRegisterService activityRegisterService;


    @PostMapping("")
    @ApiOperation(value = "活动报名接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registryType", value = "报名类型 0：公益机构或个人  1：普通用户", required = true, dataType = "int", paramType = "query")
    })
    public PlatformResult registerActivity(@RequestParam String activityId, @RequestParam String userId, @RequestParam int registryType) {
        String registerId = activityRegisterService.registerActivity(activityId, userId, registryType);
        return PlatformResult.success(registerId);
    }

    @GetMapping("")
    @ApiOperation(value = "活动报名信息详情接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "registryId", value = "活动报名Id", required = true, dataType = "String", paramType = "query"),
    })
    public PlatformResult getActivityRegistry(@RequestParam String registryId) {
        CmsActivityRegistry cmsActivityRegistry = activityRegisterService.getRegistryInfo(registryId);
        return PlatformResult.success(cmsActivityRegistry);
    }

    @GetMapping("/{activityId}/user")
    @ApiOperation(value = "根据活动id、userId查询活动报名信息详情接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "query"),

    })
    public PlatformResult getActivityRegistries(@PathVariable String activityId, @RequestParam String userId) {
        List<CmsActivityRegistry> activityCustomRegistryList = activityRegisterService.getRegistryInfoByUserId(activityId, userId);
        return PlatformResult.success(activityCustomRegistryList);
    }

    @GetMapping("/{activityId}")
    @ApiOperation(value = "根据活动id查询活动报名列表接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "path"),
    })
    public PlatformResult getActivityRegistries(@PathVariable String activityId) {
        List<CmsActivityRegistry> activityCustomRegistries = activityRegisterService.getRegistryInfoList(activityId);
        return PlatformResult.success(activityCustomRegistries);
    }


    @GetMapping("/info")
    @ApiOperation(value = "查询活动报名问卷接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "query")

    })
    public PlatformResult getRegisterQuestionInfo(@RequestParam String activityId, @RequestParam String userId) {
        CmsActivityRegistryInfo activityRegistryInfo = activityRegisterService.getRegisterQuestionInfo(activityId, userId);
        return PlatformResult.success(activityRegistryInfo);
    }


    @PostMapping("/info")
    @ApiOperation(value = "提交活动报名问卷接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "registerName", value = "报名者姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerPhone", value = "报名者手机号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerWx", value = "报名者微信号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registryType", value = "报名类型 0：公益机构或个人  1：普通用户", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contentFirst", value = "问卷答案1", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "contentSecond", value = "问卷答案2", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "contentThird", value = "问卷答案3", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "contentForth", value = "问卷答案4", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "query")

    })
    public JSONObject registerQuestionInfo(@RequestParam String activityId, @RequestParam String userId, @RequestParam int registryType,
                                           @RequestParam String registerName, @RequestParam String registerPhone, @RequestParam String registerWx,
                                           @RequestParam String contentFirst, @RequestParam String contentSecond, @RequestParam String contentThird,
                                           @RequestParam String contentForth) {
        return activityRegisterService.registerQuestionInfo(activityId, userId, registryType, registerName, registerPhone, registerWx, contentFirst, contentSecond, contentThird, contentForth);
    }

}
