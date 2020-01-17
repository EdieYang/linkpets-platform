package com.linkpets.cms.adopt.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.ICertificationService;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.cms.sms.SmsSendHandler;
import com.linkpets.cms.sms.SmsTemplateBuilder;
import com.linkpets.cms.sms.SmsTypeEnum;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.core.model.CmsUser;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Api(value = "领养模块-实名认证接口", tags = "领养模块-实名认证接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/certification")
public class CertificationResource {

    @Resource
    private ICertificationService certificationService;
    @Resource
    private IUserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @ApiOperation("上传实名信息")
    @PostMapping("")
    public PlatformResult uploadCertification(@RequestParam("imageFront") String imageFront,
                                              @RequestParam("imageBack") String imageBack,
                                              @RequestParam("userId") String userId,
                                              @RequestParam("realName") String realName,
                                              @RequestParam("idCard") String idCard,
                                              @RequestParam("wxAccount") String wxAccount,
                                              @RequestParam("mobilePhone") String mobilePhone,
                                              @RequestParam("verifyCode") String verifyCode) {
        //校验验证码
        String verifyCodeStorage = stringRedisTemplate.opsForValue().get("verifyCode." + userId + "." + mobilePhone);
        if (!verifyCode.equals(verifyCodeStorage)) {
            return PlatformResult.failure(ResultCode.VERIFY_CODE_INVALID);
        }
        //上传认证信息
        CmsAdoptCertification certification = new CmsAdoptCertification();
        certification.setId(UUIDUtils.getId());
        certification.setImageFront(imageFront);
        certification.setImageBack(imageBack);
        certification.setRealName(realName);
        certification.setIdCard(idCard);
        certification.setUserId(userId);
        certification.setCreateDate(new Date());
        certificationService.uploadCertification(certification);
        //更新用户信息
        CmsUser cmsUser = new CmsUser();
        cmsUser.setUserId(userId);
        cmsUser.setWxAccount(wxAccount);
        cmsUser.setMobilePhone(mobilePhone);
        userService.updateUserInfo(cmsUser);
        return PlatformResult.success();
    }


    @ApiOperation("修改实名信息")
    @PutMapping("")
    public PlatformResult modifyCertification(@RequestBody CmsAdoptCertification certification) {
        certificationService.modifyCertification(certification);
        return PlatformResult.success();
    }

    @ApiOperation("获取用户实名信息")
    @GetMapping("")
    public PlatformResult getUserCertification(@RequestParam("userId") String userId) {

        CmsAdoptCertification certification = certificationService.getUserCertification(userId);
        return PlatformResult.success(certification);
    }

    @ApiOperation(value = "实名认证申请列表", notes = "实名认证申请列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "status", value = "申请状态", required = false)
    })
    @GetMapping(value = "list")
    public PlatformResult getCertificationList(@RequestParam("pageNum") int pageNum,
                                               @RequestParam("pageSize") int pageSize,
                                               @RequestParam("status") String status) {

        PageInfo<CmsAdoptCertification> certificationList = certificationService.getUserCertificationList(pageNum, pageSize, status);
        return PlatformResult.success(certificationList);
    }
}
