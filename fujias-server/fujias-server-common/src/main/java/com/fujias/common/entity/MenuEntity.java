package com.fujias.common.entity;

/**
 * 用户权限数据
 * 
 * @author 陳強
 *
 */
public class MenuEntity {

    private String resourceid;
    private String resourcename;
    private String parentid;
    private String resourceurl;
    private String imgvalue;

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getImgvalue() {
        return imgvalue;
    }

    public void setImgvalue(String imgvalue) {
        this.imgvalue = imgvalue;
    }

    public String getResourceurl() {
        return resourceurl;
    }

    public void setResourceurl(String resourceurl) {
        this.resourceurl = resourceurl;
    }

}
