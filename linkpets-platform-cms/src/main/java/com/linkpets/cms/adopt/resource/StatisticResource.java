package com.linkpets.cms.adopt.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.model.AdoptionStatistic;
import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.cms.adopt.service.IStatisticService;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value = "领养模块-统计接口", tags = "领养模块-统计接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/statistic")
public class StatisticResource {

    @Resource
    IApplyService applyService;

    @Resource
    IPetService petService;

    @Resource
    IUserService userService;

    @Resource
    IStatisticService statisticService;

    @ApiOperation("查询总览统计")
    @GetMapping(value = "list")
    public PlatformResult getStatisticList() {
        String startDate = DateUtils.getCurrentDay();
        String applyEndDate = DateUtils.rollNOfDate(DateUtils.getCurrentDay(), 1, -7, "yyyy-MM-dd");
        int applyCount = applyService.getApplyCount(startDate, applyEndDate);
        int loginTodayCount = userService.getLoginCount(startDate);
        int totalUserCount = userService.getTotalUserCount();
        int adoptTotalCount = statisticService.getAdoptTotalCount("");
        List<Map<String, Object>> nealyWeekCount = statisticService.getNealyWeekCount();
        AdoptionStatistic adoptionStatistic = new AdoptionStatistic();
        adoptionStatistic.setTotalUserCount(totalUserCount);
        adoptionStatistic.setLoginTodayCount(loginTodayCount);
        adoptionStatistic.setApplyCount(applyCount);
        adoptionStatistic.setAdoptTotalCount(adoptTotalCount);
        adoptionStatistic.setNearlyWeekCount(nealyWeekCount);
        return PlatformResult.success(adoptionStatistic);
    }

    @ApiOperation("查询机构统计数据")
    @GetMapping(value = "org/list")
    public PlatformResult getOrgStatistic(@RequestParam("orgId") String orgId) {
        return PlatformResult.success(statisticService.getDataByOrg(orgId));
    }

    @ApiOperation("查询今日总览统计")
    @GetMapping(value = "today")
    public PlatformResult getTodayStatisticList() {
        String startDate = DateUtils.getCurrentDay();
        int applyTodayCount = applyService.getApplyCount(startDate + "00:00:00", startDate + "23:59:59");
        int loginTodayCount = userService.getLoginCount(startDate);
        int totalUserCount = userService.getTotalUserCount();
        int adoptTodayCount = petService.getPetAdoptCount(startDate);
        AdoptionStatistic adoptionStatistic = new AdoptionStatistic();
        adoptionStatistic.setTotalUserCount(totalUserCount);
        adoptionStatistic.setLoginTodayCount(loginTodayCount);
        adoptionStatistic.setApplyTodayCount(applyTodayCount);
        adoptionStatistic.setAdoptTodayCount(adoptTodayCount);
        return PlatformResult.success(adoptionStatistic);
    }


}
