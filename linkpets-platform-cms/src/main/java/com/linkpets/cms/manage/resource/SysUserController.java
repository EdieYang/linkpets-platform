package com.linkpets.cms.manage.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.UserLoginToken;
import com.linkpets.cms.user.service.IUserService;
import com.linkpets.cms.manage.service.ISysUserService;
import com.linkpets.core.model.SysUser;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author edie
 */
@Api(tags = "系统模块-用户接口")
@UserLoginToken
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;
    @Resource
    private IUserService userService;

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
        SysUser user=sysUserService.getSysUserByUserAccount(sysUser.getUserAccount());
        if(user!=null){
            return PlatformResult.failure(ResultCode.USER_ACCOUNT_HAS_EXISTED);
        }
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

    @ApiOperation("新增组织用户")
    @PostMapping("orgUser")
    public PlatformResult crtOrgSysUser(@RequestParam("userId") String userId,
                                        @RequestParam("orgId") String orgId) {

        String sysUserId=sysUserService.crtOrgSysUser(userId, orgId);
        if(StringUtils.isEmpty(sysUserId)){
            return PlatformResult.failure(ResultCode.CREATE_SYS_USER_ACCOUNT_FAIL);
        }
        return PlatformResult.success(userId);
    }

    @ApiOperation("删除组织用户")
    @DeleteMapping("orgUser")
    public PlatformResult delOrgSysUser(@RequestParam("userId") String userId,
                                        @RequestParam("orgId") String orgId) {

        sysUserService.delOrgSysUser(userId, orgId);
        return PlatformResult.success();
    }
}
