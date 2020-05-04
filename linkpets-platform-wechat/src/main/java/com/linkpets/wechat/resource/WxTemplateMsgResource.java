package com.linkpets.wechat.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.dao.CmsAdoptFormidMapper;
import com.linkpets.core.model.CmsAdoptFormid;
import com.linkpets.core.model.CmsAdoptMsg;
import com.linkpets.core.model.CmsUser;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.DateUtils;
import com.linkpets.util.HttpUtil;
import com.linkpets.wechat.model.KeyWordValue;
import com.linkpets.wechat.service.IUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/5/25
 */

@Api(value = "微信小程序模板消息推送")
@RestController
@RequestMapping("/templateMsg")
@Slf4j
public class WxTemplateMsgResource {

    @Value("${templateId.applyCreate.templateId}")
    private String applyCreateTemplateId;

    @Value("${templateId.applyProcess.templateId}")
    private String applyProcessTemplateId;

    @Value("${templateId.agreementProcess.templateId}")
    private String agreementProcessTemplateId;

    @Value("${templateId.certificateStatus.templateId}")
    private String certificateTemplateId;

    @Value("${templateId.adoptionCheck.templateId}")
    private String adoptionCheckTemplateId;

    @Value("${templateId.activityRegister.templateId}")
    private String activityRegisterTemplateId;

    @Value("${templateId.activityRemind.templateId}")
    private String activityRemindTemplateId;

    @Value("${templateId.activityCancel.templateId}")
    private String activityCancelTemplateId;

    @Value("${linkPet.appId}")
    private String appId;

    @Value("${linkPet.appSecret}")
    private String appSecret;


    @Autowired
    private IUserService userService;

    @Resource
    private CmsAdoptFormidMapper cmsAdoptFormidMapper;

