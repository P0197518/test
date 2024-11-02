package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum BatchLogStatusTypes {

    実行中("1"), 実行成功終了("2"), エラー終了("3");

    private String code;

    BatchLogStatusTypes(String code) {
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
