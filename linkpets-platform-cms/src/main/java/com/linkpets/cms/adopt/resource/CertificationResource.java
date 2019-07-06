package com.linkpets.cms.adopt.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.ICertificationService;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Api(value = "领养平台领养申请接口", tags = "领养申请接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/certification")
public class CertificationResource {

    @Resource
    private ICertificationService certificationService;

    @PostMapping("")
    public PlatformResult uploadCertification(@RequestParam("imageFront") String imageFront,
                                              @RequestParam("imageBack") String imageBack,
                                              @RequestParam("userId") String userId,
                                              @RequestParam("realName") String realName,
                                              @RequestParam("idCard") String idCard) {
        CmsAdoptCertification certification = new CmsAdoptCertification();
        certification.setId(UUIDUtils.getUUID());
        certification.setImageFront(imageFront);
        certification.setImageBack(imageBack);
        certification.setRealName(realName);
        certification.setIdCard(idCard);
        certification.setUserId(userId);
        certification.setCreateDate(new Date());
        certificationService.uploadCertification(certification);
        return PlatformResult.success();
    }


    @PutMapping("")
    public PlatformResult modifyCertification(@RequestBody CmsAdoptCertification certification) {
        certificationService.modifyCertification(certification);
        return PlatformResult.success();
    }


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
