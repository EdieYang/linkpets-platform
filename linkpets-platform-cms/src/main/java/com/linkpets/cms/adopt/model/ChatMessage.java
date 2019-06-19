package com.linkpets.cms.adopt.model;

import lombok.Data;

@Data
public class ChatMessage {

    private String userId;

    private String targetUserId;

    private String data;

    private String type;

    private String key;

}
