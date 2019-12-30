package com.linkpets.core.respEntity.system;

import com.linkpets.core.model.SysRoute;
import lombok.Data;

import java.util.List;

/**
 * @Author Edie
 */
@Data
public class SysRouteRes {

    private String id;

    private String parentId;

    private String title;

    private String path;

    private String name;

    private String component;

    private String componentPath;

    private Boolean cache;

    private Integer sort;

    private List<SysRouteRes> children;

    public SysRouteRes() {
    }

    public SysRouteRes(String id, String parentId, String title, String path, String name, String component, String componentPath, int cache, Integer sort) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.path = path;
        this.name = name;
        this.component = component;
        this.componentPath = componentPath;
        this.cache = cache == 1 ? true : false;
        this.sort = sort;
    }

    public SysRouteRes(SysRoute sysRoute) {
        this.id = sysRoute.getId();
        this.parentId = sysRoute.getParentId();
        this.title = sysRoute.getTitle();
        this.path = sysRoute.getPath();
        this.name = sysRoute.getName();
        this.component = sysRoute.getComponent();
        this.componentPath = sysRoute.getComponentPath();
        this.cache = sysRoute.getCache() == 1 ? true : false;
        this.sort = sysRoute.getSort();
    }
}
