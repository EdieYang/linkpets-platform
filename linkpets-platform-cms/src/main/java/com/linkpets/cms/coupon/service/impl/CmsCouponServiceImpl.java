package com.linkpets.cms.coupon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.coupon.service.ICmsCouponService;
import com.linkpets.core.dao.CmsCouponMapper;
import com.linkpets.core.model.CmsCoupon;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
@Service
public class CmsCouponServiceImpl implements ICmsCouponService {


    @Resource
    private CmsCouponMapper couponMapper;


    @Override
    public String createCoupon(String couponName, String couponEnName, int couponType, String couponRule, String couponRemind, String orgId) {
        CmsCoupon coupon = new CmsCoupon();
        coupon.setCouponId(UUIDUtils.getUUID());
        coupon.setCouponName(couponName);
        coupon.setCouponEnName(couponEnName);
        coupon.setCouponType(couponType);
        coupon.setCouponRemind(couponRemind);
        coupon.setCouponRule(couponRule);
        coupon.setOrgId(orgId);
        coupon.setCreateTime(new Date());
        couponMapper.insertSelective(coupon);
        return coupon.getCouponId();
    }

    @Override
    public CmsCoupon getCouponByCouponId(String couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }

    @Override
    public Map<String, Object> getCouponList(String orgId, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        PageHelper.startPage(pageNo, pageSize);

        List<CmsCoupon> coupons = couponMapper.getCouponList(orgId);
        PageInfo<CmsCoupon> page = new PageInfo<>(coupons);
        result.put("page", page.getPageNum());
        result.put("records", page.getTotal());
        result.put("rows", coupons);
        return result;
    }
}
