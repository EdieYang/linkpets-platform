package com.linkpets.cms.adopt.resource;

import com.alibaba.fastjson.JSONObject;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.model.UserInfo;
import com.linkpets.cms.adopt.service.*;
import com.linkpets.core.model.CmsAdoptAgreement;
import com.linkpets.core.model.CmsAdoptApply;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.core.model.CmsAdoptPet;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/5/26
 */

@Api(value = "领养平台领养协议接口", tags = "领养协议接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/agreement")
public class AgreementResource {

    @Resource
    IAgreementService agreementService;

    @Resource
    IApplyService applyService;

    @Resource
    IPetService petService;

    @Resource
    IUserService userService;

    @Resource
    ICertificationService certificationService;


    @ApiOperation("创建领养合同接口")
    @PostMapping(value = "info/{formId}")
    public PlatformResult crtAgreement(@PathVariable("formId")String formId ,@RequestBody CmsAdoptAgreement agreement) {

        String agreementId = agreementService.crtAgreement(agreement);
        String applyId=agreement.getApplyId();
        CmsAdoptApply adoptApply=new CmsAdoptApply();
        adoptApply.setApplyId(applyId);
        adoptApply.setApplyStatus("2");
        applyService.uptApply(adoptApply);
        return PlatformResult.success(agreementId);
    }

    @ApiOperation("修改领养合同接口")
    @PutMapping(value = "info/{formId}")
    public PlatformResult uptAgreement(@PathVariable("formId")String formId ,@RequestBody CmsAdoptAgreement agreement) {
        if("1".equals(agreement.getSignStatus())){
            CmsAdoptApply apply=new CmsAdoptApply();
            apply.setApplyId(agreement.getApplyId());
            apply.setApplyStatus("3");
            applyService.uptApply(apply);

            //加入领养人签署协议信息
            //获取领养人身份信息
            String applyUserId=agreement.getApplyId();
            CmsAdoptCertification certification=certificationService.getUserCertification(applyUserId);
            agreement.setApplyIdcard(certification.getIdCard());

        }

        if("2".equals(agreement.getSignStatus())){
            CmsAdoptApply apply=new CmsAdoptApply();
            apply.setApplyId(agreement.getApplyId());
            apply.setApplyStatus("4");
            applyService.uptApply(apply);
            CmsAdoptPet pet=new CmsAdoptPet();
            pet.setPetId(agreement.getPetId());
            pet.setAdoptStatus("4");
            petService.uptAdopt(pet);

            //加入送养人签署协议信息
            String adoptUserId=agreement.getCreateBy();
            CmsAdoptCertification certification=certificationService.getUserCertification(adoptUserId);
            agreement.setApplyIdcard(certification.getIdCard());
            agreement.setSignTime(new Date());
        }
        agreementService.uptAgreement(agreement);
        return PlatformResult.success();
    }


    @ApiOperation("获取领养合同详情接口")
    @GetMapping(value = "{agreementId}")
    public PlatformResult getAdoptAgreement(@PathVariable("agreementId") String agreementId) {
        CmsAdoptAgreement agreement=agreementService.getAgreement(agreementId);
        CmsAdoptPet petInfo=petService.getAdopt(agreement.getPetId());
        JSONObject result=new JSONObject();
        result.put("contractInfo",agreement);
        result.put("petInfo",petInfo);
        return PlatformResult.success(result);
    }


    @ApiOperation("根据applyId获取领养合同详情接口")
    @GetMapping(value = "info")
    public PlatformResult getAdoptAgreementByApplyId(@RequestParam("applyId") String applyId) {
        CmsAdoptAgreement agreement=agreementService.getAgreementByApplyId(applyId);
        CmsAdoptApply adoptApply = applyService.getApply(applyId);
        CmsAdoptPet petInfo=petService.getAdopt(adoptApply.getPetId());
        UserInfo applyUser=userService.getUserInfoByUserId(adoptApply.getApplyBy());
        UserInfo adopterUser=userService.getUserInfoByUserId(petInfo.getCreateBy());
        JSONObject result=new JSONObject();
        result.put("contractInfo",agreement);
        result.put("applyInfo",adoptApply);
        result.put("petInfo",petInfo);
        result.put("applyUser",applyUser);
        result.put("adopterUser",adopterUser);

        return PlatformResult.success(result);
    }


    @GetMapping(value = "agreementList")
    PlatformResult getAgreementList(@RequestParam("pageNum") int pageNum,
                                    @RequestParam("pageSize") int pageSize,
                                    @RequestParam(value = "petId", required = false) String petId,
                                    @RequestParam(value = "adopterId", required = false) String adopterId,
                                    @RequestParam(value = "applyBy", required = false, defaultValue = "") String applyBy,
                                    @RequestParam(value = "agreementStatus", required = false) String agreementStatus,
                                    @RequestParam(value = "sortCol", required = false, defaultValue = "createDate") String sortCol,
                                    @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {


        Map<String, Object> param = new HashMap<String, Object>();
        param.put("adopterId", adopterId);
        param.put("applyBy", applyBy);
        param.put("petId", petId);
        param.put("applyStatus", agreementStatus);
        JSONObject data = agreementService.getAgreementListForPage(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(data);
    }


}
