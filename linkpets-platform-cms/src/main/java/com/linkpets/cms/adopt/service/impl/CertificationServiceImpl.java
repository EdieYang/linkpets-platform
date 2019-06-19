package com.linkpets.cms.adopt.service.impl;

import com.linkpets.cms.adopt.service.ICertificationService;
import com.linkpets.core.dao.CmsAdoptCertificationMapper;
import com.linkpets.core.dao.CmsUserMapper;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.core.model.CmsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CertificationServiceImpl implements ICertificationService {

    private static final String PASSED_STATUS="1";


    @Resource
    private CmsAdoptCertificationMapper cmsAdoptCertificationMapper;

    @Resource
    private CmsUserMapper cmsUserMapper;

    @Override
    public void uploadCertification(CmsAdoptCertification certification) {
        cmsAdoptCertificationMapper.insertSelective(certification);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyCertification(CmsAdoptCertification certification) {
        String status=certification.getStatus();
        String userId=certification.getUserId();
        cmsAdoptCertificationMapper.updateByPrimaryKeySelective(certification);
        if(PASSED_STATUS.equals(status)){
            CmsUser cmsUser=new CmsUser();
            cmsUser.setUserId(userId);
            cmsUser.setAuthenticated(1);
            cmsUserMapper.updateByPrimaryKeySelective(cmsUser);
        }
    }

    @Override
    public CmsAdoptCertification getUserCertification(String userId) {
        return cmsAdoptCertificationMapper.getUserCertification(userId);
    }


}
