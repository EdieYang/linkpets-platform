package com.linkpets.cms.manage.resource;


import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.manage.service.ISysOrgService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "系统机构", description = "机构的增、改、查")
@ResponseResult
@RestController
@RequestMapping("/sysOrg")
public class SysOrgResource {

    @Autowired
    private ISysOrgService sysOrgService;

    @GetMapping("")
    @ApiOperation(value = "获取机构信息", notes = "获取机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgCode", value = "orgCode", paramType = "query", dataType = "String", required = true)
    })
    public PlatformResult getSysOrg(@RequestParam String orgCode) {
        return PlatformResult.success(sysOrgService.getSysOrg(orgCode));
    }

    @GetMapping("/chains")
    @ApiOperation(value = "获取机构下的门店列表信息", notes = "获取机构门店列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "orgId", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "chainName", value = "chainName", paramType = "query", dataType = "String", required = false)

    })
    public PlatformResult getSysChains(@RequestParam String orgId,
                                       @RequestParam(required = false) String chainName) {
        return PlatformResult.success(sysOrgService.getSysChains(orgId, chainName));
    }

    @GetMapping("/chains/chain")
    @ApiOperation(value = "根据chainId获取门店信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "chainId", paramType = "query", dataType = "String", required = true)
    })
    public PlatformResult getSysChains(@RequestParam String chainId) {
        return PlatformResult.success(sysOrgService.getSysChainByChainId(chainId));
    }


}
