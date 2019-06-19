package com.linkpets.wechat.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.core.dao.UserMapper;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.User;
import com.linkpets.util.HttpClientUtils;
import com.linkpets.util.HttpUtil;
import com.linkpets.util.wxConfig.SHA1;
import com.linkpets.util.wxConfig.Token;
import com.linkpets.util.wxpay.RandomStringGenerator;
import com.linkpets.wechat.dao.CmsUserCustomMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Fade on 2018/11/6.
 */

@Api(value = "微信环境配置", tags = "微信环境集成接口")
@ResponseResult
@RestController
@RequestMapping("/wxAuth")
public class WxAuthorizeResource {


    @Value("${linkPet.appId}")
    private String appId;

    @Value("${linkPet.appSecret}")
    private String appSecret;

    @Value("${wx.service.appId}")
    private String s_appId;

    @Value("${wx.service.secret}")
    private String s_secret;

    @Value("${wx.subscribe.appId}")
    private String sub_appId;

    @Value("${wx.subscribe.secret}")
    private String sub_secret;


    @Resource
    private CmsUserCustomMapper userCustomMapper;

    @Resource
    private UserMapper userMapper;


    @ApiOperation(value = "获取code回调地址", notes = "此接口默认选择snsapi_userinfo的应用授权域")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "授权回调url", name = "url", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "微信平台 'MP_WX':邻宠服务号 ,'SP_WX':邻宠订阅号", name = "plateForm", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/redirectCodeUrl")
    public JSONObject getCodeReturnUrl(@RequestParam("url") String url,
                                       @RequestParam("plateForm") String plateForm) {
        JSONObject result = new JSONObject();
        String appId = "";
        switch (plateForm) {
            case "MP_WX":
                appId = s_appId;
                break;
            case "SP_WX":
                appId = sub_appId;
                break;
        }
        String redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
                appId + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

        result.put("data", redirectUrl);
        return result;
    }


    @ApiOperation(value = "用户授权接口", notes = "此接口通过code获取scope为snsapi_userinfo的授权信息,统一返回用户userId")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户同意授权，获取的code", name = "code", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "微信平台 'MP_WX':邻宠服务号 ,'SP_WX':邻宠订阅号", name = "plateForm", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/authUserInfo")
    public JSONObject authUserInfo(@RequestParam("code") String code, @RequestParam("plateForm") String plateForm) {
        JSONObject result = new JSONObject();
        //获取accessToken & openId
        String secret = "";
        String appId = "";
        switch (plateForm) {
            case "MP_WX":
                appId = s_appId;
                secret = s_secret;
                break;
            case "SP_WX":
                appId = sub_appId;
                secret = sub_secret;
                break;
        }
        if (StringUtils.isEmpty(appId) && StringUtils.isEmpty(secret)) {
            result.put("data", "appId不存在");
            return result;
        }
        String reqUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
        String res = HttpClientUtils.httpGet(reqUrl);
        JSONObject resJ = JSON.parseObject(res);
        String accessToken = resJ.getString("access_token");
        String openId = resJ.getString("openid");

        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openId)) {
            result.put("data", "");
            result.put("errcode", resJ.getString("errcode"));
            result.put("errmsg", resJ.getString("errmsg"));
            return result;
        }

        //获取unionId
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        String resUnionId = HttpClientUtils.httpGet(requestUrl);
        JSONObject resUnion = JSON.parseObject(resUnionId);
        String unionId = resUnion.getString("unionid");


        //查询用户是否存在
        CmsUser user = userCustomMapper.getUserByUnionId(unionId);
        if (user == null) {
            //创建新用户
            String nickName = resUnion.getString("nickname");
            String portrait = resUnion.getString("headimgurl");

            User newUser = new User();
            String userId = UUID.randomUUID().toString();
            newUser.setCreateDatetime(new Date());
            newUser.setUserId(userId);
            newUser.setNickName(nickName);
            newUser.setPhotoPath(portrait);
            newUser.setUnionId(unionId);
            userMapper.insertSelective(newUser);
            result.put("data", userId);
        } else if (user != null) {
            result.put("data", user.getUserId());
        }

        return result;

    }

    @ApiOperation(value = "网页开发调用JS-SDK配置参数接口", notes = "此接口获取JS-SDK配置信息用于调用微信网页开发接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前网页URL", name = "url", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "微信平台 'MP_WX':邻宠服务号 ,'SP_WX':邻宠订阅号", name = "plateForm", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping(value = "/configWxEnvironment")
    public Map<String, Object> configWxEv(@RequestParam("url") String url, @RequestParam("plateForm") String plateForm) {
        Map<String, Object> map = new HashMap<>();
        String appId = "";
        switch (plateForm) {
            case "MP_WX":
                appId = s_appId;
                break;
            case "SP_WX":
                appId = sub_appId;
                break;
        }
        String ticket = Token.getTicket();
        String noncestr = RandomStringGenerator.getRandomStringByLength(32);
        long timestamp = System.currentTimeMillis() / 1000;
        String sign = "jsapi_ticket=" + ticket + "&noncestr="
                + noncestr + "&timestamp=" + timestamp
                + "&url=" + url;//请勿更换字符组装顺序
        try {
            String signature = new SHA1().getDigestOfString(sign.getBytes("utf-8"));
            map.put("appId", appId);
            map.put("timestamp", timestamp);
            map.put("nonceStr", noncestr);
            map.put("signature", signature);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return map;
    }


    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        String reqUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb716f1f37b7ef914&secret=1808ec52bc34d929810794f7ee0368bb";
        String res = HttpClientUtils.httpGet(reqUrl);
        JSONObject resJ = JSON.parseObject(res);
        String accessToken = resJ.getString("access_token");
        map.put("scene", "springFesDinner");
        map.put("page", "pages/activity/activityentrance/activityentrance");
        map.put("width", 100);
        map.put("is_hyaline", false);
        String imageUrl = HttpUtil.postInputStream("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken, map);
        System.out.println("二维码生成:" + imageUrl);
    }


}
