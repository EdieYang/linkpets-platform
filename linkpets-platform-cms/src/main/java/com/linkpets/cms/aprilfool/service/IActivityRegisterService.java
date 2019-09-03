package com.linkpets.cms.aprilfool.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsActivityRegistry;
import com.linkpets.core.model.CmsActivityRegistryInfo;

import java.util.List;

public interface IActivityRegisterService {

    String registerActivity(String activityId, String userId, int registryType);

    CmsActivityRegistry getRegistryInfo(String registryId);

    List<CmsActivityRegistry> getRegistryInfoByUserId(String activityId, String userId);

    List<CmsActivityRegistry> getRegistryInfoList(String activityId);

    JSONObject registerQuestionInfo(String activityId, String userId, int registryType,
                                    String registerName, String registerPhone, String registerWx,
                                    String contentFirst, String contentSecond, String contentThird,
                                    String contentForth);

    CmsActivityRegistryInfo getRegisterQuestionInfo(String activityId, String userId);

}
