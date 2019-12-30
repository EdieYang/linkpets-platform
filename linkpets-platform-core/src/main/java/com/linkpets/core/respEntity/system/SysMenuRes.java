package com.linkpets.core.respEntity.system;

import com.linkpets.core.model.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * @Author Edie
 */
@Data
public class SysMenuRes {

    private String id;

    private String parentId;

    private String title;

    private String path;

    private String icon;

    private Integer sort;

    private List<SysMenu> children;

    public SysMenuRes(String id, String parentId, String title, String path, String icon, Integer sort) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.path = path;
        this.icon = icon;
        this.sort = sort;
    }

    public SysMenuRes(SysMenu sysMenu) {
        this.id = sysMenu.getId();
        this.parentId = sysMenu.getParentId();
        this.title = sysMenu.getTitle();
        this.path = sysMenu.getPath();
        this.icon = sysMenu.getIcon();
        this.sort = sysMenu.getSort();
    }
}
