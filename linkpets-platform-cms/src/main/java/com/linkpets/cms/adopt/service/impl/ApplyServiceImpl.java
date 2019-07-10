package com.linkpets.cms.adopt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.core.dao.CmsAdoptAgreementMapper;
import com.linkpets.core.dao.CmsAdoptApplyMapper;
import com.linkpets.core.model.CmsAdoptAgreement;
import com.linkpets.core.model.CmsAdoptApply;
import com.linkpets.util.UUIDUtils;

@Service
public class ApplyServiceImpl implements IApplyService {

    private static final String PASS_FIRST_STATUS = "1";

    @Resource
    CmsAdoptApplyMapper cmsAdoptApplyMapper;
    


    @Override
    public String crtApply(CmsAdoptApply apply) {
        CmsAdoptApply existApply=cmsAdoptApplyMapper.getApply(apply.getApplyBy(),apply.getPetId());
        if(existApply==null){
            String applyId = UUIDUtils.getUUID();
            apply.setApplyId(applyId);
            apply.setApplyTime(new Date());
            cmsAdoptApplyMapper.insertSelective(apply);
            return applyId;
        }else{
            return "";
        }

    }

    @Override
    public void uptApply(CmsAdoptApply apply) {
        apply.setCheckTime(new Date());
        cmsAdoptApplyMapper.updateByPrimaryKeySelective(apply);
    }

    @Override
    public CmsAdoptApply getApply(String applyId) {
        return cmsAdoptApplyMapper.selectByPrimaryKey(applyId);
    }

    @Override
    public JSONObject getApplyListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        JSONObject result = new JSONObject();
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Map<String, Object>> applyList = cmsAdoptApplyMapper.getList(param);
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(applyList);
        result.put("page", page.getPageNum());
        result.put("total", page.getTotal());
        result.put("rows", applyList);
        return result;
    }

    @Override
    public int getApplyCount(String syncDate) {
        return cmsAdoptApplyMapper.getApplyCount(syncDate);
    }

    @Override
    public List<CmsAdoptApply> getExpiredAdoptApplyList() {
        return cmsAdoptApplyMapper.getExpiredAdoptApplyList();
    }


}
