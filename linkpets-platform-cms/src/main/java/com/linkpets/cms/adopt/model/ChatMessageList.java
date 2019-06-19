package com.linkpets.cms.adopt.model;

import lombok.Data;

import java.util.List;

@Data
public class ChatMessageList {

    private String userId;

    private String nickName;

    private String portrait;

    private List<ChatMessage> messageList;

}
