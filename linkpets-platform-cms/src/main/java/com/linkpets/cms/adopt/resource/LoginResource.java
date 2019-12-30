//package com.linkpets.cms.adopt.resource;
//
//import javax.annotation.Resource;
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.linkpets.annotation.ResponseResult;
//import com.linkpets.cms.adopt.service.IUserService;
//import com.linkpets.core.model.SysUser;
//import com.linkpets.enums.ResultCode;
//import com.linkpets.result.PlatformResult;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@Api(value = "领养平台-系统用户登录接口",tags = "领养平台-系统用户登录接口")
//@ResponseResult
//@RestController
//@RequestMapping("/adopt/login")
//public class LoginResource {
//
//	@Resource
//	IUserService userService;
//
//	@ApiOperation("创建领养宠物接口")
//    @PostMapping(value = "login")
//    public PlatformResult chkLogin(@RequestBody SysUser data) {
//        SysUser user = userService.getUserByAccountAndPassword(data.getUserAcc(), data.getPassword());
//        if (null != user) {
//        	//添加token,其他接口拦截器验证token
//
//        	 return PlatformResult.success(user);
//        }else {
//        	 return PlatformResult.failure(ResultCode.USER_LOGIN_ERROR);
//        }
//
//    }
//
//}
