package com.linkpets.cms.sms;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 验证手机号码
     *
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147、182
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189、177
     *
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,1,2,5-9])|(177))\\d{8}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(cellphone);
        return matcher.matches();
    }
}
