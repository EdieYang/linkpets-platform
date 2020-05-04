package com.linkpets.cms.adopt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.adopt.service.IStatisticService;
import com.linkpets.core.dao.*;
import com.linkpets.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    CmsUserLoginMapper cmsUserLoginMapper;

    @Resource
    CmsAdoptPetMapper cmsAdoptPetMapper;

    @Resource
    CmsGroupPostMapper cmsGroupPostMapper;

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

    @Override
    public int getAdoptTotalCount(String orgId) {
        return cmsAdoptApplyMapper.getApplyCountByOrgIdWithRange(orgId, "4", null, null);
    }

    @Override
    public List<Map<String, Object>> getNealyWeekCount() {
        List<Map<String, Object>> weekCount = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            Map<String, Object> dayCount = new HashMap<>();
            String currentDay = DateUtils.rollNOfDate(DateUtils.getCurrentDay(), 1, 0 - i, "yyyy-MM-dd");
            int userLoginCount = cmsUserLoginMapper.getLoginCount(currentDay);
            int applyCount = cmsAdoptApplyMapper.getApplyCountByOrgIdWithRange("", "4", currentDay, currentDay);
            int adoptSendCount = cmsAdoptPetMapper.getPetAdoptCount(currentDay);
            int postSendCount = cmsGroupPostMapper.getPostCount(currentDay);
            dayCount.put("日期", currentDay);
            dayCount.put("访问用户", userLoginCount);
            dayCount.put("申请领养数", applyCount);
            dayCount.put("送养宠物数", adoptSendCount);
            dayCount.put("发布帖子数", postSendCount);
            weekCount.add(dayCount);
        }
        return weekCount;
    }

}
