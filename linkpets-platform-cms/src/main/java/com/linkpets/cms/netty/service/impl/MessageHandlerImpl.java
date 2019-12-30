package com.linkpets.cms.netty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.adopt.model.ChatMessage;
import com.linkpets.cms.netty.Global;
import com.linkpets.cms.netty.UserChannel;
import com.linkpets.cms.netty.service.IMessageHandler;
import com.linkpets.core.dao.CmsAdoptFormidMapper;
import com.linkpets.core.model.CmsAdoptFormid;
import com.linkpets.util.DateUtils;
import com.linkpets.util.HttpUtil;
import com.linkpets.util.UUIDUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Service
public class MessageHandlerImpl implements IMessageHandler {

    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CmsAdoptFormidMapper cmsAdoptFormidMapper;

    @Value("${linkPet.lpWechat.templateMsg.chatUpt}")
    private String chatUptUrl;

    @Override
    public void middleSwitch(String userId, String textFrame) {

        try {
            rwLock.readLock().lock();
            boolean online = false;
            Set<Channel> keySet = Global.userInfos.keySet();
            for (Channel ch : keySet) {
                UserChannel userInfo = Global.userInfos.get(ch);
                if (!userInfo.getUserId().equals(userId)) {
                    continue;
                }
                online = true;
            }

            if (!online) {
                //获取今日消息记录
                String message=stringRedisTemplate.opsForList().leftPop("Chat-Receive:"+userId);
                log.info("最近发送的消息："+message);
                ChatMessage chatMessage=JSON.parseObject(message, ChatMessage.class);
                String sendTime=chatMessage.getSendTime();
                String formId=chatMessage.getFormId();
                CmsAdoptFormid formid=new CmsAdoptFormid();
                formid.setId(UUIDUtils.getId());
                formid.setUserId(userId);
                formid.setFormId(formId);
                Date createDate=new Date();
                formid.setExpireTime(org.apache.commons.lang.time.DateUtils.addDays(createDate,7));
                formid.setCreateTime(createDate);
                cmsAdoptFormidMapper.insertSelective(formid);
                if(DateUtils.diffNow(sendTime)){
                    //发送留言模板消息
                    try {
                        log.info(HttpUtil.doPost(chatUptUrl, JSON.toJSONString(chatMessage)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Override
    public void storeInRedis(String userId, String textFrame) {
        JSONObject frame= JSON.parseObject(textFrame);
        stringRedisTemplate.opsForList().leftPush("Chat-Receive:"+userId, textFrame);
        stringRedisTemplate.opsForList().leftPush("Chat-Send:{"+frame.getString("userId")+"}{"+userId+"}", textFrame);
    }


    public static void main(String[] args) {

    }
}
