package com.linkpets.cms.adopt.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.model.AdoptionStatistic;
import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.cms.adopt.service.IUserService;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Api(value = "领养平台领养申请接口", tags = "领养申请接口")
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

    @GetMapping(value = "list")
    public PlatformResult getStatisticList() {
        String syncDate = DateUtils.getFormatDateStr(new Date(), "yyyy-MM-dd");
        int applyTodayCount = applyService.getApplyCount(syncDate);
        int petAdoptTodayCount = petService.getPetAdoptCount(syncDate);
        int loginTodayCount=userService.getLoginCount(syncDate);
        int totalUserCount=userService.getTotalUserCount();
        AdoptionStatistic adoptionStatistic=new AdoptionStatistic(applyTodayCount,petAdoptTodayCount,loginTodayCount,totalUserCount);
        return PlatformResult.success(adoptionStatistic);
    }

}
