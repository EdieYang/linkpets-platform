package com.linkpets.cms.adopt.resource;


import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IOrgService;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.core.model.CmsAdoptOrg;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "领养平台宠物接口", tags = "公益组织接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/orgs")
public class OrgResource {


    @Resource
    IPetService petService;

    @Resource
    IOrgService orgService;


    @ApiOperation("获取公益组织列表")
    @GetMapping(value = "/list")
    public PlatformResult getAdoptPet() {
        List<CmsAdoptOrg> adoptOrgList=orgService.getAdoptOrgList();
        return PlatformResult.success(adoptOrgList);
    }

    @ApiOperation("获取公益组织列表")
    @GetMapping(value = "/info")
    public PlatformResult getAdoptPet(@RequestParam("orgId")String orgId) {
        CmsAdoptOrg adoptOrg=orgService.getAdoptOrgDetail(orgId);
        return PlatformResult.success(adoptOrg);
    }

}
