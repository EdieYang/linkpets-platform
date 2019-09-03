package com.linkpets.cms.coupon.resource;

import com.linkpets.annotation.ResponseResult;
import com.linkpets.cms.coupon.service.ICmsCouponBatchService;
import com.linkpets.core.model.CmsCouponBatch;
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

@Api(value = "优惠券模板生产批次接口", tags = "活动优惠券接口")
@RestController
@ResponseResult
@RequestMapping("/couponBatch")
public class CmsCouponBatchResource {

    @Autowired
    private ICmsCouponBatchService couponBatchService;

    @GetMapping("")
    @ApiOperation(value = "获取优惠券批次详情")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "批次号", name = "batchNo", required = true, dataType = "String", paramType = "query")
    })
    public PlatformResult getCouponBatch(@RequestParam String batchNo) {
        CmsCouponBatch batch = couponBatchService.getCouponBatch(batchNo);
        return PlatformResult.success(batch);
    }

    @GetMapping("/batches")
    @ApiOperation(value = "分页查询以优惠券模板id,活动id,有效时间段为条件的优惠券批次列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券模板id", name = "couponId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "有效开始时间", name = "effectiveStart", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "有效结束时间", name = "effectiveEnd", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "页码", name = "pageNo", required = false, dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "页数", name = "pageSize", required = false, dataType = "int", paramType = "query", defaultValue = "20")
    })
    public PlatformResult getCouponBatchList(@RequestParam String couponId,
                                             @RequestParam String activityId,
                                             @RequestParam(required = false) String effectiveStart,
                                             @RequestParam(required = false) String effectiveEnd,
                                             @RequestParam int pageNo,
                                             @RequestParam int pageSize) {
        Map<String, Object> batchList = couponBatchService.getCouponBatchList(couponId, activityId, effectiveStart, effectiveEnd, pageNo, pageSize);
        return PlatformResult.success(batchList);
    }


    @PostMapping("")
    @ApiOperation(value = "创建优惠券批次")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券模板id", name = "couponId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "活动id", name = "activityId", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "有效开始时间", name = "effectiveStart", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "有效结束时间", name = "effectiveEnd", required = true, dataType = "String", paramType = "query"),
    })
    public PlatformResult createCouponBatch(@RequestParam String couponId,
                                            @RequestParam String activityId,
                                            @RequestParam(required = false) String effectiveStart,
                                            @RequestParam(required = false) String effectiveEnd) {
        String batchNo = couponBatchService.createCouponBatch(couponId, activityId, effectiveStart, effectiveEnd);
        return PlatformResult.success(batchNo);
    }


    @PostMapping("/coupon")
    @ApiOperation(value = "发行批次优惠券",notes = "返回优惠券数量生成成功数量")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券批次号", name = "batchNo", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "发行优惠券数量", name = "couponAmount", required = true, dataType = "int", paramType = "query")
    })
    public PlatformResult createCouponBatch(@RequestParam String batchNo,@RequestParam int couponAmount) {
        int  couponCreateAmount=couponBatchService.createCouponBatchItems(batchNo,couponAmount);
        return PlatformResult.success(couponCreateAmount);
    }

    @GetMapping("/batch/couponitems")
    @ApiOperation(value = "分页查询以批次号,优惠券状态为条件的优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "优惠券批次号", name = "batchNo", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(value = "优惠券状态", name = "status", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "页码", name = "pageNo", required = false, dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "页数", name = "pageSize", required = false, dataType = "int", paramType = "query", defaultValue = "20")
    })
    public PlatformResult getCouponItemsByBatchNo(@RequestParam String batchNo,
                                                  @RequestParam int status,
                                                  @RequestParam(required = false) int pageNo,
                                                  @RequestParam(required = false) int pageSize) {
        Map<String,Object>  couponBatchItems=couponBatchService.getCouponBatchItemsByBatchNo(batchNo,status,pageNo,pageSize);
        return PlatformResult.success(couponBatchItems);
    }


}
