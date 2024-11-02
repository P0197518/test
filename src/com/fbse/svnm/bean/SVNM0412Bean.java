/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名       :マスタメンテ 
 * プログラム名        :従業員編集画面
 * モジュール名        :SVNM0412Bean.java
 * 担当者           :FBSE)馬春晶
 * 作成日           :2008.12.17
 * 機能概要          :従業員情報を編集する。
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:   2008.12.17   :FBSE)馬春晶
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
import com.fbse.svnm.dao.SVNM0412Dao;

/**
 * <HR>
 * 従業員情報を編集する。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>修正した従業員情報を保存する。</li>
 * <li>入力値のgetとset方法を生成する</li>
 * <li>入力値の正確性のチェック</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseBean
 */
public class SVNM0412Bean extends BaseBean {

    /** 従業員番号 */
    private String systemUserNo = "";

    /** 従業員名前 */
    private String systemUserName = "";

    /** 新しいシステム登録パスワード*/
    private String sysPassword = "";

    /** システム登録パスワード確認 */
    private String sysRegPassword = "";

    /** 元のシステム登録パスワード */
    private String sysOldPassword = "";

    /** SVN登録名 */
    private String loginName = "";

    /** メール */
    private String userMail = "";

    /** 元のSVNパスワード */
    private String oldPassword = "";

    /** 新しいSVNパスワード */
    private String loginPassword = "";

    /** コメント */
    private String userComment = "";

    /** 操作状態 */
    private String subValue = "";

    /** SVNパスワード確認 */
    private String userRegPassword = "";

    /** 更新時間 */
    private String updateDate = "";

    /** 元の登録名 */
    private String oldLogName = "";

    /** プルダウンリスト（権限） */
    private SelectBean privilegeComBean[]; 

    /** 権限値 */
    private String comPriviege = "";

    /** 頁番号 */
    private String pageId = "";

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
     * システム登録パスワード確認を取得する。<p>
     * <b>処理概要:</b><br>
     * システム登録パスワード確認を取得する。
     * @param    なし
     * @return    String    システム登録パスワード確認
     * @exception    なし
     */
    public String getSysRegPassword() {
        return sysRegPassword;
    }

    /**
     * システム登録パスワード確認を設定する。<p>
     * <b>処理概要:</b><br>
     * のシステム登録パスワード確認を設定する
     * @param    sysPassword    システム登録パスワード確認
     * @return    なし
     * @exception    なし
     */
    public void setSysRegPassword(String sysRegPassword) {
        this.sysRegPassword = sysRegPassword;
    }

    /**
     * 新しいシステム登録パスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * 新しいシステム登録パスワードを取得する。
     * @param    なし
     * @return    String    目下のシステム登録パスワード
     * @exception    なし
     */
    public String getSysPassword() {
        return sysPassword;
    }

    /**
     * 目下のシステム登録パスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * 目下のシステム登録パスワードを設定する
     * @param    sysPassword    目下のシステム登録パスワード
     * @return    なし
     * @exception    なし
     */
    public void setSysPassword(String sysPassword) {
        this.sysPassword = sysPassword;
    }

    /**
     * 元のシステム登録パスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * 元のシステム登録パスワードを取得する。
     * @param    なし
     * @return    String    元のシステム登録パスワード
     * @exception    なし
     */
    public String getSysOldPassword() {
        return sysOldPassword;
    }

    /**
     * 元のシステム登録パスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * 元のシステム登録パスワードを設定する
     * @param    systemPassword    元のシステム登録パスワード
     * @return    なし
     * @exception    なし
     */
    public void setSysOldPassword(String sysOldPassword) {
        this.sysOldPassword = sysOldPassword;
    }

    /**
     * 権限配列を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限配列を取得する。
     * @param    なし
     * @return    String    権限配列
     * @exception    なし
     */
    public SelectBean[] getPrivilegeComBean() {
        return privilegeComBean;
    }

    /**
     * 権限配列を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限配列を設定する
     * @param    privilegeComBean    権限配列
     * @return    なし
     * @exception    なし
     */
    public void setPrivilegeComBean(SelectBean[] privilegeComBean) {
        this.privilegeComBean = privilegeComBean;
    }

    /**
     * 権限値を取得する。<p>
     * <b>処理概要:</b><br>
     * 権限値を取得する。
     * @param    なし
     * @return    String    権限値
     * @exception    なし
     */
    public String getComPriviege() {
        return comPriviege;
    }

    /**
     * 権限値を設定する。<p>
     * <b>処理概要:</b><br>
     * 権限値を設定する
     * @param    comPriviege    権限値
     * @return    なし
     * @exception    なし
     */
    public void setComPriviege(String comPriviege) {
        this.comPriviege = comPriviege;
    }

