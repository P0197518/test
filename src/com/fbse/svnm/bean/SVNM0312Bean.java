/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :権限マスタ
 * モジュール名        :権限編集画面
 * 担当者            :FBSE)宋福明
 * 作成日            :2008.12.18
 * 機能概要           :画面で項目のチェックを行う。
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.18 :FBSE)宋福明
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
import com.fbse.svnm.dao.SVNM0312Dao;

/**
 * <HR>
 * 権限編集画面の入力値の取得及び入力値有効性のチェック<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>権限編集の入力値を取得</li>
 * <li>入力値のgetとsetメソッドを生成する</li>
 * <li>入力値有効性のチェックを行う</li><br>
 * <li>権限名の正確性のチェック。</li><br>
 * <li>プロジェクト管理の正確性のチェック。</li><br>
 * <li>マスタメンテの正確性のチェック。</li><br>
 * <li>コメントの正確性のチェック。</li><br>
 * <li>画面値のreset</li>
 * </ul>
 * @version  V1.0
 * @author   FBSE)宋福明
 * @see      com.fbse.svnm.base.BaseBean
 */
public class SVNM0312Bean extends BaseBean {

    // クラス名の宣言と初期化
    private static String className = "SVNM0312Bean";

    /** 権限番号の宣言と初期化 */
    private String privilegeNo = "";

    /** 権限名の宣言と初期化 */
    private String svnm0312PrivilegeName = "";

    /** プロジェクト管理権限の宣言 */
    private SelectBean projectPrivilegeBean[];

    /** プロジェクト管理権限コードの宣言 */
    private String projectPrivilegeCode = "";

    /** マスタメンテ権限の宣言 */
    private SelectBean masterPrivilegeBean[];

    /** マスタメンテ権限コードの宣言 */
    private String masterPrivilegeCode = "";

    /** コメントの宣言と初期化 */
    private String privilegeComment = "";

    /** ボタンの宣言と初期化 */
    private String submitValue = "";
    
    /** ボタンの宣言と初期化 */
    private String updateDate = "";

    /**
     * 更新時間を取得する。<p>
     * <b>処理概要:</b><br>
     * 更新時間を取得する。
     * @param    なし
     * @return    String    更新時間
     * @exception    なし
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新時間を設定する。<p>
     * <b>処理概要:</b><br>
     * 更新時間を設定する。
     * @param    updateDate    更新時間
     * @return    なし
     * @exception    なし
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 権限番号を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限番号を取得する。
     * @param    なし
     * @return    String    権限番号
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
     * @return    String    権限名
     * @exception    なし
     */
    public String getSvnm0312PrivilegeName() {
        return svnm0312PrivilegeName;
    }

    /**
     * 権限名を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限名を設定する。
     * @param    svnm0312PrivilegeName    権限名
     * @return    なし
     * @exception    なし
     */
    public void setSvnm0312PrivilegeName(String svnm0312PrivilegeName) {
        this.svnm0312PrivilegeName = svnm0312PrivilegeName;
    }

    /**
     * プロジェクト管理権限を取得する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト管理権限を取得する。
     * @param    なし
     * @return    SelectBean[]    プロジェクト管理権限
     * @exception    なし
     */
    public SelectBean[] getProjectPrivilegeBean() {
        return projectPrivilegeBean;
    }

    /**
     * プロジェクト管理権限を設定する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト管理権限を設定する。
     * @param    projectPrivilegeBean    プロジェクト管理権限
     * @return    なし
     * @exception    なし
     */
    public void setProjectPrivilegeBean(SelectBean[] projectPrivilegeBean) {
        this.projectPrivilegeBean = projectPrivilegeBean;
    }

    /**
     * プロジェクト管理権限コードを取得する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト管理権限コードを取得する。
     * @param    なし
     * @return    String    プロジェクト管理権限コード
     * @exception    なし
     */
    public String getProjectPrivilegeCode() {
        return projectPrivilegeCode;
    }

    /**
     * プロジェクト管理権限コードを設定する。<p>
     * <b>処理概要:</b><br>
     * プロジェクト管理権限コードを設定する。
     * @param    projectPrivilegeCode    プロジェクト管理権限コード
     * @return    なし
     * @exception    なし
     */
    public void setProjectPrivilegeCode(String projectPrivilegeCode) {
        this.projectPrivilegeCode = projectPrivilegeCode;
    }

    /**
     * マスタメンテ権限を取得する。<p>
     * <b>処理概要:</b><br>
     * マスタメンテ権限を取得する。
     * @param    なし
     * @return    SelectBean[]    マスタメンテ
     * @exception    なし
     */
    public SelectBean[] getMasterPrivilegeBean() {
        return masterPrivilegeBean;
    }

