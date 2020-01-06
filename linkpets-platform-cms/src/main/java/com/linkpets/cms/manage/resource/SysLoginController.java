package com.linkpets.cms.manage.resource;

import com.linkpets.annotation.UserLoginToken;
import com.linkpets.cms.adopt.utils.TokenUtils;
import com.linkpets.cms.manage.service.*;
import com.linkpets.core.model.SysPermission;
import com.linkpets.core.model.SysRole;
import com.linkpets.core.model.SysUser;
import com.linkpets.core.respEntity.system.SysLoginRes;
import com.linkpets.core.respEntity.system.SysLoginRouteRes;
import com.linkpets.core.respEntity.system.SysMenuRes;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Edie
 */
@Api(tags = "系统用户登录")
@RestController
@RequestMapping("/sys/login")
public class SysLoginController {

    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysPermissionService sysPermissionService;
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysRouteService sysRouteService;

    @ApiOperation("用户登录")
    @GetMapping("")
    public PlatformResult sysUserLogin(@RequestParam("userAccount") String userAccount,
                                       @RequestParam("password") String password) {
        SysUser sysUser = sysUserService.getSysUserByUserAccount(userAccount);
        if (sysUser == null) {
            return PlatformResult.failure(ResultCode.VALID_USER_ACCOUNT);
        }

        if (!sysUser.getPassword().equals(password)) {
            return PlatformResult.failure(ResultCode.VALID_USER_ACCOUNT_PASSWORD);
        }
        String token = TokenUtils.getToken(sysUser);
        String orgId = sysUserService.getOrgIdByUserId(sysUser.getUserId());
        SysLoginRes loginRes = new SysLoginRes();
        loginRes.setUserId(sysUser.getUserId());
        loginRes.setUserName(sysUser.getUserName());
        loginRes.setOrgId(orgId);
        loginRes.setToken(token);
        return PlatformResult.success(loginRes);
    }

    @UserLoginToken
    @ApiOperation("获取用户权限")
    @GetMapping("permission")
    public PlatformResult getSysUserPermission(@RequestParam("userId") String userId) {
        SysLoginRes sysLoginRes = new SysLoginRes();
        //查询此用户系统角色
        List<SysRole> sysRoleList = sysRoleService.getSysRoleListByUserId(userId);
        List<String> userRoles = new ArrayList<>();
        sysRoleList.forEach(sysRole -> {
            userRoles.add(sysRole.getRoleCode());
        });
        //查询此用户系统权限
        List<SysPermission> sysPermissionList = sysPermissionService.getSysPermissionListByUserId(userId);
        List<String> userPermissions = new ArrayList<>();
        sysPermissionList.forEach(sysPermission -> {
            userPermissions.add(sysPermission.getPermissionCode());
        });
        //查询所有系统Route
        List<SysLoginRouteRes> sysRouteResList = sysRouteService.getSysLoginRouteList();
        //查询此用户系统菜单
        List<SysMenuRes> sysMenuResList = sysMenuService.getSysMenuListByUserId(userId);
        sysLoginRes.setUserRoles(userRoles);
        sysLoginRes.setUserPermissions(userPermissions);
        sysLoginRes.setAccessRoutes(sysRouteResList);
        sysLoginRes.setAccessMenus(sysMenuResList);
        return PlatformResult.success(sysLoginRes);
    }

}
