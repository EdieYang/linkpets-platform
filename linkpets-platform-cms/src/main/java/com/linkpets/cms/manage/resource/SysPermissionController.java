package com.linkpets.cms.manage.resource;

import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.UserLoginToken;
import com.linkpets.cms.manage.service.ISysPermissionService;
import com.linkpets.core.model.SysPermission;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Edie
 */
@Api(tags = "系统模块-权限接口")
@UserLoginToken
@RestController
@RequestMapping("/sys/permission")
public class SysPermissionController {

    @Resource
    private ISysPermissionService sysPermissionService;

    @ApiOperation("分页获取权限列表")
    @GetMapping("page")
    public PlatformResult getSysPermissionPage(@RequestParam(value = "permissionName", required = false) String permissionName,
                                               @RequestParam(value = "permissionCode", required = false) String permissionCode,
                                               @RequestParam("pageNum") int pageNum,
                                               @RequestParam("pageSize") int pageSize) {
        PageInfo<SysPermission> permissionPage = sysPermissionService.getSysPermissionPage(permissionName, permissionCode, pageNum, pageSize);
        return PlatformResult.success(permissionPage);
    }

    @ApiOperation("获取权限信息")
    @GetMapping("")
    public PlatformResult getSysPermissionPage(@RequestParam(value = "id") String id) {
        SysPermission permissionPage = sysPermissionService.getSysPermission(id);
        return PlatformResult.success(permissionPage);
    }

    @ApiOperation("新增权限")
    @PostMapping()
    public PlatformResult crtSysPermission(@RequestBody SysPermission sysPermission) {
        String permissionId = sysPermissionService.crtSysPermission(sysPermission);
        return PlatformResult.success(permissionId);
    }

    @ApiOperation("修改权限")
    @PutMapping()
    public PlatformResult uptSysPermission(@RequestBody SysPermission sysPermission) {
        sysPermissionService.uptSysPermission(sysPermission);
        return PlatformResult.success();
    }


    @ApiOperation("删除权限")
    @DeleteMapping()
    public PlatformResult delSysPermission(@RequestParam("id") String id) {
        sysPermissionService.delSysPermission(id);
        return PlatformResult.success();
    }

    @ApiOperation("批量删除权限")
    @DeleteMapping("batch")
    public PlatformResult batchDelSysPermission(@RequestParam("ids") String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        if (idList.size() > 0) {
            sysPermissionService.batchDelSysPermission(idList);
        }
        return PlatformResult.success();
    }
}
