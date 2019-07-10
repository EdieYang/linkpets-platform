package com.linkpets.cms.adopt.service;

import com.linkpets.core.model.CmsAdoptFormid;

public interface IFormIdGeneratorService {

    CmsAdoptFormid getValidFormId(String userId);

    void addFormId(String formId,String userId);

    void inactiveFormId(String id);
}
