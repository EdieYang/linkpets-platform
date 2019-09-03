package com.linkpets.cms.coupon.service.impl;

import com.linkpets.cms.coupon.service.ICouponBatchItemService;
import com.linkpets.core.dao.CmsCouponBatchItemMapper;
import com.linkpets.core.model.CmsCouponBatchItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */

@Service
public class CouponBatchItemServiceImpl implements ICouponBatchItemService {

    @Resource
    private CmsCouponBatchItemMapper couponBatchItemMapper;

    @Override
    public List<CmsCouponBatchItem> getCouponBatchItemListByUserId(String userId, int status) {
        return couponBatchItemMapper.getCouponBatchItemListByUserId(userId, status);
    }

    @Override
    public CmsCouponBatchItem getCouponBatchItemByCouponItemId(String couponItemId) {
        return couponBatchItemMapper.getCouponBatchItemByCouponItemId(couponItemId);
    }

    @Override
    public CmsCouponBatchItem updateCouponBatchItemByCouponItemId(String couponItemId, String certifyChainId) {
        int result = couponBatchItemMapper.updateCouponBatchItemByCouponItemId(couponItemId, certifyChainId);
        if (result > 0) {
            return couponBatchItemMapper.getCouponBatchItemByCouponItemId(couponItemId);
        }
        return null;
    }

    @Override
    public List<CmsCouponBatchItem> getCouponBatchItemListByChainId(String certifyChainId) {
        return couponBatchItemMapper.getCouponBatchItemListByChainId(certifyChainId);
    }
}
