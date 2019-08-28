package com.linkpets.cms.adopt.service;

import com.linkpets.core.model.CmsAdoptFormid;

/**
 * FormId生成器Service
 */
public interface IFormIdGeneratorService {

    /**
     * 获取用户有效formId
     *
     * @param userId
     * @return
     */
    CmsAdoptFormid getValidFormId(String userId);

    /**
     * 增加用户formId
     *
     * @param formId
     * @param userId
     */
    void addFormId(String formId, String userId);

    /**
     * 设置formId失效
     *
     * @param id
     */
    void inactiveFormId(String id);
}
