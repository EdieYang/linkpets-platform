package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CmsAdoptMsg implements Serializable {

    private Integer msgId;

    private String msgTitle;

    private String msgContent;

    private Integer msgType;

    private String sender;

    private String receiver;

    private Integer isRead;

    private String petId;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String dateBefore;

    private int unreadNumber;

    private String formId;

    private String fromType;

}