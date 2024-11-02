/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ 
 * プログラム名        :従業員編集画面
 * モジュール名        :SVNM0412Action.java
 * 担当者           :FBSE)馬春晶
 * 作成日           :2008.12.17
 * 機能概要         :従業員の編集
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:   2008.12.17   :FBSE)馬春晶
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.base.Common;
import com.fbse.svnm.bean.SVNM0410Bean;
import com.fbse.svnm.bean.SVNM0412Bean;
import com.fbse.svnm.dao.SVNM0412Dao;

/**
 * <HR>
 * SVNM0412アクションの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0412画面の初期表示を行う</li>
 * <li>従業員の情報をデータベースに更新する</li>
 * <li>従業員の情報を従業員ファイルに更新する</li>
 * <li>従業員の情報を権限ファイルに更新する</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0412Action extends BaseAction {

    // 従業員番号
    private String userId = "";
    /**
     * SVNM0412従業員編集画面の初期化と各メッソドの呼び出し。<p>
     * <b>処理概要:</b><br>
     *　画面の初期化と各メッソドの呼び出し<br>
     * @param    mapping    マッピング
     * @param     form    従業員
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    Exception    異常
     */
    public ActionForward execute (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) 
                                  throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0412Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "Start");
        //セッションタイムアウトの場合
        if(request.getSession().getAttribute("sysUserId") == null) {
            // ログを出力
            log.printLog("INFO", "SVNM0412Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "execute", "End");
            //登録画面へ遷移する。
            return mapping.findForward("logoff");
        }
        // エラーメッセージオブジェクトを宣言する
        ActionMessages error = new ActionMessages();
        // システムユーザー番号を取得。
        userId = request.getSession().getAttribute("sysUserId").toString();
        // SVNM0412Beanプロジェクトを宣言する
        SVNM0412Bean bean = (SVNM0412Bean) form;
        try {
            // マスタメンテ権限が存在している場合
            if(request.getSession().getAttribute("masterPrivilege") != null){
                // 権限変数を宣言して権限値を設定する
                String master = request.getSession().getAttribute("masterPrivilege").toString();
                // マスタメンテ権限は管理権限がない場合
                if (!master.equals("2")) {
                    // ログを出力
                    log.printLog("INFO", "SVNM0412Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 登録画面へ遷移する。
                    return mapping.findForward("logoff");
                }
            }
            // マスタメンテの権限がない
            else {
                // ログを出力
                log.printLog("INFO", "SVNM0412Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                             .getLineNumber()), "execute", "End");
                // 登録画面へ遷移する。
                return mapping.findForward("logoff");
            }
            // 頁フラグはヌルではない場合、初期化を行う
            if (request.getAttribute("SVNM0412Page") != null) {
                userId = request.getAttribute("SVNM0412Page").toString();
                // 頁フラグを削除する
                request.removeAttribute("SVNM0412Page");
                // 初期化のデータを検索
                selectUser(form, request, userId);
                // SVNM0411編集画面へ遷移する
                return mapping.getInputForward();
            }
            // 戻るボタンを押下した場合
            if (bean.getSubValue().equals("back")) {
                // 検索画面へ遷移する
                return mapping.findForward("SVNM0410");
            }
            // 更新ボタンを押下する
            if (bean.getSubValue().equals("update")) {
                // 更新メソッドを呼び出す。データを更新する
                int state = updateUser(form, request);
                // 更新しているデータはすでに他の人に更新された場合
                if (state == 1) {
                    // エラーメッセージを保存する
                    error.add("reg", new ActionMessage("I007"));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // ログを出力
                    log.printLog("INFO", "SVNM0412Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 本画面へ遷移する
                    return mapping.getInputForward();
                }
                // 異常が発生した場合
                if (state == 2) {
                    // ログを出力
                    log.printLog("INFO", "SVNM0412Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // エラー画面へ遷移する
                    return mapping.findForward("error");
                }
                // 更新に成功した場合
                if (state == 3) {
                    // 新規することが成功するメッセージを保存する
                    request.setAttribute("sousaMsg","I004"+","+((SVNM0412Bean)form).getSystemUserNo());
                    // ログを出力
                    log.printLog("INFO", "SVNM0412Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // SVNM0410検索画面へ遷移する
                    return mapping.findForward("SVNM0410");
                }
                //登録名が重複していた場合
                if (state == 4) {
                    // SVN登録名が重複したというメッセージを保存する
                    error.add("reg",new ActionMessage("E007","SVN登録名"));
                   // SVN登録名のバックグラウンドを赤くしてフォーカスをあわせる
                    bean.setHidError(
                            bean.svnmError("SVNM0412Bean", "loginName"));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // ログを出力
                    log.printLog("INFO", "SVNM0412Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // SVNM0411新規画面へ遷移する
                    return mapping.getInputForward();
                }
            }
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0412Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "execute", e.toString());
            // エラー画面へ遷移する
            return mapping.findForward("error");
        }
        // ログを出力
        log.printLog("INFO", "SVNM0412Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "End");
        // 登録画面へ遷移する
        return mapping.findForward("logoff");
    }

    /**
     * 従業員情報検索する。<p>
     * <b>処理概要:</b><br>
     * 従業員の情報をデータベースに検索する。<br>
     * @param    form    従業員
     * @param    request    リクエスト
     * @param    svnUserNo   従業員番号
     * @return    なし
     * @exception    Exception    異常
     */
    public void selectUser (ActionForm form,
                            HttpServletRequest request,
                            String svnUserNo)
                            throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0412Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "Start");
        // SVNM0412Daoプロジェクトを宣言する
        SVNM0412Dao dao = new SVNM0412Dao();
        // SVNM0412Beanを宣言する
        SVNM0412Bean bean = new SVNM0412Bean();
        // 更新するデータをデータベースから検索する
        FBSEDataResultSet result = dao.selectUser(svnUserNo);
        // 検索した結果がある場合
        if (result.getNext("table1")) {
            // 従業員番号を取得する
            bean.setSystemUserNo(userId);
            // 従業員名前を取得する
            bean.setSystemUserName(result.getObject("USERNAME").toString());
            // SVN登録名を取得する
            bean.setLoginName(result.getObject("SVNLOGINNAME").toString());
            // SVN登録名を保存する
            bean.setOldLogName(bean.getLoginName());
            // 元のSVNパスワードを取得する
            bean.setOldPassword(result.getObject("SVNPASSWORD").toString());
            // メールを取得する
            bean.setUserMail(result.getObject("MAIL").toString());
            // 権限番号を設定する
            bean.setComPriviege(result.getObject("PRIVILEGENO").toString());
            // コメントがヌルではない場合
            try {
                // コメントを取得する
                bean.setUserComment(result.getObject("COMMENT").toString());
            }
            // コメントがヌルの場合
            catch (Exception e) {
                bean.setUserComment("");
            }
            // 更新時間を取得する
            bean.setUpdateDate(result.getObject("UPDATEDATE").toString());
        }
        // 権限プルダウンリストを設定する
        bean.setPrivilegeComBean(dao.selectDownList());
        // リクエストにSVNM0412Beanを設定する
        request.setAttribute("SVNM0412Bean", bean);
        // ログを出力
        log.printLog("INFO", "SVNM0412Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectSvnUser", "End");
    }

    /**
     * 従業員を編集する。<p>
     * <b>処理概要:</b><br>
     * 従業員の情報をデータベースに更新する。<br>
     * 従業員の情報をSVNユーザファイルに更新する。<br>
     * 従業員の情報を権限ファイルに更新する。<br>
     * 従業員の登録名の正確性のチェック<br>
     * @param    form    従業員
     * @param    request    リクエスト
     * @return    int
     *               1    更新しているデータは他の人が更新された
     *               2    異常が発生する
     *               3    更新することが成功
     *               4    登録名が重複だ
     * @exception    Exception    異常
     */
    public int updateUser (ActionForm form,
                           HttpServletRequest request)
                           throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0412Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "updateSvnUser", "Start");
        // SVNM0412Beanプロジェクトを宣言する
        SVNM0412Bean bean = (SVNM0412Bean)form;
        // 従業員番号を取得する
        String userNo = bean.getSystemUserNo();
        // 従業員名前を取得する
        String userName = Common.trim(bean.getSystemUserName());
        // システムパスワードを取得する
        String systemPass = Common.trim(bean.getSysPassword());
        // SVN登録名を取得する
        String userLoginName = Common.trim(bean.getLoginName());
        // 元の登録名を取得する
        String oldLoginName = bean.getOldLogName();
        // メールを取得する
        String mail = Common.trim(bean.getUserMail());
        // SVNパスワードを取得する
        String password = bean.getLoginPassword();
        // 元のパスワードを取得する
        String oldPass = bean.getOldPassword();
        // 権限を取得する
        String privilegeno = bean.getComPriviege();
        // コメントを取得する
        String comment = Common.trim(bean.getUserComment());
        // 更新時間を取得する
        String updateDate = bean.getUpdateDate();
        // 更新者を取得する
        String updateUser = request.getSession().getAttribute("sysUserId").toString();
        // SVNM0412Daoプロジェクトを宣言する
        SVNM0412Dao dao = new SVNM0412Dao();
        int retrunValue = dao.updateUser(userNo,        // 従業員番号
                userName,      // 従業員名前
                systemPass,    // システム登録パスワード
                userLoginName, // SVN登録名
                oldLoginName,  // 元のSVN登録名
                mail,          // メール
                password,      // SVNパスワード
                oldPass,       // 元のSVNパスワード
                privilegeno,   // 権限番号
                comment,       // コメント
                updateUser,    // 更新者
                updateDate);   // 更新時間
        if(retrunValue == 3 && userId.equals(bean.getSystemUserNo())) {
            // セッションにプロジェクト管理の権限を設定。
            request.getSession().setAttribute("pjPrivilege", dao.pjPrivilege);
            // セッションにマスタメンテの権限を設定。
            request.getSession().setAttribute("masterPrivilege", dao.masterPrivilege);
            request.getSession().setAttribute("privilegeNo", bean.getComPriviege());
            SVNM0410Bean svnm0410Bean = new SVNM0410Bean();
            svnm0410Bean.setHidError("<script language='javascript'>top.frames['mainFrame']" +
                    ".frames['TABLE_toubu'].location.reload();</script>");
            request.setAttribute("SVNM0410Bean", svnm0410Bean);
        }
        // ログを出力
        log.printLog("INFO", "SVNM0412Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "updateSvnUser", "End");
        // 更新メソッドを呼び出す
        return retrunValue;
    }
}