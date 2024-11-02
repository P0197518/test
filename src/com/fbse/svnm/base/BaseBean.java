package com.fbse.svnm.base;

import org.apache.struts.action.ActionForm;
import com.fbse.common.FBSELogHandler;

/**
 * 共通のチェックする
 */
public class BaseBean extends ActionForm {

    /**
     * ログの宣言
     */
    protected FBSELogHandler log;

    /**
     * 画面のJavaScriptの変数の宣言
     */
    protected String hidError = "";

    /**
     * 画面のJavaScriptの変数を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * 画面のJavaScriptの変数を取得する。
     * @param    なし
     * @return    String    画面のJavaScriptの変数
     * @exception    なし
     */
    public String getHidError() {
        return hidError;
    }

    /**
     * 画面のJavaScriptの変数を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * 画面のJavaScriptの変数を設定する。
     * @param    hidError    画面のJavaScriptの変数
     * @return    なし
     * @exception    なし
     */
    public void setHidError(String hidError) {
        this.hidError = hidError;
    }

    /**
     * BaseBeanクラスのコンストラクタ
     */
    public BaseBean() {
        log = Common.getLog();
    }

    /**
     * 入力した文字列が"F9999"の形式かをチェックする。
     * 
     * @param    str    チェックされる文字
     * @return    boolean    チェックの結果
     */
    protected boolean isBangou(String str) {
        return str.matches("^F[0-9]{4}$");
    }

    /**
     * ログの変数を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * ログの変数を取得する。
     * @param    なし
     * @return    String    ログの変数
     * @exception    なし
     */
    public FBSELogHandler getLog() {
        return log;
    }

    /**
     * ログを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * ログを設定する。
     * @param    log    ログの変数
     * @return    なし
     * @exception    なし
     */
    public void setLog(FBSELogHandler log) {
        this.log = log;
    }

    /**
     * 間違う項目をフォーカスを取得して背景色を赤くするメッソド
     * @param    formname    フォーム名
     * @param    errorItem    間違う項目
     * @return    String    JavaScriptのコード
     */
    public String svnmError(String formname, String errorItem) {
        // JavaScriptのコード
        String message = "<script language='javascript'> " + "document."
                + formname + "." + errorItem + ".focus();" + "document."
                + formname + "." + errorItem
                + ".style.backgroundColor = \"red\";" + "</script>";
        // JavaScriptのコードを戻す
        return message;
    }

    /**
     * 半角英数字をチェックする
     * @param    str    チェックされる文字
     * @return    boolean    チェックの結果
     */
    protected boolean isNE(String str) {
        return str.matches("^[0-9a-zA-Z_]+$");
    }

}