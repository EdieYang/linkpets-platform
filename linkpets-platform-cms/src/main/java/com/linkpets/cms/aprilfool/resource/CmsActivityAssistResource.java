package com.linkpets.cms.aprilfool.resource;


import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.aprilfool.service.IActivityAssistService;
import com.linkpets.cms.aprilfool.service.IActivityDraftService;
import com.linkpets.core.model.CmsActivityAssistance;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "活动助力接口", tags = "活动助力接口")
@ResponseResult
@RestController
@RequestMapping("/activity/assistance")
public class CmsActivityAssistResource {


    @Autowired
    private IActivityAssistService activityAssistService;

    @Autowired
    private IActivityDraftService activityDraftService;

    @PostMapping("")
    @ApiOperation(value = "新增助力接口", notes = "此接口自动判断是否存在之前的记录，若已存在则返回记录id不进行新增")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "userId", name = "userId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "助力类型 0：官方 1：公益机构或个人", name = "assistType", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "助力对象userId (助力类型为官方时传空字符串即可)", name = "assistUserId", required = false, dataType = "String", paramType = "query")
    })
    public PlatformResult createAssistance(@RequestParam String activityId,
                                           @RequestParam String userId,
                                           @RequestParam int assistType,
                                           @RequestParam(required = false) String assistUserId) {
        if (assistType == 1 && StringUtils.isEmpty(assistUserId)) {
            return PlatformResult.failure(ResultCode.PARAM_IS_INVALID, "assistUserId is empty");
        }

        String result = activityAssistService.createActivityAssistRecord(activityId, userId, assistType, assistUserId);
        return PlatformResult.success(result);
    }

    @GetMapping("")
    @ApiOperation(value = "查询助力记录", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "userId", name = "userId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult getAssistance(@RequestParam String activityId,
                                        @RequestParam String userId) {

        CmsActivityAssistance activityAssistance = activityAssistService.getActivityAssistRecordByUserId(activityId, userId);
        int draftExistNo = activityDraftService.getActivityDraftExistNoByUserId(activityId, userId);
        Map<String, Object> result = new HashMap<>();
        result.put("activityAssistance", activityAssistance);
        result.put("draftExistNo", draftExistNo);
        return PlatformResult.success(result);
    }

}
