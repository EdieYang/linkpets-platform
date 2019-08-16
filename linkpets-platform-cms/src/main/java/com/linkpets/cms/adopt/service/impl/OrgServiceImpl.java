package com.linkpets.cms.adopt.service.impl;

import com.linkpets.cms.adopt.service.IOrgService;
import com.linkpets.core.dao.CmsAdoptOrgMapper;
import com.linkpets.core.model.CmsAdoptOrg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class OrgServiceImpl implements IOrgService {

    @Resource
    private CmsAdoptOrgMapper orgMapper;


    @Override
    public List<CmsAdoptOrg> getAdoptOrgList() {
        return orgMapper.getAdoptOrgList();
    }

    @Override
    public CmsAdoptOrg getAdoptOrgDetail(String orgId) {
        return orgMapper.selectByPrimaryKey(orgId);
    }
}
