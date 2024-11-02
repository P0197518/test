package com.fujias.business.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum DownloadPdfTypes {

    REDBOOKLABEL("P0700202"), YAXUANLABEL("P07002"), POSITIONS("P07007");

    private String code;

    DownloadPdfTypes(String code) {
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
