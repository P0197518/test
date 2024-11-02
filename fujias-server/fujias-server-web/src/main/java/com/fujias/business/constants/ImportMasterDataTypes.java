package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum ImportMasterDataTypes {

    物料一览("1001");

    private String code;

    ImportMasterDataTypes(String code) {
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

    /**
     * 根据code获取枚举
     * 
     * @param code code
     * @return 枚举
     */
    public static ImportMasterDataTypes getByCode(String code) {
        for (ImportMasterDataTypes item : ImportMasterDataTypes.values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }

}
