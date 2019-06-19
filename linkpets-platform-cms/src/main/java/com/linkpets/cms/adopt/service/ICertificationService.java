package com.linkpets.cms.adopt.service;

import com.linkpets.core.model.CmsAdoptCertification;

public interface ICertificationService {

    void uploadCertification(CmsAdoptCertification certification);

    void modifyCertification(CmsAdoptCertification certification);

    CmsAdoptCertification getUserCertification(String userId);
}
