package com.linkpets.cms.adopt.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.core.model.*;
import com.linkpets.cms.adopt.service.IOrgService;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "领养平台宠物接口", tags = "公益组织接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/orgs")
public class OrgResource {


    @Resource
    IPetService petService;

    @Resource
    IOrgService orgService;


    @ApiOperation("获取公益组织列表")
    @GetMapping(value = "/list")
    public PlatformResult getAdoptOrgList() {
        List<CmsAdoptOrg> adoptOrgList = orgService.getAdoptOrgList();
        return PlatformResult.success(adoptOrgList);
    }

    @ApiOperation("获取公益组织详情")
    @GetMapping(value = "/info")
    public PlatformResult getAdoptOrgInfo(@RequestParam("orgId") String orgId) {
        CmsAdoptOrg adoptOrg = orgService.getAdoptOrgDetail(orgId);
        return PlatformResult.success(adoptOrg);
    }


    @ApiOperation("获取公益组织宠物列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orgId", value = "组织id", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "createBy", value = "创建人id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "adoptStatus", value = "领养状态", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "petType", value = "宠物类别", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "ageArr", value = "年龄，支持多条件，用','逗号分隔", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sexArr", value = "性别，支持多条件，用','逗号分隔", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sterilization", value = "绝育", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "vaccine", value = "疫苗", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "parasite", value = "驱虫", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "/pets")
    PlatformResult getPetList(
            @RequestParam("orgId") String orgId,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize,
            @RequestParam(value = "createBy", required = false) String createBy,
            @RequestParam(value = "adoptStatus", required = false) String adoptStatus,
            @RequestParam(value = "petType", required = false) String petType,
            @RequestParam(value = "ageArr", required = false) String ageArr,
            @RequestParam(value = "sexArr", required = false) String sexArr,
            @RequestParam(value = "sterilization", required = false) String sterilization,
            @RequestParam(value = "vaccine", required = false) String vaccine,
            @RequestParam(value = "parasite", required = false) String parasite,
            @RequestParam(value = "sortCol", required = false, defaultValue = "create_date") String sortCol,
            @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("createBy", createBy);
        if (null != adoptStatus && !"".equals(adoptStatus)) {
            param.put("adoptStatus", Arrays.asList(adoptStatus.split(",")));
        }
        if (null != ageArr && !"".equals(ageArr)) {
            param.put("ageArr", Arrays.asList(ageArr.split(",")));
        }
        if (null != sexArr && !"".equals(sexArr)) {
            param.put("sexArr", Arrays.asList(sexArr.split(",")));
        }
        param.put("orgId", orgId);
        param.put("petType", petType);
        param.put("sterilization", sterilization);
        param.put("vaccine", vaccine);
        param.put("parasite", parasite);
        PageInfo<CmsAdoptPet> petPageInfo = orgService.getAdoptListForPage(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(petPageInfo);
    }

    @ApiOperation("获取公益组织相册列表")
    @GetMapping(value = "/gallery")
    public PlatformResult getAdoptOrgGallery(@RequestParam("orgId") String orgId,
                                             @RequestParam("pageNum") int pageNum,
                                             @RequestParam("pageSize") int pageSize) {
        PageInfo<CmsAdoptOrgGallery> petPageInfo = orgService.getAdoptGalleryList(pageNum, pageSize, orgId);
        return PlatformResult.success(petPageInfo);
    }

    @ApiOperation("获取公益组织活动列表")
    @GetMapping(value = "/activity")
    public PlatformResult getAdoptOrgActivity(@RequestParam("orgId") String orgId,
                                              @RequestParam("pageNum") int pageNum,
                                              @RequestParam("pageSize") int pageSize) {
        PageInfo<CmsAdoptOrgActivity> petPageInfo = orgService.getAdoptActivityList(pageNum, pageSize, orgId);
        return PlatformResult.success(petPageInfo);
    }


    @ApiOperation("获取公益组织粉丝列表")
    @GetMapping(value = "/follow")
    public PlatformResult getAdoptOrgFollows(@RequestParam("orgId") String orgId) {
        List<CmsAdoptOrgFollow> adoptOrgFollows = orgService.getAdoptOrgFollows(orgId);
        return PlatformResult.success(adoptOrgFollows);
    }


    @ApiOperation("关注公益组织")
    @PostMapping(value = "/follow")
    public PlatformResult postAdoptOrgFollow(@RequestParam("orgId") String orgId,
                                             @RequestParam("userId") String userId) {
        orgService.crtAdoptOrgFollow(orgId, userId);
        return PlatformResult.success();
    }

    @ApiOperation("取消关注公益组织")
    @DeleteMapping(value = "/follow")
    public PlatformResult deleteAdoptOrgFollow(@RequestParam("orgId") String orgId,
                                               @RequestParam("userId") String userId) {
        orgService.uptAdoptOrgFollow(orgId, userId);
        return PlatformResult.success();
    }


    @ApiOperation("获取公益组织基础数据")
    @GetMapping(value = "/statistic")
    public PlatformResult getAdoptOrgStatistics(@RequestParam("orgId") String orgId,
                                                @RequestParam("userId") String userId) {
        AdoptOrgStatistic adoptOrgStatistic = orgService.getAdoptOrgStatistic(orgId,userId);
        adoptOrgStatistic.setPublishNum(adoptOrgStatistic.getActivityNum()+adoptOrgStatistic.getAdoptPets()+adoptOrgStatistic.getGalleryNum());
        return PlatformResult.success(adoptOrgStatistic);
    }


}
