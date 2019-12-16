package com.linkpets.cms.adopt.resource;


import com.github.pagehelper.PageInfo;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IBannerService;
import com.linkpets.core.model.CmsBanner;
import com.linkpets.enums.ResultCode;
import com.linkpets.result.PlatformResult;
import com.linkpets.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "领养平台-BANNER接口", tags = "领养平台-BANNER接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/banner")
public class BannerResource {


    @Resource
    private IBannerService bannerService;


    @ApiOperation("查询Banner列表")
    @GetMapping("/list")
    public PlatformResult getBannerList(@RequestParam("activityId") String activityId,
                                        @RequestParam(value = "count", required = false) Integer count) {
        List<CmsBanner> bannerList = bannerService.getBannerList(activityId, count);
        return PlatformResult.success(bannerList);
    }

    @ApiOperation("分页查询Banner列表")
    @GetMapping("/page")
    public PlatformResult getBannerPage(@ApiParam(name = "activityId", value = "BANNER类型 -ADOPT：公益平台", required = true)
                                        @RequestParam("activityId") String activityId,
                                        @RequestParam(value = "pageNum") Integer pageNum,
                                        @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<CmsBanner> bannerPage = bannerService.getBannerPage(activityId, pageNum, pageSize);
        return PlatformResult.success(bannerPage);
    }

    @ApiOperation("创建Banner")
    @PostMapping("")
    public PlatformResult crtBanner(@RequestBody CmsBanner cmsBanner) {
        String bannerId = bannerService.crtBanner(cmsBanner);
        return PlatformResult.success(bannerId);
    }

    @ApiOperation("更新Banner")
    @PutMapping("/")
    public PlatformResult uptBanner(@RequestBody CmsBanner cmsBanner) {
        bannerService.uptBanner(cmsBanner);
        return PlatformResult.success();
    }

}
