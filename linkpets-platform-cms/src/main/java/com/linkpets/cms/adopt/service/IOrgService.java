package com.linkpets.cms.adopt.service;

import com.linkpets.core.model.CmsAdoptOrg;

import java.util.List;

public interface IOrgService {

    List<CmsAdoptOrg> getAdoptOrgList();

    CmsAdoptOrg getAdoptOrgDetail(String orgId);


}
