package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum OccupyTypeStatus {

    完成品库存("1"), 完成品在制("2"), 原材料库存("3");

    private String code;

    OccupyTypeStatus(String code) {
        this.code = code;
    }

    /**
     * codeを取得する。
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
