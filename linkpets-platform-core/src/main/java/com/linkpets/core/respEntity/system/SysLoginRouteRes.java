package com.linkpets.core.respEntity.system;

import com.linkpets.core.model.SysRoute;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author Edie
 */
@Data
public class SysLoginRouteRes {

    private String id;

    private String parentId;

    private String path;

    private String name;

    private String component;

    private String componentPath;

    private Map<String, Object> meta;

    private Integer sort;

    private List<SysLoginRouteRes> children;

    public SysLoginRouteRes() {
    }

    public SysLoginRouteRes(String id, String parentId, String path, String name, String component, String componentPath, Integer sort) {
        this.id = id;
        this.parentId = parentId;
        this.path = path;
        this.name = name;
        this.component = component;
        this.componentPath = componentPath;
        this.sort = sort;
    }

    public SysLoginRouteRes(SysRoute sysRoute) {
        this.id = sysRoute.getId();
        this.parentId = sysRoute.getParentId();
        this.path = sysRoute.getPath();
        this.name = sysRoute.getName();
        this.component = sysRoute.getComponent();
        this.componentPath = sysRoute.getComponentPath();
        this.sort = sysRoute.getSort();
    }
}
