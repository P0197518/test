/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト新規画面
 * モジュール名        :SVNM0211Bean.java
 * 担当者            :FBSE)張志明
 * 作成日            :2008.12.17
 * 機能概要          :画面で項目のチェックを行う。
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
import org.apache.struts.action.ActionMessage;
import com.fbse.common.FBSEChecker;
import com.fbse.common.FBSEDateHandler;
import com.fbse.common.FBSEXmlHandler;
import com.fbse.svnm.base.BaseBean;
import com.fbse.svnm.base.Common;
import com.fbse.svnm.base.FileOperator;

/**
 * <HR>
 * プロジェクト新規画面の入力値を取得及び入力値有効性のチェックを行う
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト新規の入力値を取得</li>
 * <li>入力値のgetとsetメソッドを生成する</li>
 * <li>入力値有効性のチェックを行う</li>
 * <li>画面値のリセットを行う</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0211Bean extends BaseBean {

    /** 画面ID */
    private String pageId = "";

    /** SVNフォルダ名の宣言と初期化。 */
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

    /** フォルダ移動日の宣言と初期化。 */
    private String pjRemoveDate = "";

    /** バックアップ時間間隔の宣言と初期化。 */
    private String pjBackupTime = "";

    /** メンバーの宣言と初期化。 */
    private String member = "";

    /** メンバー番号の宣言と初期化。 */
    private String memberNo = "";

    /** コメントの宣言と初期化。 */
    private String comment = "";

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
     * @exception    なし;
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
     * PJリーダ番号を取得する。
     * 
     * @param    なし
     * @return    String    PJリーダ番号
     * @exception    なし
     */
    public String getPjLeaderNo() {
        return pjLeaderNo;
    }

    /**
     * PJリーダ番号を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * PJリーダ番号を設定する。
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
     * PJ責任者番号を取得する。
     * 
     * @param    なし
     * @return    String    PJ責任者番号
     * @exception    なし
     */
    public String getPjMasterNo() {
        return pjMasterNo;
    }

    /**
     * PJ責任者番号を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * PJ責任者番号を設定する。
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
     * SVNフォルダ名を取得する。
     * 
     * @param    なし
     * @return    String    SVNフォルダ名
     * @exception    なし
     */
    public String getSvnFolderName() {
        return svnFolderName;
    }

    /**
     * SVNフォルダ名を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * SVNフォルダ名を設定する。
     * 
     * @param    svnFolderName    SVNフォルダ名
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
     * メンバー番号を取得する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバー番号を取得する。
     * 
     * @param    なし
     * @return    String    メンバー番号
     * @exception    なし
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * メンバー番号を設定する。
     * <p>
     * <b>処理概要:</b><br>
     * メンバー番号を設定する。
     * 
     * @param    memberNo    メンバー番号
     * @return    なし
     * @exception    なし
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
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
        log.printLog("INFO", "SVNM0211Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        ActionErrors error = new ActionErrors();
        try {
            // セッションタイムアウトの場合
            if (request.getSession().getAttribute("sysUserId") == null) {
                error = null;
            }
            // 別の画面から遷移した場合
            else if (!pageId.split(",")[0].equals("SVNM0211")
                    || pageId.split(",")[1].equals("return")) {
                error = null;
            }
            // SVNフォルダ名は入力していない場合
            else if (FBSEChecker.isEmpty(svnFolderName)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E001", "SVNフォルダ名"));
                // SVNフォルダ名のバックグラウンドを赤くして、フォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "svnFolderName"));
            }
            // 入力したSVNフォルダ名は半角英数字ではない場合
            else if (!isNE(svnFolderName)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E013", "SVNフォルダ名"));
                // SVNフォルダ名のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "svnFolderName"));
            }
            // プロジェクト名は入力していない場合
            else if (FBSEChecker.isEmpty(projectName)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E001", "プロジェクト名"));
                // プロジェクト名のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "projectName"));
            }
            // プロジェクト名は日本語じゃない。
            else if (!FBSEChecker.isJapanese(projectName, false)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E005", "プロジェクト名"));
                // プロジェクト名のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "projectName"));
            }
            // ＰＪ責任者は選択していない場合
            else if (FBSEChecker.isEmpty(pjMasterNo)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E002", "ＰＪ責任者"));
                // ＰＪ責任者のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjMaster"));
            }
            // ＰＪリーダは選択しない。
            else if (FBSEChecker.isEmpty(pjLeaderNo)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E002", "ＰＪリーダ"));
                // ＰＪリーダのバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjLeader"));
            }
            // プロジェクト開始日は選択しない。
            else if (FBSEChecker.isEmpty(pjStartDate)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E002", "プロジェクト開始日"));
                // プロジェクト開始日のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjStartDate"));
            }
            // プロジェクト開始日はシステム日付以前の場合
            else if (FBSEDateHandler.isGreaterThan(
                    Common.getCurrentTime("yyyyMMdd"), pjStartDate.replace("/", ""))) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E012", "プロジェクト開始日"));
                // プロジェクト開始日のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjStartDate"));
            }
            // プロジェクト完了日は選択しない。
            else if (FBSEChecker.isEmpty(pjEndDate)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E002", "プロジェクト完了日"));
                // プロジェクト完了日のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjEndDate"));
            }
            // プロジェクト開始日はプロジェクト完了日以降の場合
            else if (FBSEDateHandler.isGreaterThan(pjStartDate.replace("/", ""),
                    pjEndDate.replace("/", ""))) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E009", "プロジェクト完了日",
                        "プロジェクト開始日"));
                // プロジェクト開始日とプロジェクト完了日のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjStartDate")
                        + svnmError("SVNM0211Bean", "pjEndDate"));
            }
            // フォルダ移動日は選択しない。
            else if (FBSEChecker.isEmpty(pjRemoveDate)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E002", "フォルダ移動日"));
                // フォルダ移動日のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjRemoveDate"));
            }
            // フォルダ移動日はプロジェクト完了日以前の場合
            else if (FBSEDateHandler.isGreaterThan(pjEndDate.replace("/", ""),
                    pjRemoveDate.replace("/", ""))) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E009", "フォルダ移動日",
                        "プロジェクト完了日"));
                // フォルダ移動日とプロジェクト完了日のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "pjRemoveDate"));
            }
            // メンバーは選択しない。
            else if (FBSEChecker.isEmpty(memberNo)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E002", "メンバー"));
                // メンバーのバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "member"));
            }
            // 入力したコメントは100桁じゃない。
            else if (comment.length() > 100) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E008", "100", "コメント"));
                // コメントのバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0211Bean", "comment"));
            }
            String basePath = Common.getAppPath(FileOperator.class);
            // XMLハンドラーを宣言
            FBSEXmlHandler svnXml = new FBSEXmlHandler(basePath
                    + "/SVNConfig.xml");
            // パスワードファイルのパスを取得
            String leaveDays = svnXml.xmlSelectNode("//Option/leaveDays");
            request.setAttribute("leaveDays", leaveDays);
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "SVNM0211Bean", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", e.toString());
        }
        // ログ出力
        log.printLog("INFO", "SVNM0211Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return error;
    }
}