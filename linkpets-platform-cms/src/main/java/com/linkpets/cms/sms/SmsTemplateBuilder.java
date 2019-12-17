package com.linkpets.cms.sms;

import lombok.Data;

@Data
public class SmsTemplateBuilder {
    /**
     * 接收短信的手机号码
     */
    private String phoneNumbers;
    /**
     * 短信签名名称
     */
    private String signName;
    /**
     * 短信模板ID
     */
    private String templateCode;
    /**
     * 主账号AccessKey的ID
     */
    private String accesskeyId;
    /**
     * 系统规定参数。取值：SendSms
     */
    private String action;
    /**
     * 外部流水扩展字段。
     */
    private String outId;
    /**
     * 上行短信扩展码
     */
    private String smsUpExtendCode;
    /**
     * 短信模板变量对应的实际值
     */
    private String templateParam;

    public SmsTemplateBuilder() {
    }

    public SmsTemplateBuilder(String phoneNumbers, String signName, String templateCode, String accesskeyId, String action, String outId, String smsUpExtendCode, String templateParam) {
        this.phoneNumbers = phoneNumbers;
        this.signName = signName;
        this.templateCode = templateCode;
        this.accesskeyId = accesskeyId;
        this.action = action;
        this.outId = outId;
        this.smsUpExtendCode = smsUpExtendCode;
        this.templateParam = templateParam;
    }
}