     /**
     * 元の登録名を取得する。<p>
     * <b>処理概要:</b><br>
     * 元の登録名を取得する。
     * @param    なし
     * @return    String    元の登録名
     * @exception    なし
     */
    public String getOldLogName() {
        return oldLogName;
    }

    /**
     * 元の登録名を設定する。<p>
     * <b>処理概要:</b><br>
     * 元の登録名を設定する。
     * @param    oldLogName    元の登録名
     * @return    なし
     * @exception    なし
     */
    public void setOldLogName(String oldLogName) {
        this.oldLogName = oldLogName;
    }

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
     * コメントを取得する。<p>
     * <b>処理概要:</b><br>
     * コメントを取得する。
     * @param    なし
     * @return    String    コメント
     * @exception    なし
     */
    public String getUserComment() {
        return userComment;
    }

    /**
     * コメントを設定する。<p>
     * <b>処理概要:</b><br>
     * コメントを設定する。
     * @param    comment    コメント
     * @return    なし
     * @exception    なし
     */
    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    /**
     * メールを取得する。<p>
     * <b>処理概要:</b><br>
     * メールを取得する。
     * @param    なし
     * @return    String    メール
     * @exception    なし
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * メールを設定する。<p>
     * <b>処理概要:</b><br>
     * メールを設定する。
     * @param    mail    メール
     * @return    なし
     * @exception    なし
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    /**
     * SVN登録名を取得する。<p>
     * <b>処理概要:</b><br>
     * SVN登録名を取得する。
     * @param    なし
     * @return    String    SVN登録名
     * @exception    なし
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * SVN登録名を設定する。<p>
     * <b>処理概要:</b><br>
     * SVN登録名を設定する。
     * @param    svnLoginName    SVN登録名
     * @return    なし
     * @exception    なし
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * SVNパスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * 登録パスワードを取得する。
     * @param    なし
     * @return    String    SVNパスワード
     * @exception    なし
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * SVNパスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * 登録パスワードを設定する。
     * @param    loginPassword    SVNパスワード
     * @return    なし
     * @exception    なし
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    /**
     * 従業員名前を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を取得する。
     * @param    なし
     * @return    String    従業員名前
     * @exception    なし
     */
    public String getSystemUserName() {
        return systemUserName;
    }

    /**
     * 従業員名前を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員名前を設定する。
     * @param    systemUserName    従業員名前
     * @return    なし
     * @exception    なし
     */
    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    /**
     * 従業員番号を取得する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を取得する。
     * @param    なし
     * @return    String    従業員番号
     * @exception    なし
     */
    public String getSystemUserNo() {
        return systemUserNo;
    }

    /**
     * 従業員番号を設定する。<p>
     * <b>処理概要:</b><br>
     * 従業員番号を設定する。
     * @param    systemUserNo    従業員番号
     * @return    なし
     * @exception    なし
     */
    public void setSystemUserNo(String systemUserNo) {
        this.systemUserNo = systemUserNo;
    }

     /**
     * 押下したボタンの値を取得する。<p>
     * <b>処理概要:</b><br>
     * 押下するボタン値を取得する。
     * @param    なし
     * @return    String    押下するボタン値
     * @exception    なし
     */
    public String getSubValue() {
        return subValue;
    }

     /**
     * SVN確認パスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * SVN確認パスワードを取得する。
     * @param    なし
     * @return    String    SVN確認パスワード
     * @exception    なし
     */
    public String getUserRegPassword() {
        return userRegPassword;
    }

