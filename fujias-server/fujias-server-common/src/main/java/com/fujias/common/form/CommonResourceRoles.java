package com.fujias.common.form;

import java.util.List;

/**
 * 用户权限数据
 * 
 * @author 陳強
 *
 */
public class CommonResourceRoles {

    private String resourcecd;
    private String path;

    private List<String> roles;

    /**
     * 构造函数
     */
    public CommonResourceRoles() {

    }

    /**
     * 构造函数
     * 
     * @param path path
     * @param roles roles
     */
    public CommonResourceRoles(String path, List<String> roles) {
        this.path = path;
        this.roles = roles;
    }

    /**
     * pathを取得する。
     * 
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * pathを設定する。
     * 
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * rolesを取得する。
     * 
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * rolesを設定する。
     * 
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getResourcecd() {
        return resourcecd;
    }

    public void setResourcecd(String resourcecd) {
        this.resourcecd = resourcecd;
    }

}
