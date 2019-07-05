package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptCertification;

public interface ICertificationService {

    void uploadCertification(CmsAdoptCertification certification);

    void modifyCertification(CmsAdoptCertification certification);

    CmsAdoptCertification getUserCertification(String userId);

    PageInfo<CmsAdoptCertification> getUserCertificationList(int pageNum,int pageSize,String status);
}
