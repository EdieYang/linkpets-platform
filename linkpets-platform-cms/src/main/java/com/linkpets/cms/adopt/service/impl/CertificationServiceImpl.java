package com.linkpets.cms.adopt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.ICertificationService;
import com.linkpets.core.dao.CmsAdoptCertificationMapper;
import com.linkpets.core.dao.CmsUserMapper;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.core.model.CmsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CertificationServiceImpl implements ICertificationService {

    private static final String PASSED_STATUS = "1";


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
        String status = certification.getStatus();
        CmsAdoptCertification cmsAdoptCertification = cmsAdoptCertificationMapper.selectByPrimaryKey(certification.getId());
        String userId = cmsAdoptCertification.getUserId();
        cmsAdoptCertificationMapper.updateByPrimaryKeySelective(certification);
        if (PASSED_STATUS.equals(status)) {
            CmsUser cmsUser = new CmsUser();
            cmsUser.setUserId(userId);
            cmsUser.setAuthenticated(1);
            cmsUserMapper.updateByPrimaryKeySelective(cmsUser);
        }
    }

    @Override
    public CmsAdoptCertification getUserCertification(String userId) {
        return cmsAdoptCertificationMapper.getUserCertification(userId);
    }

    @Override
    public PageInfo<CmsAdoptCertification> getUserCertificationList(int pageNum, int pageSize, String status) {
        PageHelper.startPage(pageNum, pageSize);
        List<CmsAdoptCertification> cmsAdoptCertificationList = cmsAdoptCertificationMapper.getUserCertificationList(status);
        PageInfo<CmsAdoptCertification> pageInfo = new PageInfo<>(cmsAdoptCertificationList);
        return pageInfo;
    }

    @Override
    public Integer isAuthenticated(String userId) {
        return cmsAdoptCertificationMapper.isAuthenticated(userId);
    }


}
