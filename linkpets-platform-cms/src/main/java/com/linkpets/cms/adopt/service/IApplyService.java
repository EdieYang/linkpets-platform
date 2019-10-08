package com.linkpets.cms.adopt.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsAdoptApply;

import java.util.List;
import java.util.Map;

public interface IApplyService {

    /**
     * 创建申请领养信息
     *
     * @param apply
     */
    String crtApply(CmsAdoptApply apply);

    /**
     * 更新申请领养信息
     *
     * @param apply
     */
    void uptApply(CmsAdoptApply apply);

    /**
     * 获取申请领养信息
     *
     * @param applyId
     * @return
     */
    CmsAdoptApply getApply(String applyId);

    /**
     * 获取申请领养列表（分页）
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    JSONObject getApplyListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    /**
     * 获取公益组织申请领养列表（分页）
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    JSONObject getOrgApplyListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);


    /**
     * 获取当天领养申请数
     *
     * @param syncDate
     * @return
     */
    int getApplyCount(String syncDate);

    /**
     * 获取已超期无效领养申请列表
     *
     * @return
     */
    List<CmsAdoptApply> getExpiredAdoptApplyList();


}
