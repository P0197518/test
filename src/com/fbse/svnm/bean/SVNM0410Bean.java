/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :従業員検索画面
 * モジュール名        :SVNM0410Bean.java
 * 担当者           :FBSE)馬春晶
 * 作成日           :2008.12.17
 * 機能概要          :検索した従業員情報を保存する。
 *******************************************************************
 * 変更履歴    版数:    変更日       :変更者
 *           V1.0:   2008.12.17  :FBSE)馬春晶
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.bean;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import com.fbse.svnm.base.BaseBean;

/**
 * <HR>
 * 検索した従業員情報を保存する。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>検索した従業員情報を保存する。</li>
 * <li>入力値のgetとsetメソッドを生成する</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0410Bean extends BaseBean {

    /** 従業員番号 */
    private String userNo = "";

    /** 従業員名前 */
    private String userName = "";

    /** 従業員登録名 */
    private String svnLoginName = "";
    
    /** 権限値 */
    private String privilege = "";

    /** 従業員メール */
    private String mail = "";

    /** SVNパスワード */
    private String svnLoginPassword = "";
    
    /** システム登録パスワード */
    private String systemPassword = "";

    /** コメント */
    private String comment = "";
    
    /** 操作状態 */
    private String buttonValue = "";
    
    /** 状態 */
    private String stateValue = "";
    
    /** 引数 */
    private String paramValue = "";

    /** ダウンリスト（状態） */
    private SelectBean selectBean[]; 

    /** ダウンリスト（権限） */
    private SelectBean privilegeBean[]; 

    /**
     * ダウンリスト（状態）を取得する。<p>
     * <b>処理概要:</b><br>
     * ダウンリスト（状態）を取得する。
     * @param    なし
     * @return    SelectBean[]    コンボボックス(プルダウンリスト)（状態）
     * @exception    なし
     */
    public SelectBean[] getSelectBean() {
        return selectBean;
    }

    /**
     * ダウンリスト（状態）を設定する。<p>
     * <b>処理概要:</b><br>
     * ダウンリスト（状態）を設定する。
     * @param    selectBean    ダウンリスト（状態）
     * @return    なし
     * @exception    なし
     */
    public void setSelectBean(SelectBean[] selectBean) {
        this.selectBean = selectBean;
    }

    /**
     * 操作状態を取得する。<p>
     * <b>処理概要:</b><br>
     * 操作状態を取得する。
     * @param    なし
     * @return    String    操作状態
     * @exception    なし
     */
    public String getButtonValue() {
        return buttonValue;
    }

    /**
     * 状態値を取得する。<p>
     * <b>処理概要:</b><br>
     * 状態値を取得する。
     * @param    なし
     * @return    String    状態値
     * @exception    なし
     */
    public String getStateValue() {
        return stateValue;
    }

    /**
     * 引数値を取得する。<p>
     * <b>処理概要:</b><br>
     * 引数値を取得する。
     * @param    なし
     * @return    String    引数値
     * @exception    なし
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 操作状態を設定する。<p>
     * <b>処理概要:</b><br>
     * 操作状態を設定する。
     * @param    buttonValue    操作状態
     * @return    なし
     * @exception    なし
     */
    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    /**
     * 状態を設定する。<p>
     * <b>処理概要:</b><br>
     * 状態を設定する。
     * @param    stateValue    状態
     * @return    なし
     * @exception    なし
     */
    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    /**
     * 引数を設定する。<p>
     * <b>処理概要:</b><br>
     * 引数を設定する。
     * @param    paramValue    引数
     * @return    なし
     * @exception    なし
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * コメントを取得する。<p>
     * <b>処理概要:</b><br>
     * コメントを取得する。
     * @param    なし
     * @return    String    コメント
     * @exception    なし
     */
    public String getComment() {
        return comment;
    }

    /**
     * コメントを設定する。<p>
     * <b>処理概要:</b><br>
     * コメントを設定する。
     * @param    comment    コメント
     * @return    なし
     * @exception    なし
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * メールを取得する。<p>
     * <b>処理概要:</b><br>
     * メールを取得する。
     * @param    なし
     * @return    String    メール
     * @exception    なし
     */
    public String getMail() {
        return mail;
    }

    /**
     * メールを設定する。<p>
     * <b>処理概要:</b><br>
     * メールを設定する。
     * @param    mail    メール
     * @return    なし
     * @exception    なし
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * SVN登録名を取得する。<p>
     * <b>処理概要:</b><br>
     * SVN登録名を取得する。
     * @param    なし
     * @return    String    SVN登録名
     * @exception    なし
     */
    public String getSvnLoginName() {
        return svnLoginName;
    }

    /**
     * SVN登録名を設定する。<p>
     * <b>処理概要:</b><br>
     * SVN登録名を設定する。
     * @param    svnLoginName    SVN登録名
     * @return    なし
     * @exception    なし
     */
    public void setSvnLoginName(String svnLoginName) {
        this.svnLoginName = svnLoginName;
    }

    /**
     * SVNパスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * 登録パスワードを取得する。
     * @param    なし
     * @return    String    SVNパスワード
     * @exception    なし
     */
    public String getSvnLoginPassword() {
        return svnLoginPassword;
    }

    /**
     * SVNパスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * SVNパスワードを設定する。
     * @param    svnLoginPassword    SVNパスワード
     * @return    なし
     * @exception    なし
     */
    public void setSvnLoginPassword(String svnLoginPassword) {
        this.svnLoginPassword = svnLoginPassword;
    }

    /**
     * 従業員名前を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を取得する。
     * @param    なし
     * @return    String    従業員名前
     * @exception    なし
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 従業員名前を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を設定する。
     * @param    userName    従業員名前
     * @return    なし
     * @exception    なし
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 従業員番号を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を取得する。
     * @param    なし
     * @return    String    従業員番号
     * @exception    なし
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * 従業員番号を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を設定する。
     * @param    userNo    従業員番号
     * @return    なし
     * @exception    なし
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 権限値を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限値を取得する。
     * @param    なし
     * @return    String    権限値
     * @exception    なし
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 権限配列を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限配列を取得する。
     * @param    なし
     * @return    SelectBean[]    権限配列
     * @exception    なし
     */
    public SelectBean[] getPrivilegeBean() {
        return privilegeBean;
    }

    /**
     * 権限値を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限値を設定する
     * @param    privilege    権限値
     * @return    なし
     * @exception    なし
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * 権限配列を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限配列を設定する
     * @param    privilegeBean    権限配列
     * @return    なし
     * @exception    なし
     */
    public void setPrivilegeBean(SelectBean[] privilegeBean) {
        this.privilegeBean = privilegeBean;
    }

    /**
     * システム登録パスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * システム登録パスワードを取得する。
     * @param    なし
     * @return    String    システム登録パスワード
     * @exception    なし
     */
    public String getSystemPassword() {
        return systemPassword;
    }

    /**
     * システム登録パスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * システム登録パスワードを設定する
     * @param    systemPassword    システム登録パスワード
     * @return    なし
     * @exception    なし
     */
    public void setSystemPassword(String systemPassword) {
        this.systemPassword = systemPassword;
    }

    /**
     * 入力されたデータを検証する。
     * @param    mapping    画面の遷移
     * @param    request    リクエスト
     * @return    ActionErrors    エラーメッセージ
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
        // ログを出力
        log.printLog("INFO", "SVNM0410Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "validate", "Start");
        // ログを出力
        log.printLog("INFO", "SVNM0410Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "validate", "End");
        return null;
    }
}