    /**
     * マスタメンテ権限を設定する。<p>
     * <b>処理概要:</b><br>
     * マスタメンテ権限を設定する。
     * @param    masterPrivilegeBean    マスタメンテ権限
     * @return    なし
     * @exception    なし
     */
    public void setMasterPrivilegeBean(SelectBean[] masterPrivilegeBean) {
        this.masterPrivilegeBean = masterPrivilegeBean;
    }

    /**
     * マスタメンテ権限コードを取得する。<p>
     * <b>処理概要:</b><br>
     * マスタメンテ権限コードを取得する。
     * @param    なし
     * @return    String    マスタメンテコード
     * @exception    なし
     */
    public String getMasterPrivilegeCode() {
        return masterPrivilegeCode;
    }

    /**
     * マスタメンテ権限を設定する。<p>
     * <b>処理概要:</b><br>
     * マスタメンテ権限を設定する。
     * @param    masterPrivilegeCode    マスタメンテ権限コード
     * @return    なし
     * @exception    なし
     */
    public void setMasterPrivilegeCode(String masterPrivilegeCode) {
        this.masterPrivilegeCode = masterPrivilegeCode;
    }

    /**
     * コメントを取得する。<p>
     * <b>処理概要:</b><br>
     * コメントを取得する。
     * @param    なし
     * @return    String    コメント
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
     * ボタンの値を取得する。<p>
     * <b>処理概要:</b><br>
     * ボタン値を取得する。
     * @param    なし
     * @return    String    ボタン値
     * @exception    なし
     */
    public String getSubmitValue() {
        return submitValue;
    }

    /**
     * ボタン値を設定する。<p>
     * <b>処理概要:</b><br>
     * ボタン値を設定する。
     * @param    submitValue    ボタン値
     * @return    なし
     * @exception    なし
     */
    public void setSubmitValue(String submitValue) {
        this.submitValue = submitValue;
    }

    /**
     * 入力されたデータを検証する。
     * @param    mapping    ActionMapping
     * @param    request    HttpServletRequest
     * @return    errors    ActionErrors
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        ActionErrors error = new ActionErrors();
        // ログ出力
        log.printLog("INFO", "SVNM0312Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        SVNM0312Dao svnm0312Dao = new SVNM0312Dao();
        try {
            this.setProjectPrivilegeBean(svnm0312Dao.getCommbox("1002"));
            this.setMasterPrivilegeBean(svnm0312Dao.getCommbox("1002"));
            request.setAttribute("SVNM0312Bean", this);
            // セッションタイムアウトの場合
            if (request.getSession().getAttribute("sysUserId") == null) {
                // ログ出力
                log.printLog("INFO", "SVNM0312Bean", String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return null;
            }
            else if(request.getSession().getAttribute("masterPrivilege")==null){
                // ログ出力
                log.printLog("INFO", "SVNM0312Bean", String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return null;
            }
            else if(request.getSession().getAttribute("masterPrivilege").equals("0")||
                    request.getSession().getAttribute("masterPrivilege").equals("1")){
                // ログ出力
                log.printLog("INFO", "SVNM0312Bean", String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return null;
            }
            // 別の画面から遷移した場合
            else if (submitValue.equals("back")||request.getAttribute("SVNM0310Page") != null) {
                // ログ出力
                log.printLog("INFO", "SVNM0312Bean", String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return null;
            }
            // 本画面からじゃない場合、チェックしない。
            else if(submitValue.equals("")||submitValue==null){
                // ログ出力
                log.printLog("INFO", "SVNM0312Bean", String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return null;
            }
            // 権限名は入力していない場合
            else if (FBSEChecker.isEmpty(svnm0312PrivilegeName)) {
                // エラーメッセージを設定する
                error.add("", new ActionMessage("E001", "権限名"));
                // SVNフォルダ名のバックグラウンドを赤くして、フォーカスを合わせる
                setHidError(svnmError("SVNM0312Bean", "svnm0312PrivilegeName"));
            }
            // 権限名は日本語ではない場合
            else if (!FBSEChecker.isJapanese(svnm0312PrivilegeName, false)) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E005", "権限名"));
                // 権限名のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0312Bean", "svnm0312PrivilegeName"));
            }
            // プロジェクト管理とマスタメンテは同時に「なし」を選択する場合
            else if (projectPrivilegeCode.equals("0")&&masterPrivilegeCode.equals("0")) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E018"));
                // プロジェクト管理のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0312Bean", "projectPrivilegeCode"));
            }
            // 入力したコメントは100桁じゃない。
            else if (privilegeComment.length() > 100) {
                // エラーメッセージの設定
                error.add("", new ActionMessage("E008", "100", "コメント"));
                // コメントのバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0312Bean", "privilegeComment"));
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "SVNM0312Bean", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", e.toString());
        }
        // ログ出力
        log.printLog("INFO", "SVNM0312Bean", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return error;
    }
}