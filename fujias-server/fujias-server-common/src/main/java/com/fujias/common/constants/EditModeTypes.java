package com.fujias.common.constants;

/**
 * 承認状態の列挙です。
 * 
 * @author 陳強
 *
 */
public enum EditModeTypes {

    查看详情("view"), 添加("create"), 编辑("update"), 删除("delete"), 参照作成("copy"), 确定("confirm");

    private String code;

    EditModeTypes(String code) {
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
