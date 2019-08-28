package com.linkpets.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class CmsAdoptAttention {

    private Integer pkId;

    private String userId;

    private String attentBy;

    private Date attentTime;

    private String isValid;

}