package com.fujias.business.forms.p07;

import java.util.List;

/**
 * 角色资源管理的单条显示用Form类
 * 
 * @author chenqiang
 *
 */
public class P0713PermissionListItem {
    private String parentId;

    private String parentName;
    private String resourceId;
    private String resourceName;
    private boolean useFlag;
    private String type;

    private List<P0713PermissionListItem> functionList;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public boolean isUseFlag() {
        return useFlag;
    }

    public void setUseFlag(boolean useFlag) {
        this.useFlag = useFlag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<P0713PermissionListItem> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<P0713PermissionListItem> functionList) {
        this.functionList = functionList;
    }

}
