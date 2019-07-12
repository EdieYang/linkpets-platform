package com.linkpets.cms.adopt.resource;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.model.UserInfo;
import com.linkpets.cms.adopt.service.IPetService;
import com.linkpets.cms.adopt.service.IUserService;
import com.linkpets.core.model.CmsAdoptAttention;
import com.linkpets.core.model.CmsAdoptPet;
import com.linkpets.core.model.CmsAdoptPetCollect;
import com.linkpets.core.model.CmsUserLogin;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UserAnalyseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Api(value = "领养平台宠物接口", tags = "宠物接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/pets")
public class PetResource {

    @Resource
    IPetService petService;

    @Resource
    IUserService userService;


    @ApiOperation(value = "宠物列表", notes = "分页获取宠物列表（支持模糊查询）")
    @ApiImplicitParams({
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
    @GetMapping(value = "list")
    PlatformResult getPetList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
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
        param.put("petType", petType);
        param.put("sterilization", sterilization);
        param.put("vaccine", vaccine);
        param.put("parasite", parasite);
        PageInfo<CmsAdoptPet> petPageInfo = petService.getAdoptListForPage(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(petPageInfo);
    }

    @ApiOperation("获取宠物详情接口")
    @GetMapping(value = "{petId}")
    public PlatformResult getAdoptPet(@PathVariable("petId") String petId,
                                      @RequestParam("userId") String userId) {
        CmsAdoptPet adoptPet = petService.getAdopt(petId);
        UserInfo userInfo = userService.getUserInfoByUserId(adoptPet.getCreateBy());
        String birthday = userInfo.getBirthday();
        String starSign = UserAnalyseUtil.getStarSignName(birthday);
        userInfo.setStarSign(starSign);
        userInfo.setAgeFrom(UserAnalyseUtil.getAgeFrom(birthday));
        String lastLoginTime = userService.getLastLoginTime(adoptPet.getCreateBy());
        userInfo.setLastLoginTime(lastLoginTime);
        boolean collected = petService.getIfCollectedPet(userId, petId);
        adoptPet.setCollected(collected);
        Map<String, Object> userAddition = userService.getUserAddition(adoptPet.getCreateBy());
        userInfo.setAdoptingNum(Integer.parseInt(String.valueOf(userAddition.get("adoptingNum"))));
        userInfo.setAdoptedNum(Integer.parseInt(String.valueOf(userAddition.get("adoptedNum"))));
        int applyNum = Integer.parseInt(String.valueOf(userAddition.get("applyNum")));
        int applyHandle =  Integer.parseInt(String.valueOf(userAddition.get("applyHandle")));
        if(applyNum==0){
            userInfo.setApplyHandle("0%");
        }else{
            userInfo.setApplyHandle(Math.round(applyHandle/(applyNum*1.00)*100)+"%");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userInfo", userInfo);
        map.put("petInfo", adoptPet);
        return PlatformResult.success(map);
    }

    @ApiOperation("创建领养宠物接口")
    @PostMapping(value = "info")
    public PlatformResult crtAdopt(@RequestBody CmsAdoptPet pet) {
        String petId = petService.crtAdopt(pet);
        return PlatformResult.success(petId);
    }

    @ApiOperation("修改领养宠物接口")
    @PutMapping(value = "info")
    public PlatformResult uptAdopt(@RequestBody CmsAdoptPet pet) {
        petService.uptAdopt(pet);
        return PlatformResult.success();
    }

    @ApiOperation(value = "我收藏的宠物列表", notes = "分页获取我收藏的宠物列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "{userId}/collect")
    PlatformResult getCollectPetList(@PathVariable("userId") String userId,
                                     @RequestParam("pageNum") int pageNum,
                                     @RequestParam("pageSize") int pageSize,
                                     @RequestParam(value = "sortCol", required = false, defaultValue = "create_date") String sortCol,
                                     @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        JSONObject data = petService.getCollectPetList(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(data);
    }


    @ApiOperation(value = "我关注的人创建的宠物列表", notes = "分页获取我关注的人创建的宠物列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "{userId}/attention")
    PlatformResult getPetListByAttention(@PathVariable("userId") String userId,
                                         @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize,
                                         @RequestParam(value = "sortCol", required = false, defaultValue = "create_date") String sortCol,
                                         @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        PageInfo<CmsAdoptPet> data = petService.getFollowedPetList(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(data);
    }

    @PostMapping("collect")
    PlatformResult setCollect(@RequestBody CmsAdoptPetCollect record) {
        petService.crtCollect(record);
        return PlatformResult.success(null);
    }

    @DeleteMapping("collect")
    PlatformResult delCollect(@RequestParam("userId") String userId, @RequestParam("petId") String petId) {
        petService.delCollect(userId, petId);
        return PlatformResult.success(null);
    }

    @ApiOperation(value = "同城宠物列表", notes = "分页获取同城宠物列表（支持模糊查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "pageSize", value = "页面记录条数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "withOutPet", value = "不包含的宠物id", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sortCol", value = "排序字段", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "sort", value = "排序规则", required = false)})
    @GetMapping(value = "shanghai/list")
    PlatformResult getPetListInCity(@RequestParam("pageNum") int pageNum,
                                    @RequestParam("pageSize") int pageSize,
                                    @RequestParam(value = "withOutPet", required = false) String withOutPet,
                                    @RequestParam(value = "sortCol", required = false, defaultValue = "create_date") String sortCol,
                                    @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {

        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("city", city);
        param.put("withOutPet", withOutPet);
        PageInfo<CmsAdoptPet> petPageInfo = petService.getAdoptListForPage(param, pageNum, pageSize, sortCol + " " + sort);
        return PlatformResult.success(petPageInfo);
    }

}
