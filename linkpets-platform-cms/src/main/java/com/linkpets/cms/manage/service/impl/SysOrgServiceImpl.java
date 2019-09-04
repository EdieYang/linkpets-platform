package com.linkpets.cms.manage.service.impl;

import com.linkpets.cms.manage.service.ISysOrgService;
import com.linkpets.core.dao.SysChainMapper;
import com.linkpets.core.dao.SysOrgMapper;
import com.linkpets.core.model.SysChain;
import com.linkpets.core.model.SysOrg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysOrgServiceImpl implements ISysOrgService {

    @Resource
    private SysOrgMapper orgMapper;

    @Resource
    private SysChainMapper chainMapper;

    @Override
    public SysOrg getSysOrg(String code) {
        return orgMapper.getSysOrg(code);
    }

    @Override
    public List<SysChain> getSysChains(String orgId, String chainName) {
        return chainMapper.getSysChains(orgId, chainName);
    }

    @Override
    public SysChain getSysChainByChainId(String chainId) {
        return chainMapper.selectByPrimaryKey(chainId);
    }
}
