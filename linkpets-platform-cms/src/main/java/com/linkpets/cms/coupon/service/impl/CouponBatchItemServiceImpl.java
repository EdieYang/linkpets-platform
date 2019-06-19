package com.linkpets.cms.coupon.service.impl;

import com.linkpets.cms.coupon.dao.CmsCouponBatchItemCustomMapper;
import com.linkpets.cms.coupon.model.CmsCouponBatchCustomItem;
import com.linkpets.cms.coupon.service.ICouponBatchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SteveYang
 * @date 2019/3/27
 */

@Service
public class CouponBatchItemServiceImpl  implements ICouponBatchItemService{

    @Autowired
    private CmsCouponBatchItemCustomMapper couponBatchItemCustomMapper;

    @Override
    public List<CmsCouponBatchCustomItem> getCouponBatchItemListByUserId(String userId, int status) {
        return couponBatchItemCustomMapper.getCouponBatchItemListByUserId(userId,status);
    }

    @Override
    public CmsCouponBatchCustomItem getCouponBatchItemByCouponItemId(String couponItemId) {
        return couponBatchItemCustomMapper.getCouponBatchItemByCouponItemId(couponItemId);
    }

    @Override
    public CmsCouponBatchCustomItem updateCouponBatchItemByCouponItemId(String couponItemId,String certifyChainId) {
        int result= couponBatchItemCustomMapper.updateCouponBatchItemByCouponItemId(couponItemId,certifyChainId);
        if(result>0){
           return  couponBatchItemCustomMapper.getCouponBatchItemByCouponItemId(couponItemId);
        }
        return null;
    }

    @Override
    public List<CmsCouponBatchCustomItem> getCouponBatchItemListByChainId(String certifyChainId) {
        return couponBatchItemCustomMapper.getCouponBatchItemListByChainId( certifyChainId);
    }
}
