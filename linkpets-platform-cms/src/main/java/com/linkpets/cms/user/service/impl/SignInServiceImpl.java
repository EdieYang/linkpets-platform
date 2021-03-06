package com.linkpets.cms.user.service.impl;

import com.linkpets.cms.user.model.SignInPoints;
import com.linkpets.cms.user.service.ISignInService;
import com.linkpets.core.dao.CmsUserSignMapper;
import com.linkpets.core.model.CmsUserSign;
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
    private static final Integer SIGN_IN_DAY_3 = 75 + 25;
    private static final Integer SIGN_IN_DAY_4 = 100 + 30;
    private static final Integer SIGN_IN_DAY_5 = 120;
    private static final Integer SIGN_IN_DAY_6 = 150;
    private static final Integer SIGN_IN_DAY_7 = 200 + 100;

    @Resource
    private CmsUserSignMapper signMapper;

    @Override
    public SignInPoints crtSignInRecord(String userId) {
        SignInPoints signInPoints = new SignInPoints();
        String today = DateUtils.getCurrentDay();
        CmsUserSign userSign = signMapper.getSignRecordByDate(userId, today);
        if (userSign != null) {
            return signInPoints;
        }
        CmsUserSign CmsUserSign = new CmsUserSign();
        CmsUserSign.setId(UUIDUtils.getId());
        CmsUserSign.setSignInDate(new Date());
        CmsUserSign.setUserId(userId);
        signMapper.insertSelective(CmsUserSign);
        List<CmsUserSign> userSignList = signMapper.getSignRecordList(userId);
        if (userSignList != null && userSignList.size() == 1) {
            signInPoints.setGroupDays(-1);
            signInPoints.setPoints(SIGN_IN_DAY_FIRST);
            return signInPoints;
        }
        int continueDays = 0;
        for (int i = 0; i < 365 * 5; i++) {
            String compareDate = DateUtils.rollNOfDate(DateUtils.getCurrentDay(), 1, 0 - i, "yyyy-MM-dd");
            boolean hasSigned = false;
            for (CmsUserSign sign : userSignList) {
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

        signInPoints.setGroupDays(groupDays);
        signInPoints.setPoints(points);
        return signInPoints;
    }

    @Override
    public int getSignInTimes(String userId) {
        int continueDays = 0;
        List<CmsUserSign> userSignList = signMapper.getSignRecordList(userId);
        if (userSignList == null || userSignList.size() == 0) {
            return continueDays;
        }
        for (int i = 0; i < 365 * 5; i++) {
            String compareDate = DateUtils.rollNOfDate(DateUtils.getCurrentDay(), 1, 0 - i, "yyyy-MM-dd");
            boolean hasSigned = false;
            for (CmsUserSign sign : userSignList) {
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

