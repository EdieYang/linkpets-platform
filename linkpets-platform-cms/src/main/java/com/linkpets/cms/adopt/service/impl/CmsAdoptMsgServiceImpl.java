package com.linkpets.cms.adopt.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.model.ChatMessage;
import com.linkpets.cms.adopt.service.ICmsAdoptMsgService;
import com.linkpets.core.dao.CmsAdoptMsgMapper;
import com.linkpets.core.model.CmsAdoptMsg;
import com.linkpets.util.DateUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CmsAdoptMsgServiceImpl implements ICmsAdoptMsgService {

    @Resource
    private CmsAdoptMsgMapper msgMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public CmsAdoptMsg getSystemLatestMsg(String userId) {
        CmsAdoptMsg msg = msgMapper.getSystemLatestMsg(userId);
        if (msg != null) {
            msg.setDateBefore(DateUtils.getDateBefore(msg.getCreateTime()));
        }
        return msg;
    }

    @Override
    public CmsAdoptMsg getApplyLatestMsg(String userId) {
        CmsAdoptMsg msg = msgMapper.getApplyLatestMsg(userId);
        if (msg != null) {
            msg.setDateBefore(DateUtils.getDateBefore(msg.getCreateTime()));
        }
        return msg;
    }

    @Override
    public CmsAdoptMsg getAgreementLatestMsg(String userId) {
        CmsAdoptMsg msg = msgMapper.getAgreementLatestMsg(userId);
        if (msg != null) {
            msg.setDateBefore(DateUtils.getDateBefore(msg.getCreateTime()));
        }
        return msg;
    }

    @Override
    public void crtMessage(CmsAdoptMsg msg) {
        msgMapper.insertSelective(msg);
    }

    @Override
    public PageInfo<CmsAdoptMsg> getDetailListMsg(Map<String, Object> param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptMsg> msgList = msgMapper.getDetailMsgList(param);
        PageInfo<CmsAdoptMsg> page = new PageInfo<>(msgList);
        return page;
    }

    @Override
    public List<ChatMessage> getChatReceiverMessageList(String userId) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        List<String> redisCacheReceiverList = stringRedisTemplate.opsForList().range("Chat-Receive:" + userId, 0, -1);
        redisCacheReceiverList.stream().forEach(item -> {
            chatMessages.add(JSON.parseObject(item, ChatMessage.class));
        });

        return chatMessages;
    }

    @Override
    public List<ChatMessage> getChatSenderMessageList(String userId, String targetUserId) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        List<String> redisCacheSenderList = stringRedisTemplate.opsForList().range("Chat-Send:{" + userId + "}{" + targetUserId + "}", 0, -1);

        redisCacheSenderList.stream().forEach(item -> {
            chatMessages.add(JSON.parseObject(item, ChatMessage.class));
        });
        return chatMessages;
    }

    @Override
    public void uptAdoptMsg(String userId, String type) {
        msgMapper.uptAdoptMsg(userId, Integer.parseInt(type));
    }

    @Override
    public List<CmsAdoptMsg> getUnreadMessage(String userId) {
        return msgMapper.getUnreadMessage(userId);
    }
}
