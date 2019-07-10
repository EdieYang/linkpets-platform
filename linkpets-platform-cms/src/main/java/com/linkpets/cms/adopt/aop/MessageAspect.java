package com.linkpets.cms.adopt.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.adopt.model.UserInfo;
import com.linkpets.cms.adopt.service.*;
import com.linkpets.core.model.*;
import com.linkpets.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class MessageAspect {

    @Resource
    private IPetService petService;

    @Resource
    private ICmsAdoptMsgService msgService;

    @Resource
    private IUserService userService;

    @Resource
    private IApplyService applyService;

    @Resource
    private IFormIdGeneratorService formIdGeneratorService;

    private static final String UNPASS = "1";

    private static final String PASS = "3";

    @Value("${linkPet.lpWechat.templateMsg.applyUpt}")
    private String applyUptUrl;

    @Value("${linkPet.lpWechat.templateMsg.certificateUpt}")
    private String certificateUptUrl;

    @Value("${linkPet.lpWechat.templateMsg.adoptionUpt}")
    private String adoptionUptUrl;

    /**
     * 新建领养信息切点
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.IPetService.crtAdopt(..))")
    public void crtAdoptPointCut() {
    }

    /**
     * 审核用户新发布的领养信息切点
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.IPetService.uptAdopt(..))")
    public void uptAdoptPointCut() {
    }

    /**
     * 发起领养申请切点
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.IApplyService.crtApply(..))")
    public void crtApplyPointCut() {
    }

    /**
     * 更新领养申请状态切点
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.IApplyService.uptApply(..))")
    public void uptApplyPointCut() {
    }


    /**
     * 上传实名认证身份信息
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.ICertificationService.uploadCertification(..))")
    public void uploadCertificationPointCut() {
    }


    /**
     * 审核实名认证身份信息
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.ICertificationService.modifyCertification(..))")
    public void uptCertificationPointCut() {
    }


    @Around("crtAdoptPointCut()")
    public String aroundCrtPetPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>crt new petAdoption start.....");
        CmsAdoptPet uptCmsAdoptPet = (CmsAdoptPet) pjp.getArgs()[0];
        String petId = "";
        String receiverFormId="";
        try {
            Object result = pjp.proceed();
            if (result != null) {
                //推送系统消息
                petId = (String) result;

                CmsAdoptPet pet = petService.getAdopt(petId);

                String createBy = pet.getCreateBy();
                formIdGeneratorService.addFormId(uptCmsAdoptPet.getFormId(), createBy);

                //获取有效formId
                CmsAdoptFormid formId = formIdGeneratorService.getValidFormId(createBy);
                receiverFormId=formId.getFormId();
                CmsAdoptMsg msg = new CmsAdoptMsg();
                msg.setMsgTitle(MessageTemplate.PET_MSG_TITLE);
                JSONObject msgContent = new JSONObject();
                msgContent.put("portrait", "");
                msgContent.put("nickName", "");
                msgContent.put("title", MessageTemplate.PET_MSG_CONTENT_TITLE);
                msgContent.put("content", MessageTemplate.PET_MSG_CONTENT_LOG);
                msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                msgContent.put("petName", pet.getPetName());
                msgContent.put("status", "0");
                msg.setMsgContent(msgContent.toJSONString());
                msg.setMsgType(0);
                msg.setPetId(petId);
                msg.setSender("SYS");
                msg.setReceiver(createBy);
                msg.setCreateTime(new Date());
                msg.setFormId(receiverFormId);
                msgService.crtMessage(msg);


                //推微信模板消息
                this.sendTemplateMsg(adoptionUptUrl,formId,msg);

            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            return petId;
        }
    }


    @Around("uptAdoptPointCut()")
    public void aroundUptPetPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>check new petAdoption start.....");
        CmsAdoptPet uptCmsAdoptPet = (CmsAdoptPet) pjp.getArgs()[0];
        String receiverFormId = "";
        try {
            pjp.proceed();
            String petId = uptCmsAdoptPet.getPetId();
            CmsAdoptPet pet = petService.getAdopt(petId);
            String createBy = pet.getCreateBy();
            CmsAdoptMsg msg = new CmsAdoptMsg();
            JSONObject msgContent = new JSONObject();

            //推送系统消息
            switch (uptCmsAdoptPet.getAdoptStatus()) {
                case UNPASS:
                    //审核失败通知
                    msg.setMsgTitle(MessageTemplate.PET_CHECK_UNPASS_MSG_TITLE);
                    msgContent.put("portrait", "");
                    msgContent.put("nickName", "");
                    msgContent.put("title", MessageTemplate.PET_CHECK_UNPASS_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.PET_CHECK_UNPASS_MSG_CONTENT_LOG + uptCmsAdoptPet.getMemo());
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("status", "1");
                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(0);
                    msg.setPetId(petId);
                    msg.setSender("SYS");
                    msg.setReceiver(createBy);
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);
                    break;
                case PASS:
                    //审核通过上线通知
                    msg.setMsgTitle(MessageTemplate.PET_CHECK_PASS_MSG_TITLE);
                    msgContent.put("portrait", "");
                    msgContent.put("nickName", "");
                    msgContent.put("title", MessageTemplate.PET_CHECK_PASS_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.PET_CHECK_PASS_MSG_CONTENT_LOG);
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("status", "2");
                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(0);
                    msg.setPetId(petId);
                    msg.setSender("SYS");
                    msg.setReceiver(createBy);
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);
                    break;
                default:
                    break;
            }

            CmsAdoptFormid formId = formIdGeneratorService.getValidFormId(createBy);
            receiverFormId=formId.getFormId();
            msg.setFormId(receiverFormId);
            log.info("发送模板消息请求参数：=======================》" + JSON.toJSONString(msg));
            this.sendTemplateMsg(adoptionUptUrl,formId,msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Around("crtApplyPointCut()")
    public void aroundCrtApplyPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>create new Apply start.....");
        CmsAdoptApply newApply = (CmsAdoptApply) pjp.getArgs()[0];
        formIdGeneratorService.addFormId(newApply.getFormId(),newApply.getApplyBy());

        try {
            Object result = pjp.proceed();
            String applyId = (String) result;
            String applyUserId = newApply.getApplyBy();
            String petId = newApply.getPetId();

            if (StringUtils.isNotEmpty(applyUserId) && StringUtils.isNotEmpty(petId)) {
                CmsAdoptPet pet = petService.getAdopt(petId);
                String createBy = pet.getCreateBy();
                //获取有效formId
                CmsAdoptFormid formId = formIdGeneratorService.getValidFormId(createBy);
                UserInfo user = userService.getUserInfoByUserId(applyUserId);
                CmsAdoptMsg msg = new CmsAdoptMsg();
                msg.setMsgTitle(MessageTemplate.APPLY_MSG_TITLE.replace("#", user.getNickName()));
                JSONObject msgContent = new JSONObject();
                msgContent.put("portrait", user.getPortrait());
                msgContent.put("nickName", user.getNickName());
                msgContent.put("title", MessageTemplate.APPLY_MSG_CONTENT_TITLE);
                msgContent.put("content", MessageTemplate.APPLY_MSG_CONTENT_LOG);
                msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                msgContent.put("petName", pet.getPetName());
                msgContent.put("applyId", applyId);
                msgContent.put("status", "0");
                msg.setMsgContent(msgContent.toJSONString());
                msg.setMsgType(1);
                msg.setPetId(petId);
                msg.setSender(applyUserId);
                msg.setReceiver(createBy);
                msg.setCreateTime(new Date());

                msgService.crtMessage(msg);

                log.info("发送模板消息请求参数：=======================》" + JSON.toJSONString(msg));

                //发送创建申请模板消息
                this.sendTemplateMsg(applyUptUrl,formId,msg);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Around("uptApplyPointCut()")
    public void aroundUptApplyPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>update new Apply start.....");
        CmsAdoptApply uptApply = (CmsAdoptApply) pjp.getArgs()[0];

        try {
            pjp.proceed();
            String applyId = uptApply.getApplyId();
            CmsAdoptApply apply = applyService.getApply(applyId);
            CmsAdoptPet pet = petService.getAdopt(apply.getPetId());
            String createBy = pet.getCreateBy();
            UserInfo user = userService.getUserInfoByUserId(createBy);
            UserInfo applyUser = userService.getUserInfoByUserId(apply.getApplyBy());
            formIdGeneratorService.addFormId(uptApply.getFormId(), apply.getApplyBy());
            CmsAdoptMsg msg = new CmsAdoptMsg();
            JSONObject msgContent = new JSONObject();
            String receiverFormId = "";
            String senderFormId = "";
            CmsAdoptFormid formid=null;
            switch (uptApply.getApplyStatus()) {
                case "5":
                    msg.setMsgTitle(MessageTemplate.APPLY_REFUSE_MSG_TITLE.replace("#", pet.getPetName()));
                    msgContent.put("portrait", user.getPortrait());
                    msgContent.put("nickName", user.getNickName());
                    msgContent.put("title", MessageTemplate.APPLY_REFUSE_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.APPLY_REFUSE_MSG_CONTENT_LOG + uptApply.getApplyResp());
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("applyId", applyId);
                    msgContent.put("status", "5");
                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(1);
                    msg.setPetId(apply.getPetId());
                    msg.setSender(pet.getCreateBy());
                    msg.setReceiver(apply.getApplyBy());
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);

                    msg.setSender(apply.getApplyBy());
                    msg.setReceiver(pet.getCreateBy());
                    formid= formIdGeneratorService.getValidFormId(pet.getCreateBy());
                    receiverFormId =formid.getFormId();
                    msg.setFormId(receiverFormId);
                    msgService.crtMessage(msg);

                    break;
                case "1":
                    msg.setMsgTitle(MessageTemplate.APPLY_PASS_FIRST_MSG_TITLE.replace("#", pet.getPetName()));
                    msgContent.put("portrait", user.getPortrait());
                    msgContent.put("nickName", user.getNickName());
                    msgContent.put("title", MessageTemplate.APPLY_PASS_FIRST_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.APPLY_PASS_FIRST_MSG_CONTENT_LOG);
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("applyId", applyId);
                    msgContent.put("status", "1");

                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(1);
                    msg.setPetId(apply.getPetId());
                    msg.setSender(pet.getCreateBy());
                    msg.setReceiver(apply.getApplyBy());
                    formid = formIdGeneratorService.getValidFormId(apply.getApplyBy());
                    receiverFormId=formid.getFormId();
                    msg.setFormId(receiverFormId);
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);
                    break;
                case "2":
                    msg.setMsgTitle(MessageTemplate.APPLY_PASS_SECOND_MSG_TITLE.replace("#", pet.getPetName()));
                    msgContent.put("portrait", user.getPortrait());
                    msgContent.put("nickName", user.getNickName());
                    msgContent.put("title", MessageTemplate.APPLY_PASS_SECOND__MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.APPLY_PASS_SECOND_MSG_CONTENT_LOG);
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("applyId", applyId);
                    msgContent.put("status", "2");

                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(2);
                    msg.setPetId(apply.getPetId());
                    msg.setSender(pet.getCreateBy());
                    msg.setReceiver(apply.getApplyBy());
                    msg.setCreateTime(new Date());
                    formid = formIdGeneratorService.getValidFormId(apply.getApplyBy());
                    receiverFormId=formid.getFormId();
                    msg.setFormId(receiverFormId);
                    msgService.crtMessage(msg);
                    break;
                case "3":
                    msg.setMsgTitle(MessageTemplate.APPLY_PASS_THIRD_MSG_TITLE.replace("#", applyUser.getNickName()));
                    msgContent.put("portrait", applyUser.getPortrait());
                    msgContent.put("nickName", applyUser.getNickName());
                    msgContent.put("title", MessageTemplate.APPLY_PASS_THIRD_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.APPLY_PASS_THIRD_MSG_CONTENT_LOG);
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("applyId", applyId);
                    msgContent.put("status", "3");

                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(2);
                    msg.setPetId(apply.getPetId());
                    msg.setSender(apply.getApplyBy());
                    msg.setReceiver(pet.getCreateBy());
                    msg.setCreateTime(new Date());
                    formid = formIdGeneratorService.getValidFormId(pet.getCreateBy());
                    receiverFormId=formid.getFormId();
                    msg.setFormId(receiverFormId);
                    msgService.crtMessage(msg);
                    break;
                case "4":
                    msg.setMsgTitle(MessageTemplate.APPLY_PASS_FORTH_ADOPTER_MSG_TITLE);
                    msgContent.put("portrait", user.getPortrait());
                    msgContent.put("nickName", user.getNickName());
                    msgContent.put("title", MessageTemplate.APPLY_PASS_FORTH_ADOPTER_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.APPLY_PASS_FORTH_ADOPTER_MSG_CONTENT_LOG);
                    msgContent.put("status", "4");

                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setSender("SYS");
                    msg.setReceiver(pet.getCreateBy());
                    msgService.crtMessage(msg);

                    msg.setMsgTitle(MessageTemplate.APPLY_PASS_FORTH_MSG_TITLE);
                    msgContent.put("portrait", applyUser.getPortrait());
                    msgContent.put("nickName", applyUser.getNickName());
                    msgContent.put("title", MessageTemplate.APPLY_PASS_FORTH_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.APPLY_PASS_FORTH_MSG_CONTENT_LOG);
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("applyId", applyId);
                    msgContent.put("status", "4");

                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(2);
                    msg.setPetId(apply.getPetId());
                    msg.setSender("SYS");
                    msg.setReceiver(apply.getApplyBy());
                    formid = formIdGeneratorService.getValidFormId(apply.getApplyBy());
                    receiverFormId=formid.getFormId();
                    msg.setFormId(receiverFormId);
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);

                    break;
                default:
                    break;
            }

            log.info("发送模板消息请求参数：=======================》" + JSON.toJSONString(msg));
            this.sendTemplateMsg(applyUptUrl,formid,msg);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Around("uploadCertificationPointCut()")
    public void aroundCrtCertificationPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>crt new certification start.....");
        CmsAdoptCertification certification = (CmsAdoptCertification) pjp.getArgs()[0];
        String receiverFormId = "";
        formIdGeneratorService.addFormId(certification.getFormId(), certification.getUserId());
        try {
            pjp.proceed();
            String userId = certification.getUserId();
            CmsAdoptMsg msg = new CmsAdoptMsg();
            msg.setMsgTitle(MessageTemplate.CER_MSG_TITLE);
            JSONObject msgContent = new JSONObject();
            msgContent.put("portrait", "");
            msgContent.put("nickName", "");
            msgContent.put("title", MessageTemplate.CER_MSG_CONTENT_TITLE);
            msgContent.put("content", MessageTemplate.CER_MSG_CONTENT_LOG);
            msgContent.put("petPic", "");
            msgContent.put("petName", "");
            msgContent.put("status", "0");
            msg.setMsgContent(msgContent.toJSONString());
            msg.setMsgType(0);
            msg.setPetId("");
            msg.setSender("SYS");
            msg.setReceiver(userId);

            CmsAdoptFormid formid = formIdGeneratorService.getValidFormId(userId);
            receiverFormId=formid.getFormId();
            msg.setFormId(receiverFormId);

            msg.setCreateTime(new Date());
            msgService.crtMessage(msg);


            //发送申请创建模板消息
            this.sendTemplateMsg(certificateUptUrl,formid,msg);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Around("uptCertificationPointCut()")
    public void aroundUptCertificationPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>check certification start.....");
        CmsAdoptCertification certification = (CmsAdoptCertification) pjp.getArgs()[0];
        String receiverFormId = "";
        formIdGeneratorService.addFormId(certification.getFormId(), certification.getUserId());
        try {
            pjp.proceed();
            String userId = certification.getUserId();
            CmsAdoptMsg msg = new CmsAdoptMsg();
            JSONObject msgContent = new JSONObject();

            switch (certification.getStatus()) {
                case "1":
                    msg.setMsgTitle(MessageTemplate.CER_PASS_MSG_TITLE);
                    msgContent.put("portrait", "");
                    msgContent.put("nickName", "");
                    msgContent.put("title", MessageTemplate.CER_PASS_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.CER_PASS_MSG_CONTENT_LOG);
                    msgContent.put("petPic", "");
                    msgContent.put("petName", "");
                    msgContent.put("status", "1");
                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(0);
                    msg.setPetId("");
                    msg.setSender("SYS");
                    msg.setReceiver(userId);
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);
                    break;
                case "2":
                    msg.setMsgTitle(MessageTemplate.CER_REJECT_MSG_TITLE);
                    msgContent.put("portrait", "");
                    msgContent.put("nickName", "");
                    msgContent.put("title", MessageTemplate.CER_REJECT_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.CER_REJECT_MSG_CONTENT_LOG + certification.getMemo());
                    msgContent.put("petPic", "");
                    msgContent.put("petName", "");
                    msgContent.put("status", "2");
                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setMsgType(0);
                    msg.setPetId("");
                    msg.setSender("SYS");
                    msg.setReceiver(userId);
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);
                    break;
                default:
                    break;
            }

            CmsAdoptFormid formid = formIdGeneratorService.getValidFormId(userId);
            receiverFormId=formid.getFormId();
            msg.setFormId(receiverFormId);


            //发送申请更新模板消息
            this.sendTemplateMsg(certificateUptUrl,formid,msg);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    private void sendTemplateMsg(String sendUrl,CmsAdoptFormid formId,CmsAdoptMsg msg){
        if(formId!=null){
            msg.setFormId(formId.getFormId());
            try {
                log.info(HttpUtil.doPost(sendUrl, JSON.toJSONString(msg)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            formIdGeneratorService.inactiveFormId(formId.getId());
        }
    }

}