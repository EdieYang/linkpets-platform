package com.linkpets.cms.adopt.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptPet;
import com.linkpets.core.model.CmsAdoptPetCollect;

import java.util.List;
import java.util.Map;

public interface IPetService {

    /**
     * 创建领养信息
     *
     * @param pet
     */
    String crtAdopt(CmsAdoptPet pet);

    /**
     * 更新领养信息
     *
     * @param pet
     */
    void uptAdopt(CmsAdoptPet pet);

    /**
     * 获取领养信息
     *
     * @param petId
     * @return
     */
    CmsAdoptPet getAdopt(String petId);

    /**
     * 获取领养列表（分页）
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    PageInfo<CmsAdoptPet> getAdoptListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    /**
     * 通过userId获取petId
     *
     * @param userId
     * @return
     */
    List<String> getAdoptPetIdsByUserId(String userId);

    JSONObject getCollectPetList(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    PageInfo<CmsAdoptPet> getFollowedPetList(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    void crtCollect(CmsAdoptPetCollect record);

    void delCollect(String userId, String petId);

    boolean getIfCollectedPet(String userId, String petId);

    int getPetAdoptCount(String syncDate);
}
