package com.linkpets.cms.adopt.service;

public interface IFormIdGeneratorService {

    String getValidFormId(String userId);

    void addFormId(String formId,String userId);
}
