package com.linkpets.cms.manage.service;

import com.linkpets.core.model.SysRoute;
import com.linkpets.core.respEntity.system.SysLoginRouteRes;
import com.linkpets.core.respEntity.system.SysRouteRes;

import java.util.List;

public interface ISysRouteService {

    List<SysRouteRes> getSysRouteList();

    List<SysLoginRouteRes> getSysLoginRouteList();

    SysRouteRes getSysRoute(String id);

    String crtSysRoute(SysRoute sysRoute);

    void uptSysRoute(SysRoute sysRoute);

    void delSysRoute(String routeId);
}
