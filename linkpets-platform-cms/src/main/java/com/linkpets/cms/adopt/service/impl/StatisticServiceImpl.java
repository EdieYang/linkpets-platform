package com.linkpets.cms.adopt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.adopt.service.IStatisticService;
import com.linkpets.core.dao.CmsAdoptApplyMapper;
import com.linkpets.core.dao.CmsAdoptOrgActivityMapper;
import com.linkpets.core.dao.CmsAdoptOrgFollowMapper;
import com.linkpets.core.dao.CmsAdoptOrgGalleryMapper;
import com.linkpets.core.dao.CmsAdoptPetOrgRelMapper;
import com.linkpets.util.DateUtils;

@Service
public class StatisticServiceImpl implements IStatisticService {

	@Resource
	CmsAdoptPetOrgRelMapper cmsAdoptPetOrgRelMapper;

	@Resource
	CmsAdoptOrgFollowMapper cmsAdoptOrgFollowMapper;

	@Resource
	CmsAdoptApplyMapper cmsAdoptApplyMapper;

	@Resource
	CmsAdoptOrgGalleryMapper cmsAdoptOrgGalleryMapper;

	@Resource
	CmsAdoptOrgActivityMapper cmsAdoptOrgActivityMapper;

	@Override
	public JSONObject getDataByOrg(String orgId) {
		JSONObject data = new JSONObject();
		String fromDay = DateUtils.getLastWeekDay();
		String toDay = DateUtils.getCurrentDay();
		//一周内发布领养数
		data.put("adoptCountInWeek",
				cmsAdoptPetOrgRelMapper.getAdoptCountForCreateByOrgIdWithRange(orgId, fromDay, toDay));
		//一周内粉丝增长数
		data.put("fansCountInWeek",
				cmsAdoptOrgFollowMapper.getFansCountForCreateByOrgIdWithRange(orgId, fromDay, toDay));
		//一周内领养申请数
		data.put("applyCountInWeek", cmsAdoptApplyMapper.getApplyCountByOrgIdWithRange(orgId, null, fromDay, toDay));

		fromDay = DateUtils.getLastMonthDay();
		//一个月内成功领养数
		data.put("successAdoptCountInMonth",
				cmsAdoptApplyMapper.getApplyCountByOrgIdWithRange(orgId, "4", fromDay, toDay));
		//一个月内上传照片数
		data.put("galleryCountInMonth",
				cmsAdoptOrgGalleryMapper.getGalleryCountForByOrgIdWithRange(orgId, fromDay, toDay));
		//一个月内发布活动数
		data.put("activityCountInMonth",
				cmsAdoptOrgActivityMapper.getActivityCountByOrgIdWithRange(orgId, fromDay, toDay));
		
		//成功领养总数
		data.put("successAdoptCountTotal",
				cmsAdoptApplyMapper.getApplyCountByOrgIdWithRange(orgId, "4", null, null));
		//发布领养总数
		data.put("adoptCountTotal",
				cmsAdoptPetOrgRelMapper.getAdoptCountForCreateByOrgIdWithRange(orgId, null, null));
		//申请领养总数
		data.put("applyCountTotal",
				cmsAdoptApplyMapper.getApplyCountByOrgIdWithRange(orgId, null, null, null));
		//粉丝总数
		data.put("fansCountTotal",
				cmsAdoptOrgFollowMapper.getFansCountForCreateByOrgIdWithRange(orgId, null, null));
		
		return data;
	}

}
