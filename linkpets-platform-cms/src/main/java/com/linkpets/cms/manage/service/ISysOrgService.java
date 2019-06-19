package com.linkpets.cms.manage.service;


import com.linkpets.core.model.SysChain;
import com.linkpets.core.model.SysOrg;

import java.util.List;

public interface ISysOrgService {

    SysOrg getSysOrg(String code);

    List<SysChain> getSysChains(String orgId, String chainName);

    SysChain getSysChainByChainId(String chainId);
}
