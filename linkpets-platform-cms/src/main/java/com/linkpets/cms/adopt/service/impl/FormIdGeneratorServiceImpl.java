package com.linkpets.cms.adopt.service.impl;

import com.linkpets.cms.adopt.service.IFormIdGeneratorService;
import com.linkpets.core.dao.CmsAdoptFormidMapper;
import com.linkpets.core.model.CmsAdoptFormid;
import com.linkpets.util.UUIDUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class FormIdGeneratorServiceImpl implements IFormIdGeneratorService {

    @Resource
    private CmsAdoptFormidMapper cmsAdoptFormidMapper;

    @Override
    public CmsAdoptFormid getValidFormId(String userId) {
        return cmsAdoptFormidMapper.getValidFormId(userId);
    }

    @Override
    public void addFormId(String formId, String userId) {
        CmsAdoptFormid cmsAdoptFormid=new CmsAdoptFormid();
        cmsAdoptFormid.setId(UUIDUtils.getUUID());
        cmsAdoptFormid.setFormId(formId);
        cmsAdoptFormid.setUserId(userId);
        Date createDate=new Date();
        cmsAdoptFormid.setCreateTime(createDate);
        cmsAdoptFormid.setExpireTime(DateUtils.addDays(createDate,7));
        cmsAdoptFormidMapper.insertSelective(cmsAdoptFormid);
    }

    @Override
    public void inactiveFormId(String id) {
        CmsAdoptFormid formid=new CmsAdoptFormid();
        formid.setIsValid(0);
        formid.setId(id);
        cmsAdoptFormidMapper.updateByPrimaryKeySelective(formid);
    }
}
