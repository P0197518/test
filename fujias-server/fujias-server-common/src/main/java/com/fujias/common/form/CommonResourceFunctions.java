package com.fujias.common.form;

import java.util.List;

/**
 * 画面处理数据
 * 
 * @author 陳強
 *
 */
public class CommonResourceFunctions {

    private String resourcecd;
    private String functioncd;
    private String elementid;

    private List<CommonResourceFunctions> elementids;

    public String getResourcecd() {
        return resourcecd;
    }

    public void setResourcecd(String resourcecd) {
        this.resourcecd = resourcecd;
    }

    public String getFunctioncd() {
        return functioncd;
    }

    public void setFunctioncd(String functioncd) {
        this.functioncd = functioncd;
    }

    public String getElementid() {
        return elementid;
    }

    public void setElementid(String elementid) {
        this.elementid = elementid;
    }

    public List<CommonResourceFunctions> getElementids() {
        return elementids;
    }

    public void setElementids(List<CommonResourceFunctions> elementids) {
        this.elementids = elementids;
    }

}
