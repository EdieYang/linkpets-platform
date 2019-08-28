package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.model.ChatMessage;
import com.linkpets.core.model.CmsAdoptMsg;

import java.util.List;
import java.util.Map;

public interface ICmsAdoptMsgService {

    /**
     * 获取系统最新推送消息
     *
     * @param userId
     * @return
     */
    CmsAdoptMsg getSystemLatestMsg(String userId);

    /**
     * 获取领养申请最新推送消息
     *
     * @param userId
     * @return
     */
    CmsAdoptMsg getApplyLatestMsg(String userId);

    /**
     * 获取领养协议最新推送消息
     *
     * @param userId
     * @return
     */
    CmsAdoptMsg getAgreementLatestMsg(String userId);

    /**
     * 创建消息
     *
     * @param msg
     */
    void crtMessage(CmsAdoptMsg msg);

    /**
     * 分页获取消息列表
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CmsAdoptMsg> getDetailListMsg(Map<String, Object> param, int pageNum, int pageSize);

    /**
     * 获取接收私聊消息列表
     *
     * @param userId
     * @return
     */
    List<ChatMessage> getChatReceiverMessageList(String userId);

    /**
     * 获取发送私聊消息列表
     *
     * @param userId
     * @param targetUserId
     * @return
     */
    List<ChatMessage> getChatSenderMessageList(String userId, String targetUserId);

    /**
     * 设置消息为已读状态
     *
     * @param userId
     * @param type
     */
    void uptAdoptMsg(String userId, String type);

    /**
     * 获取未读消息列表
     *
     * @param userId
     * @return
     */
    List<CmsAdoptMsg> getUnreadMessage(String userId);

}