    /**
     * 操作状態を設定する。<p>
     * <b>処理概要:</b><br>
     * 操作状態を設定する。
     * @param    subValue    操作状態
     * @return    なし
     * @exception    なし
     */
    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }

    /**
     * SVN確認パスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * SVN確認パスワードを設定する。
     * @param    userRegPassword    SVN確認パスワード
     * @return    なし
     * @exception    なし
     */
    public void setUserRegPassword(String userRegPassword) {
        this.userRegPassword = userRegPassword;
    }

     /**
     * 元のSVNパスワードを取得する。<p>
     * <b>処理概要:</b><br>
     * 元のSVNパスワードを取得する。
     * @param    なし
     * @return    String    元のSVN確認パスワード
     * @exception    なし
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * 元のSVNパスワードを設定する。<p>
     * <b>処理概要:</b><br>
     * 元のSVNパスワードを設定する。
     * @param    oldPassword    元のSVNパスワード
     * @return    なし
     * @exception    なし
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * 入力されたデータを検証する。
     * @param    mapping    画面の遷移
     * @param    request    リクエスト
     * @return    ActionErrors    エラーメッセージ
     * @throws    Exception    異常
     * @exception    なし
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request){
        // ログを出力
        log.printLog("INFO", "SVNM0412Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "validate", "Start");
        // エラープロジェクトを宣言する
        ActionErrors error = new ActionErrors();
        try {
            //セッションタイムアウトの場合
            if(request.getSession().getAttribute("sysUserId") == null) {
                return null;
            }
            // 権限プルダウンリストを設定する
            this.setPrivilegeComBean((new SVNM0412Dao()).selectDownList());
            // 別の画面から遷移した場合
            if (request.getAttribute("SVNM0412Page") != null) {
                // Actionに入る為にヌルを戻す
                return null;
            }
            // 頁番号がない場合
            if (pageId.equals("")) {
                // Actionに入る為にヌルを戻す
                return null;
            }
            // 従業員名前がヌルの場合
            if (FBSEChecker.isEmpty(systemUserName)) {
                //エラーメッセージの設定
                error.add("", new ActionMessage("E001", "従業員名前"));
                //SVNユーザー名前のバックグラウンドを赤くして、フォーカスを合わせる
                setHidError(svnmError("SVNM0412Bean", "systemUserName"));
                return error;
            }
            // 従業員名前が日本語ではない場合
            if(!FBSEChecker.isJapanese(systemUserName, false)) {
                //エラーメッセージの設定
                error.add("", new ActionMessage("E005", "従業員名前"));
                //SVNユーザー名前のバックグラウンドを赤くして、フォーカスを合わせる
                setHidError(svnmError("SVNM0412Bean", "systemUserName"));
                // エラーを戻す
                return error;
            }
            // システム登録パスワードがヌルではない場合
            if (!FBSEChecker.isEmpty(sysPassword) || !FBSEChecker.isEmpty(sysRegPassword)) {
                // 新システム登録パスワードとシステム登録パスワード確認は違う場合
                if (!sysPassword.equals(sysRegPassword)) {
                    // エラーメッセージを設定する
                    error.add("", new ActionMessage("E004", "新システム登録パスワード", "システム登録パスワード確認"));
                    // 新システム登録パスワードのバックグラウンドを赤くするとフォーカスを合わせる
                    setHidError(svnmError("SVNM0412Bean", "sysPassword"));
                    // システム登録パスワードを空にする
                    this.sysPassword = "";
                    // システム登録パスワード確認を空白にする
                    this.sysRegPassword = "";
                    // エラーを戻す
                    return error;
                }
            }
            // SVN登録名がヌルの場合
            if (FBSEChecker.isEmpty(loginName)) {
                //エラーメッセージの設定
                error.add("", new ActionMessage("E001", "SVN登録名"));
                //SVN登録名のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0412Bean", "loginName"));
                return error;
            }
            // SVN登録名が英数字じゃないの場合
            if (!loginName.matches("^[0-9a-zA-Z]+$")) {
                //エラーメッセージの設定
                error.add("", new ActionMessage("E013", "SVN登録名"));
                //SVNユーザー登録名のバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0412Bean", "loginName"));
                return error;
            }
            // パスワードがヌルじゃない場合
            if (!FBSEChecker.isEmpty(loginPassword) || !FBSEChecker.isEmpty(userRegPassword)) {
                // SVN登録パスワードとSVN登録パスワード確認は間違う場合
                if (!loginPassword.equals(userRegPassword)) {
                    //エラーメッセージの設定
                    error.add("", new ActionMessage("E004", "新SVNパスワード", "SVNパスワード確認"));
                    // SVN登録パスワードのバックグラウンドを赤くするとフォーカスを合わせる
                    setHidError(svnmError("SVNM0412Bean", "loginPassword"));
                    // 新SVNパスワードを空白にする
                    this.loginPassword = "";
                    // SVNパスワード確認を空白にする
                    this.userRegPassword = "";
                    // エラーを戻す
                    return error;
                }
            }
            // メールがヌルの場合
            if (FBSEChecker.isEmpty(userMail)) {
                //エラーメッセージの設定
                error.add("", new ActionMessage("E001", "メール"));
                //メールのバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0412Bean", "userMail"));
                // エラーを戻す
                return error;
            }
            // メールがメールの形式じゃない場合
            if (!FBSEChecker.isEmail(userMail)) {
                //エラーメッセージの設定
                error.add("", new ActionMessage("E003", "メール"));
                //メールのバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0412Bean", "userMail"));
                return error;
            }
            //入力したコメントは100桁を超えた場合
            if(userComment.length() > 100) {
                //エラーメッセージの設定
                error.add("", new ActionMessage("E008", "100", "コメント"));
                //コメントのバックグラウンドを赤くするとフォーカスを合わせる
                setHidError(svnmError("SVNM0412Bean", "userComment"));
                return error;
            }
        }catch (Exception e) {
            // ログを出力
            log.printLog("INFO", "SVNM0412Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "validate", e.toString());
        }
        // ログを出力
        log.printLog("INFO", "SVNM0412Bean", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "validate", "End");
        // ヌルを戻す
        return null;
    }
}