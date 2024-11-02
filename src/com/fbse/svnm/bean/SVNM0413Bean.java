/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :従業員検索（ポップアップ）画面
 * モジュール名        :SVNM0413Bean.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.18
 * 機能概要          :従業員情報の検索を行う
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.18  :FBSE)張建君
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
 * 従業員検索（ポップアップ）画面の入力値を取得する。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト検索画面の入力値を取得する。</li>
 * <li>入力値のgetとset方法を生成する。</li>
 * <li>画面値のreset。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0413Bean extends BaseBean {

    /** 従業員番号の宣言と初期化。 */
    private String svnUserId = "";

    /** 従業員名前の宣言と初期化。 */
    private String svnUserName = "";

    /** 従業員情報の宣言と初期化。 */
    private String svnUser = "";

    /** 従業員情報の宣言と初期化。 */
    private SelectBean selectBean[];

    /** ＰＪ責任者の宣言と初期化。 */
    private String pjMaster = "";

    /** ＰＪ責任者番号の宣言と初期化。 */
    private String pjMasterNo = "";

    /** ＰＪリーダの宣言と初期化。 */
    private String pjLeader = "";

    /** ＰＪリーダ番号の宣言と初期化。 */
    private String pjLeaderNo = "";

    /** 選択するメンバーの宣言と初期化。 */
    private String sentakuMember = "";

    /**
     * 選択したメンバーを取得する。<p>
     * <b>処理概要:</b><br>
     * 選択するメンバーを取得する。
     * @param    なし
     * @return    String    選択するメンバー
     * @exception    なし
     */
    public String getSentakuMember() {
        return sentakuMember;
    }

    /**
     * 選択するメンバーを設定する。<p>
     * <b>処理概要:</b><br>
     * 選択するメンバーを設定する。
     * @param    sentakuMember    選択するメンバー
     * @return    なし
     * @exception    なし
     */
    public void setSentakuMember(String sentakuMember) {
        this.sentakuMember = sentakuMember;
    }

    /**
     * 従業員情報を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員情報を取得する。
     * @param    なし
     * @return    SelectBean[]    従業員情報
     * @exception    なし
     */
    public SelectBean[] getSelectBean() {
        return selectBean;
    }

    /**
     * 従業員情報を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員情報を設定する。
     * @param    selectBean    従業員情報
     * @return    なし
     * @exception    なし
     */
    public void setSelectBean(SelectBean[] selectBean) {
        this.selectBean = selectBean;
    }

    /**
     * 従業員番号を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を取得する。
     * @param    なし
     * @return    String    従業員番号
     * @exception    なし
     */
    public String getSvnUserId() {
        return svnUserId;
    }

    /**
     * 従業員番号を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を設定する。
     * @param    svnUserId    従業員番号
     * @return    なし
     * @exception    なし
     */
    public void setSvnUserId(String svnUserId) {
        this.svnUserId = svnUserId;
    }

    /**
     * 従業員名前を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を取得する。
     * @param    なし
     * @return    String    従業員名前
     * @exception    なし
     */
    public String getSvnUserName() {
        return svnUserName;
    }

    /**
     * 従業員名前を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を設定する。
     * @param    svnUserName    従業員名前
     * @return    なし
     * @exception    なし
     */
    public void setSvnUserName(String svnUserName) {
        this.svnUserName = svnUserName;
    }

    /**
     * SVNユーザーを取得する。<p>
     * <b>処理概要:</b><br>
     * SVNユーザーを取得する。
     * @param    なし
     * @return    String    SVNユーザー
     * @exception    なし
     */
    public String getSvnUser() {
        return svnUser;
    }

    /**
     * SVNユーザーを設定する。<p>
     * <b>処理概要:</b><br>
     * SVNユーザーを設定する。
     * @param    svnUser    SVNユーザー
     * @return    なし
     * @exception    なし
     */
    public void setSvnUser(String svnUser) {
        this.svnUser = svnUser;
    }

    /**
     * ＰＪ責任者を取得する。<p>
     * <b>処理概要:</b><br>
     * ＰＪ責任者を取得する。
     * @param    なし
     * @return    String    ＰＪ責任者
     * @exception    なし
     */
    public String getPjMaster() {
        return pjMaster;
    }

    /**
     * ＰＪ責任者を設定する。<p>
     * <b>処理概要:</b><br>
     * ＰＪ責任者を設定する。
     * @param    pjMaster    ＰＪ責任者
     * @return    なし
     * @exception    なし
     */
    public void setPjMaster(String pjMaster) {
        this.pjMaster = pjMaster;
    }

    /**
     * ＰＪ責任者番号を取得する。<p>
     * <b>処理概要:</b><br>
     * ＰＪ責任者番号を取得する。
     * @param    なし
     * @return    String    ＰＪ責任者番号
     * @exception    なし
     */
    public String getPjMasterNo() {
        return pjMasterNo;
    }

    /**
     * ＰＪ責任者番号を設定する。<p>
     * <b>処理概要:</b><br>
     * ＰＪ責任者番号を設定する。
     * @param    pjMasterNo    ＰＪ責任者番号
     * @return    なし
     * @exception    なし
     */
    public void setPjMasterNo(String pjMasterNo) {
        this.pjMasterNo = pjMasterNo;
    }

    /**
     * ＰＪリーダを取得する。<p>
     * <b>処理概要:</b><br>
     * ＰＪリーダを取得する。
     * @param    なし
     * @return    String    ＰＪリーダ
     * @exception    なし
     */
    public String getPjLeader() {
        return pjLeader;
    }

    /**
     * ＰＪリーダを設定する。<p>
     * <b>処理概要:</b><br>
     * ＰＪリーダを設定する。
     * @param    pjLeader    ＰＪリーダ
     * @return    なし
     * @exception    なし
     */
    public void setPjLeader(String pjLeader) {
        this.pjLeader = pjLeader;
    }

    /**
     * ＰＪリーダ番号を取得する。<p>
     * <b>処理概要:</b><br>
     * ＰＪリーダ番号を取得する。
     * @param    なし
     * @return    String    ＰＪリーダ番号
     * @exception    なし
     */
    public String getPjLeaderNo() {
        return pjLeaderNo;
    }

    /**
     * ＰＪリーダ番号を設定する。<p>
     * <b>処理概要:</b><br>
     * ＰＪリーダ番号を設定する。
     * @param    pjLeaderNo    ＰＪリーダ番号
     * @return    なし
     * @exception    なし
     */
    public void setPjLeaderNo(String pjLeaderNo) {
        this.pjLeaderNo = pjLeaderNo;
    }

    /**
     * 入力されたデータを検証する。<p>
     * <b>処理概要:</b><br>
     * 入力されたデータを検証する。
     * @param    mapping    マッピング
     * @param    request    リクエスト
     * @return    ActionErrors    エラーメッセージ
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
        // ログを出力する。
        log.printLog("INFO", "SVNM0413Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        // ログを出力する。
        log.printLog("INFO", "SVNM0413Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return null;
    }
}