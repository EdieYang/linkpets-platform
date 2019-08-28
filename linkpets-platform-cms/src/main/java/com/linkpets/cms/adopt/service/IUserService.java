package com.linkpets.cms.adopt.service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsAdoptAttention;
import com.linkpets.core.model.CmsUser;

import java.util.Map;

public interface IUserService {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    CmsUser getUserInfoByUserId(String userId);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUserInfo(CmsUser user);

    /**
     * 获取用户最近登录时间
     *
     * @param userId
     * @return
     */
    String getLastLoginTime(String userId);

    /**
     * 获取当天用户登录总数
     *
     * @param syncDate
     * @return
     */
    int getLoginCount(String syncDate);

    /**
     * 分页获取用户关注列表
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param string
     * @return
     */
    JSONObject getUserListAttentTo(Map<String, Object> param, int pageNum, int pageSize, String string);

    /**
     * 分页获取用户粉丝列表
     *
     * @param param
     * @param pageNum
     * @param pageSize
     * @param string
     * @return
     */
    JSONObject getUserListAttentBy(Map<String, Object> param, int pageNum, int pageSize, String string);

    /**
     * 获取关注状态（是否关注）
     *
     * @param userId
     * @param targetUserId
     * @return
     */
    int getAttentionStatus(String userId, String targetUserId);

    /**
     * 添加关注
     *
     * @param record
     */
    void crtAttention(CmsAdoptAttention record);

    /**
     * 取消关注
     *
     * @param userId
     * @param attentBy
     */
    void delAttention(String userId, String attentBy);

    /**
     * 获取用户领养统计数据
     *
     * @param userId
     * @return
     */
    Map<String, Object> getUserAddition(String userId);

    /**
     * 获取用户总数
     *
     * @return
     */
    int getTotalUserCount();
}
