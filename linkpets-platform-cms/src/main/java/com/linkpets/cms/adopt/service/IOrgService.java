package com.linkpets.cms.adopt.service;

import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.*;

import java.util.List;
import java.util.Map;

public interface IOrgService {

    /**
     * 获取公益机构列表
     *
     * @return
     */
    List<CmsAdoptOrg> getAdoptOrgList();

    /**
     * 获取公益机构详情
     *
     * @param orgId
     * @return
     */
    CmsAdoptOrg getAdoptOrgDetail(String orgId);

    /**
     * 新增公益机构
     *
     * @param org
     * @return
     */
    String insertOrg(CmsAdoptOrg org);

    /**
     * 更新公益机构
     *
     * @param org
     */
    void uptOrg(CmsAdoptOrg org);

    /**
     * 获取公益机构粉丝列表
     *
     * @param orgId
     * @return
     */
    List<CmsAdoptOrgFollow> getAdoptOrgFollows(String orgId);

    /**
     * 获取公益机构粉丝人数
     *
     * @param orgId
     * @return
     */
    int getAdoptOrgFollowNums(String orgId);

    /**
     * 添加关注
     *
     * @param orgId
     * @param userId
     */
    void crtAdoptOrgFollow(String orgId, String userId);

    /**
     * 更新关注
     *
     * @param orgId
     * @param userId
     */
    void uptAdoptOrgFollow(String orgId, String userId);

    /**
     * 获取公益机构统计数据
     *
     * @param orgId
     * @param userId
     * @return
     */
    CmsAdoptOrgStatistic getAdoptOrgStatistic(String orgId, String userId);

    /**
     * 分页获取公益机构领养宠物列表
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    PageInfo<CmsAdoptPet> getAdoptListForPage(Map<String, Object> param, int pageNum, int pageSize, String orderBy);

    /**
     * 分页获取公益机构相册列表
     *
     * @param pageNum
     * @param pageSize
     * @param orgId
     * @return
     */
    PageInfo<CmsAdoptOrgGallery> getAdoptGalleryList(int pageNum, int pageSize, String orgId);

    /**
     * 分页获取公益机构活动列表
     *
     * @param pageNum
     * @param pageSize
     * @param orgId
     * @return
     */
    PageInfo<CmsAdoptOrgActivity> getAdoptActivityList(int pageNum, int pageSize, String orgId);

    /**
     * 获取宠物绑定公益机构信息
     *
     * @param petId
     * @return
     */
    CmsAdoptPetOrgRel getAdoptPetOrgInfoByPetId(String petId);

    /**
     * 绑定公益机构关系
     *
     * @param orgId
     * @param petId
     */
    void crtAdoptOrgPet(String orgId, String petId);

    /**
     * 更新公益机构宠物关系
     *
     * @param id
     */
    void uptAdoptOrgPet(String id);

    /**
     * 新增公益机构活动
     *
     * @param activity
     * @return
     */
    String insertActivity(CmsAdoptOrgActivity activity);

    /**
     * 更新公益机构活动
     *
     * @param activity
     */
    void uptActivity(CmsAdoptOrgActivity activity);

    /**
     * 新增公益机构相册
     *
     * @param gallery
     */
    String insertGallery(CmsAdoptOrgGallery gallery);

    /**
     * 删除公益机构相册
     *
     * @param gallery
     */
    void delGallery(CmsAdoptOrgGallery gallery);

    PageInfo<Map<String, Object>> getOrgUserList(String orgId, int pageNum, int pageSize, String orderBy);

}
