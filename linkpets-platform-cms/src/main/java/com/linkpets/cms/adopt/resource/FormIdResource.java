package com.linkpets.cms.adopt.resource;


import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IFormIdGeneratorService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(value = "领养平台formId接口",tags = "领养模块-formId接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/formId")
public class FormIdResource {

    @Resource
    private IFormIdGeneratorService formIdGeneratorService;


    @PostMapping("")
    public PlatformResult generateFormId(@RequestParam("formId") String formId, @RequestParam("userId") String userId) {
        formIdGeneratorService.addFormId(formId, userId);
        return PlatformResult.success();
    }
}
