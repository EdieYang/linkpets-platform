package com.linkpets.wechat.dao;

import com.linkpets.core.model.CmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/3/25
 */
public interface CmsUserCustomMapper {

    CmsUser getUserByUnionId(String unionId);

    String getMaxUserNo(@Param("userIdStart") String userIdStart, @Param("year") String year);

    List<Map<String, Object>> selectUserList(Map<String, Object> map);

    List<Map<String, String>> getUserInfoByUserId(List<String> userIds);
}
