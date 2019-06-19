package com.linkpets.cms.manage.dao;

import com.linkpets.core.model.SysChain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysChainCustomMapper {

    List<SysChain> getSysChains(@Param("orgId") String orgId, @Param("chainName") String chainName);

}
