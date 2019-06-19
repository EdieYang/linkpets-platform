package com.linkpets.cms.aprilfool.resource;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.aprilfool.service.IActivityAssistService;
import com.linkpets.cms.aprilfool.service.IActivityService;
import com.linkpets.core.model.CmsActivity;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/3/23
 */
@Api(tags = "活动管理", description = "活动相关接口")
@ResponseResult
@RestController
@RequestMapping("/activities")
public class CmsActivityResource {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IActivityAssistService activityAssistService;

    @PostMapping("/activity")
    @ApiOperation(value = "新增活动接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityName", value = "活动标题", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "introduction", value = "活动介绍", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "活动开始时间", required = true, dataType = "String", paramType = "query", example = "2019-03-22 10:23:33"),
            @ApiImplicitParam(name = "endTime", value = "活动结束时间", required = true, dataType = "String", paramType = "query", example = "2019-03-25 10:23:33"),
            @ApiImplicitParam(name = "orgId", value = "活动举办机构Id", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult createActivity(@RequestParam String activityName,
                                         @RequestParam String introduction,
                                         @RequestParam String startTime,
                                         @RequestParam String endTime,
                                         @RequestParam String orgId) {
        String activityId = activityService.createActivity(activityName, introduction, startTime, endTime, orgId);
        return PlatformResult.success(activityId);
    }


    @DeleteMapping("/activity/{activityId}")
    @ApiOperation(value = "删除活动接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "path"),
    })
    public void deleteActivity(@PathVariable String activityId) {
        activityService.deleteActivity(activityId);
    }


    @PutMapping("/activity/{activityId}")
    @ApiOperation(value = "修改活动接口", notes = "活动字段修改及状态停启")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "activityName", value = "活动标题", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "introduction", value = "活动介绍", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "活动开始时间", required = false, dataType = "String", paramType = "query", example = "2019-03-22 10:23:33"),
            @ApiImplicitParam(name = "endTime", value = "活动结束时间", required = false, dataType = "String", paramType = "query", example = "2019-03-25 10:23:33"),
            @ApiImplicitParam(name = "orgId", value = "活动举办机构Id", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "isActive", value = "是否开启活动", required = false, dataType = "int", paramType = "query", defaultValue = "-1")
    })
    public PlatformResult createActivity(@PathVariable String activityId,
                                         @RequestParam(required = false) String activityName,
                                         @RequestParam(required = false) String introduction,
                                         @RequestParam(required = false) String startTime,
                                         @RequestParam(required = false) String endTime,
                                         @RequestParam(required = false) String orgId,
                                         @RequestParam(required = false, defaultValue = "-1") int isActive) {
        CmsActivity cmsActivity = activityService.modifyActivity(activityId, activityName, introduction, startTime, endTime, orgId, isActive);
        return PlatformResult.success(cmsActivity);
    }


    @GetMapping("/activity/{activityId}")
    @ApiOperation(value = "获去活动详情接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动Id", required = true, dataType = "String", paramType = "path"),
    })
    public PlatformResult getActivity(@PathVariable String activityId) {
        Map<String,Object> map=new HashMap<>();
        CmsActivity cmsActivity = activityService.getActivity(activityId);
        int assistNo=activityAssistService.getActivityAssistNo(activityId);
        map.put("activity",cmsActivity);
        map.put("assistNo",assistNo);

        return PlatformResult.success(map);
    }


    @GetMapping("")
    @ApiOperation(value = "获去活动列表接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "活动开始时间", required = false, dataType = "String", paramType = "query", example = "2019-03-22 10:23:33"),
            @ApiImplicitParam(name = "endTime", value = "活动结束时间", required = false, dataType = "String", paramType = "query", example = "2019-03-25 10:23:33"),
            @ApiImplicitParam(name = "orgId", value = "活动举办机构Id", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "isActive", value = "是否开启活动", required = false, dataType = "int", paramType = "query", defaultValue = "-1")
    })
    public PlatformResult getActivities(@RequestParam(required = false) String startTime,
                                        @RequestParam(required = false) String endTime,
                                        @RequestParam(required = false) String orgId,
                                        @RequestParam(required = false, defaultValue = "-1") int isActive) {
        List<CmsActivity> activityList = activityService.getActivitiesList(orgId, startTime, endTime, isActive);
        return PlatformResult.success(activityList);
    }

}
