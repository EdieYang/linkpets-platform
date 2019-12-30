package com.linkpets.cms.manage.resource;

import com.linkpets.annotation.UserLoginToken;
import com.linkpets.cms.manage.service.ISysRouteService;
import com.linkpets.core.model.SysRoute;
import com.linkpets.core.respEntity.system.SysRouteRes;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author edie
 */
@Api(tags = "系统路由")
@UserLoginToken
@RestController
@RequestMapping("/sys/route")
public class SysRouteController {

    @Resource
    private ISysRouteService sysRouteService;

    @ApiOperation("查询路由列表")
    @GetMapping("list")
    public PlatformResult getSysRouteList() {
        List<SysRouteRes> sysRouteList = sysRouteService.getSysRouteList();
        return PlatformResult.success(sysRouteList);
    }

    @ApiOperation("查询路由")
    @GetMapping("")
    public PlatformResult getSysRoute(@RequestParam("id") String id) {
        SysRouteRes sysRouteRes = sysRouteService.getSysRoute(id);
        return PlatformResult.success(sysRouteRes);
    }


    @ApiOperation("新增路由")
    @PostMapping()
    public PlatformResult crtSysRoute(@RequestBody SysRoute sysRoute) {
        String routeId = sysRouteService.crtSysRoute(sysRoute);
        return PlatformResult.success(routeId);
    }

    @ApiOperation("修改路由")
    @PutMapping()
    public PlatformResult uptSysRoute(@RequestBody SysRoute sysRole) {
        sysRouteService.uptSysRoute(sysRole);
        return PlatformResult.success();
    }


    @ApiOperation("删除路由")
    @DeleteMapping()
    public PlatformResult delSysRoute(@RequestParam("id") String routeId) {
        sysRouteService.delSysRoute(routeId);
        return PlatformResult.success();
    }


}
