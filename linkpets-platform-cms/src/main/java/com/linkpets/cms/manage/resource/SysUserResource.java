package com.linkpets.cms.manage.resource;


import com.alibaba.fastjson.JSONObject;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.manage.service.ISysUserService;
import com.linkpets.core.model.SysUser;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

@Api(tags = "系统账户", description = "用户的增、改、查")
@ResponseResult
@RestController
@RequestMapping("/sysUser")
public class SysUserResource {

    @Autowired
    @Qualifier("sysUserService")
    private ISysUserService sysUserService;

    @PostMapping("/login")
    @ApiOperation(value = "账号登录", notes = "根据userName 和 password 获取账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "userName", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "password", paramType = "query", dataType = "String", required = true)
    })
    public JSONObject sysUserLogin(@RequestParam String userName,
                                   @RequestParam String password) {
        return sysUserService.loginSysUser(userName, password);
    }


    @GetMapping("")
    @ApiOperation(value = "获取账号", notes = "根据分页参数获取账号列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构Id", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "chainId", value = "连锁Id", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query", dataType = "Integer", required = false),
            @ApiImplicitParam(name = "pageSize", value = "页大小", paramType = "query", dataType = "Integer", required = false)})
    public JSONObject getSysUserList(@RequestParam(required = false) String orgId,
                                     @RequestParam(required = false) String chainId,
                                     @RequestParam(defaultValue = "20", required = false) int pageNo,
                                     @RequestParam(defaultValue = "1", required = false) int pageSize) {
        return sysUserService.listSysUserCustom(orgId, chainId, pageNo, pageSize);
    }


    @GetMapping("/user")
    @ApiOperation(value = "获取账号", notes = "根据分页参数获取账号列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", dataType = "String", required = false)
    })
    public SysUser getSysUserList(@PathVariable(required = false) String userId) {
        return sysUserService.getSysUser(userId);
    }


    @PutMapping("/user")
    @ApiOperation(value = "更新账号信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "chainId", value = "chainId", paramType = "query", dataType = "String", required = false),


    })
    public PlatformResult updateSysUser(@RequestParam String userId,
                                        @RequestParam(required = false) String chainId) {

        return PlatformResult.success(sysUserService.updateSysUser(userId,chainId));
    }



}
