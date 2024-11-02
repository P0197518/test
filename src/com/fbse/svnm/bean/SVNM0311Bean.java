/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :権限新規画面
 * モジュール名        :SVNM0311Bean.java
 * 担当者            :FBSE)張志明
 * 作成日            :2008.12.18
 * 機能概要           :画面で項目のチェックを行う。
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.18 :FBSE)張志明
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
import com.fbse.svnm.base.BaseDao;

/**
 * <HR>
 * 権限新規画面の入力値の取得及び入力値有効性のチェックを行う<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>権限新規の入力値を取得</li>
 * <li>入力値のgetとsetメソッドを生成する</li>
 * <li>入力値有効性をチェックする</li><br>
 * <li>権限名の正確性をチェックする。</li><br>
 * <li>プロジェクト管理の正確性のチェック。</li><br>
 * <li>マスタメンテの正確性のチェック。</li><br>
 * <li>コメントの正確性のチェック。</li><br>
 * <li>画面値のreset</li>
 * </ul>
 * @version  V1.0
 * @author   FBSE)張志明
 * @see      com.fbse.svnm.base.BaseBean
 */
public class SVNM0311Bean extends BaseBean {

    // クラス名の宣言と初期化
    private static String className = "SVNM0311Bean";

    /** 権限名の宣言と初期化 */
    private String svnm0311PrivilegeName = "";

    /** プロジェクト管理権限の宣言 */
    private SelectBean[] privilegeBean;

    /** プロジェクト管理権限コードの宣言 */
    private String projectPrivilegeCode;

    /** マスタメンテ権限コードの宣言 */
    private String masterPrivilegeCode;

    /** コメントの宣言と初期化 */
    private String privilegeComment = "";

    /** ボタンの宣言と初期化 */
    private String submitValue = "";

    /**
     * 権限名を取得する。<p>
     * <b>処理概要:</b><br>
     * <li>権限名を取得する。</li>
     * @param    なし
     * @return    String    権限名
     * @exception    なし
     */
    public String getSvnm0311PrivilegeName() {
        return svnm0311PrivilegeName;
    }

    /**
     * 権限名を設定する。<p>
     * <b>処理概要:</b><br>
     * <li>権限名を設定する。</li>
     * @param    svnm0312PrivilegeName    権限名
     * @return    なし
     * @exception    なし
     */
    public void setSvnm0311PrivilegeName(String svnm0311PrivilegeName) {
        this.svnm0311PrivilegeName = svnm0311PrivilegeName;
    }

    /**
     * 権限を取得する。<p>
     * <b>処理概要:</b><br>
     * <li>権限を取得する。</li>
     * @param    なし
     * @return    SelectBean[]    権限
     * @exception    なし
     */
    public SelectBean[] getPrivilegeBean() {
        return privilegeBean;
    }

    /**
     * 権限を設定する。<p>
     * <b>処理概要:</b><br>
     * <li>権限を設定する。</li>
     * @param    privilegeBean    権限
     * @return    なし
     * @exception    なし
     */
    public void setPrivilegeBean(SelectBean[] privilegeBean) {
        this.privilegeBean = privilegeBean;
    }

    /**
     * プロジェクト管理権限コードを取得する。<p>
     * <b>処理概要:</b><br>
     * <li>プロジェクト管理権限コードを取得する。</li>
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
     * <li>プロジェクト管理権限コードを設定する。</li>
     * @param    projectPrivilegeCode    プロジェクト管理権限コード
     * @return    なし
     * @exception    なし
     */
    public void setProjectPrivilegeCode(String projectPrivilegeCode) {
        this.projectPrivilegeCode = projectPrivilegeCode;
    }

    /**
     * マスタメンテ権限コードを取得する。<p>
     * <b>処理概要:</b><br>
     * <li>マスタメンテ権限コードを取得する。</li>
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
     * <li>マスタメンテ権限を設定する。</li>
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
     * <li>コメントを取得する。</li>
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
     * <li>コメントを設定する。</li>
     * @param    privilegeComment    コメント
     * @return    なし
     * @exception    なし
     */
    public void setPrivilegeComment(String privilegeComment) {
        this.privilegeComment = privilegeComment;
    }

    /**
     * ボタン値を取得する。<p>
     * <b>処理概要:</b><br>
     * <li>ボタン値を取得する。</li>
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
     * <li>ボタン値を設定する。</li>
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
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        ActionErrors error = new ActionErrors();
        // セッションタイムアウトの場合
        if (request.getSession().getAttribute("sysUserId") == null) {
            error = null;
        }
        // 別の画面から遷移した場合
        else if (!submitValue.split(",")[0].equals("SVNM0311")
                || submitValue.split(",")[1].equals("return")) {
            error = null;
        }
        // 権限名は入力していない場合
        else if (FBSEChecker.isEmpty(svnm0311PrivilegeName)) {
            // エラーメッセージを設定する
            error.add("", new ActionMessage("E001", "権限名"));
            // 権限名のバックグラウンドを赤くして、フォーカスを合わせる
            setHidError(svnmError("SVNM0311Bean", "svnm0311PrivilegeName"));
        }
        // 入力した権限名は日本語じゃない。
        else if (!FBSEChecker.isJapanese(svnm0311PrivilegeName, false)) {
            // エラーメッセージの設定
            error.add("", new ActionMessage("E005", "権限名"));
            // 権限名のバックグラウンドを赤くするとフォーカスを合わせる
            setHidError(svnmError("SVNM0311Bean", "svnm0311PrivilegeName"));
        }
        // プロジェクト管理とマスタメンテは同時に「なし」を選択した場合。
        else if (projectPrivilegeCode.equals("0") && masterPrivilegeCode.equals("0")) {
            // エラーメッセージの設定
            error.add("", new ActionMessage("E018", "プロジェクト管理", "マスタメンテ"));
            // プロジェクト管理のバックグラウンドを赤くするとフォーカスを合わせる
            setHidError(svnmError("SVNM0311Bean", "projectPrivilegeCode"));
        }
        // 入力したコメントの長さは100以内じゃない。
        else if (privilegeComment.length() > 100) {
            // エラーメッセージの設定
            error.add("", new ActionMessage("E008", "100", "コメント"));
            // コメントのバックグラウンドを赤くするとフォーカスを合わせる
            setHidError(svnmError("SVNM0311Bean", "privilegeComment"));
        }
        try {
            privilegeBean = new BaseDao().getCommbox("1002");
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", e.toString());
            return null;
        }
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return error;
    }
}