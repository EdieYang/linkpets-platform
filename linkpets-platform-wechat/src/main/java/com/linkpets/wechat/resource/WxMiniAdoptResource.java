package com.linkpets.wechat.resource;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.HttpClientUtils;
import com.linkpets.util.HttpUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "领养小程序服务API资源接口",tags = "领养小程序服务API资源接口")
@RestController
@RequestMapping("/miniSystemApi")
@Slf4j
public class WxMiniAdoptResource {

    @Value("${linkPet.appId}")
    private String appId;

    @Value("${linkPet.appSecret}")
    private String appSecret;

    /**
     * 生成小程序分享码
     * @param scene
     * @param path
     * @return
     */
    @RequestMapping(value = "/generateWxACode", method = RequestMethod.GET)
    public PlatformResult generateWxACode(@RequestParam(value = "scene",required = false) String scene, @RequestParam("path") String path) {
        Map<String, Object> map = new HashMap<>();
        String reqUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String res = HttpClientUtils.httpGet(reqUrl);
        JSONObject resJ = JSON.parseObject(res);
        String accessToken = resJ.getString("access_token");

        map.put("path", path+"?scene=" + scene);
        map.put("auto_color", false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("r", "255");
        jsonObject.put("g", "255");
        jsonObject.put("b", "255");

        map.put("line_color", jsonObject);
        map.put("is_hyaline", true);
        String imageUrl = HttpUtil.postInputStream("https://api.weixin.qq.com/wxa/getwxacode?access_token=" + accessToken, map);
        return PlatformResult.success(imageUrl);
    }
}
