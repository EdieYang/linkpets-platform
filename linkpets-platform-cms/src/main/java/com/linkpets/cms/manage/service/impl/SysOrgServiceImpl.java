package com.linkpets.cms.manage.service.impl;

import com.linkpets.cms.manage.dao.SysChainCustomMapper;
import com.linkpets.cms.manage.dao.SysOrgCustomMapper;
import com.linkpets.cms.manage.service.ISysOrgService;
import com.linkpets.core.dao.SysChainMapper;
import com.linkpets.core.model.SysChain;
import com.linkpets.core.model.SysOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOrgServiceImpl implements ISysOrgService {

    @Autowired
    private SysOrgCustomMapper orgCustomMapper;

    @Autowired
    private SysChainCustomMapper chainCustomMapper;

    @Autowired
    private SysChainMapper chainMapper;

    @Override
    public SysOrg getSysOrg(String code) {
        return orgCustomMapper.getSysOrg(code);
    }

    @Override
    public List<SysChain> getSysChains(String orgId, String chainName) {
        return chainCustomMapper.getSysChains(orgId,chainName);
    }

    @Override
    public SysChain getSysChainByChainId(String chainId) {
        return chainMapper.selectByPrimaryKey(chainId);
    }
}
