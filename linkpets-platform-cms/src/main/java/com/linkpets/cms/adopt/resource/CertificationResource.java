package com.linkpets.cms.adopt.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.ICertificationService;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
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
                                              @RequestParam("userId") String userId) {
        CmsAdoptCertification certification = new CmsAdoptCertification();
        certification.setId(UUIDUtils.getUUID());
        certification.setImageFront(imageFront);
        certification.setImageBack(imageBack);
        certification.setUserId(userId);
        certification.setStatus("0");
        certification.setCreateDate(new Date());
        certification.setIsValid(1);
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

        CmsAdoptCertification certification=certificationService.getUserCertification(userId);
        return PlatformResult.success(certification);
    }



}
