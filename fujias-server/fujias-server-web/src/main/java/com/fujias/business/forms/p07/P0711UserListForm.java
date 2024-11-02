package com.fujias.business.forms.p07;

import com.fujias.common.db.entity.MUsers;
import com.fujias.common.entity.Pager;

/**
 * 用户一览的Form类
 * 
 * @author chenqiang
 *
 */
public class P0711UserListForm extends MUsers {
    private Pager pager;

    private String id;

    private String depid;

    private String department;

    private String roleid;

    private String typename;

    private String gendername;

    private String mode;

    private String type;

    private String delFlgText;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @return the depid
     */
    public String getDepid() {
        return depid;
    }

    /**
     * @param depid the depid to set
     */
    public void setDepid(String depid) {
        this.depid = depid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the typename
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename the typename to set
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * @return the gendername
     */
    public String getGendername() {
        return gendername;
    }

    /**
     * @param gendername the gendername to set
     */
    public void setGendername(String gendername) {
        this.gendername = gendername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDelFlgText() {
        return delFlgText;
    }

    public void setDelFlgText(String delFlgText) {
        this.delFlgText = delFlgText;
    }

}