    @PostMapping("applyUpt")
    public PlatformResult sendApplyTemplate(@RequestBody String msgData) {
        CmsAdoptMsg cmsAdoptMsg = JSONObject.parseObject(msgData, CmsAdoptMsg.class);
        String msgContent = cmsAdoptMsg.getMsgContent();
        JSONObject msgJson = JSONObject.parseObject(msgContent);
        String applyStatus = msgJson.getString("status");
        String applyId = msgJson.getString("applyId");
        String petName = msgJson.getString("petName");
        CmsUser user = userService.getUserInfo(cmsAdoptMsg.getReceiver());
        String openId = user.getOpenid();
        String templateId = "";
        JSONObject templateForm = new JSONObject();
        JSONObject templateData = new JSONObject();
        String sendTemplateMsg = "";
        //获取accessToken
        try {
            String response = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, "");
            JSONObject resJsonObj = JSON.parseObject(response);
            String accessToken = resJsonObj.getString("access_token");
            Map<String, String> map = new HashMap<>();
            switch (applyStatus) {
                //申请领养通知
                case "0":
                    templateId = applyCreateTemplateId;
                    templateData.put("thing1", new KeyWordValue(petName + "有一条新的领养申请").toJson());
                    templateData.put("date2", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing3", new KeyWordValue("点击此处查看领养申请详情").toJson());
                    break;
                case "1":
                    templateId = applyProcessTemplateId;
                    templateData.put("phrase3", new KeyWordValue("初审已通过").toJson());
                    templateData.put("thing1", new KeyWordValue(petName).toJson());
                    templateData.put("date2", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing4", new KeyWordValue("等待送养人终审，通过后您会收到领养协议").toJson());
                    break;
                case "2":
                    templateId = applyProcessTemplateId;
                    templateData.put("phrase3", new KeyWordValue("终审已通过").toJson());
                    templateData.put("thing1", new KeyWordValue(petName).toJson());
                    templateData.put("date2", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing4", new KeyWordValue("您有一份领养协议待签署,点击完善协议内容").toJson());
                    break;
                case "3":
                    templateId = agreementProcessTemplateId;
                    templateData.put("thing1", new KeyWordValue("您有一份领养协议待签署").toJson());
                    templateData.put("thing4", new KeyWordValue("请确认领养人信息正确性，一旦签署无法修改").toJson());
                    templateData.put("time2", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    break;
                case "4":
                    templateId = agreementProcessTemplateId;
                    templateData.put("thing1", new KeyWordValue(petName + "的领养协议已生效").toJson());
                    templateData.put("thing4", new KeyWordValue("协议内容已具备法律效应,身份信息已经备案").toJson());
                    templateData.put("time2", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    break;
                case "5":
                    templateId = applyProcessTemplateId;
                    templateData.put("phrase3", new KeyWordValue("已取消").toJson());
                    templateData.put("thing1", new KeyWordValue(petName).toJson());
                    templateData.put("date2", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing4", new KeyWordValue("领养申请已取消，点击查看原因").toJson());
                    break;
                default:
                    break;
            }
            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/mine/receiveapplydetail/receiveapplydetail?scene=" + applyId);
            templateForm.put("data", templateData);
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, templateForm.toJSONString());
            log.info(sendTemplateMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PlatformResult.success(sendTemplateMsg);
    }


    @PostMapping("certificateUpt")
    public PlatformResult sendCertificationTemplate(@RequestBody String msgData) {
        CmsAdoptMsg cmsAdoptMsg = JSONObject.parseObject(msgData, CmsAdoptMsg.class);
        String msgContent = cmsAdoptMsg.getMsgContent();
        JSONObject msgJson = JSONObject.parseObject(msgContent);
        String status = msgJson.getString("status");
        String receiver = cmsAdoptMsg.getReceiver();
        CmsUser user = userService.getUserInfo(receiver);
        String openId = user.getOpenid();
        String templateId = "";
        JSONObject templateForm = new JSONObject();
        JSONObject templateData = new JSONObject();
        String sendTemplateMsg = "";
        //获取accessToken
        try {
            String response = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, "");
            JSONObject resJsonObj = JSON.parseObject(response);
            String accessToken = resJsonObj.getString("access_token");
            switch (status) {
                //实名认证通知
                case "0":
                    templateId = certificateTemplateId;
                    templateData.put("phrase1", new KeyWordValue("认证中"));
                    templateData.put("thing2", new KeyWordValue("平台实名认证").toJson());
                    templateData.put("date3", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    break;
                case "1":
                    templateId = certificateTemplateId;
                    templateData.put("phrase1", new KeyWordValue("认证已通过").toJson());
                    templateData.put("thing2", new KeyWordValue("平台实名认证").toJson());
                    templateData.put("date3", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    break;
                case "2":
                    templateId = certificateTemplateId;
                    templateData.put("phrase1", new KeyWordValue("认证失败").toJson());
                    templateData.put("thing2", new KeyWordValue("平台实名认证").toJson());
                    templateData.put("date3", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    break;
                default:
                    break;
            }

            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/mine/identify/identify?scene=1");
            templateForm.put("data", templateData);
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, templateForm.toJSONString());
            log.info(sendTemplateMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PlatformResult.success(sendTemplateMsg);
    }


    @PostMapping("adoptionUpt")
    public PlatformResult sendAdoptionTemplate(@RequestBody String msgData) {
        log.info("adoptionUpt:" + msgData);
        CmsAdoptMsg cmsAdoptMsg = JSONObject.parseObject(msgData, CmsAdoptMsg.class);
        String msgContent = cmsAdoptMsg.getMsgContent();
        JSONObject msgJson = JSONObject.parseObject(msgContent);
        String status = msgJson.getString("status");
        String receiver = cmsAdoptMsg.getReceiver();
        CmsUser user = userService.getUserInfo(receiver);
        String openId = user.getOpenid();
        String templateId = "";
        JSONObject templateForm = new JSONObject();
        JSONObject templateData = new JSONObject();
        String sendTemplateMsg = "";
        //获取accessToken
        try {
            String response = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, "");
            JSONObject resJsonObj = JSON.parseObject(response);
            String accessToken = resJsonObj.getString("access_token");
            switch (status) {
                //申请领养通知
                case "0":
                    templateId = adoptionCheckTemplateId;
                    templateData.put("phrase1", new KeyWordValue("待审核").toJson());
                    templateData.put("thing5", new KeyWordValue(msgJson.getString("petName") + "的送养申请已创建，等待审核中").toJson());
                    templateData.put("date4", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing8", new KeyWordValue("平台将在1个工作日内进行审核,请耐心等待").toJson());
                    break;
                case "1":
                    templateId = adoptionCheckTemplateId;
                    templateData.put("phrase1", new KeyWordValue("未通过").toJson());
                    templateData.put("thing5", new KeyWordValue("很抱歉，" + msgJson.getString("petName") + "的送养申请未通过").toJson());
                    templateData.put("date4", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing8", new KeyWordValue("点击此处查看未通过原因").toJson());
                    break;
                case "2":
                    templateId = adoptionCheckTemplateId;
                    templateData.put("phrase1", new KeyWordValue("已通过").toJson());
                    templateData.put("thing5", new KeyWordValue("恭喜您！" + msgJson.getString("petName") + "的送养申请已通过上线").toJson());
                    templateData.put("date4", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing8", new KeyWordValue("点击查看送养详情").toJson());
                    break;
                default:
                    break;
            }
            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/adoption/detail/detail?scene=" + cmsAdoptMsg.getPetId());
            templateForm.put("data", templateData);
            log.info("发送模板消息请求报文：" + templateForm.toJSONString());
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, templateForm.toJSONString());
            log.info(sendTemplateMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PlatformResult.success(sendTemplateMsg);
    }


    @PostMapping("activityNotification")
    public PlatformResult sendActivityNotificationTemplate(@RequestBody String msgData) {
        log.info("activityNotification:" + msgData);
        CmsAdoptMsg cmsAdoptMsg = JSONObject.parseObject(msgData, CmsAdoptMsg.class);
        String msgContent = cmsAdoptMsg.getMsgContent();
        JSONObject msgJson = JSONObject.parseObject(msgContent);
        String type = msgJson.getString("type");
        String receiver = cmsAdoptMsg.getReceiver();
        String activityId = msgJson.getString("activityId");
        String activityTitle = msgJson.getString("activityTitle");
        if (StringUtils.isNotEmpty(activityTitle)) {
            activityTitle = activityTitle.length() > 20 ? activityTitle.substring(0, 19) : activityTitle;
        }
        String activityAddress = msgJson.getString("activityAddress");
        if (StringUtils.isNotEmpty(activityAddress)) {
            activityAddress = activityAddress.length() > 20 ? activityAddress.substring(0, 19) : activityAddress;
        }
        String involvementTime = msgJson.getString("involvementTime");
        String cancelMemo=msgJson.getString("memo");
        CmsUser user = userService.getUserInfo(receiver);
        String openId = user.getOpenid();
        String templateId = "";
        JSONObject templateForm = new JSONObject();
        JSONObject templateData = new JSONObject();
        String sendTemplateMsg = "";
        //获取accessToken
        try {
            String response = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, "");
            JSONObject resJsonObj = JSON.parseObject(response);
            String accessToken = resJsonObj.getString("access_token");
            switch (type) {
                //申请领养通知
                case "REGISTER":
                    templateId = activityRegisterTemplateId;
                    templateData.put("thing1", new KeyWordValue(activityTitle).toJson());
                    templateData.put("thing2", new KeyWordValue(activityAddress).toJson());
                    templateData.put("date3", new KeyWordValue(involvementTime).toJson());
                    templateData.put("thing6", new KeyWordValue("点击查看详情联系客服").toJson());
                    break;
                case "CANCEL":
                    templateId = activityCancelTemplateId;
                    templateData.put("thing1", new KeyWordValue(activityTitle).toJson());
                    templateData.put("date2", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    templateData.put("thing4", new KeyWordValue(cancelMemo).toJson());
                    break;
                case "REMIND":
                    templateId = activityRemindTemplateId;
                    templateData.put("thing1", new KeyWordValue(activityTitle).toJson());
                    templateData.put("date2", new KeyWordValue(involvementTime).toJson());
                    templateData.put("thing8", new KeyWordValue(activityAddress).toJson());
                    templateData.put("thing7", new KeyWordValue("若当天无法参加请提前联系客服").toJson());
                    break;
                default:
                    break;
            }
            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/circle/activityDetail/index?scene=" + activityId);
            templateForm.put("data", templateData);
            log.info("发送模板消息请求报文：" + templateForm.toJSONString());
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, templateForm.toJSONString());
            log.info(sendTemplateMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PlatformResult.success(sendTemplateMsg);
    }
}
