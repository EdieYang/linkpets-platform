package com.linkpets.wechat.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class KeyWordValue {

    private String valueInfo;

    public KeyWordValue( String valueInfo) {
        this.valueInfo = valueInfo;
    }

    public JSONObject toJson(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("value",valueInfo);
        return jsonObject;
    }
}
