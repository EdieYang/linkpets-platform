package com.linkpets.wechat.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.linkpets.core.model.CmsUser;
import com.linkpets.core.model.UserTemp;
import com.linkpets.enums.DefaultSettingCode;
import com.linkpets.util.HttpClientUtils;
import com.linkpets.util.UUIDUtils;
import com.linkpets.util.wxutil.WXCore;
import com.linkpets.wechat.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "领养小程序授权接口",tags = "linkpets-adoption")
@RestController
@RequestMapping("/miniSystem")
@Slf4j
public class WxMiniAdoptAuthorizeResource {


    @Value("${temporary.login.ticket.url}")
    private String codeTicketUrl;

    @Value("${linkPet.appId}")
    private String appId;

    @Value("${linkPet.appSecret}")
    private String appSecret;

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @ApiOperation("登录小程序获取userId")
    @RequestMapping("/login")
    public Map<String, Object> login(@RequestParam(value = "code") String code, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String openId = "";
        String sessionKey = "";
        log.info("[通过code获取openId]");
        String requestCodeUrl = codeTicketUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        String response = HttpClientUtils.httpGet(requestCodeUrl);
        try {
            JSONObject resJsonObj = JSON.parseObject(response);
            openId = resJsonObj.getString("openid");
            sessionKey = resJsonObj.getString("session_key");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("{openId}===>" + openId);
        } finally {
            if (StringUtils.isEmpty(openId)) {
                map.put("userId", "");
                map.put("sessionKey", sessionKey);
                return map;
            }

            //查询临时用户
            UserTemp userTemp = userService.getTempUserByOpenId(openId);
            if (userTemp == null) {
                //注册平台用户
                UserTemp newUser = new UserTemp();
                String userTempId = UUIDUtils.randomUUID();
                newUser.setUserTempId(userTempId);
                newUser.setOpenId(openId);
                newUser.setCreateTime(new Date());
                newUser.setDelFlag("1");
                userService.insertUserTemp(newUser);
                map.put("userId", userTempId);

            } else {
                map.put("userId", userTemp.getUserTempId());
            }
            map.put("sessionKey", sessionKey);
            map.put("openId", openId);
            try {
                //保存sessionKey和userId
                stringRedisTemplate.opsForValue().set(map.get("userId").toString(), sessionKey);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("redis error:" + e.getMessage());
                //调度中心解决connection timeout 问题

            } finally {
                return map;
            }
        }
    }

    @ApiOperation(value = "用户授权")
    @RequestMapping("/checkIfUserAuthorized")
    public Map<String, Object> checkIfUserAuthorized(@RequestParam("userId") String userId) {
        Map<String, Object> map = new HashMap<>();
        //find authorized user by userId
        CmsUser user = userService.getUserInfo(userId);
        if (user != null && StringUtil.isNotEmpty(user.getUnionId())) {
            map.put("authorized", true);
            map.put("userInfo", user);
        } else {
            map.put("authorized", false);
            map.put("userInfo", null);
        }
        return map;
    }

    @ApiOperation(value = "用户授权登录接口")
    @RequestMapping("/authorizeUser/{userId}")
    public Map<String, Object> authorizeUserAndReturnUserInfo(@RequestBody JSONObject data, @PathVariable("userId") String userId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String encryptedData = data.getString("encryptedData");
        String iv = data.getString("iv");
        String sessionKey = "";
        try {
            sessionKey = stringRedisTemplate.opsForValue().get(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (StringUtils.isEmpty(sessionKey)) {
                sessionKey = data.getString("sessionKey");
            }
            String decryptData = WXCore.decrypt(appId, encryptedData, sessionKey, iv);
            if (StringUtil.isNotEmpty(decryptData)) {
                JSONObject jsonObject = JSON.parseObject(decryptData);
                String unionId = jsonObject.getString("unionId");
                String nickName = jsonObject.getString("nickName");
                String avatarUrl = jsonObject.getString("avatarUrl");
                String openId =userService.getOpenIdByUserId(userId);

                //register authorized  user
                CmsUser user = new CmsUser();
                user.setUserId(userId);
                user.setNickName(nickName);
                user.setPortrait(avatarUrl);
                user.setUnionId(unionId);
                user.setOpenid(openId);
                user.setCreateDate(new Date());
                user.setIsValid(DefaultSettingCode.getCode("DEFAULT_FLAG"));

                //获取正式用户记录
                CmsUser userExist = userService.getUserByUnionId(unionId);
                if (userExist != null) {
                    userId = userExist.getUserId();
                    user.setUserId(userId);
                    int flag = userService.modifyUser(user);
                    if (flag > 0) {
                        CmsUser userInfo = userService.getUserInfo(userId);
                        map.put("userInfo", userInfo);
                        map.put("authorized", true);
                        return map;
                    }
                } else {
                    boolean saveAuthorization = userService.insertUser(user);
                    if (saveAuthorization) {
                        CmsUser userInfo = userService.getUserInfo(userId);
                        map.put("userInfo", userInfo);
                        map.put("authorized", true);
                        return map;
                    }
                }

            }
            map.put("userInfo", null);
            map.put("authorized", false);
            return map;
        }

    }


}
