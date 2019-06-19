package com.linkpets.util;


/**
 * @author SteveYang
 * @date 2019/3/16
 */
public enum ResponseCode {

    INVALID_ACCOUNT("10001", "账号不存在"),
    PASSIVE_ACCOUNT("10002","账号已被停用"),
    PASSWORD_WRONG("10003", "密码错误");

    private String responseCode;
    private String responseMsg;


    ResponseCode(String responseCode, String responseMsg) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }
}
