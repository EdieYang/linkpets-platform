package com.linkpets.cms.adopt.resource;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IApplyService;
import com.linkpets.cms.adopt.service.IOrgService;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.core.model.*;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "领养平台公益机构接口", tags = "领养平台-公益机构接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/orgs")
public class OrgResource {

    @Resource
    IPetService petService;

    @Resource
    IOrgService orgService;

    @Resource
    IApplyService applyService;

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

    @ApiOperation("新增公益组织")
    @PostMapping(value = "/org")
    public PlatformResult insertAdoptOrgInfo(@RequestBody CmsAdoptOrg adoptOrg) {
        String orgId = orgService.insertOrg(adoptOrg);
        return PlatformResult.success(orgId);
    }

    @ApiOperation("更新公益组织")
    @PutMapping(value = "/org")
    public PlatformResult uptAdoptOrgInfo(@RequestBody CmsAdoptOrg adoptOrg) {
        orgService.uptOrg(adoptOrg);
        return PlatformResult.success();
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
    PlatformResult getPetList(@RequestParam("orgId") String orgId, @RequestParam("pageNum") int pageNum,
                              @RequestParam("pageSize") int pageSize, @RequestParam(value = "createBy", required = false) String createBy,
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
        PageInfo<CmsAdoptPet> petPageInfo = orgService.getAdoptListForPage(param, pageNum, pageSize,
                sortCol + " " + sort);
        return PlatformResult.success(petPageInfo);
    }

    @ApiOperation("获取公益组织相册列表")
    @GetMapping(value = "/gallery")
    public PlatformResult getAdoptOrgGallery(@RequestParam("orgId") String orgId, @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize) {
        PageInfo<CmsAdoptOrgGallery> petPageInfo = orgService.getAdoptGalleryList(pageNum, pageSize, orgId);
        return PlatformResult.success(petPageInfo);
    }

    @ApiOperation("新增公益机构相册")
    @PostMapping(value = "/gallery")
    public PlatformResult addAdoptOrgGallery(@RequestBody CmsAdoptOrgGallery gallery) {
        String id = orgService.insertGallery(gallery);
        return PlatformResult.success(id);

    }

    @ApiOperation("删除公益机构相册")
    @DeleteMapping(value = "/gallery")
    public PlatformResult delAdoptOrgGallery(@RequestBody CmsAdoptOrgGallery gallery) {
        orgService.delGallery(gallery);
        return PlatformResult.success();
    }

    @ApiOperation("获取公益组织活动列表")
    @GetMapping(value = "/activity")
    public PlatformResult getAdoptOrgActivity(@RequestParam("orgId") String orgId, @RequestParam("pageNum") int pageNum,
                                              @RequestParam("pageSize") int pageSize) {
        PageInfo<CmsAdoptOrgActivity> petPageInfo = orgService.getAdoptActivityList(pageNum, pageSize, orgId);
        return PlatformResult.success(petPageInfo);
    }

    @ApiOperation("新增公益机构活动")
    @PostMapping(value = "/activity")
    public PlatformResult addAdoptOrgActivity(@RequestBody CmsAdoptOrgActivity activity) {
        String activityId = orgService.insertActivity(activity);
        return PlatformResult.success(activityId);
    }

    @ApiOperation("更新公益机构活动")
    @PutMapping(value = "/activity")
    public PlatformResult uptAdoptOrgActivity(@RequestBody CmsAdoptOrgActivity activity) {
        orgService.uptActivity(activity);
        return PlatformResult.success();
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
        CmsAdoptOrgStatistic adoptOrgStatistic = orgService.getAdoptOrgStatistic(orgId, userId);
        adoptOrgStatistic.setPublishNum(adoptOrgStatistic.getActivityNum() + adoptOrgStatistic.getAdoptPets()
                + adoptOrgStatistic.getGalleryNum());
        return PlatformResult.success(adoptOrgStatistic);
    }

    @ApiOperation("获取公益组织用户列表")
    @GetMapping(value = "/{orgId}/user")
    public PlatformResult getOrgUserList(@PathVariable("orgId") String orgId, @RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam(value = "sortCol", required = false, defaultValue = "userAcc") String sortCol,
                                         @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {
        return PlatformResult.success(orgService.getOrgUserList(orgId, pageNum, pageSize, sortCol + " " + sort));
    }


    @ApiOperation(value = "公益组织领养申请列表", notes = "分页获取领养申请列表（支持模糊查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "orgId", value = "orgId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "petId", value = "宠物id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "adopterId", value = "送养人id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "applyBy", value = "创建人id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "applyStatus", value = "申请状态", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "/apply")
    PlatformResult getApplyList(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam("orgId") String orgId,
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
        } else if (StringUtils.isNotEmpty(adopterId)) {
            param.put("petId", Arrays.asList(petId.split(",")));
        }
        if (StringUtils.isNotEmpty(applyStatus)) {
            param.put("applyStatus", Arrays.asList(applyStatus.split(",")));
        }

        param.put("adopterId", adopterId);
        param.put("applyBy", applyBy);
        param.put("orgId", orgId);
        JSONObject data = applyService.getOrgApplyListForPage(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(data);
    }

}
