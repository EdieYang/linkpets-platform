package com.linkpets.cms.adopt.schedule;


import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.core.model.CmsAdoptApply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
@EnableAsync
@EnableScheduling
public class ApplyScheduleManager {

    @Resource
    private IApplyService applyService;


    @Async
    @Scheduled(cron = "0 0 2 * * ?")
    public void applyHandlerJob() {
        log.info("处理过期申请领养单JOB>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<CmsAdoptApply> cmsAdoptApplyList = applyService.getExpiredAdoptApplyList();
        cmsAdoptApplyList.stream().forEach(item -> {
            if (!item.getApplyStatus().equals("4") || !item.getApplyStatus().equals("5")) {
                item.setApplyStatus("5");
                item.setApplyResp("此申请超过系统有效时间，已自动取消");
                applyService.uptApply(item);
            }
        });
    }

}
