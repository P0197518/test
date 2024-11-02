/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名     :マスタメンテ（従業員管理）
 * プログラム名       :従業員新規画面
 * モジュール名       :SVNM0411Bean.java
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
import com.fbse.svnm.dao.SVNM0411Dao;

/**
 * <HR>
 * 従業員新規画面の入力値の取得及び入力値の有効性のチェックを行う<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>従業員新規の入力値を取得</li>
 * <li>入力値のgetとset方法を生成する</li>
 * <li>入力値有効性のチェック</li>
 * </ul>
 *
 * @version V1.0
 * @author  FBSE)王志龍
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0411Bean extends BaseBean {

    /** クラス名の宣言と初期化*/
    private static String className = "SVNM0411Bean";

    /** 権限リストBeanの宣言*/
    private SelectBean privilegeSelect[];

    /** 従業員番号の宣言と初期化*/
    private String sysUserNo = null;

    /** 従業員名前の宣言と初期化*/
    private String sysUserName = null;

    /** システム登録パスワードの宣言と初期化*/
    private String sysLoginPsw = null;

    /** システム登録パスワード確認の宣言と初期化*/
    private String sysLoginPswKakuninn = null;

    /** SVN登録名の宣言と初期化*/
    private String svnLoginName = null;

    /** SVNパスワードの宣言と初期化*/
    private String svnPassword = null;

    /** SVNパスワード確認の宣言と初期化*/
    private String svnPasswordKakuninn = null;
    
    /** メールの宣言と初期化*/
    private String mail = null;
    
    /** 権限の宣言と初期化*/
    private String privilege = null;

    /** コメントの宣言と初期化*/
    private String comment = null;

    /**
    * 権限リストBean値を取得する。<p>
    * <b>処理概要:</b><br>
    * 権限リストBeanを取得する。
    * @param    なし
    * @return    String    プルダウンリスト配列
    * @exception    なし
    */
    public SelectBean[] getPrivilegeSelect() {
        return privilegeSelect;
    }

    /**
     * 権限リストBean値を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限リストBean値を設定する。
     * @param    privilegeSelect    ダウンリスト配列
     * @return    なし
     * @exception    なし
     */
    public void setPrivilegeSelect(SelectBean[] privilegeSelect) {
        this.privilegeSelect = privilegeSelect;
    }

    /**
     * 従業員番号を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を取得する。
     * @param    なし
     * @return    String    従業員番号
     * @exception    なし
     */
    public String getSysUserNo() {
        return sysUserNo;
    }

    /**
     * 従業員番号を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を設定する。
     * @param    sysUserNo    従業員番号
     * @return    なし
     * @exception    なし
     */
    public void setSysUserNo(String sysUserNo) {
        this.sysUserNo = sysUserNo;
    }

    /**
     * 従業員名前を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を取得する。
     * @param    なし    
     * @return    String    従業員名
     * @exception    なし    
     */
    public String getSysUserName() {
        return sysUserName;
    }

    /**
     * 従業員名前を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を設定する。
     * @param    sysUserName    従業員名
     * @return    なし
     * @exception    なし
     */
    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    /**
     * システム登録パスワードを取得する。<p>
     *<b>処理概要:</b><br>
     * システム登録パスワードを取得する。
     * @param    なし    
     * @return    String    システム登録パスワード
     * @exception    なし    
     */
    public String getSysLoginPsw() {
        return sysLoginPsw;
    }

    /**
     * システム登録パスワードを設定する。<p>
     *<b>処理概要:</b><br>
     * システム登録パスワードを設定する。
     * @param    sysLoginPsw    システム登録パスワード
     * @return    なし
     * @exception    なし
     */
    public void setSysLoginPsw(String sysLoginPsw) {
        this.sysLoginPsw = sysLoginPsw;
    }

    /**
     * システム登録パスワード確認を取得する。<p>
     *<b>処理概要:</b><br>
     * システム登録パスワード確認を取得する。
     * @param    なし    
     * @return    String    システム登録パスワード確認
     * @exception    なし    
     */
    public String getSysLoginPswKakuninn() {
        return sysLoginPswKakuninn;
    }

    /**
     * システム登録パスワード確認を設定する。<p>
     *<b>処理概要:</b><br>
     * システム登録パスワード確認を設定する。
     * @param    sysLoginPswKakuninn    システム登録パスワード確認
     * @return    なし
     * @exception    なし
     */
    public void setSysLoginPswKakuninn(String sysLoginPswKakuninn) {
        this.sysLoginPswKakuninn = sysLoginPswKakuninn;
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
     *<b>処理概要:</b><br>
     * SVNパスワードを取得する。
     * @param    なし    
     * @return    String    SVNパスワード
     * @exception    なし    
     */
    public String getSvnPassword() {
        return svnPassword;
    }

    /**
     * SVNパスワードを設定する。<p>
     *<b>処理概要:</b><br>
     * SVNパスワードを設定する。
     * @param    svnPassword    SVNパスワード
     * @return    なし
     * @exception    なし
     */
    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }

    /**
     * SVNパスワード確認を取得する。<p>
     *<b>処理概要:</b><br>
     * SVNパスワード確認を取得する。
     * @param    なし    
     * @return    String    SVNパスワード確認
     * @exception    なし    
     */
    public String getSvnPasswordKakuninn() {
        return svnPasswordKakuninn;
    }

    /**
     * SVNパスワード確認を設定する。<p>
     *<b>処理概要:</b><br>
     * SVNパスワード確認を設定する。
     * @param    svnPasswordKakuninn    SVNパスワード確認
     * @return    なし
     * @exception    なし
     */
    public void setSvnPasswordKakuninn(String svnPasswordKakuninn) {
        this.svnPasswordKakuninn = svnPasswordKakuninn;
    }

    /**
     * メールを取得する。<p>
     * <b>処理概要:</b><br>
     * メールを取得する。
     * @param    なし
     * @return    String   メール 
     * @exception なし;
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
     * 権限を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限を取得する。
     * @param    なし
     * @return    String   権限 
     * @exception    なし
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 権限を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限を設定する。
     * @param    privilege    権限
     * @return    なし
     * @exception    なし
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * コメントを取得する。<p>
     * <b>処理概要:</b><br>
     * コメントを取得する。
     * @param    なし
     * @return    String   コメント 
     * @exception なし;
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
     * 入力されたデータを検証する。
     * @param    mapping    ActionMapping
     * @param    request    HttpServletRequest
     * @return    errors    ActionErrors
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "Start");
        // セッションタイムアウトの場合
        HttpSession session = request.getSession();
        // セッションにシステムユーザー番号がない時
        if (session.getAttribute("sysUserId") == null) {
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            // nullを戻す
            return null;
        }
        // 権限コンボボックスの値を取得する
        try{
            SVNM0411Dao svnm0411Dao = new SVNM0411Dao();
            // 権限コンボボックスの値を取得する
            SelectBean[] privilegeSelect = svnm0411Dao.getCommbox();
            // 権限コンボボックス値を設定する
            this.setPrivilegeSelect(privilegeSelect);
        // 異常時
        }catch (Exception e){
            // ログの出力
            log.printLog("ERROR", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", e.toString());
            // 「null」を戻す
            return null;
        }
        // 操作していない場合、画面の初期化を実行する
        if(request.getParameter("sousa")==null){
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "validate", "End");
            return null;
        }
        // ActionErrorsオブジェクトの宣言と初期化
        ActionErrors error = new ActionErrors();
        // 確定ボタンを押下した場合、、チェックを実行する
        if (request.getParameter("sousa").toString().equals("kakutei")) {
            // 従業員番号は空である場合
            if (FBSEChecker.isEmpty(this.sysUserNo)) {
                error.add("sysUserNoNull", new ActionMessage("E001", "従業員番号"));
                setHidError(this.svnmError("SVNM0411Bean", "sysUserNo"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // 従業員番号は「F9999」の形式ではない場合
            if (!this.isBangou(this.sysUserNo)) {
                error.add("sysUserNoIsError", new ActionMessage("E017", "従業員番号"));
                setHidError(this.svnmError("SVNM0411Bean", "sysUserNo"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // 従業員名前が空時
            if (FBSEChecker.isEmpty(this.sysUserName)) {
                error.add("sysUserNameNull", new ActionMessage("E001", "従業員名前"));
                setHidError(this.svnmError("SVNM0411Bean", "sysUserName"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // 従業員名前は日本語ではない場合
            if (!FBSEChecker.isJapanese(this.sysUserName,false)) {
                error.add("sysUserNameIsNotJapanese", new ActionMessage("E005", "従業員名前"));
                setHidError(this.svnmError("SVNM0411Bean", "sysUserName"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // システム登録パスワードが空時
            if (FBSEChecker.isEmpty(this.sysLoginPsw)) {
                error.add("sysLoginPswNull", new ActionMessage("E001", "システム登録パスワード"));
                setHidError(this.svnmError("SVNM0411Bean", "sysLoginPsw"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // システム登録パスワード確認が空時
            if (FBSEChecker.isEmpty(this.sysLoginPswKakuninn)) {
                error.add("sysLoginPswKakuninnNull", new ActionMessage("E001", "システム登録パスワード確認"));
                setHidError(this.svnmError("SVNM0411Bean", "sysLoginPswKakuninn"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // システム登録パスワードとシステム登録パスワード確認は同じではない(違う)場合
            if (!this.sysLoginPsw.equals(this.sysLoginPswKakuninn)) {
                error.add("sysPswNotEqual", new ActionMessage("E004", "システム登録パスワード","システム登録パスワード確認"));
                setHidError(this.svnmError("SVNM0411Bean", "sysLoginPsw"));
                this.sysLoginPsw = null;
                this.sysLoginPswKakuninn = null;
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // SVN登録名は入力していない場合
            if (FBSEChecker.isEmpty(this.svnLoginName)) {
                error.add("svnLoginNameNull", new ActionMessage("E001", "SVN登録名"));
                setHidError(this.svnmError("SVNM0411Bean", "svnLoginName"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // SVN登録名がは半角英数字ではない時
            if (!this.svnLoginName.matches("^[0-9a-zA-Z]+$")) {
                error.add("svnLoginNameWrong", new ActionMessage("E013", "SVN登録名"));
                setHidError(this.svnmError("SVNM0411Bean", "svnLoginName"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // SVNパスワードが空時
            if (FBSEChecker.isEmpty(this.svnPassword)) {
                error.add("svnPasswordNull", new ActionMessage("E001", "SVNパスワード"));
                setHidError(this.svnmError("SVNM0411Bean", "svnPassword"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // SVNパスワード確認が空時
            if (FBSEChecker.isEmpty(this.svnPasswordKakuninn)) {
                error.add("sysLoginPswKakuninnNull", new ActionMessage("E001", "SVNパスワード確認"));
                setHidError(this.svnmError("SVNM0411Bean", "svnPasswordKakuninn"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // SVNパスワードとSVNパスワード確認が間違う時
            if (!this.svnPassword.equals(this.svnPasswordKakuninn)) {
                error.add("svnPswNotEqual", new ActionMessage("E004", "SVNパスワード","SVNパスワード確認"));
                setHidError(this.svnmError("SVNM0411Bean", "svnPassword"));
                this.svnPassword = null;
                this.svnPasswordKakuninn = null;
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // メールが空時
            if (FBSEChecker.isEmpty(this.mail)) {
                error.add("mailNull", new ActionMessage("E001", "メール"));
                setHidError(this.svnmError("SVNM0411Bean", "mail"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // メールの形式が間違った場合
            if (!FBSEChecker.isEmail(this.mail)) {
                error.add("mailWrong", new ActionMessage("E003", "メール"));
                setHidError(this.svnmError("SVNM0411Bean", "mail"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // 権限が選択されない場合
            if (FBSEChecker.isEmpty(this.privilege)) {
                error.add("privilegeNull", new ActionMessage("E002", "権限"));
                setHidError(this.svnmError("SVNM0411Bean", "privilege"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "validate", "End");
                return error;
            }
            // コメントが入力した場合
            if (!FBSEChecker.isEmpty(this.comment)) {
                // コメントが100桁を超えた場合
                if(this.comment.length() > 100){
                    error.add("commentNull", new ActionMessage("E008", "100", "コメント"));
                    setHidError(this.svnmError("SVNM0411Bean", "comment"));
                    // ログの出力
                    log.printLog("INFO", className, String.valueOf((new Throwable())
                            .getStackTrace()[0].getLineNumber()), "validate", "End");
                    return error;
                }
            }
        }
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "validate", "End");
        return error;
    }
}