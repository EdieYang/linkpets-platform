package com.linkpets.cms.aprilfool.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.aprilfool.model.CmsActivityCustomRegistry;
import com.linkpets.core.model.CmsActivityRegistryInfo;

import java.util.List;

public interface IActivityRegisterService {

    String registerActivity(String activityId, String userId, int registryType);

    CmsActivityCustomRegistry getRegistryInfo(String registryId);

    List<CmsActivityCustomRegistry> getRegistryInfoByUserId(String activityId, String userId);

    List<CmsActivityCustomRegistry> getRegistryInfoList(String activityId);

    JSONObject registerQuestionInfo(String activityId, String userId, int registryType,
                                    String registerName, String registerPhone, String registerWx,
                                    String contentFirst, String contentSecond, String contentThird,
                                    String contentForth);

    CmsActivityRegistryInfo getRegisterQuestionInfo(String activityId, String userId);

}
