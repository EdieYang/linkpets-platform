package com.linkpets.cms.adopt.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsAdoptAgreement;
import com.linkpets.core.model.CmsAdoptApply;

public interface IApplyService {
	
	/**
	 * 创建申请领养信息
	 * @param apply
	 */
	public String crtApply(CmsAdoptApply apply);
	
	/**
	 * 更新申请领养信息
	 * @param apply
	 */
	public void uptApply(CmsAdoptApply apply);
	
	/**
	 * 获取申请领养信息
	 * @param applyId
	 * @return
	 */
	public CmsAdoptApply getApply(String applyId);
	
	/**
	 * 获取申请领养列表（分页）
	 * @param param
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
	public JSONObject getApplyListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);
	

}
