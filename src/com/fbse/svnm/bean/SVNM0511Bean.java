/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ 
 * プログラム名        :SVNパスワード定期維持画面
 * モジュール名        :SVNM0511Bean.java
 * 担当者            :FBSE)馬春晶
 * 作成日            :2008.12.18
 * 機能概要          :全部SVNユーザーのSVNパスワードを変更する
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:   2008.12.18    :FBSE)馬春晶
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.bean;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import com.fbse.common.FBSEChecker;
import com.fbse.svnm.base.BaseBean;

/**
 * <HR>
 * 全部SVNユーザーのSVNパスワードを変更する。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>修正した従業員情報を保存する。</li>
 * <li>入力値のgetとset方法を生成する</li>
 * <li>入力値の正確性をチェックする</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0511Bean extends BaseBean {

    /** 新SVNパスワード */
    private String newSvnPassword = "";
    
    /** 新SVNパスワード確認 */
    private String svnRePassword = "";

    /** 頁番号 */
    private String pageId = "";
    
    /** 操作状態 */
    private String subButtonValue = "";

    /**
     * 頁番号を取得する。<p>
     * <b>処理概要:</b><br>
     * 頁番号を取得する。
     * @param    なし
     * @return    String    頁番号
     * @exception    なし
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 操作状態を取得する。<p>
     * <b>処理概要:</b><br>
     * 操作状態を取得する。
     * @param    なし
     * @return    String    操作状態
     * @exception    なし
     */
    public String getSubButtonValue() {
        return subButtonValue;
    }

    /**
     * 頁番号を設定する。<p>
     * <b>処理概要:</b><br>
     * 頁番号を設定する
     * @param    pageId    頁番号
     * @return    なし
     * @exception    なし
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 操作状態を設定する。<p>
     * <b>処理概要:</b><br>
     * 操作状態を設定する
     * @param    subButtonValue    操作状態
     * @return    なし
     * @exception    なし
     */
    public void setSubButtonValue(String subButtonValue) {
        this.subButtonValue = subButtonValue;
    }

    /**
     * 新SVNパスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * 新SVNパスワードを取得する。
     * @param    なし
     * @return    String    新SVNパスワード
     * @exception    なし
     */
    public String getNewSvnPassword() {
        return newSvnPassword;
    }

    /**
     * 新SVNパスワード確認を取得する。<p>
     * <b>処理概要:</b><br>
     * 新SVNパスワード確認を取得する。
     * @param    なし
     * @return    String    新SVNパスワード確認
     * @exception    なし
     */
    public String getSvnRePassword() {
        return svnRePassword;
    }

    /**
     * 新SVNパスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * 新SVNパスワードを設定する
     * @param    newSvnPassword    新SVNパスワード
     * @return    なし
     * @exception    なし
     */
    public void setNewSvnPassword(String newSvnPassword) {
        this.newSvnPassword = newSvnPassword;
    }

    /**
     * 新SVNパスワード確認を設定する。<p>
     * <b>処理概要:</b><br>
     * 新SVNパスワード確認を設定する
     * @param    svnRePassword    新SVNパスワード確認
     * @return    なし
     * @exception    なし
     */
    public void setSvnRePassword(String svnRePassword) {
        this.svnRePassword = svnRePassword;
    }

    /**
     * 入力されたパスワードを検証する。
     * @param    mapping    画面の遷移
     * @param    request    リクエスト
     * @return    ActionErrors    エラーメッセージ
     * @throws    Exception    異常
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request){
        // ログを出力
        log.printLog("INFO", "SVNM0511Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "Start");
        //セッションタイムアウトの場合
        if(request.getSession().getAttribute("sysUserId") == null) {
            return null;
        }
        // 頁番号がヌルの場合
        if (pageId.equals("")) {
            // Actionに行く
            return null;
        }
        // エラープロジェクトを宣言する
        ActionErrors error = new ActionErrors();
        // 新SVNパスワードがヌルの場合
        if (FBSEChecker.isEmpty(newSvnPassword)) {
            //エラーメッセージの設定
            error.add("", new ActionMessage("E001", "新SVNパスワード"));
            //新SVNパスワードのバックグラウンドを赤くして、フォーカスを合わせる
            setHidError(svnmError("SVNM0511Bean", "newSvnPassword"));
            return error;
        }
        // 新SVNパスワード確認がヌルの場合
        if (FBSEChecker.isEmpty(svnRePassword)) {
            //エラーメッセージの設定
            error.add("", new ActionMessage("E001", "新SVNパスワード確認"));
            //新SVNパスワード確認のバックグラウンドを赤くするとフォーカスを合わせる
            setHidError(svnmError("SVNM0511Bean", "svnRePassword"));
            return error;
        }
        // 新SVNパスワードと新SVNパスワード確認は同じではない場合
        if (!newSvnPassword.equals(svnRePassword)) {
            //エラーメッセージの設定
            error.add("", new ActionMessage("E004", "新SVNパスワード", "新SVNパスワード確認"));
            // SVN登録パスワードのバックグラウンドを赤くするとフォーカスを合わせる
            setHidError(svnmError("SVNM0511Bean", "newSvnPassword"));
            this.newSvnPassword = "";
            this.svnRePassword = "";
            return error;
        }
        // ログを出力
        log.printLog("INFO", "SVNM0511Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "End");
        return null;
    }
}