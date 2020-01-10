package com.linkpets.wechat.resource;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.core.model.SysUser;
import com.linkpets.core.model.UserTemp;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.HttpClientUtils;
import com.linkpets.util.UUIDUtils;
import com.linkpets.util.wxutil.WXCore;
import com.linkpets.wechat.service.ISysUserService;
import com.linkpets.wechat.service.IUserTempService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(value = "微信小程序系统用户环境配置", tags = "微信环境集成接口")
@ResponseResult
@RestController
@RequestMapping("/wxmini/sysUser")
@Slf4j
public class WxMiniAuthorizeResource {


    @Value("${linkPet.chain.appId}")
    private String chainAppId;

    @Value("${linkPet.chain.appSecret}")
    private String chainAppSecret;

    @Value("${temporary.login.ticket.url}")
    private String codeTicketUrl;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IUserTempService userTempService;

    @Autowired
    private ISysUserService sysUserService;

    private static final int ACTIVE_SYS_USER = 1;


    @RequestMapping("/login")
    public PlatformResult login(@RequestParam(value = "code") String code) {
        Map<String, Object> map = new HashMap<>();
        String openId = "";
        String sessionKey = "";
        log.info("[通过code获取openId]");
        String requestCodeUrl = codeTicketUrl + "?appid=" + chainAppId + "&secret=" + chainAppSecret + "&js_code=" + code + "&grant_type=authorization_code";
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
                return PlatformResult.success(map);
            }

            //查询用户信息临时表
            UserTemp userTemp = userTempService.getTempUserByOpenId(openId);
            if (userTemp == null) {
                //注册平台用户
                UserTemp newUser = new UserTemp();
                String userTempId = UUIDUtils.randomUUID();
                newUser.setUserTempId(userTempId);
                newUser.setOpenId(openId);
                newUser.setCreateDate(new Date());
                userTempService.insertUserTemp(newUser);
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
                return PlatformResult.success(map);
            }
        }
    }


//    @RequestMapping("/checkIfSysUserAuthorized")
//    public Map<String, Object> checkIfUserAuthorized(@RequestParam("userId") String userId) {
//        Map<String, Object> map = new HashMap<>();
//        //find authorized user by userId
//        SysUser sysUser = sysUserService.getSysUserInfo(userId);
//        if (sysUser != null && StringUtil.isNotEmpty(sysUser.getUnionId()) && sysUser.getIsActive()==1) {
//            map.put("authorized", true);
//            map.put("userInfo", sysUser);
//        } else {
//            map.put("authorized", false);
//            map.put("userInfo", null);
//        }
//        return map;
//    }


//    @RequestMapping("/authorizeUser/{userId}")
//    public PlatformResult authorizeUserAndReturnUserInfo(@RequestBody JSONObject data, @PathVariable("userId") String userId) {
//        Map<String, Object> map = new HashMap<>();
//        String encryptedData = data.getString("encryptedData");
//        String iv = data.getString("iv");
//        String sessionKey = "";
//        try {
//            sessionKey = stringRedisTemplate.opsForValue().get(userId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (StringUtils.isEmpty(sessionKey)) {
//                sessionKey = data.getString("sessionKey");
//            }
//            String decryptData = WXCore.decrypt(chainAppId, encryptedData, sessionKey, iv);
//            if (StringUtil.isNotEmpty(decryptData)) {
//                JSONObject jsonObject = JSON.parseObject(decryptData);
//                String unionId = jsonObject.getString("unionId");
//                String nickName = jsonObject.getString("nickName");
//
//                //register authorized  user
//                SysUser user = sysUserService.getSysUserInfo(userId);
//                if (user == null) {
//                    user = new SysUser();
//                    user.setUserId(userId);
//                    user.setUserName(nickName);
//                    user.setRoleId(ROLE_ID);
//                    user.setUnionId(unionId);
//                    user.setIsActive(ACTIVE_SYS_USER);
//                    user.setCreateTime(new Date());
//                    user.setUpdateTime(new Date());
//                    sysUserService.insertSysUser(user);
//                }
//
//                map.put("userInfo", user);
//            } else {
//                map.put("userInfo", null);
//            }
//        }
//        return PlatformResult.success(map);
//    }


    @RequestMapping("/authorizeUserPhoneNumber/{userId}")
    public PlatformResult authorizeUserPhoneNumberAndReturnUserInfo(@RequestBody JSONObject data, @PathVariable("userId") String userId) {
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
            String decryptData = WXCore.decrypt(chainAppId, encryptedData, sessionKey, iv);
            if (StringUtil.isNotEmpty(decryptData)) {
                JSONObject jsonObject = JSON.parseObject(decryptData);
                String phoneNumber = jsonObject.getString("phoneNumber");
                //update authorized  user
                SysUser user = sysUserService.getSysUserInfo(userId);
                if (user != null) {
                    user.setUserName(phoneNumber);
                    sysUserService.updateSysUser(user);
                }

                map.put("userInfo", user);
            } else {
                map.put("userInfo", null);
            }
        }
        return PlatformResult.success(map);
    }

}
