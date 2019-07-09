package com.linkpets.cms.adopt.resource;


import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IFormIdGeneratorService;
import com.linkpets.core.model.CmsAdoptCertification;
import com.linkpets.core.model.CmsAdoptFormid;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


@Api(value = "formId生成器", tags = "formId生成器")
@ResponseResult
@RestController
@RequestMapping("/adopt/formId")
public class FormIdResource {

    @Resource
    private IFormIdGeneratorService formIdGeneratorService;


    @PostMapping("")
    public PlatformResult generateFormId(@RequestParam("formId") String formId,@RequestParam("userId") String userId) {
        formIdGeneratorService.addFormId(formId,userId);
        return PlatformResult.success();
    }
}
