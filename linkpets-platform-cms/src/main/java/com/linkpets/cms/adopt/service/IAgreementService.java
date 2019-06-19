package com.linkpets.cms.adopt.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsAdoptAgreement;

import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/5/26
 */
public interface IAgreementService {

    /**
     * 创建合同
     * @param agreement
     * @return
     */
     String crtAgreement(CmsAdoptAgreement agreement);

    /**
     * 更新合同内容
     * @param agreement
     */
     void uptAgreement(CmsAdoptAgreement agreement);

    /**
     * 获取合同详情
     * @param agreementId
     * @return
     */
     CmsAdoptAgreement getAgreement(String agreementId);

    /**
     * 根据applyId获取合同详情
     * @param applyId
     * @return
     */
    CmsAdoptAgreement getAgreementByApplyId(String applyId);

    /**
     * 获取合同列表
     * @param param
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
     JSONObject getAgreementListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

}
