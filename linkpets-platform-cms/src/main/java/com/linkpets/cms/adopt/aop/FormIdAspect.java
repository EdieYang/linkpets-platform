package com.linkpets.cms.adopt.aop;


import com.linkpets.cms.adopt.service.IFormIdGeneratorService;
import com.linkpets.core.model.CmsAdoptApply;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.core.model.CmsAdoptPet;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Aspect
@Component
@Slf4j
public class FormIdAspect {

    @Resource
    private IFormIdGeneratorService formIdGeneratorService;



//
//    /**
//     * formId resource
//     */
//    @Pointcut("execution(public * com.linkpets.cms.adopt.service.IPetService.crtAdopt(..))")
//    public void crtAdoptFormPointCut() {
//
//    }
//
//
//
//    @Around("crtAdoptFormPointCut()")
//    public void aroundUptApplyPointCut(ProceedingJoinPoint pjp) {
//        CmsAdoptPet adoptPet = (CmsAdoptPet) pjp.getArgs()[0];
//        formIdGeneratorService.addFormId(adoptPet.getFormId(),adoptPet.getCreateBy());
//    }
}
