package com.linkpets.cms.user.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.linkpets.core.model.CmsAdoptAttention;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.SysUser;
import com.linkpets.core.respEntity.RespOrgUser;

import java.util.List;
import java.util.Map;

public interface IUserService {

    /**
     * 分页获取用户列表
     *
     * @param wxAccount
     * @param mobilePhone
     * @param authenticated
     * @return
     */
    PageInfo<CmsUser> getUserPage(String wxAccount, String mobilePhone, Integer authenticated, Integer pageNum, Integer pageSize);

    /**
     * 获取用户列表
     *
     * @param wxAccount
     * @param mobilePhone
     * @param authenticated
     * @return
     */
    List<CmsUser> getUserList(String wxAccount, String mobilePhone, Integer authenticated);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    CmsUser getUserInfoByUserId(String userId);

    /**
     * 获取用户基础信息
     *
     * @param userId
     * @return
     */
    CmsUser getUser(String userId);

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

//    /**
//     *
//    * @Title: getUserByAccountAndPassword 
//    * @Description: 根据账号密码查找用户
//    * @param @param userAcc
//    * @param @param password
//    * @param @return
//    * @return SysUser
//    * @author wando 
//    * @throws
//    * @date 2019年9月10日 上午10:52:16 
//    * @version V1.0   
//     */
//	SysUser getUserByAccountAndPassword(String userAcc, String password);

    /**
     * 分页获取组织用户列表
     *
     * @param wxAccount
     * @param orgId
     * @param mobilePhone
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<RespOrgUser> getOrgUserInfoPage(String wxAccount, String mobilePhone, String orgId, Integer pageNum, Integer pageSize);

    /**
     * 获取组织用户列表
     *
     * @param orgId
     * @return
     */
    List<RespOrgUser> getOrgUserInfoList(String orgId);


}
