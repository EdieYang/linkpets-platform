package com.linkpets.cms.coupon.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkpets.cms.coupon.dao.CmsCouponBatchCustomMapper;
import com.linkpets.cms.coupon.dao.CmsCouponBatchItemCustomMapper;
import com.linkpets.cms.coupon.service.ICmsCouponBatchService;
import com.linkpets.core.dao.CmsCouponBatchMapper;
import com.linkpets.core.model.CmsCouponBatch;
import com.linkpets.core.model.CmsCouponBatchItem;
import com.linkpets.util.DateUtils;
import com.linkpets.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author SteveYang
 * @date 2019/3/27
 */
@Service
public class CmsCouponBatchServiceImpl implements ICmsCouponBatchService {

    @Autowired
    private CmsCouponBatchMapper couponBatchMapper;

    @Autowired
    private CmsCouponBatchCustomMapper couponBatchCustomMapper;

    @Autowired
    private CmsCouponBatchItemCustomMapper couponBatchItemCustomMapper;

    private static final int COUPON_STATUS_UNUSED = 0;

    @Override
    public CmsCouponBatch getCouponBatch(String batchNo) {
        return couponBatchMapper.selectByPrimaryKey(batchNo);
    }

    @Override
    public Map<String, Object> getCouponBatchList(String couponId, String activityId, String effectiveStart, String effectiveEnd, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap<>();

        PageHelper.startPage(pageNo, pageSize);
        List<CmsCouponBatch> couponBatches = couponBatchCustomMapper.getCmsCouponBatchList(couponId, activityId, effectiveStart, effectiveEnd);
        PageInfo<CmsCouponBatch> page = new PageInfo<>(couponBatches);
        result.put("page", page.getPageNum());
        result.put("records", page.getTotal());
        result.put("rows", couponBatches);
        return result;
    }

    @Override
    public String createCouponBatch(String couponId, String activityId, String effectiveStart, String effectiveEnd) {
        CmsCouponBatch couponBatch = new CmsCouponBatch();
        couponBatch.setCouponId(couponId);
        couponBatch.setActivityId(activityId);
        couponBatch.setBatchNo(generateBatchNo(couponId, activityId));
        couponBatch.setEffectiveStart(DateUtils.getFormatDateTime(effectiveStart));
        couponBatch.setEffectiveEnd(DateUtils.getFormatDateTime(effectiveEnd));
        couponBatch.setCreateTime(new Date());
        couponBatchMapper.insertSelective(couponBatch);
        return couponBatch.getBatchNo();
    }

    @Override
    public int createCouponBatchItems(String batchNo, int couponAmount) {
        List<CmsCouponBatchItem> items = new ArrayList<>();
        int index = 0;
        while (index < couponAmount) {
            CmsCouponBatchItem cmsCouponBatchItem = new CmsCouponBatchItem();
            cmsCouponBatchItem.setBatchNo(batchNo);
            cmsCouponBatchItem.setCouponItemId(UUIDUtils.getUUID());
            cmsCouponBatchItem.setCreateTime(new Date());
            cmsCouponBatchItem.setStatus(COUPON_STATUS_UNUSED);
            index++;
            items.add(cmsCouponBatchItem);
        }

        int insertAmount = couponBatchCustomMapper.insertBatchCouponItem(items);


        return insertAmount;
    }

    @Override
    public Map<String, Object> getCouponBatchItemsByBatchNo(String batchNo, int status, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap<>();

        PageHelper.startPage(pageNo, pageSize);
        List<CmsCouponBatchItem> couponBatchItems = couponBatchItemCustomMapper.getCmsCouponBatchItemList(batchNo, status);
        PageInfo<CmsCouponBatchItem> page = new PageInfo<>(couponBatchItems);
        result.put("page", page.getPageNum());
        result.put("records", page.getTotal());
        result.put("rows", couponBatchItems);
        return result;
    }

    /**
     * 优惠券批次号命名规则:发行日期年月日+活动id前8位字符+优惠券模板id前4位字符
     *
     * @param couponId
     * @param activityId
     * @return
     */
    public String generateBatchNo(String couponId, String activityId) {
        String batchNo = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        batchNo = format.format(new Date()) + "-" + activityId.substring(0, 7) + "-" + couponId.substring(0, 3);
        return batchNo;
    }

}
