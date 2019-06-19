package com.linkpets.cms.coupon.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.coupon.model.CmsCouponBatchCustomItem;
import com.linkpets.cms.coupon.service.ICouponBatchItemService;
import com.linkpets.result.PlatformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */

@Api(value = "优惠券操作接口", tags = "优惠券接口")
@ResponseResult
@RestController
@RequestMapping("/couponItems")
public class CmsCouponBatchItemResource {

    @Autowired
    private ICouponBatchItemService couponBatchItemService;

    @GetMapping("")
    @ApiOperation(value = "获取用户优惠券列表接口", notes = "可根据优惠券状态,userId查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "userId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "优惠券状态", name = "status", required = false, dataType = "String", paramType = "query", defaultValue = "-1")
    })
    public PlatformResult getCouponBatchItemListByUserId(@RequestParam String userId,
                                                         @RequestParam(required = false, defaultValue = "-1") int status) {
        List<CmsCouponBatchCustomItem> cmsCouponBatchCustomItems = couponBatchItemService.getCouponBatchItemListByUserId(userId, status);
        return PlatformResult.success(cmsCouponBatchCustomItems);
    }


    @GetMapping("/coupon")
    @ApiOperation(value = "获取优惠券详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券id", name = "couponItemId", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult getCouponBatchItemByCouponItemId(@RequestParam String couponItemId) {
        CmsCouponBatchCustomItem cmsCouponBatchCustomItem = couponBatchItemService.getCouponBatchItemByCouponItemId(couponItemId);
        return PlatformResult.success(cmsCouponBatchCustomItem);
    }


    @PutMapping("/coupon")
    @ApiOperation(value = "核销优惠券", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券id", name = "couponItemId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "核销门店Id", name = "certifyChainId", required = true, dataType = "String", paramType = "query")

    })
    public PlatformResult updateCouponBatchItemByCouponItemId(@RequestParam String couponItemId,
                                                              @RequestParam String certifyChainId) {
        CmsCouponBatchCustomItem cmsCouponBatchCustomItem = couponBatchItemService.updateCouponBatchItemByCouponItemId(couponItemId,certifyChainId);
        return PlatformResult.success(cmsCouponBatchCustomItem);
    }


    @GetMapping("/chain")
    @ApiOperation(value = "查询门店核销优惠券列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "核销门店Id", name = "certifyChainId", required = true, dataType = "String", paramType = "query")

    })
    public PlatformResult getCouponBatchItemListByChainId(@RequestParam String certifyChainId) {
        List<CmsCouponBatchCustomItem> cmsCouponBatchCustomItem = couponBatchItemService.getCouponBatchItemListByChainId(certifyChainId);
        return PlatformResult.success(cmsCouponBatchCustomItem);
    }


}
