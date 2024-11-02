package com.fujias.business.forms.s01;

import com.fujias.common.db.entity.MKbn;
import com.fujias.common.entity.Pager;

/**
 * 区分一览的Form类
 * 
 * @author 薛秋凤
 *
 */
public class S0101101CodeListForm extends MKbn {
    private Pager pager;

    /** 是否删除 */
    private String delflgt;

    private String department;

    private String name;

    private String roleid;

    private String mode;

    private String text;

    private String value;

    private String bigclassify;
    private String smallclassify;

    private String id;

    public String getBigclassify() {
        return bigclassify;
    }

    public void setBigclassify(String bigclassify) {
        this.bigclassify = bigclassify;
    }

    public String getSmallclassify() {
        return smallclassify;
    }

    public void setSmallclassify(String smallclassify) {
        this.smallclassify = smallclassify;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * pagerを取得する。
     * 
     * @return the pager
     */
    public Pager getPager() {
        return pager;
    }

    /**
     * pagerを設定する。
     * 
     * @param pager the pager to set
     */
    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDelflgt() {
        return delflgt;
    }

    public void setDelflgt(String delflgt) {
        this.delflgt = delflgt;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
