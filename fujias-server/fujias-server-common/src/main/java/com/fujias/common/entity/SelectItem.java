package com.fujias.common.entity;

/**
 * ドロップダウンなど選択項目の値の定義クラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
public class SelectItem {
    private String value;
    private String text;
    private String option1;
    private String option2;

    /**
     * コンストラクタです。
     */
    public SelectItem() {

    }

    /**
     * コンストラクタです。
     * 
     * @param value 値
     * @param text 表示内容
     */
    public SelectItem(String value, String text) {
        this.value = value;
        this.text = text;
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
     * textを取得する。
     * 
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * textを設定する。
     * 
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 空白Itemを作成する
     * 
     * @return 空白Item
     */
    public static SelectItem emptyItem() {
        return new SelectItem("", "");
    }

    /**
     * optionを取得する。
     * 
     * @return the option
     */
    public String getOption1() {
        return option1;
    }

    /**
     * optionを設定する。
     * 
     * @param option1 the option1 to set
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }
}
