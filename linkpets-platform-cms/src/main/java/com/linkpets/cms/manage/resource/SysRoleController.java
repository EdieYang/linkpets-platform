package com.linkpets.cms.manage.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.UserLoginToken;
import com.linkpets.cms.manage.service.ISysRoleService;
import com.linkpets.core.model.SysRole;
import com.linkpets.core.respEntity.system.SysRolePermissionRes;
import com.linkpets.core.respEntity.system.SysRoleUserRes;
import com.linkpets.core.respEntity.system.SysUserRoleRes;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @author edie
 */
@Api(tags = "系统角色")
@UserLoginToken
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Resource
    private ISysRoleService sysRoleService;

    @ApiOperation("分页获取角色列表")
    @GetMapping("page")
    public PlatformResult getSysRolePage(@RequestParam(value = "roleName", required = false) String roleName,
                                         @RequestParam(value = "roleCode", required = false) String roleCode,
                                         @RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        PageInfo<SysRole> rolePage = sysRoleService.getSysRolePage(roleName, roleCode, pageNum, pageSize);
        return PlatformResult.success(rolePage);
    }


    @ApiOperation("获取角色信息")
    @GetMapping("")
    public PlatformResult getSysRolePage(@RequestParam(value = "id") String id) {
        SysRole rolePage = sysRoleService.getSysRole(id);
        return PlatformResult.success(rolePage);
    }

    @ApiOperation("根据ROLE_CODE获取角色信息")
    @GetMapping("roleCode")
    public PlatformResult getSysRoleByRoleCode(@RequestParam(value = "roleCode") String roleCode) {
        SysRole rolePage = sysRoleService.getSysRoleByRoleCode(roleCode);
        return PlatformResult.success(rolePage);
    }

    @ApiOperation("新增角色")
    @PostMapping()
    public PlatformResult crtSysRole(@RequestBody SysRole sysRole) {
        String roleId = sysRoleService.crtSysRole(sysRole);
        return PlatformResult.success(roleId);
    }


    @ApiOperation("修改角色")
    @PutMapping()
    public PlatformResult uptSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.uptSysRole(sysRole);
        return PlatformResult.success();
    }


    @ApiOperation("删除角色")
    @DeleteMapping()
    public PlatformResult delSysRole(@RequestParam("id") String id) {
        sysRoleService.delSysRole(id);
        return PlatformResult.success();
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("batch")
    public PlatformResult batchDelSysRole(@RequestParam("ids") String ids) {
        List<String> roleIdList = Arrays.asList(ids.split(","));
        if (roleIdList.size() > 0) {
            sysRoleService.batchDelSysRole(roleIdList);
        }
        return PlatformResult.success();
    }

    @ApiOperation("分页根据角色获取用户列表")
    @GetMapping("user/page")
    public PlatformResult getSysRoleUserPage(@RequestParam(value = "roleId") String roleId,
                                             @RequestParam(value = "userAccount", required = false) String userAccount,
                                             @RequestParam(value = "userName", required = false) String userName,
                                             @RequestParam("pageNum") int pageNum,
                                             @RequestParam("pageSize") int pageSize) {
        PageInfo<SysRoleUserRes> rolePage = sysRoleService.getSysRoleUserPage(roleId, userAccount, userName, pageNum, pageSize);
        return PlatformResult.success(rolePage);
    }


    @ApiOperation("新增用户角色")
    @PostMapping("user")
    public PlatformResult crtSysUserRole(@RequestParam("userId") String userId,
                                         @RequestParam("roleId") String roleId) {
        sysRoleService.crtSysUserRole(userId, roleId);
        return PlatformResult.success();
    }


    @ApiOperation("删除用户角色")
    @DeleteMapping("user")
    public PlatformResult delSysUserRole(@RequestParam("userId") String userId,
                                         @RequestParam("roleId") String roleId) {
        sysRoleService.delSysUserRole(userId, roleId);
        return PlatformResult.success();
    }


    @ApiOperation("分页获取角色权限列表")
    @GetMapping("permission/page")
    public PlatformResult getSysRolePermissionPage(@RequestParam(value = "roleId") String roleId,
                                                   @RequestParam(value = "permissionName", required = false) String permissionName,
                                                   @RequestParam(value = "permissionCode", required = false) String permissionCode,
                                                   @RequestParam("pageNum") int pageNum,
                                                   @RequestParam("pageSize") int pageSize) {
        PageInfo<SysRolePermissionRes> rolePage = sysRoleService.getSysRolePermissionPage(roleId, permissionName, permissionCode, pageNum, pageSize);
        return PlatformResult.success(rolePage);
    }


    @ApiOperation("新增角色权限")
    @PostMapping("permission")
    public PlatformResult crtSysRolePermission(@RequestParam("permissionId") String permissionId,
                                               @RequestParam("roleId") String roleId) {
        sysRoleService.crtSysRolePermission(roleId, permissionId);
        return PlatformResult.success();
    }


    @ApiOperation("删除角色权限")
    @DeleteMapping("permission")
    public PlatformResult delSysRolePermission(@RequestParam("permissionId") String permissionId,
                                               @RequestParam("roleId") String roleId) {
        sysRoleService.delSysRolePermission(roleId, permissionId);
        return PlatformResult.success();
    }


    @ApiOperation("分页根据用户获取角色列表")
    @GetMapping("userRole/page")
    public PlatformResult getRolePage(@RequestParam(value = "roleName", required = false) String roleName,
                                      @RequestParam(value = "roleCode", required = false) String roleCode,
                                      @RequestParam(value = "userId") String userId,
                                      @RequestParam("pageNum") int pageNum,
                                      @RequestParam("pageSize") int pageSize) {
        PageInfo<SysUserRoleRes> userRolePage = sysRoleService.getSysUserRolePage(userId, roleName, roleCode, pageNum, pageSize);
        return PlatformResult.success(userRolePage);
    }

    @ApiOperation("新增角色菜单")
    @PostMapping("menu")
    public PlatformResult crtSysRoleMenus(@RequestParam("menus") String menus,
                                          @RequestParam("roleId") String roleId) {
        sysRoleService.crtSysRoleMenus(roleId, menus);
        return PlatformResult.success();
    }

}
