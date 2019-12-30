package com.linkpets.cms.manage.service.impl;

import com.linkpets.cms.manage.service.ISysRouteService;
import com.linkpets.core.dao.SysRouteMapper;
import com.linkpets.core.model.SysRoute;
import com.linkpets.core.respEntity.system.SysLoginRouteRes;
import com.linkpets.core.respEntity.system.SysRouteRes;
import com.linkpets.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysRouteServiceImpl implements ISysRouteService {

    @Resource
    private SysRouteMapper sysRouteMapper;

    @Override
    public List<SysRouteRes> getSysRouteList() {
        List<SysRoute> sysRouteList = sysRouteMapper.getSysRouteListByParentId("0");
        List<SysRouteRes> sysRouteResList = new ArrayList<>();
        sysRouteList.forEach(sysRoute -> {
            SysRouteRes sysRouteRes = new SysRouteRes(sysRoute);
            List<SysRoute> sysRouteChildrenList = sysRouteMapper.getSysRouteListByParentId(sysRoute.getId());
            List<SysRouteRes> sysRouteResChildrenList = new ArrayList<>();
            sysRouteChildrenList.forEach(sysRouteChildren -> {
                SysRouteRes sysRouteResChildren = new SysRouteRes(sysRouteChildren);
                sysRouteResChildrenList.add(sysRouteResChildren);
            });
            sysRouteRes.setChildren(sysRouteResChildrenList);
            sysRouteResList.add(sysRouteRes);
        });
        return sysRouteResList;
    }

    @Override
    public List<SysLoginRouteRes> getSysLoginRouteList() {
        List<SysRoute> sysRouteList = sysRouteMapper.getSysRouteListByParentId("0");
        List<SysLoginRouteRes> sysRouteResList = new ArrayList<>();
        sysRouteList.forEach(sysRoute -> {
            SysLoginRouteRes sysRouteRes = new SysLoginRouteRes(sysRoute);
            List<SysRoute> sysRouteChildrenList = sysRouteMapper.getSysRouteListByParentId(sysRoute.getId());
            List<SysLoginRouteRes> sysRouteResChildrenList = new ArrayList<>();
            sysRouteChildrenList.forEach(sysRouteChildren -> {
                SysLoginRouteRes sysRouteResChildren = new SysLoginRouteRes(sysRouteChildren);
                Map<String, Object> meta = new HashMap<>();
                meta.put("title", sysRouteChildren.getTitle());
                meta.put("cache", sysRouteChildren.getCache() == 1 ? true : false);
                sysRouteResChildren.setMeta(meta);
                sysRouteResChildrenList.add(sysRouteResChildren);
            });
            sysRouteRes.setChildren(sysRouteResChildrenList);
            sysRouteResList.add(sysRouteRes);
        });
        return sysRouteResList;
    }

    @Override
    public SysRouteRes getSysRoute(String id) {
        SysRoute sysRoute = sysRouteMapper.selectByPrimaryKey(id);
        List<SysRoute> sysRouteList = sysRouteMapper.getSysRouteListByParentId(sysRoute.getId());
        List<SysRouteRes> sysRouteResList = new ArrayList<>();
        sysRouteList.forEach(item -> {
            SysRouteRes sysRouteRes = new SysRouteRes(item);
            sysRouteResList.add(sysRouteRes);
        });
        SysRouteRes sysRouteRes = new SysRouteRes(sysRoute);
        sysRouteRes.setChildren(sysRouteResList);
        return sysRouteRes;
    }

    @Override
    public String crtSysRoute(SysRoute sysRoute) {
        String routeId = UUIDUtils.getId();
        sysRoute.setId(routeId);
        sysRoute.setCreateDate(new Date());
        sysRouteMapper.insertSelective(sysRoute);
        return routeId;
    }

    @Override
    public void uptSysRoute(SysRoute sysRoute) {
        sysRouteMapper.updateByPrimaryKeySelective(sysRoute);
    }

    @Override
    public void delSysRoute(String routeId) {
        sysRouteMapper.delSysRoute(routeId);
    }
}
