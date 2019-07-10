package com.linkpets.cms.adopt.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsAdoptAgreement;
import com.linkpets.core.model.CmsAdoptApply;

public interface IApplyService {
	
	/**
	 * 创建申请领养信息
	 * @param apply
	 */
	String crtApply(CmsAdoptApply apply);
	
	/**
	 * 更新申请领养信息
	 * @param apply
	 */
	void uptApply(CmsAdoptApply apply);
	
	/**
	 * 获取申请领养信息
	 * @param applyId
	 * @return
	 */
	CmsAdoptApply getApply(String applyId);
	
	/**
	 * 获取申请领养列表（分页）
	 * @param param
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
	JSONObject getApplyListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

	int getApplyCount(String syncDate);

	List<CmsAdoptApply> getExpiredAdoptApplyList();
	

}
