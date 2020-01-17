package com.linkpets.cms.sms;


import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.ICertificationService;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Api(value = "短信模块-短信发送接口", tags = "短信模块-短信发送接口")
@ResponseResult
@RestController
@RequestMapping("/sms")
public class SmsResource {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("获取短信验证码")
    @GetMapping("verifyCode")
    public PlatformResult sendVerifyCode(@RequestParam("mobilePhone") String mobilePhone,
                                         @RequestParam("userId") String userId) {
        SmsTemplateBuilder templateBuilder = new SmsTemplateBuilder();
        if (mobilePhone.length() != 11 || !SmsTemplateBuilder.checkCellphone(mobilePhone)) {
            return PlatformResult.failure(ResultCode.MOBILE_PHONE_INVALID);
        }
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        stringRedisTemplate.opsForValue().set("verifyCode." + userId + "." + mobilePhone, code, 60 * 10, TimeUnit.SECONDS);
        templateBuilder.setPhoneNumbers(mobilePhone);
        templateBuilder.setSignName(SmsTypeEnum.USER_AUTHORIZE.getSignName());
        templateBuilder.setTemplateCode(SmsTypeEnum.USER_AUTHORIZE.getTemplateCode());
        templateBuilder.setTemplateParam(code);
        boolean sendStatus = SmsSendHandler.sendSms(templateBuilder);
        if (sendStatus) {
            return PlatformResult.success(code);
        } else {
            return PlatformResult.failure(ResultCode.VERIFY_CODE_SEND_FAIL);
        }
    }
}