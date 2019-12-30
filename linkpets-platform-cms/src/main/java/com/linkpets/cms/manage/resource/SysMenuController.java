package com.linkpets.cms.manage.resource;

import com.linkpets.annotation.UserLoginToken;
import com.linkpets.cms.manage.service.ISysMenuService;
import com.linkpets.core.model.SysMenu;
import com.linkpets.core.respEntity.system.SysMenuRes;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author edie
 */
@Api(tags = "系统菜单")
@UserLoginToken
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Resource
    private ISysMenuService sysMenuService;


    @ApiOperation("查询菜单列表")
    @GetMapping("list")
    public PlatformResult getSysMenuList() {
        List<SysMenuRes> sysMenuRespList = sysMenuService.getSysMenuList();
        return PlatformResult.success(sysMenuRespList);
    }


    @ApiOperation("查询菜单")
    @GetMapping("")
    public PlatformResult getSysMenuList(@RequestParam("id") String id) {
        SysMenu sysMenu = sysMenuService.getSusMenuById(id);
        return PlatformResult.success(sysMenu);
    }


    @ApiOperation("新增菜单")
    @PostMapping()
    public PlatformResult crtSysMenu(@RequestBody SysMenu sysMenu) {
        String menuId = sysMenuService.crtSysMenu(sysMenu);
        return PlatformResult.success(menuId);
    }

    @ApiOperation("修改菜单")
    @PutMapping()
    public PlatformResult uptSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.uptSysMenu(sysMenu);
        return PlatformResult.success();
    }


    @ApiOperation("删除菜单")
    @DeleteMapping()
    public PlatformResult delSysMenu(@RequestParam("id") String id) {
        sysMenuService.delSysMenu(id);
        return PlatformResult.success();
    }

    @ApiOperation("根据角色查询菜单列表")
    @GetMapping("role")
    public PlatformResult getSysRoleMenuList(@RequestParam("roleId") String roleId) {
        List<SysMenu> sysMenuList = sysMenuService.getSysRoleMenuList(roleId);
        return PlatformResult.success(sysMenuList);
    }

}
