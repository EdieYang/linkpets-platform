package com.linkpets.cms.coupon.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.coupon.service.ICmsCouponService;
import com.linkpets.core.model.CmsCoupon;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/3/27
 */


@Api(value = "优惠券模板配置接口", tags = "优惠券接口")
@RestController
@ResponseResult
@RequestMapping("/coupons")
public class CmsCouponResource {

    @Autowired
    private ICmsCouponService couponService;

    @PostMapping("/coupon")
    @ApiOperation(value = "创建优惠券模板")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券名称", name = "couponName", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "优惠券副标题", name = "couponEnName", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "优惠券类型", name = "couponType", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "优惠券规则", name = "couponRule", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "优惠券提示", name = "couponReminder", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "优惠券发行机构id", name = "orgId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult createCouponTemplate(@RequestParam String couponName,
                                               @RequestParam(required = false) String couponEnName,
                                               @RequestParam int couponType,
                                               @RequestParam String couponRule,
                                               @RequestParam String couponReminder,
                                               @RequestParam String orgId) {
        String couponId = couponService.createCoupon(couponName, couponEnName, couponType, couponRule, couponReminder, orgId);
        return PlatformResult.success(couponId);
    }


    @ApiOperation(value = "获取优惠券模板详情")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券Id", name = "couponId", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/coupon/{couponId}")
    public PlatformResult getCouponTemplate(@PathVariable("couponId") String couponId) {
        CmsCoupon coupon = couponService.getCouponByCouponId(couponId);
        return PlatformResult.success(coupon);
    }


    @ApiOperation(value = "根据发行机构id分页查询优惠券模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "orgId", name = "orgId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "pageNo", name = "页码", required = true, dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "pageSize", name = "页数", required = true, dataType = "int", paramType = "query", defaultValue = "20")
    })
    @GetMapping("")
    public PlatformResult getCouponTemplateList(@RequestParam("orgId") String orgId,
                                                @RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {
        Map<String, Object> couponPages = couponService.getCouponList(orgId, pageNo, pageSize);
        return PlatformResult.success(couponPages);
    }


}
