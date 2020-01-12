package com.linkpets.cms.group.aop;

import com.linkpets.cms.group.enums.PointsChannelEnum;
import com.linkpets.cms.group.service.IPointStatementService;
import com.linkpets.cms.user.model.SignInPoints;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.core.model.CmsPointStatement;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Aspect
@Component
@Slf4j
public class PointStatementAspect {

    @Resource
    private IPointStatementService pointStatementService;

    /**
     * 每日签到获取积分
     */
    @Pointcut("execution(public * com.linkpets.cms.user.service.ISignInService.crtSignInRecord(..))")
    public void crtSignInRecord() {
    }

    /**
     * 上传实名认证身份信息获取积分
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.ICertificationService.uploadCertification(..))")
    public void uploadCertificationPointCut() {
    }


    @Around("crtSignInRecord()")
    public SignInPoints aroundCrtSignInRecordPointCut(ProceedingJoinPoint pjp) {
        log.info("{PointStatementAspect} =>crt new SignIn start.....");
        String userId = (String) pjp.getArgs()[0];
        SignInPoints points = new SignInPoints();
        try {
            points = (SignInPoints) pjp.proceed();
            if (points.getPoints() != 0) {
                pointStatementService.crtPointStatement(userId, points.getPoints(), "", points.getPoints() == 100 ? PointsChannelEnum.FIRST_SIGN_IN : PointsChannelEnum.DAILY_POINTS);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return points;
    }

    @Around("uploadCertificationPointCut()")
    public void aroundCrtCertificationPointCut(ProceedingJoinPoint pjp) {
        log.info("{PointStatementAspect} =>crt new certification start.....");
        CmsAdoptCertification certification = (CmsAdoptCertification) pjp.getArgs()[0];
        try {
            pjp.proceed();
            String userId = certification.getUserId();
            List<CmsPointStatement> pointStatmentList = pointStatementService.getPointStatementListByChannel(userId, PointsChannelEnum.COMPLETE_USER_INFO.getChannel());
            if (pointStatmentList == null || pointStatmentList.size() == 0) {
                pointStatementService.crtPointStatement(userId, PointsChannelEnum.COMPLETE_USER_INFO.getPoints(), "", PointsChannelEnum.COMPLETE_USER_INFO);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


}
