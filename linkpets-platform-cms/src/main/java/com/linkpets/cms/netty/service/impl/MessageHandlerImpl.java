package com.linkpets.cms.netty.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.netty.Global;
import com.linkpets.cms.netty.UserChannel;
import com.linkpets.cms.netty.service.IMessageHandler;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Service
public class MessageHandlerImpl implements IMessageHandler {

    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
                ch.writeAndFlush(new TextWebSocketFrame(textFrame));
            }

            if (!online) {
                log.info("将消息推送到redis中保存：" + textFrame);
                //将消息推送到redis中保存
                stringRedisTemplate.opsForList().leftPush(userId, textFrame);
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
