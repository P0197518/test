package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum ImportFileTypes {

    問合せ("1"), 受注入力("2"), 手配依頼書("3"), 問合結果("4"), JANコード別入金("5"), 棚卸結果記入("6");

    private String code;

    ImportFileTypes(String code) {
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
    public static ImportFileTypes getByCode(String code) {
        for (ImportFileTypes item : ImportFileTypes.values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }

}
