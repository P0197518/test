/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト確認画面
 * モジュール名        :SVNM0213Bean.java
 * 担当者            :FBSE)張志明
 * 作成日            :2008.12.17
 * 機能概要          :プロジェクト情報を保存する。
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.17 :FBSE)張志明
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
 * プロジェクト新規画面とプロジェクト編集画面からプロジェクト情報を取得
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト新規画面とプロジェクト編集画面からプロジェクト情報を取得</li>
 * <li>プロジェクト情報のgetとsetメソッドを生成する</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0213Bean extends BaseBean {

    /** 画面ID */
    private String pageId = "";

    /** プロジェクト番号の宣言と初期化。 */
    private String projectNo;

    /** SVNフォルダー名の宣言と初期化。 */
    private String svnFolderName = "";

    /** プロジェクト名の宣言と初期化。 */
    private String projectName = "";

    /** PJ責任者の宣言と初期化。 */
    private String pjMaster = "";

    /** PJリーダの宣言と初期化。 */
    private String pjLeader = "";

    /** PJ責任者番号の宣言と初期化。 */
    private String pjMasterNo = "";

    /** PJリーダ番号の宣言と初期化。 */
    private String pjLeaderNo = "";

    /** プロジェクト開始日の宣言と初期化。 */
    private String pjStartDate = "";

    /** プロジェクト完了日の宣言と初期化。 */
    private String pjEndDate = "";

    /** プロジェクト転出日の宣言と初期化。 */
    private String pjRemoveDate = "";

    /** バックアップ時間間隔の宣言と初期化。 */
    private String pjBackupTime = "";

    /** メンバーの宣言と初期化。 */
    private String member = "";

    /** メンバー番号の宣言と初期化。 */
    private String memberNo = "";

    /** コメントの宣言と初期化。 */
    private String comment = "";

    /** 更新日時の宣言と初期化。 */
    private String updateDate = "";

    /**
     * コメントを取得する。
     * <p>
     * <b>処理概要:</b><br>
     * コメントを取得する。
     * 
     * @param    なし
     * @return    String    コメント
     * @exception    なし
     */
    public String getComment() {
        return comment;
    }

    /**
     * コメントを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * コメントを設定する。
     * 
     * @param    comment    コメント
     * @return    なし
     * @exception    なし
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * バックアップ間隔を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * バックアップ間隔を取得する。
     * 
     * @param    なし
     * @return    String    バックアップ間隔
     * @exception    なし
     */
    public String getPjBackupTime() {
        return pjBackupTime;
    }

    /**
     * バックアップ間隔を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * バックアップ間隔を設定する。
     * 
     * @param    pjBackupTime    バックアップ間隔
     * @return    なし
     * @exception    なし
     */
    public void setPjBackupTime(String pjBackupTime) {
        this.pjBackupTime = pjBackupTime;
    }

    /**
     * プロジェクト完了日を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト完了日を取得する。
     * 
     * @param    なし
     * @return    String    プロジェクト完了日
     * @exception    なし
     */
    public String getPjEndDate() {
        return pjEndDate;
    }

    /**
     * プロジェクト完了日を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト完了日を設定する。
     * 
     * @param    pjEndDate    プロジェクト完了日
     * @return    なし
     * @exception    なし
     */
    public void setPjEndDate(String pjEndDate) {
        this.pjEndDate = pjEndDate;
    }

    /**
     * PJリーダを取得する。
     * <p>
     * <b>処理概要:</b><br>
     * PJリーダを取得する。
     * 
     * @param    なし
     * @return    String    PJリーダ
     * @exception    なし
     */
    public String getPjLeader() {
        return pjLeader;
    }

    /**
     * PJリーダを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * PJリーダを設定する。
     * 
     * @param    pjLeader    PJリーダ
     * @return    なし
     * @exception    なし
     */
    public void setPjLeader(String pjLeader) {
        this.pjLeader = pjLeader;
    }

    /**
     * PJリーダ番号を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * PJリーダを取得する。
     * 
     * @param    なし
     * @return    String    PJリーダ番号
     * @exception    なし
     */
    public String getPjLeaderNo() {
        return pjLeaderNo;
    }

    /**
     * PJリーダを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * PJリーダを設定する。
     * 
     * @param    pjLeaderNo    PJリーダ番号
     * @return    なし
     * @exception    なし
     */
    public void setPjLeaderNo(String pjLeaderNo) {
        this.pjLeaderNo = pjLeaderNo;
    }

    /**
     * PJ責任者を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * PJ責任者を取得する。
     * 
     * @param    なし
     * @return    String    PJ責任者
     * @exception    なし
     */
    public String getPjMaster() {
        return pjMaster;
    }

    /**
     * PJ責任者を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * PJ責任者を設定する。
     * 
     * @param    pjMaster    PJ責任者
     * @return    なし
     * @exception    なし
     */
    public void setPjMaster(String pjMaster) {
        this.pjMaster = pjMaster;
    }

    /**
     * PJ責任者番号を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * PJ責任者を取得する。
     * 
     * @param    なし
     * @return    String    PJ責任者番号
     * @exception    なし
     */
    public String getPjMasterNo() {
        return pjMasterNo;
    }

    /**
     * PJ責任者を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * PJ責任者を設定する。
     * 
     * @param    pjMasterNo    PJ責任者番号
     * @return    なし
     * @exception    なし
     */
    public void setPjMasterNo(String pjMasterNo) {
        this.pjMasterNo = pjMasterNo;
    }

    /**
     * プロジェクト移動日を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト移動日を取得する。
     * 
     * @param    なし
     * @return    String    プロジェクト移動日
     * @exception    なし
     */
    public String getPjRemoveDate() {
        return pjRemoveDate;
    }

    /**
     * プロジェクト移動日を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト移動日を設定する。
     * 
     * @param    pjRemoveDate    プロジェクト移動日
     * @return    なし
     * @exception    なし
     */
    public void setPjRemoveDate(String pjRemoveDate) {
        this.pjRemoveDate = pjRemoveDate;
    }

    /**
     * プロジェクト開始日を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト開始日を取得する。
     * 
     * @param    なし
     * @return    String    プロジェクト開始日
     * @exception    なし
     */
    public String getPjStartDate() {
        return pjStartDate;
    }

    /**
     * プロジェクト開始日を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト開始日を設定する。
     * 
     * @param    pjStartDate    プロジェクト開始日
     * @return    なし
     * @exception    なし
     */
    public void setPjStartDate(String pjStartDate) {
        this.pjStartDate = pjStartDate;
    }

    /**
     * プロジェクト名を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト名を取得する。
     * 
     * @param    なし
     * @return    String    プロジェクト名
     * @exception    なし
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * プロジェクト名を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト名を設定する。
     * 
     * @param    projectName    プロジェクト名
     * @return    なし
     * @exception    なし
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * SVNフォルダ名を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * SVNフォルダー名を取得する。
     * 
     * @param    なし
     * @return    String    SVNフォルダー名
     * @exception    なし
     */
    public String getSvnFolderName() {
        return svnFolderName;
    }

    /**
     * SVNフォルダー名を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * SVNフォルダー名を設定する。
     * 
     * @param    svnFolderName    SVNフォルダー名
     * @return    なし
     * @exception    なし
     */
    public void setSvnFolderName(String svnFolderName) {
        this.svnFolderName = svnFolderName;
    }

    /**
     * メンバーを取得する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを取得する。
     * 
     * @param    なし
     * @return    String    メンバー
     * @exception    なし
     */
    public String getMember() {
        return member;
    }

    /**
     * メンバーを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを設定する。
     * 
     * @param    member    メンバー
     * @return    なし
     * @exception    なし
     */
    public void setMember(String member) {
        this.member = member;
    }

    /**
     * メンバーを取得する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを取得する。
     * 
     * @param    なし
     * @return    String    メンバー番号
     * @exception    なし
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * メンバーを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを設定する。
     * 
     * @param    memberNo    メンバー番号
     * @return    なし
     * @exception    なし
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * プロジェクト番号を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト番号を取得する。
     * 
     * @param    なし
     * @return    String    プロジェクト番号
     * @exception    なし
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * プロジェクト番号を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト番号を設定する。
     * 
     * @param    projectNo    プロジェクト番号
     * @return    なし
     * @exception    なし
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    /**
     * 画面IDを取得する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを設定する。
     * 
     * @param    なし
     * @return    String    画面ID
     * @exception    なし
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 画面IDを設定する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを設定する。
     * 
     * @param    pageId    画面ID
     * @return    なし
     * @exception    なし
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 更新日付を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを設定する。
     * 
     * @param    なし
     * @return    String    更新日時
     * @exception    なし
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新日時を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバーを設定する。
     * 
     * @param    updateDate    更新日時
     * @return    なし
     * @exception    なし
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 入力されたデータを検証する。
     * <p>
     * <b>処理概要:</b><br>
     * 入力されたデータを検証する。
     * 
     * @param    mapping    マッピング
     * @param    request    リクエスト
     * @return    ActionErrors    エラー情報
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        // ログ出力
        log.printLog("INFO", "SVNM0213Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        // ログ出力
        log.printLog("INFO", "SVNM0213Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return null;
    }
}