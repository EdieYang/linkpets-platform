package com.linkpets.cms.adopt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.core.dao.CmsAdoptPetCollectMapper;
import com.linkpets.core.dao.CmsAdoptPetMapper;
import com.linkpets.core.dao.CmsAdoptPetMediaMapper;
import com.linkpets.core.model.CmsAdoptPet;
import com.linkpets.core.model.CmsAdoptPetCollect;
import com.linkpets.core.model.CmsAdoptPetMedia;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PetServiceImpl implements IPetService {

    @Resource
    CmsAdoptPetMapper cmsAdoptPetMapper;

    @Resource
    CmsAdoptPetMediaMapper cmsAdoptPetMediaMapper;

    @Resource
    CmsAdoptPetCollectMapper cmsAdoptPetCollectMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String crtAdopt(CmsAdoptPet pet) {
        String petId = UUIDUtils.getUUID();
        pet.setPetId(petId);
        pet.setCreateDate(new Date());
        cmsAdoptPetMapper.insertSelective(pet);

        List<CmsAdoptPetMedia> mediaList = pet.getMediaList();
        if (mediaList != null) {
            for (int i = 0; i < mediaList.size(); i++) {
                CmsAdoptPetMedia media = mediaList.get(i);
                media.setPetId(petId);
                // add
                cmsAdoptPetMediaMapper.insertSelective(media);
            }
        }
        return petId;
    }

    @Override
    public void uptAdopt(CmsAdoptPet pet) {
        String petId = pet.getPetId();
        cmsAdoptPetMapper.updateByPrimaryKeySelective(pet);
        if (pet.getMediaList() != null) {
            List<CmsAdoptPetMedia> mediaList = pet.getMediaList();
            for (int i = 0; i < mediaList.size(); i++) {
                CmsAdoptPetMedia media = mediaList.get(i);
                media.setPetId(petId);
                if (null != media.getMediaId()) {
                    // upt
                    cmsAdoptPetMediaMapper.updateByPrimaryKeySelective(media);
                } else {
                    // add
                    cmsAdoptPetMediaMapper.insertSelective(media);
                }

            }
        }

    }

    @Override
    public CmsAdoptPet getAdopt(String petId) {
        CmsAdoptPet cmsAdoptPet = cmsAdoptPetMapper.selectByPrimaryKey(petId);
        List<CmsAdoptPetMedia> mediaList = cmsAdoptPetMediaMapper.selectByPetId(petId);
        cmsAdoptPet.setMediaList(mediaList);
        return cmsAdoptPet;
    }

    @Override
    public PageInfo<CmsAdoptPet> getAdoptListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<CmsAdoptPet> list = cmsAdoptPetMapper.getList(param);
        PageInfo<CmsAdoptPet> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<String> getAdoptPetIdsByUserId(String userId) {
        return cmsAdoptPetMapper.getAdoptPetIdsByUserId(userId);
    }

    @Override
    public JSONObject getCollectPetList(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        JSONObject result = new JSONObject();
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<CmsAdoptPet> list = cmsAdoptPetMapper.getCollectPetList(param);
        PageInfo<CmsAdoptPet> page = new PageInfo<>(list);
        result.put("page", page.getPageNum());
        result.put("total", page.getTotal());
        result.put("rows", list);
        return result;
    }

    @Override
    public PageInfo<CmsAdoptPet> getFollowedPetList(Map<String, Object> param, int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<CmsAdoptPet> list = cmsAdoptPetMapper.getFollowedPetList(param);
        PageInfo<CmsAdoptPet> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public void crtCollect(CmsAdoptPetCollect record) {
        record.setCollectTime(new Date());
        cmsAdoptPetCollectMapper.insertSelective(record);
    }

    @Override
    public void delCollect(String userId, String petId) {
        cmsAdoptPetCollectMapper.delCollect(userId, petId);
    }

    @Override
    public boolean getIfCollectedPet(String userId, String petId) {
        List<CmsAdoptPetCollect> cmsAdoptPetCollect = cmsAdoptPetCollectMapper.getCollectionRel(userId, petId);
        if (cmsAdoptPetCollect != null && cmsAdoptPetCollect.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getPetAdoptCount(String syncDate) {
        return cmsAdoptPetMapper.getPetAdoptCount(syncDate);
    }

}
