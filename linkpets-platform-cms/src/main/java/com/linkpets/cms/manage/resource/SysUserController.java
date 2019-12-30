package com.linkpets.cms.manage.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.UserLoginToken;
import com.linkpets.cms.manage.service.ISysUserService;
import com.linkpets.core.model.SysUser;
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
@Api(tags = "系统用户")
@UserLoginToken
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;

    @ApiOperation("分页获取用户列表")
    @GetMapping("page")
    public PlatformResult getSysUserPage(@RequestParam(value = "userAccount", required = false) String userAccount,
                                         @RequestParam(value = "userName", required = false) String userName,
                                         @RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize) {
        PageInfo<SysUser> userPage = sysUserService.getSysUserPage(userAccount, userName, pageNum, pageSize);
        return PlatformResult.success(userPage);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("")
    public PlatformResult getSysUser(@RequestParam(value = "userId") String userId) {
        SysUser sysUser = sysUserService.getSysUser(userId);
        return PlatformResult.success(sysUser);
    }

    @ApiOperation("新增用户")
    @PostMapping()
    public PlatformResult crtSysUser(@RequestBody SysUser sysUser) {
        String userId = sysUserService.crtSysUser(sysUser);
        return PlatformResult.success(userId);
    }

    @ApiOperation("修改用户")
    @PutMapping()
    public PlatformResult uptSysUser(@RequestBody SysUser sysUser) {
        sysUserService.uptSysUser(sysUser);
        return PlatformResult.success();
    }


    @ApiOperation("删除用户")
    @DeleteMapping()
    public PlatformResult delSysUser(@RequestParam("userId") String userId) {
        sysUserService.delSysUser(userId);
        return PlatformResult.success();
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("batch")
    public PlatformResult batchDelSysUser(@RequestParam("ids") String ids) {
        List<String> userIdList = Arrays.asList(ids.split(","));
        if (userIdList.size() > 0) {
            sysUserService.batchDelSysUser(userIdList);
        }
        return PlatformResult.success();
    }

}
