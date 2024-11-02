/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :権限検索画面
 * モジュール名        :SVNM0310Bean.java
 * 担当者            :FBSE)宋福明
 * 作成日            :2008.12.17
 * 機能概要           :画面で項目のチェックを行う。
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.17 :FBSE)宋福明
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.bean;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.fbse.svnm.base.BaseBean;

/**
 * <HR>
 * 権限検索画面の入力値を取得<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>権限検索の入力値を取得。</li>
 * <li>入力値のgetとsetメソッドを生成する。</li>
 * <li>入力値有効性のチェックを行う。</li>
 * <li>画面値をリセットする</li>
 * </ul>
 *
 * @version V1.0
 * @author  FBSE)宋福明
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0310Bean extends BaseBean {

    /** 権限番号の宣言と初期化 */
    private String privilegeNo = "";

    /** 権限名の宣言と初期化 */
    private String privilegeName = "";

    /** コメントの宣言と初期化 */
    private String privilegeComment = "";
    
    /** ボタンの宣言と初期化 */
    private String buttonNo = "";
    
    /** 更新時間と操作の宣言と初期化 */
    private String paramValue = "";

    /**
     * 更新操作時該当データを更新する時のシステム日付と操作類型を取得する。<p>
     * <b>処理概要:</b><br>
     * 更新時間と操作を取得する。
     * @param    なし
     * @return    String    更新操作時該当データの更新時間と操作類型。
     * @exception    なし
     */    
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 更新操作時該当データの更新時間と操作類型を設定する。<p>
     * <b>処理概要:</b><br>
     * 更新時間と操作を設定する。
     * @param    paramValue    更新操作時該当データの更新時間と操作類型
     * @return    なし
     * @exception    なし
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * ボタンの値を取得する。<p>
     * <b>処理概要:</b><br>
     * ボタン値を取得する。
     * @param    なし
     * @return    String    ボタン値。
     * @exception    なし
     */    
    public String getButtonNo() {
        return buttonNo;
    }

    /**
     * ボタン値を設定する。<p>
     * <b>処理概要:</b><br>
     * ボタン値を設定する。
     * @param    buttonNo    ボタン値
     * @return    なし
     * @exception    なし
     */
    public void setButtonNo(String buttonNo) {
        this.buttonNo = buttonNo;
    }

    /**
     * 権限番号を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限番号を取得する。
     * @param    なし
     * @return    String    権限番号。
     * @exception    なし
     */    
    public String getPrivilegeNo() {
        return privilegeNo;
    }

    /**
     * 権限番号を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限番号を設定する。
     * @param    privilegeNo    権限番号
     * @return    なし
     * @exception    なし
     */
    public void setPrivilegeNo(String privilegeNo) {
        this.privilegeNo = privilegeNo;
    }

    /**
     * 権限名を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限名を取得する。
     * @param    なし
     * @return    String    権限名。
     * @exception    なし
     */
    public String getPrivilegeName() {
        return privilegeName;
    }

    /**
     * 権限名を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限名を設定する。
     * @param    privilegeName    権限名
     * @return    なし
     * @exception    なし
     */    
    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    /**
     * コメントを取得する。<p>
     * <b>処理概要:</b><br>
     * コメントを取得する。
     * @param    なし
     * @return    String    コメント。
     * @exception    なし
     */
    public String getPrivilegeComment() {
        return privilegeComment;
    }

    /**
     * コメントを設定する。<p>
     * <b>処理概要:</b><br>
     * コメントを設定する。
     * @param    privilegeComment    コメント
     * @return    なし
     * @exception    なし
     */
    public void setPrivilegeComment(String privilegeComment) {
        this.privilegeComment = privilegeComment;
    }

    /**
     * 入力されたデータを検証する。
     * @param    mapping    ActionMapping
     * @param    request    HttpServletRequest
     * @return    errors    ActionErrors。
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        // ログ出力
        log.printLog("INFO", "SVNM0310Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "validate", "Start");
        // ログ出力
        log.printLog("INFO", "SVNM0310Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "validate", "End");
        return null;
    }
}