/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名     :パスワード変更
 * プログラム名       :システムユーザパスワード編集
 * モジュール名       :SVNM0610Bean.java
 * 担当者             :FBSE)王志龍
 * 作成日             :2008.12.17
 * 機能概要           :画面で項目のチェックを行う。
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)王志龍
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import com.fbse.common.FBSEChecker;
import com.fbse.svnm.base.BaseBean;

/**
 * <HR>
 * SVNM0610ビーンである。<P><BR>
 * <ul>
 * <li>データを取得する。</li>
 * <li>チェックを行う。</li>
 * </ul>
 * @version 1.0
 * @author  FBSE)王志龍
 * @see     com.fbse.svnm.bean.BaseBean
 */
public class SVNM0610Bean extends BaseBean {

    /** クラス名の宣言と初期化*/
    private static String className = "SVNM0610Bean";

    /** 従業員番号の宣言と初期化*/
    private String sysUserNo = null;

    /** 元のパスワードの宣言と初期化*/
    private String oldPassword = null;

    /** 新パスワードの宣言と初期化*/
    private String newPassword = null;

    /** 新パスワード確認の宣言と初期化*/
    private String kakuninnPassword = null;

    /**
     * 従業員番号値を取得する。<p>
     * <b>処理概要:</b><br>
     * sysUserNoを取得する。
     * @param    なし
     * @return    String    従業員番号;
     * @exception    なし
     */
    public String getSysUserNo() {
        return sysUserNo;
    }

    /**
     * 従業員番号値を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号値を設定する。
     * @param    sysUserNo    従業員番号値
     * @return    なし
     * @exception    なし
     */
    public void setSysUserNo(String sysUserNo) {
        this.sysUserNo = sysUserNo;
    }

    /**
     * 元のパスワード値を取得する。<p>
     * <b>処理概要:</b><br>
     * oldPasswordを取得する。
     * @param    なし
     * @return    String    元のパスワード値;
     * @exception    なし
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * 元のパスワード値を設定する。<p>
     * <b>処理概要:</b><br>
     * 元のパスワード値を設定する。
     * @param    oldPassword    元のパスワード値
     * @return    なし
     * @exception    なし
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * 新パスワード値を取得する。<p>
     * <b>処理概要:</b><br>
     * 新パスワードを取得する。
     * @param    なし
     * @return     String    新パスワード値
     * @exception    なし
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 新パスワード値を設定する。<p>
     * <b>処理概要:</b><br>
     * 新パスワード値を設定する。
     * @param    newPassword    新パスワード値
     * @return    なし
     * @exception    なし
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * 新パスワード確認値を取得する。<p>
     * <b>処理概要:</b><br>
     * 新パスワード確認値を取得する。
     * @param    なし
     * @return     String    新パスワード確認値;
     * @exception    なし
     */
    public String getKakuninnPassword() {
        return kakuninnPassword;
    }

    /**
     * 新パスワード確認値を設定する。<p>
     * <b>処理概要:</b><br>
     * 新パスワード確認値を設定する。
     * @param    kakuninnPassword    新パスワード確認;
     * @return    なし
     * @exception    なし
     */
    public void setKakuninnPassword(String kakuninnPassword) {
        this.kakuninnPassword = kakuninnPassword;
    }

    /**
     * 入力されたデータを検証する。
     * @param    mapping    ActionMapping
     * @param    request    HttpServletRequest
     * @return    errors    ActionErrors
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        // セッションオブジェクトの宣言と初期化
        HttpSession session = request.getSession();
        // セッションタイムアウトの場合
        if (session.getAttribute("sysUserId") == null) {
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            // nullを戻す
            return null;
        }
        // 操作していない場合、画面の初期化を実行する
        if(request.getParameter("sousa") == null){
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            return null;
        }
        // ActionErrorsオブジェクトの宣言と初期化
        ActionErrors error = new ActionErrors();
        // 現行パスワードはヌルの場合
        if (FBSEChecker.isEmpty(this.oldPassword)) {
            error.add("oldPasswordNull", new ActionMessage("E001", "現行パスワード"));
            setHidError(this.svnmError("SVNM0610Bean", "oldPassword"));
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            return error;
        }
        // 新パスワードは入力していない場合
        if (FBSEChecker.isEmpty(this.newPassword)) {
            error.add("newPasswordNull", new ActionMessage("E001", "新パスワード"));
            setHidError(this.svnmError("SVNM0610Bean", "newPassword"));
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            return error;
        }
        // 新パスワード確認は入力していない場合
        if (FBSEChecker.isEmpty(this.kakuninnPassword)) {
            error.add("kakuninnPasswordNull", new ActionMessage("E001", "新パスワード確認"));
            setHidError(this.svnmError("SVNM0610Bean", "kakuninnPassword"));
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            return error;
        }
        // 入力した新パスワードと新パスワード確認が一致しない時
        if (!this.newPassword.equals(this.kakuninnPassword)) {
            error.add("pswNotEqual", new ActionMessage("E004", "新パスワード","確認パスワード"));
            this.newPassword = null;
            this.kakuninnPassword = null;
            setHidError(this.svnmError("SVNM0610Bean", "newPassword"));
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            return error;
        }
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return null;
    }
}