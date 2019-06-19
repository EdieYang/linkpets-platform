package com.linkpets.cms.adopt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IAgreementService;
import com.linkpets.core.dao.CmsAdoptAgreementMapper;
import com.linkpets.core.model.CmsAdoptAgreement;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/5/26
 */
@Service
public class AgreementServiceImpl implements IAgreementService {

    @Resource
    CmsAdoptAgreementMapper cmsAdoptAgreementMapper;


    @Override
    public String crtAgreement(CmsAdoptAgreement agreement) {
        String agreementId = UUIDUtils.getUUID();
        agreement.setAgreementId(agreementId);
        agreement.setCreateDate(new Date());
        cmsAdoptAgreementMapper.insertSelective(agreement);
        return agreementId;
    }

    @Override
    public void uptAgreement(CmsAdoptAgreement agreement) {
        cmsAdoptAgreementMapper.updateByPrimaryKeySelective(agreement);
    }

    @Override
    public CmsAdoptAgreement getAgreement(String agreementId) {
        return cmsAdoptAgreementMapper.selectByPrimaryKey(agreementId);
    }

    @Override
    public CmsAdoptAgreement getAgreementByApplyId(String applyId) {
        return cmsAdoptAgreementMapper.getAgreementByApplyId(applyId);
    }

    @Override
    public JSONObject getAgreementListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        JSONObject result = new JSONObject();
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Map<String, Object>> applyList = cmsAdoptAgreementMapper.getList(param);
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(applyList);
        result.put("page", page.getPageNum());
        result.put("total", page.getTotal());
        result.put("rows", applyList);
        return result;
    }
}


