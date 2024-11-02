package com.fujias.common.form;

import com.fujias.common.entity.MenuEntity;

/**
 * MenuForm
 * 
 * @author fujias
 *
 */
public class MenuForm extends MenuEntity {

    private String id;
    private String employeeid;
    private String name;
    private String nowdate;

    public String getNowdate() {
        return nowdate;
    }

    public void setNowdate(String nowdate) {
        this.nowdate = nowdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
