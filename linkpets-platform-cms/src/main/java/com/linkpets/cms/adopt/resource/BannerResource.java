package com.linkpets.cms.adopt.resource;


import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.adopt.service.IBannerService;
import com.linkpets.core.model.CmsBanner;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "banner接口", tags = "banner接口")
@ResponseResult
@RestController
@RequestMapping("/adopt/banner")
public class BannerResource {


    @Resource
    private IBannerService bannerService;


    @GetMapping("/list")
    public PlatformResult getBannerList(){
        List<CmsBanner> bannerList=bannerService.getBannerList("ADOP");
        return PlatformResult.success(bannerList);
    }

}
