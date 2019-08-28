package com.linkpets.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

import java.util.Date;

@Data
public class CmsCouponBatchItem {

    private String couponItemId;

    private String batchNo;

    private String userId;

    private Integer status;

    private Date certifyTime;

    private String certifyChainId;

    private Integer version;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer isValid;

}