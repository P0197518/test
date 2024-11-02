package com.fujias.common.entity;

import java.util.List;

/**
 * ドロップダウンなど選択項目の値の定義クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public class TreeItem {
    private String value;
    private String text;
    private String option;
    private String parentId;

    private List<TreeItem> children;

    /**
     * コンストラクタです。
     */
    public TreeItem() {

    }

    /**
     * コンストラクタです。
     * 
     * @param value 値
     * @param text 表示内容
     */
    public TreeItem(String value, String text) {
        this.value = value;
        this.setText(text);
    }

    /**
     * valueを取得する。
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * valueを設定する。
     * 
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 空白Itemを作成する
     * 
     * @return 空白Item
     */
    public static TreeItem emptyItem() {
        return new TreeItem("", "");
    }

    /**
     * optionを取得する。
     * 
     * @return the option
     */
    public String getOption() {
        return option;
    }

    /**
     * optionを設定する。
     * 
     * @param option the option to set
     */
    public void setOption(String option) {
        this.option = option;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeItem> children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
