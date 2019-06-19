package com.linkpets.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author SteveYang
 * @date 2019/3/16
 */
public class ResponseCodeFactory {

    private ResponseCode responseCode;

    public ResponseCodeFactory(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public final JSONObject getResponseCode(){
        JSONObject responseJb=new JSONObject();
        responseJb.put("code",this.responseCode.getResponseCode());
        responseJb.put("errorMsg",this.responseCode.getResponseMsg());
        return responseJb;
    }
}
