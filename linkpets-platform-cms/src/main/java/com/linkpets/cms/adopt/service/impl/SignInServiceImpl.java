package com.linkpets.cms.adopt.service.impl;

import com.linkpets.cms.adopt.resource.AdoptSignInResource;
import com.linkpets.cms.adopt.service.ISignInService;
import com.linkpets.core.dao.CmsAdoptSignMapper;
import com.linkpets.core.model.CmsAdoptSign;
import com.linkpets.util.DateUtils;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SignInServiceImpl implements ISignInService {

    private static final Integer SIGN_IN_DAY_FIRST = 100;
    private static final Integer SIGN_IN_DAY_1 = 20;
    private static final Integer SIGN_IN_DAY_2 = 50 + 20;
    private static final Integer SIGN_IN_DAY_3 = 70 + 25;
    private static final Integer SIGN_IN_DAY_4 = 80 + 30;
    private static final Integer SIGN_IN_DAY_5 = 120;
    private static final Integer SIGN_IN_DAY_6 = 150;
    private static final Integer SIGN_IN_DAY_7 = 200 + 100;

    @Resource
    private CmsAdoptSignMapper signMapper;

    @Override
    public int crtSignInRecord(String userId) {
        String today = DateUtils.getCurrentDay();
        CmsAdoptSign adoptSign = signMapper.getSignRecordByDate(userId, today);
        if (adoptSign != null) {
            return 0;
        }
        CmsAdoptSign cmsAdoptSign = new CmsAdoptSign();
        cmsAdoptSign.setId(UUIDUtils.getId());
        cmsAdoptSign.setSignInDate(new Date());
        cmsAdoptSign.setUserId(userId);
        signMapper.insertSelective(cmsAdoptSign);
        List<CmsAdoptSign> adoptSignList = signMapper.getSignRecordList(userId);
        if (adoptSignList != null && adoptSignList.size() == 1) {
            return SIGN_IN_DAY_FIRST;
        }
        int continueDays = 0;
        for (int i = 0; i < 365 * 5; i++) {
            String compareDate = DateUtils.rollNOfDate(DateUtils.getCurrentDay(), 1, 0 - i, "yyyy-MM-dd");
            boolean hasSigned = false;
            for (CmsAdoptSign sign : adoptSignList) {
                String signDate = DateUtils.getFormatDateStr(sign.getSignInDate(), "yyyy-MM-dd");
                if (signDate.equals(compareDate)) {
                    continueDays++;
                    hasSigned = true;
                }
            }
            if (!hasSigned) {
                break;
            }
        }
        int points = 0;
        int groupDays = continueDays % 7;
        switch (groupDays) {
            case 0:
                points = SIGN_IN_DAY_7;
                break;
            case 1:
                points = SIGN_IN_DAY_1;
                break;
            case 2:
                points = SIGN_IN_DAY_2;
                break;
            case 3:
                points = SIGN_IN_DAY_3;
                break;
            case 4:
                points = SIGN_IN_DAY_4;
                break;
            case 5:
                points = SIGN_IN_DAY_5;
                break;
            case 6:
                points = SIGN_IN_DAY_6;
                break;
            default:
                break;
        }
        return points;
    }

    @Override
    public int getSignInTimes(String userId) {
        int continueDays = 0;
        List<CmsAdoptSign> adoptSignList = signMapper.getSignRecordList(userId);
        if (adoptSignList == null || adoptSignList.size() == 0) {
            return continueDays;
        }
        for (int i = 0; i < 365 * 5; i++) {
            String compareDate = DateUtils.rollNOfDate(DateUtils.getCurrentDay(), 1, 0 - i, "yyyy-MM-dd");
            boolean hasSigned = false;
            for (CmsAdoptSign sign : adoptSignList) {
                String signDate = DateUtils.getFormatDateStr(sign.getSignInDate(), "yyyy-MM-dd");
                if (signDate.equals(compareDate)) {
                    continueDays++;
                    hasSigned = true;
                }
            }
            if (!hasSigned && i != 0) {
                break;
            }
        }
        return continueDays % 7;
    }

}

