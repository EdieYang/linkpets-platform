package com.linkpets.wechat.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.core.model.CmsAdoptMsg;
import com.linkpets.core.model.CmsUser;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.DateUtils;
import com.linkpets.util.HttpUtil;
import com.linkpets.wechat.model.KeyWordValue;
import com.linkpets.wechat.service.IUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/5/25
 */

@Api(value = "微信模板消息推送", tags = "微信模板消息")
@RestController
@RequestMapping("/templateMsg")
@Slf4j
public class WxTemplateMsgResource {

    @Value("${templateId.applyCreate.formId}")
    private String applyCreateTemplateId;

    @Value("${templateId.applyProcess.formId}")
    private String applyProcessTemplateId;

    @Value("${templateId.applyCancel.formId}")
    private String applyCancelTemplateId;

    @Value("${templateId.certificateStatus.formId}")
    private String certificateTemplateId;

    @Value("${templateId.adoptionCheck.formId}")
    private String adoptionCheckTemplateId;

    @Value("${templateId.chatMessage.formId}")
    private String chatTemplateId;


    @Value("${linkPet.appId}")
    private String appId;

    @Value("${linkPet.appSecret}")
    private String appSecret;


    @Autowired
    private IUserService userService;

    @PostMapping("applyUpt")
    public PlatformResult sendApplyTemplate(@RequestBody String msgData) {
        CmsAdoptMsg cmsAdoptMsg = JSONObject.parseObject(msgData, CmsAdoptMsg.class);
        String msgContent = cmsAdoptMsg.getMsgContent();
        JSONObject msgJson = JSONObject.parseObject(msgContent);
        String applyStatus = msgJson.getString("status");
        String applyId = msgJson.getString("applyId");
        String petName = msgJson.getString("petName");

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
            Map<String, String> map = new HashMap<>();
            switch (applyStatus) {
                //申请领养通知
                case "0":
                    templateId = applyCreateTemplateId;

                    map.put("value", msgJson.getString("nickName") + "发来领养申请");
                    templateData.put("keyword1", map);
                    map.put("value", "点击此处查看领养申请详情");
                    templateData.put("keyword2", map);
                    break;
                case "1":
                    templateId = applyProcessTemplateId;
                    map.put("value", "恭喜您！初审已通过");
                    templateData.put("keyword1", map);
                    map.put("value", "您发出对" + petName + "的领养申请已经初步通过。");
                    templateData.put("keyword2", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword3", map);
                    map.put("value", "请耐心等待送养人的终审，终审通过后您会收到领养协议。");
                    templateData.put("keyword4", map);
                    break;
                case "2":
                    templateId = applyProcessTemplateId;
                    map.put("value", "恭喜您！终审已通过");
                    templateData.put("keyword1", map);
                    map.put("value", "送养人" + "给您发了份领养协议，请及时填写");
                    templateData.put("keyword2", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword3", map);
                    map.put("value", "请确认协议条款，签上自己的名字，并发送给送养人进行签字");
                    templateData.put("keyword4", map);
                    break;
                case "3":
                    templateId = applyProcessTemplateId;
                    map.put("value", "您有一份领养协议待签署");
                    templateData.put("keyword1", map);
                    map.put("value", "领养人" + msgJson.getString("nickName") + "已在领养协议中签字");
                    templateData.put("keyword2", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword3", map);
                    map.put("value", "请确认领养人信息正确性，并签上自己的名字，使协议最终生效");
                    templateData.put("keyword4", map);
                    break;
                case "4":
                    templateId = applyProcessTemplateId;
                    map.put("value", "恭喜您！" + petName + "的领养协议已生效!");
                    templateData.put("keyword1", map);
                    map.put("value", "恭喜您成功领养,快去线下接回你的新主子吧!");
                    templateData.put("keyword2", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword3", map);
                    map.put("value", "协议内容已具备法律效应,身份信息已经备案,请双方遵守协议内容,以免承担相应的法律责任");
                    templateData.put("keyword4", map);
                    break;
                case "5":
                    templateId = applyCancelTemplateId;
                    map.put("value", "领养人取消了对" + petName + "的领养申请，点击查看原因");
                    templateData.put("keyword1", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword2", map);
                    break;
                default:
                    break;
            }

            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/mine/applyreceivedetail/applyreceivedetail?share=1&applyId=" + applyId);
            templateForm.put("", cmsAdoptMsg.getFormId());
            templateForm.put("data", templateData);
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken, templateForm.toJSONString());
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
            Map<String, String> map = new HashMap<>();
            switch (status) {
                //申请领养通知
                case "0":
                    templateId = certificateTemplateId;

                    map.put("value", "平台将在1-3个工作日内审核您的实名认证信息");
                    templateData.put("keyword1", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword2", map);
                    map.put("value", "认证中");
                    templateData.put("keyword3", map);
                    break;
                case "1":
                    templateId = certificateTemplateId;
                    map.put("value", "恭喜您！实名认证信息已通过");
                    templateData.put("keyword1", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword2", map);
                    map.put("value", "认证成功");
                    templateData.put("keyword3", map);

                    break;
                case "2":
                    templateId = certificateTemplateId;
                    map.put("value", "很抱歉！您的实名认证信息未通过审核。");
                    templateData.put("keyword1", map);
                    map.put("value", DateUtils.getFormatDateStr(new Date()));
                    templateData.put("keyword2", map);
                    map.put("value", "认证失败");
                    templateData.put("keyword3", map);

                    break;
                default:
                    break;
            }

            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/mine/identify/identify?share=1");
            templateForm.put("form_id", cmsAdoptMsg.getFormId());
            templateForm.put("data", templateData);
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken, templateForm.toJSONString());
            log.info(sendTemplateMsg);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return PlatformResult.success(sendTemplateMsg);
    }



    @PostMapping("adoptionUpt")
    public PlatformResult sendAdoptionTemplate(@RequestBody String msgData) {
        log.info("adoptionUpt:"+msgData);
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

                    templateData.put("keyword1", new KeyWordValue(msgJson.getString("petName")+"的送养申请已创建，等待审核中").toJson());
                    templateData.put("keyword2", new KeyWordValue("平台将在1-3个工作日内对送养申请进行审核,请耐心等待").toJson());
                    templateData.put("keyword3", new KeyWordValue("-").toJson());
                    break;
                case "1":
                    templateId = adoptionCheckTemplateId;
                    templateData.put("keyword1", new KeyWordValue("很抱歉，"+msgJson.getString("petName")+"的送养申请未通过").toJson());
                    templateData.put("keyword2", new KeyWordValue("点击此处查看未通过原因").toJson());
                    templateData.put("keyword3", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    break;
                case "2":
                    templateId = adoptionCheckTemplateId;
                    templateData.put("keyword1", new KeyWordValue( "恭喜您！"+msgJson.getString("petName")+"的送养申请已通过上线").toJson());
                    templateData.put("keyword2", new KeyWordValue("点击查看送养详情").toJson());
                    templateData.put("keyword3", new KeyWordValue(DateUtils.getFormatDateStr(new Date())).toJson());
                    break;
                default:
                    break;
            }
            log.info("formId=>>>>"+cmsAdoptMsg.getFormId());
            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/adoption/detail/detail?scene="+cmsAdoptMsg.getPetId());
            templateForm.put("form_id", cmsAdoptMsg.getFormId());
            templateForm.put("data", templateData);
            log.info("发送模板消息请求报文："+templateForm.toJSONString());
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken, templateForm.toJSONString());
            log.info(sendTemplateMsg);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return PlatformResult.success(sendTemplateMsg);
    }

    @PostMapping("chatUpt")
    public PlatformResult sendChatMessageTemplate(@RequestBody String msgData) {
        JSONObject chatMsg = JSONObject.parseObject(msgData);
        String targetUserId=chatMsg.getString("targetUserId");
        CmsUser targetUser = userService.getUserInfo(targetUserId);
        String userId=chatMsg.getString("userId");
        CmsUser user=userService.getUserInfo(userId);

        String openId = targetUser.getOpenid();
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
            templateId = chatTemplateId;

            map.put("value", user.getNickName()+"给你留了一条信息，点击查看详情");
            templateData.put("keyword1", map);
            map.put("value", DateUtils.getFormatDateStr(new Date()));
            templateData.put("keyword2", map);
            templateForm.put("touser", openId);
            templateForm.put("template_id", templateId);
            templateForm.put("page", "pages/msg/index/index");
            templateForm.put("form_id", chatMsg.getString("formId"));
            templateForm.put("data", templateData);
            sendTemplateMsg = HttpUtil.doPost("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken, templateForm.toJSONString());
            log.info(sendTemplateMsg);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return PlatformResult.success(sendTemplateMsg);
    }





}
