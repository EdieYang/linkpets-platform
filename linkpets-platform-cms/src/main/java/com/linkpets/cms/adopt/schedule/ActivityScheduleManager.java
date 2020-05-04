package com.linkpets.cms.adopt.schedule;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.adopt.aop.MessageTemplate;
import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.cms.adopt.service.ICmsAdoptMsgService;
import com.linkpets.cms.group.service.IGroupActivityRegisterService;
import com.linkpets.cms.group.service.IGroupActivityService;
import com.linkpets.core.model.CmsAdoptApply;
import com.linkpets.core.model.CmsAdoptMsg;
import com.linkpets.core.model.CmsGroupActivity;
import com.linkpets.core.model.CmsGroupActivityRegister;
import com.linkpets.util.DateUtils;
import com.linkpets.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@EnableAsync
@EnableScheduling
public class ActivityScheduleManager {

    @Resource
    private IGroupActivityService activityService;

    @Resource
    private IGroupActivityRegisterService activityRegisterService;

    @Resource
    private ICmsAdoptMsgService msgService;

    @Value("${linkPet.lpWechat.templateMsg.activityNotification}")
    private String activityNotificationUrl;

    @Async
    @Scheduled(cron = "0 50 16 * * ?")
    public void remindHandlerJob() {
        log.info("处理活动开始前提醒JOB>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String rangeDate = DateUtils.rollNOfDate(DateUtils.getCurrentDay(),1,1,"yyyy-MM-dd");
        List<CmsGroupActivity> activityList = activityService.getGroupActivityListWithTimeRange(rangeDate + " 00:00:00", rangeDate + " 23:59:59");
        activityList.forEach(activity -> {
            List<CmsGroupActivityRegister> registerList = activityRegisterService.getGroupActivityRegisterListByActivityId(activity.getId());
            registerList.forEach(register -> {
                CmsAdoptMsg msg = new CmsAdoptMsg();
                msg.setMsgTitle(MessageTemplate.ACTIVITY_START_REMIND);
                JSONObject msgContent = new JSONObject();
                msgContent.put("activityId", activity.getId());
                msgContent.put("activityTitle", activity.getActivityTitle());
                msgContent.put("activityAddress", activity.getActivityArea() + " " + activity.getActivityAddress());
                msgContent.put("involvementTime", register.getInvolvementTime().substring(0, 19));
                msgContent.put("type", "REMIND");
                msgContent.put("status", "0");
                msg.setMsgContent(msgContent.toJSONString());
                msg.setMsgType(3);
                msg.setPetId("");
                msg.setSender("SYS");
                msg.setReceiver(register.getUserId());
                msg.setCreateTime(new Date());
                msgService.crtMessage(msg);
                //发送申请创建模板消息
                this.sendTemplateMsg(activityNotificationUrl, msg);
            });
        });
    }

    private void sendTemplateMsg(String sendUrl, CmsAdoptMsg msg) {
        try {
            log.info(HttpUtil.doPost(sendUrl, JSON.toJSONString(msg)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
