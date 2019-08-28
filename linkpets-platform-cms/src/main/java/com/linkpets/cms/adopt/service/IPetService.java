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

    /**
     * 分页获取收藏宠物列表
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    JSONObject getCollectPetList(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    /**
     * 分页获取关注宠物列表
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    PageInfo<CmsAdoptPet> getFollowedPetList(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    /**
     * 收藏宠物
     *
     * @param record
     */
    void crtCollect(CmsAdoptPetCollect record);

    /**
     * 取消收藏
     *
     * @param userId
     * @param petId
     */
    void delCollect(String userId, String petId);

    /**
     * 宠物是否收藏
     *
     * @param userId
     * @param petId
     * @return
     */
    boolean getIfCollectedPet(String userId, String petId);

    /**
     * 获取当天宠物创建数
     *
     * @param syncDate
     * @return
     */
    int getPetAdoptCount(String syncDate);
}
