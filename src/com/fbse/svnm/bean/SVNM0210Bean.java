/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト検索画面
 * モジュール名        :SVNM0210Bean.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.17
 * 機能概要          :プロジェクト情報の検索、削除、修復
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)張建君
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
 * プロジェクト検索画面の入力値を取得する。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト検索画面の入力値を取得する。</li>
 * <li>入力値のgetとsetメソッドを生成する。</li>
 * <li>画面入力項目の値をリセットする</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0210Bean extends BaseBean {

    /** 画面コードの宣言と初期化。 */
    private String gamenId = "";

    /** 操作状態の宣言と初期化。 */
    private String sousaStart = "";

    /** 更新日の宣言と初期化。 */
    private String updateData = "";

    /** プロジェクト番号の宣言と初期化。 */
    private String projectNo = "";

    /** 削除プロジェクト名の宣言と初期化。 */
    private String deleteProjectName = "";

    /** SVNフォルダ名の宣言と初期化。 */
    private String svnFolderName = "";

    /** SVNフォルダ名の宣言と初期化。 */
    private String svnFolderNameSql = "";

    /** プロジェクト名の宣言と初期化。 */
    private String projectName = "";

    /** プロジェクト名の宣言と初期化。 */
    private String projectNameSql = "";

    /** 開始時間の宣言と初期化。 */
    private String pjStartDate = "";

    /** 開始時間の宣言と初期化。 */
    private String pjStartDateSql = "";

    /** 終了時間の宣言と初期化。 */
    private String pjEndDate = "";

    /** 終了時間の宣言と初期化。 */
    private String pjEndDateSql = "";

    /** PJ責任者の宣言と初期化。 */
    private String pjMaster = "";

    /** PJ責任者番号の宣言と初期化。 */
    private String pjMasterNo = "";

    /** PJ責任者番号の宣言と初期化。 */
    private String pjMasterNoSql = "";

    /** PJリーダの宣言と初期化。 */
    private String pjLeader = "";

    /** PJリーダ番号の宣言と初期化。 */
    private String pjLeaderNo = "";

    /** PJリーダ番号の宣言と初期化。 */
    private String pjLeaderNoSql = "";

    /** プロジェクト状態の宣言と初期化。 */
    private String delflg = "0";

    /** プロジェクト状態の宣言と初期化。 */
    private String delflgSql = "0";

    /** メンバーの宣言と初期化。 */
    private String member = "";

    /** メンバー番号の宣言と初期化。 */
    private String memberNo = "";

    /** メンバー番号の宣言と初期化。 */
    private String memberSql = "";

    /** プロジェクト状態リストの宣言と初期化。 */
    private SelectBean selectBean[];

    /** 更新SVNフォルダ名の宣言と初期化。 */
    private String updateSvnFolder = "";

    /**
     * 更新SVNフォルダ名を取得する。<p>
     * <b>処理概要:</b><br>
     * 更新SVNフォルダー名を取得する。
     * @param    なし
     * @return    String    更新SVNフォルダー名
     * @exception    なし
     */
    public String getUpdateSvnFolder() {
        return updateSvnFolder;
    }

    /**
     * 更新SVNフォルダー名を設定する。<p>
     * <b>処理概要:</b><br>
     * 更新SVNフォルダー名を設定する。
     * @param    updateSvnFolder    更新SVNフォルダー名
     * @return    なし
     * @exception    なし
     */
    public void setUpdateSvnFolder(String updateSvnFolder) {
        this.updateSvnFolder = updateSvnFolder;
    }

    /**
     * プロジェクト状態を取得する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト状態を取得する。
     * @param    なし
     * @return    String    プロジェクト状態
     * @exception    なし
     */
    public String getDelflg() {
        return delflg;
    }

    /**
     * プロジェクト状態を設定する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト状態を設定する。
     * @param    delflg    プロジェクト状態
     * @return    なし
     * @exception    なし
     */
    public void setDelflg(String delflg) {
        this.delflg = delflg;
    }

    /**
     * プロジェクト状態を取得する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト状態を取得する。
     * @param    なし
     * @return    String    プロジェクト状態
     * @exception    なし
     */
    public String getDelflgSql() {
        return delflgSql;
    }

    /**
     * プロジェクト状態を設定する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト状態を設定する。
     * @param    delflgSql    プロジェクト状態
     * @return    なし
     * @exception    なし
     */
    public void setDelflgSql(String delflgSql) {
        this.delflgSql = delflgSql;
    }

    /**
     * 開始時間を取得する。<p>
     * <b>処理概要:</b><br>
     * 開始時間を取得する。
     * @param    なし
     * @return    String    開始時間
     * @exception    なし
     */
    public String getPjStartDate() {
        return pjStartDate;
    }

    /**
     * 開始時間を設定する。<p>
     * <b>処理概要:</b><br>
     * 開始時間を設定する。
     * @param    pjStartDate    開始時間
     * @return    なし
     * @exception    なし
     */
    public void setPjStartDate(String pjStartDate) {
        this.pjStartDate = pjStartDate;
    }

    /**
     * 開始時間を取得する。<p>
     * <b>処理概要:</b><br>
     * 開始時間を取得する。
     * @param    なし
     * @return    String    開始時間
     * @exception    なし
     */
    public String getPjStartDateSql() {
        return pjStartDateSql;
    }

    /**
     * 開始時間を設定する。<p>
     * <b>処理概要:</b><br>
     * 開始時間を設定する。
     * @param    pjStartDateSql    開始時間
     * @return    なし
     * @exception    なし
     */
    public void setPjStartDateSql(String pjStartDateSql) {
        this.pjStartDateSql = pjStartDateSql;
    }

    /**
     * 終了時間を取得する。<p>
     * <b>処理概要:</b><br>
     * 終了時間を取得する。
     * @param    なし
     * @return    String    終了時間
     * @exception    なし
     */
    public String getPjEndDate() {
        return pjEndDate;
    }

    /**
     * 終了時間を設定する。<p>
     * <b>処理概要:</b><br>
     * 終了時間を設定する。
     * @param    pjEndDate    終了時間
     * @return    なし
     * @exception    なし
     */
    public void setPjEndDate(String pjEndDate) {
        this.pjEndDate = pjEndDate;
    }

    /**
     * 終了時間を取得する。<p>
     * <b>処理概要:</b><br>
     * 終了時間を取得する。
     * @param    なし
     * @return    String    終了時間
     * @exception    なし
     */
    public String getPjEndDateSql() {
        return pjEndDateSql;
    }

    /**
     * 終了時間を設定する。<p>
     * <b>処理概要:</b><br>
     * 終了時間を設定する。
     * @param    pjEndDateSql    終了時間
     * @return    なし
     * @exception    なし
     */
    public void setPjEndDateSql(String pjEndDateSql) {
        this.pjEndDateSql = pjEndDateSql;
    }

    /**
     * PJリーダを取得する。<p>
     * <b>処理概要:</b><br>
     * PJリーダを取得する。
     * @param    なし
     * @return    String    PJリーダ
     * @exception    なし
     */
    public String getPjLeader() {
        return pjLeader;
    }

    /**
     * PJリーダを設定する。<p>
     * <b>処理概要:</b><br>
     * PJリーダを設定する。
     * @param    pjLeader    PJリーダ
     * @return    なし
     * @exception    なし
     */
    public void setPjLeader(String pjLeader) {
        this.pjLeader = pjLeader;
    }

    /**
     * PJリーダ番号を取得する。<p>
     * <b>処理概要:</b><br>
     * PJリーダ番号を取得する。
     * @param    なし
     * @return    String    PJリーダ番号
     * @exception    なし
     */
    public String getPjLeaderNo() {
        return pjLeaderNo;
    }

    /**
     * PJリーダ番号を設定する。<p>
     * <b>処理概要:</b><br>
     * PJリーダ番号を設定する。
     * @param    pjLeaderNo    PJリーダ番号
     * @return    なし
     * @exception    なし
     */
    public void setPjLeaderNo(String pjLeaderNo) {
        this.pjLeaderNo = pjLeaderNo;
    }

    /**
     * PJリーダ番号を取得する。<p>
     * <b>処理概要:</b><br>
     * PJリーダ番号を取得する。
     * @param    なし
     * @return    String    PJリーダ番号
     * @exception    なし
     */
    public String getPjLeaderNoSql() {
        return pjLeaderNoSql;
    }

    /**
     * PJリーダ番号を設定する。<p>
     * <b>処理概要:</b><br>
     * PJリーダ番号を設定する。
     * @param    pjLeaderNoSql    PJリーダ番号
     * @return    なし
     * @exception    なし
     */
    public void setPjLeaderNoSql(String pjLeaderNoSql) {
        this.pjLeaderNoSql = pjLeaderNoSql;
    }

    /**
     * PJ責任者を取得する。<p>
     * <b>処理概要:</b><br>
     * PJ責任者を取得する。
     * @param    なし
     * @return    String    PJ責任者
     * @exception    なし
     */
    public String getPjMaster() {
        return pjMaster;
    }

    /**
     * PJ責任者を設定する。<p>
     * <b>処理概要:</b><br>
     * PJ責任者を設定する。
     * @param    pjMaster    PJ責任者
     * @return    なし
     * @exception    なし
     */
    public void setPjMaster(String pjMaster) {
        this.pjMaster = pjMaster;
    }

    /**
     * PJ責任者番号を取得する。<p>
     * <b>処理概要:</b><br>
     * PJ責任者番号を取得する。
     * @param    なし
     * @return    String    PJ責任者番号
     * @exception    なし
     */
    public String getPjMasterNo() {
        return pjMasterNo;
    }

    /**
     * PJ責任者番号を設定する。<p>
     * <b>処理概要:</b><br>
     * PJ責任者番号を設定する。
     * @param    pjMasterNo    PJ責任者番号
     * @return    なし
     * @exception    なし
     */
    public void setPjMasterNo(String pjMasterNo) {
        this.pjMasterNo = pjMasterNo;
    }

    /**
     * PJ責任者番号を取得する。<p>
     * <b>処理概要:</b><br>
     * PJ責任者番号を取得する。
     * @param    なし
     * @return    String    PJ責任者番号
     * @exception    なし
     */
    public String getPjMasterNoSql() {
        return pjMasterNoSql;
    }

    /**
     * PJ責任者番号を設定する。<p>
     * <b>処理概要:</b><br>
     * PJ責任者番号を設定する。
     * @param    pjMasterNoSql    PJ責任者番号
     * @return    なし
     * @exception    なし
     */
    public void setPjMasterNoSql(String pjMasterNoSql) {
        this.pjMasterNoSql = pjMasterNoSql;
    }

    /**
     * プロジェクト名を取得する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト名を取得する。
     * @param    なし
     * @return    String    プロジェクト名
     * @exception    なし
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * プロジェクト名を設定する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト名を設定する。
     * @param    projectName    プロジェクト名
     * @return    なし
     * @exception    なし
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * プロジェクト名を取得する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト名を取得する。
     * @param    なし
     * @return    String    プロジェクト名
     * @exception    なし
     */
    public String getProjectNameSql() {
        return projectNameSql;
    }

    /**
     * プロジェクト名を設定する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト名を設定する。
     * @param    projectNameSql    プロジェクト名
     * @return    なし
     * @exception    なし
     */
    public void setProjectNameSql(String projectNameSql) {
        this.projectNameSql = projectNameSql;
    }

    /**
     * SVNフォルダ名を取得する。<p>
     * <b>処理概要:</b><br>
     * SVNフォルダー名を取得する。
     * @param    なし
     * @return    String    SVNフォルダー名
     * @exception    なし
     */
    public String getSvnFolderName() {
        return svnFolderName;
    }

    /**
     * SVNフォルダー名を設定する。<p>
     * <b>処理概要:</b><br>
     * SVNフォルダー名を設定する。
     * @param    svnFolderName    SVNフォルダー名
     * @return    なし
     * @exception    なし
     */
    public void setSvnFolderName(String svnFolderName) {
        this.svnFolderName = svnFolderName;
    }

    /**
     * SVNフォルダー名を取得する。<p>
     * <b>処理概要:</b><br>
     * SVNフォルダー名を取得する。
     * @param    なし
     * @return    String    SVNフォルダー名
     * @exception    なし
     */
    public String getSvnFolderNameSql() {
        return svnFolderNameSql;
    }

    /**
     * SVNフォルダー名を設定する。<p>
     * <b>処理概要:</b><br>
     * SVNフォルダー名を設定する。
     * @param    svnFolderNameSql    SVNフォルダー名
     * @return    なし
     * @exception    なし
     */
    public void setSvnFolderNameSql(String svnFolderNameSql) {
        this.svnFolderNameSql = svnFolderNameSql;
    }

    /**
     * メンバーを取得する。<p>
     * <b>処理概要:</b><br>
     * メンバーを取得する。
     * @param    なし
     * @return    String    メンバー
     * @exception    なし
     */
    public String getMember() {
        return member;
    }

    /**
     * メンバーを設定する。<p>
     * <b>処理概要:</b><br>
     * メンバーを設定する。
     * @param    member    メンバー
     * @return    なし
     * @exception    なし
     */
    public void setMember(String member) {
        this.member = member;
    }

    /**
     * メンバー番号を取得する<p>
     * <b>処理概要:</b><br>
     * メンバー番号を取得する。
     * @param    なし
     * @return    String    メンバー番号
     * @exception    なし
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * メンバー番号を設定する。<p>
     * <b>処理概要:</b><br>
     * メンバー番号を設定する。
     * @param    memberNo    メンバー番号
     * @return    なし
     * @exception    なし
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * メンバーSQL文を取得する。<p>
     * <b>処理概要:</b><br>
     * メンバーSQL文を取得する。
     * @param    なし
     * @return    String    メンバーSQL文
     * @exception    なし
     */
    public String getMemberSql() {
        return memberSql;
    }

    /**
     * メンバーSQL文を設定する。<p>
     * <b>処理概要:</b><br>
     * メンバーSQL文を設定する。
     * @param    memberSql    メンバーSQL文
     * @return    なし
     * @exception    なし
     */
    public void setMemberSql(String memberSql) {
        this.memberSql = memberSql;
    }

    /**
     * 操作状態を取得する。<p>
     * <b>処理概要:</b><br>
     * 操作状態を取得する。
     * @param    なし
     * @return    String    操作状態
     * @exception    なし
     */
    public String getSousaStart() {
        return sousaStart;
    }

    /**
     * 操作状態を設定する。<p>
     * <b>処理概要:</b><br>
     * 操作状態を設定する。
     * @param    sousaStart    操作状態
     * @return    なし
     * @exception    なし
     */
    public void setSousaStart(String sousaStart) {
        this.sousaStart = sousaStart;
    }

    /**
     * 更新日を取得する。<p>
     * <b>処理概要:</b><br>
     * 更新日を取得する。
     * @param    なし
     * @return    String    更新日
     * @exception    なし
     */
    public String getUpdateData() {
        return updateData;
    }

    /**
     * 更新日を設定する。<p>
     * <b>処理概要:</b><br>
     * 更新日を設定する。
     * @param    updateData    更新日
     * @return    なし
     * @exception    なし
     */
    public void setUpdateData(String updateData) {
        this.updateData = updateData;
    }

    /**
     * プロジェクト番号を取得する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト番号を取得する。
     * @param    なし
     * @return    String    プロジェクト番号
     * @exception    なし
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * プロジェクト番号を設定する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト番号を設定する。
     * @param    projectNo    プロジェクト番号
     * @return    なし
     * @exception    なし
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    /**
     * 削除プロジェクト名を取得する。<p>
     * <b>処理概要:</b><br>
     * 削除プロジェクト名を取得する。
     * @param    なし
     * @return    String    削除プロジェクト名
     * @exception    なし
     */
    public String getDeleteProjectName() {
        return deleteProjectName;
    }

    /**
     * 削除プロジェクト名を設定する。<p>
     * <b>処理概要:</b><br>
     * 削除プロジェクト名を設定する。
     * @param    deleteProjectName    削除プロジェクト名
     * @return    なし
     * @exception    なし
     */
    public void setDeleteProjectName(String deleteProjectName) {
        this.deleteProjectName = deleteProjectName;
    }

    /**
     * 状態リストを取得する。<p>
     * <b>処理概要:</b><br>
     * 状態リストを取得する。
     * @param    なし
     * @return    SelectBean[]    状態リスト
     * @exception    なし
     */
    public SelectBean[] getSelectBean() {
        return selectBean;
    }

    /**
     * 状態リストを設定する。<p>
     * <b>処理概要:</b><br>
     * 状態リストを設定する。
     * @param    selectBean[]    状態リスト
     * @return    なし
     * @exception    なし
     */
    public void setSelectBean(SelectBean[] selectBean) {
        this.selectBean = selectBean;
    }

    /**
     * 画面コードを取得する。<p>
     * <b>処理概要:</b><br>
     * 画面コードを取得する。
     * @param    なし
     * @return    String    画面コード
     * @exception    なし
     */
    public String getGamenId() {
        return gamenId;
    }

    /**
     * 画面コードを設定する。<p>
     * <b>処理概要:</b><br>
     * 画面コードを設定する。
     * @param    gamenId    画面コード
     * @return    なし
     * @exception    なし
     */
    public void setGamenId(String gamenId) {
        this.gamenId = gamenId;
    }

    /**
     * 画面情報をリセットする。<p>
     * <b>処理概要:</b><br>
     * 画面情報をリセットする。
     * @param    mapping    マッピング
     * @param    request    リクエスト
     * @return    なし
     * @exception    なし
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "reset", "Start");
        this.svnFolderName = "";
        this.projectName = "";
        this.pjStartDate = "";
        this.pjEndDate = "";
        this.pjMaster = "";
        this.pjMasterNo = "";
        this.pjLeader = "";
        this.pjLeaderNo = "";
        this.delflg = "0";
        this.member = "";
        this.memberNo = "";
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "reset", "End");
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
        log.printLog("INFO", "SVNM0210Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return null;
    }
}