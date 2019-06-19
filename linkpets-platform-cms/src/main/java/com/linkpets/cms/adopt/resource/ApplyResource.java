package com.linkpets.cms.adopt.resource;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.model.UserInfo;
import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.cms.adopt.service.IUserService;
import com.linkpets.core.model.CmsAdoptAgreement;
import com.linkpets.core.model.CmsAdoptApply;
import com.linkpets.core.model.CmsAdoptPet;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UserAnalyseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.*;

@Api(value = "领养平台领养申请接口", tags = "领养申请接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/apply")
public class ApplyResource {



    @Resource
    IPetService petService;

    @Resource
    IUserService userService;

    @Resource
    IApplyService applyService;

    @ApiOperation(value = "领养申请列表", notes = "分页获取领养申请列表（支持模糊查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "petId", value = "宠物id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "adopterId", value = "送养人id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "applyBy", value = "创建人id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "applyStatus", value = "申请状态", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "list")
    PlatformResult getUserList(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam(value = "petId", required = false) String petId,
                               @RequestParam(value = "adopterId", required = false) String adopterId,
                               @RequestParam(value = "applyBy", required = false, defaultValue = "") String applyBy,
                               @RequestParam(value = "applyStatus", required = false) String applyStatus,
                               @RequestParam(value = "sortCol", required = false, defaultValue = "applyTime") String sortCol,
                               @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {


        Map<String, Object> param = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(adopterId) && StringUtils.isEmpty(petId)) {
            List<String> petIdList = petService.getAdoptPetIdsByUserId(adopterId);
            param.put("petId", petIdList);

        } else if(StringUtils.isNotEmpty(adopterId)){
            param.put("petId", Arrays.asList(petId.split(",")));
        }
        if (StringUtils.isNotEmpty(applyStatus)){
            param.put("applyStatus", Arrays.asList(applyStatus.split(",")));
        }

        param.put("adopterId", adopterId);
        param.put("applyBy", applyBy);
        JSONObject data = applyService.getApplyListForPage(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(data);
    }

    @ApiOperation("获取领养申请详情接口")
    @GetMapping(value = "{applyId}")
    public PlatformResult getAdoptPet(@PathVariable("applyId") String applyId) {
        CmsAdoptApply adoptApply = applyService.getApply(applyId);
        UserInfo userInfo=userService.getUserInfoByUserId(adoptApply.getApplyBy());
        CmsAdoptPet petInfo=petService.getAdopt(adoptApply.getPetId());
        UserInfo adopterInfo=userService.getUserInfoByUserId(petInfo.getCreateBy());
        String birthday = adopterInfo.getBirthday();
        String starSign = UserAnalyseUtil.getStarSignName(birthday);
        adopterInfo.setStarSign(starSign);
        adopterInfo.setAgeFrom(UserAnalyseUtil.getAgeFrom(birthday));
        String lastLoginTime = userService.getLastLoginTime(petInfo.getCreateBy());
        adopterInfo.setLastLoginTime(lastLoginTime);
        JSONObject result=new JSONObject();
        result.put("applyInfo",adoptApply);
        result.put("userInfo",userInfo);
        result.put("petInfo",petInfo);
        result.put("adopterInfo",adopterInfo);
        return PlatformResult.success(result);
    }



    @ApiOperation("创建领养申请接口")
    @PostMapping(value = "info")
    public PlatformResult crtAdopt(@RequestBody CmsAdoptApply apply) {
        String applyId = applyService.crtApply(apply);
        return PlatformResult.success(applyId);
    }

    @ApiOperation("修改领养申请接口")
    @PutMapping(value = "info")
    public PlatformResult uptAdopt(@RequestBody CmsAdoptApply apply) {
        applyService.uptApply(apply);
        return PlatformResult.success();
    }
    


}




