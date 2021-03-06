package com.linkpets.cms.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


import java.util.HashMap;
import java.util.Map;

public class SmsSendHandler {

    public static boolean sendSms(SmsTemplateBuilder smsTemplateBuilder) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FvxeNqnQGoUXMp3wtVD", "09lQAHCswKBZ1Wqi0z0MciNoejbrdE");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", smsTemplateBuilder.getPhoneNumbers());
        request.putQueryParameter("SignName", smsTemplateBuilder.getSignName());
        request.putQueryParameter("TemplateCode", smsTemplateBuilder.getTemplateCode());
        JSONObject codeJson = new JSONObject();
        codeJson.put("code", smsTemplateBuilder.getTemplateParam());
        request.putQueryParameter("TemplateParam", codeJson.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            JSONObject respJson = JSON.parseObject(response.getData());
            if (respJson.getString("Code").equals("OK")) {
                return true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SmsTemplateBuilder smsTemplateBuilder = new SmsTemplateBuilder();
        smsTemplateBuilder.setPhoneNumbers("18217570589");
        smsTemplateBuilder.setSignName("邻宠");
        smsTemplateBuilder.setTemplateCode("SMS_180352151");
        JSONObject codeJson = new JSONObject();
        codeJson.put("code", "123444");
        smsTemplateBuilder.setTemplateParam(codeJson.toString());
    }

}
