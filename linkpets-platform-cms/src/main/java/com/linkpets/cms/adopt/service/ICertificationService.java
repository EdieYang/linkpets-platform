package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptCertification;

public interface ICertificationService {

    /**
     * 上传实名认证信息
     *
     * @param certification
     */
    void uploadCertification(CmsAdoptCertification certification);

    /**
     * 更新实名认证信息
     *
     * @param certification
     */
    void modifyCertification(CmsAdoptCertification certification);

    /**
     * 获取用户实名认证信息详情
     *
     * @param userId
     * @return
     */
    CmsAdoptCertification getUserCertification(String userId);

    /**
     * 根据状态分页获取实名认证用户信息列表
     *
     * @param pageNum
     * @param pageSize
     * @param status
     * @return
     */
    PageInfo<CmsAdoptCertification> getUserCertificationList(int pageNum, int pageSize, String status);
}
