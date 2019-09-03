package com.linkpets.cms.aprilfool.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.aprilfool.service.IActivityDraftService;
import com.linkpets.core.model.CmsActivityDraft;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/26
 */


@Api(value = "活动抽奖接口", tags = "活动抽奖接口")
@RestController
@ResponseResult
@RequestMapping("/activity/drafts")
public class CmsActivityDraftResource {

    @Autowired
    private IActivityDraftService activityDraftService;


    @GetMapping("")
    @ApiOperation(value = "查询用户抽奖列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "userId", name = "userId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult getActivityDraftList(@RequestParam String activityId,
                                               @RequestParam String userId) {
        List<CmsActivityDraft> activityCustomDraftList = activityDraftService.getActivityDraftListByUserId(activityId, userId);
        return PlatformResult.success(activityCustomDraftList);
    }


    @GetMapping("/count")
    @ApiOperation(value = "查询用户未抽奖次数")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "userId", name = "userId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult getActivityDraftCount(@RequestParam String activityId,
                                                @RequestParam String userId) {
        int draftCount = activityDraftService.getActivityDraftExistNoByUserId(activityId, userId);
        return PlatformResult.success(draftCount);
    }


    @PostMapping("")
    @ApiOperation(value = "发起抽奖", notes = "返回 30001 错误码，则此用户已无抽奖机会；返回参数data为null,默认用户未抽中; ")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "userId", name = "userId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult doActivityDraft(@RequestParam String activityId,
                                          @RequestParam String userId) {
        int draftCount = activityDraftService.getActivityDraftExistNoByUserId(activityId, userId);
        if (draftCount == 0) {
            return PlatformResult.failure(ResultCode.DRAFT_COUNT_NOT_EXIST, ResultCode.DRAFT_COUNT_NOT_EXIST.message());
        }

        CmsActivityDraft draft = activityDraftService.doActivityDraftProcess(activityId, userId);

        return PlatformResult.success(draft);
    }


    @PostMapping("/draft")
    @ApiOperation(value = "新增抽奖", notes = "返回 30002 错误码，则此用户抽奖机会已达上限; ")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "userId", name = "userId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult AddActivityDraft(@RequestParam String activityId,
                                           @RequestParam String userId) {
        int draftCount = activityDraftService.getActivityDraftCount(activityId, userId);
        if (draftCount == 2) {
            return PlatformResult.failure(ResultCode.DRAFT_COUNT_NO_MORE, ResultCode.DRAFT_COUNT_NO_MORE.message());
        }

        CmsActivityDraft draft = activityDraftService.addActivityDraft(activityId, userId);

        return PlatformResult.success(draft);
    }


}
