package com.fujias.business.common.entity;

/**
 * 部门结构体定义
 * 
 * @author chenqiang
 *
 */
public class CommonDepEntity {

    private String depid;

    private String name;

    private String defualt;

    private String depparentid;

    private String userid;

    private boolean withUser;

    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefualt() {
        return defualt;
    }

    public void setDefualt(String defualt) {
        this.defualt = defualt;
    }

    public String getDepparentid() {
        return depparentid;
    }

    public void setDepparentid(String depparentid) {
        this.depparentid = depparentid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isWithUser() {
        return withUser;
    }

    public void setWithUser(boolean withUser) {
        this.withUser = withUser;
    }

}
