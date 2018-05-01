package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//系统菜单
@Getter
@Setter
public class SystemMenu extends BaseDomain implements IJsonObject {
    private String name;//菜单名称
    private String url;//菜单链接,子菜单有，父菜单没有
    private String sn;//菜单编号，父菜单有，子菜单没有，用来加载自己的子菜单
    private SystemMenu parent;//上一级菜单
    private List<SystemMenu> child = new ArrayList<>();//下一级菜单

    @Override
    public Object toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("name", name);
        json.put("action", url);
        json.put("pId", this.parent != null ? this.parent.getId() : null);
        return json;
    }
}
