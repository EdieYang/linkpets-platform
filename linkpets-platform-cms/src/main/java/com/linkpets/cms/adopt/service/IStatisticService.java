package com.linkpets.cms.adopt.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface IStatisticService {

    JSONObject getDataByOrg(String orgId);

    /**
     * 获取送养成功总数
     *
     * @param orgId
     * @return
     */
    int getAdoptTotalCount(String orgId);

	/**
	 * 获取近7天统计数据
	 * @return
	 */
	List<Map<String, Object>> getNealyWeekCount();

}
