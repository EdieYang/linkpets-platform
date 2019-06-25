package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.model.ChatMessage;
import com.linkpets.core.model.CmsAdoptMsg;

import java.util.List;
import java.util.Map;

public interface ICmsAdoptMsgService {

    CmsAdoptMsg getSystemLatestMsg(String userId);

    CmsAdoptMsg getApplyLatestMsg(String userId);

    CmsAdoptMsg getAgreementLatestMsg(String userId);

    void crtMessage(CmsAdoptMsg msg);

    PageInfo<CmsAdoptMsg> getDetailListMsg(Map<String,Object> param, int pageNum, int pageSize);


    List<ChatMessage> getChatReceiverMessageList(String userId);

    List<ChatMessage> getChatSenderMessageList(String userId,String targetUserId);

    void uptDetailListMsg(String userId,String type);

    List<CmsAdoptMsg> getUnreadMessage(String userId);

}
