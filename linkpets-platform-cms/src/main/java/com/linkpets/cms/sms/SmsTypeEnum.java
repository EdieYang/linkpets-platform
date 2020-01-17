package com.linkpets.cms.sms;

/**
 * @author Gaga
 */

public enum SmsTypeEnum {

    USER_AUTHORIZE("实名认证验证码", "邻宠", "SMS_180352151");

    private String smsTemplateName;
    private String signName;
    private String templateCode;

    SmsTypeEnum() {
    }

    SmsTypeEnum(String smsTemplateName, String signName, String templateCode) {
        this.smsTemplateName = smsTemplateName;
        this.signName = signName;
        this.templateCode = templateCode;
    }

    public String getSmsTemplateName() {
        return smsTemplateName;
    }

    public void setSmsTemplateName(String smsTemplateName) {
        this.smsTemplateName = smsTemplateName;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
