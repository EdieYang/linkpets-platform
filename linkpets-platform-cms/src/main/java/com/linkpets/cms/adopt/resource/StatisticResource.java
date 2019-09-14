package com.linkpets.cms.adopt.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.model.AdoptionStatistic;
import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.cms.adopt.service.IStatisticService;
import com.linkpets.cms.adopt.service.IUserService;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@Api(value = "领养平台统计接口",tags = "领养平台-统计接口")
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

    @GetMapping(value = "list")
    public PlatformResult getStatisticList() {
        String syncDate = DateUtils.getFormatDateStr(new Date(), "yyyy-MM-dd");
        int applyTodayCount = applyService.getApplyCount(syncDate);
        int petAdoptTodayCount = petService.getPetAdoptCount(syncDate);
        int loginTodayCount = userService.getLoginCount(syncDate);
        int totalUserCount = userService.getTotalUserCount();
        AdoptionStatistic adoptionStatistic = new AdoptionStatistic(applyTodayCount, petAdoptTodayCount, loginTodayCount, totalUserCount);
        return PlatformResult.success(adoptionStatistic);
    }
    
    /**
     * 
    * @Title: getOrgStatistic 
    * @Description: 获取某公义机构的统计数据
    * @param @param orgId
    * @param @return
    * @return PlatformResult
    * @author wando 
    * @throws
    * @date 2019年9月14日 下午3:26:09 
    * @version V1.0   
     */
    @GetMapping(value = "{orgId}/data")
    public PlatformResult getOrgStatistic(@PathVariable("orgId") String orgId) {
        return PlatformResult.success(statisticService.getDataByOrg(orgId));
    }
    
    
    
    

}
