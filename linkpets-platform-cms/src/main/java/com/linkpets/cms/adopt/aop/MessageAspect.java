package com.linkpets.cms.adopt.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.cms.adopt.service.*;
import com.linkpets.cms.aprilfool.service.IActivityService;
import com.linkpets.cms.group.service.IGroupActivityRegisterService;
import com.linkpets.cms.group.service.IGroupActivityService;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.core.model.*;
import com.linkpets.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
    private IGroupActivityService groupActivityService;

    @Resource
    private IGroupActivityRegisterService groupActivityRegisterService;

    /**
     * 更新状态
     */
    private static final String UPT = "0";
    /**
     * 已领养状态
     */
    private static final String ADOPTED = "0";
    /**
     * 审核未通过状态
     */
    private static final String UN_PASS = "1";

    /**
     * 审核通过状态
     */
    private static final String PASS = "3";

    @Value("${linkPet.lpWechat.templateMsg.applyUpt}")
    private String applyUptUrl;

    @Value("${linkPet.lpWechat.templateMsg.certificateUpt}")
    private String certificateUptUrl;

    @Value("${linkPet.lpWechat.templateMsg.adoptionUpt}")
    private String adoptionUptUrl;

    @Value("${linkPet.lpWechat.templateMsg.activityNotification}")
    private String activityNotificationUrl;

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
     * 上传实名认证身份信息切点
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.ICertificationService.uploadCertification(..))")
    public void uploadCertificationPointCut() {
    }


    /**
     * 审核实名认证身份信息切点
     */
    @Pointcut("execution(public * com.linkpets.cms.adopt.service.ICertificationService.modifyCertification(..))")
    public void uptCertificationPointCut() {
    }

    /**
     * 活动报名成功切点
     */
    @Pointcut("execution(public * com.linkpets.cms.group.service.IGroupActivityRegisterService.crtGroupActivityRegister(..))")
    public void crtGroupActivityRegisterPointCut() {
    }

    /**
     * 活动报名取消切点
     */
    @Pointcut("execution(public * com.linkpets.cms.group.service.IGroupActivityRegisterService.delGroupActivityRegister(..))")
    public void delGroupActivityRegisterPointCut() {
    }

    /**
     * 创建领养信息
     *
     * @param pjp
     * @return
     */
    @Around("crtAdoptPointCut()")
    public String aroundCrtPetPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>crt new petAdoption start.....");
        String petId = "";
        try {
            Object result = pjp.proceed();
            if (result != null) {
                //推送系统消息
                petId = (String) result;
                CmsAdoptPet pet = petService.getAdopt(petId);
                String createBy = pet.getCreateBy();
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
                msgService.crtMessage(msg);
                //推微信模板消息
                this.sendTemplateMsg(adoptionUptUrl, msg);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            return petId;
        }
    }

    /**
     * 更新领养信息
     *
     * @param pjp
     */
    @Around("uptAdoptPointCut()")
    public void aroundUptPetPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>check new petAdoption start.....");
        CmsAdoptPet uptCmsAdoptPet = (CmsAdoptPet) pjp.getArgs()[0];
        try {
            pjp.proceed();
            String petId = uptCmsAdoptPet.getPetId();
            CmsAdoptPet pet = petService.getAdopt(petId);
            String createBy = pet.getCreateBy();
            CmsAdoptMsg msg = new CmsAdoptMsg();
            JSONObject msgContent = new JSONObject();
            //推送系统消息
            switch (uptCmsAdoptPet.getAdoptStatus()) {
                case UN_PASS:
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
            if (uptCmsAdoptPet.getAdoptStatus().equals(UN_PASS) && !uptCmsAdoptPet.getAdoptStatus().equals(PASS)) {
                log.info("发送模板消息请求参数：=======================》" + JSON.toJSONString(msg));
                this.sendTemplateMsg(adoptionUptUrl, msg);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建领养申请信息
     *
     * @param pjp
     */
    @Around("crtApplyPointCut()")
    public String aroundCrtApplyPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>create new Apply start.....");
        CmsAdoptApply newApply = (CmsAdoptApply) pjp.getArgs()[0];
        String applyId = "";
        try {
            Object result = pjp.proceed();
            applyId = (String) result;
            if (StringUtils.isNotEmpty(applyId)) {
                String applyUserId = newApply.getApplyBy();
                String petId = newApply.getPetId();
                if (StringUtils.isNotEmpty(applyUserId) && StringUtils.isNotEmpty(petId)) {
                    CmsAdoptPet pet = petService.getAdopt(petId);
                    String createBy = pet.getCreateBy();
                    CmsUser user = userService.getUserInfoByUserId(applyUserId);
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
                    this.sendTemplateMsg(applyUptUrl, msg);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return applyId;
    }


    /**
     * 更新领养申请信息
     *
     * @param pjp
     */
    @Around("uptApplyPointCut()")
    public void aroundUptApplyPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>update new Apply start.....");
        CmsAdoptApply uptApply = (CmsAdoptApply) pjp.getArgs()[0];
        String operateUserId = uptApply.getOperateUserId();
        try {
            pjp.proceed();
            String applyId = uptApply.getApplyId();
            CmsAdoptApply apply = applyService.getApply(applyId);
            CmsAdoptPet pet = petService.getAdopt(apply.getPetId());
            String createBy = pet.getCreateBy();
            CmsUser user = userService.getUserInfoByUserId(createBy);
            CmsUser applyUser = userService.getUserInfoByUserId(apply.getApplyBy());
            CmsAdoptMsg msg = new CmsAdoptMsg();
            JSONObject msgContent = new JSONObject();
            switch (uptApply.getApplyStatus()) {
                case "5":
                    //取消申请发送给领养申请人
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
                    //取消申请发送给送养人
                    msg.setSender(apply.getApplyBy());
                    msg.setReceiver(pet.getCreateBy());
                    msgService.crtMessage(msg);
                    log.info("正在进行取消申请操作：" + operateUserId);
                    if (operateUserId.equals(apply.getApplyBy())) {
                        msg.setSender(apply.getApplyBy());
                        msg.setReceiver(pet.getCreateBy());
                    } else {
                        msg.setSender(pet.getCreateBy());
                        msg.setReceiver(apply.getApplyBy());
                    }
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
                    msgService.crtMessage(msg);
                    break;
                case "4":
                    msg.setMsgTitle(MessageTemplate.APPLY_PASS_FORTH_ADOPTER_MSG_TITLE);
                    msgContent.put("portrait", user.getPortrait());
                    msgContent.put("nickName", user.getNickName());
                    msgContent.put("title", MessageTemplate.APPLY_PASS_FORTH_ADOPTER_MSG_CONTENT_TITLE);
                    msgContent.put("content", MessageTemplate.APPLY_PASS_FORTH_ADOPTER_MSG_CONTENT_LOG);
                    msgContent.put("petPic", pet.getMediaList().get(0).getMediaPath());
                    msgContent.put("petName", pet.getPetName());
                    msgContent.put("applyId", applyId);
                    msgContent.put("status", "4");
                    msg.setMsgContent(msgContent.toJSONString());
                    msg.setSender("SYS");
                    msg.setMsgType(2);
                    msg.setPetId(apply.getPetId());
                    msg.setReceiver(pet.getCreateBy());
                    msg.setCreateTime(new Date());
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
                    msg.setCreateTime(new Date());
                    msgService.crtMessage(msg);
                    break;
                default:
                    break;
            }
            log.info("发送模板消息请求参数：=======================》" + JSON.toJSONString(msg));
            this.sendTemplateMsg(applyUptUrl, msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Around("uploadCertificationPointCut()")
    public void aroundCrtCertificationPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>crt new certification start.....");
        CmsAdoptCertification certification = (CmsAdoptCertification) pjp.getArgs()[0];
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
            msg.setCreateTime(new Date());
            msgService.crtMessage(msg);
            //发送申请创建模板消息
            this.sendTemplateMsg(certificateUptUrl, msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Around("uptCertificationPointCut()")
    public void aroundUptCertificationPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>check certification start.....");
        CmsAdoptCertification certification = (CmsAdoptCertification) pjp.getArgs()[0];
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
            //发送申请更新模板消息
            this.sendTemplateMsg(certificateUptUrl, msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Around("crtGroupActivityRegisterPointCut()")
    public void aroundCrtGroupActivityRegisterPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>crt new activityRegister start.....");
        String userId = (String) pjp.getArgs()[0];
        String activityId = (String) pjp.getArgs()[1];
        String involvementTime = (String) pjp.getArgs()[2];
        CmsGroupActivity activity = groupActivityService.getGroupActivityInfo(activityId);
        try {
            pjp.proceed();
            CmsAdoptMsg msg = new CmsAdoptMsg();
            msg.setMsgTitle(MessageTemplate.ACTIVITY_REGISTER_SUCCESS);
            JSONObject msgContent = new JSONObject();
            msgContent.put("activityId", activity.getId());
            msgContent.put("activityTitle", activity.getActivityTitle());
            msgContent.put("activityAddress", activity.getActivityArea() + " " + activity.getActivityAddress());
            msgContent.put("involvementTime", involvementTime.substring(0, 19));
            msgContent.put("type", "REGISTER");
            msgContent.put("status", "0");
            msg.setMsgContent(msgContent.toJSONString());
            msg.setMsgType(3);
            msg.setPetId("");
            msg.setSender("SYS");
            msg.setReceiver(userId);
            msg.setCreateTime(new Date());
            msgService.crtMessage(msg);
            //发送申请创建模板消息
            this.sendTemplateMsg(activityNotificationUrl, msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Around("delGroupActivityRegisterPointCut()")
    public void aroundDelGroupActivityRegisterPointCut(ProceedingJoinPoint pjp) {
        log.info("{MessageAspect} =>crt new cancelActivityRegister start.....");
        String userId = (String) pjp.getArgs()[0];
        String activityId = (String) pjp.getArgs()[1];
        String memo = (String) pjp.getArgs()[2];
        CmsGroupActivity activity = groupActivityService.getGroupActivityInfo(activityId);
        CmsGroupActivityRegister activityRegister = groupActivityRegisterService.getGroupActivityRegisterListByUserId(userId, activityId);
        try {
            pjp.proceed();
            CmsAdoptMsg msg = new CmsAdoptMsg();
            msg.setMsgTitle(MessageTemplate.ACTIVITY_REGISTER_CANCEL);
            JSONObject msgContent = new JSONObject();
            msgContent.put("activityId", activity.getId());
            msgContent.put("activityTitle", activity.getActivityTitle());
            msgContent.put("activityAddress", activity.getActivityArea() + " " + activity.getActivityAddress());
            msgContent.put("involvementTime", activityRegister.getInvolvementTime().substring(0, 19));
            msgContent.put("memo", memo);
            msgContent.put("type", "CANCEL");
            msgContent.put("status", "0");
            msg.setMsgContent(msgContent.toJSONString());
            msg.setMsgType(3);
            msg.setPetId("");
            msg.setSender("SYS");
            msg.setReceiver(userId);
            msg.setCreateTime(new Date());
            msgService.crtMessage(msg);
            //发送申请创建模板消息
            this.sendTemplateMsg(activityNotificationUrl, msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    private void sendTemplateMsg(String sendUrl, CmsAdoptMsg msg) {
        try {
            log.info(HttpUtil.doPost(sendUrl, JSON.toJSONString(msg)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}