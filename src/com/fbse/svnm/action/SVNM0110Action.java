/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :ログイン
 * プログラム名        :登録画面
 * モジュール名        :SVNM0110Action.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.17
 * 機能概要          :システムユーザーの正確性の判断を行う。
                      システムユーザーの登録を行う。
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)張建君
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.action;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.base.Common;
import com.fbse.svnm.bean.SVNM0110Bean;
import com.fbse.svnm.dao.SVNM0110Dao;

/**
 * <HR>
 * SVNM0110アクションの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>システムユーザーの正確性の判断を行う。</li>
 * <li>システムユーザーの登録を行う。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0110Action extends BaseAction {

    /**
     * システムユーザーの登録を行う。
     * <p>
     * <b>処理概要:</b><br>
     * システムユーザーの正確性の判断を行う。<br>
     * システムユーザーの登録を行う。<br>
     * @param    mapping    マッピング
     * @param    form    フォーム
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面遷移
     * @exception    Exception    異常
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                                 throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0110Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");
        // 遷移ページID。
        String forward = "body";
        // セッションの宣言。
        HttpSession session = request.getSession();
        // エラーメッセージの宣言。
        ActionMessages errors = new ActionMessages();
        // SVNM0110Beanオブジェクトを取得する。
        SVNM0110Bean user = (SVNM0110Bean) form;
        // SVNM0110Dao対象の宣言。
        SVNM0110Dao indexDao = new SVNM0110Dao();
        // ログインボタンを押下した場合。
        if (user.getTourouku().equals("true")) {
            // システムユーザの検索を行って、システムユーザ名を戻る。
            indexDao.loginCheck(user.getUserId(), Common
                    .convertToMD5(user.getPassword()));
            // データベース異常の判断。
            if (!indexDao.flag) {
                // エラー画面に遷移する。
                forward = "error";
            }
            // 管理員ユーザを登録する場合。
            else if(user.getUserId().equals("F0000") && (!indexDao.userName.equals(""))) {
                // セッションに名前を設定。
                session.setAttribute("sysUserId", user.getUserId());
                // セッションに従業員番号を設定。
                session.setAttribute("sysUserName", indexDao.userName);
                // セッションにプロジェクト管理の権限を設定。
                session.setAttribute("pjPrivilege", "2");
                // セッションにマスタメンテの権限を設定。
                session.setAttribute("masterPrivilege", "2");
            }
            // ユーザを成功に登録する場合。
            else if((!indexDao.userName.equals("")) && (!indexDao.pjPrivilege.equals(""))) {
                // セッションに名前を設定。
                session.setAttribute("sysUserId", user.getUserId());
                // セッションに従業員番号を設定。
                session.setAttribute("sysUserName", indexDao.userName);
                // セッションにプロジェクト管理の権限を設定。
                session.setAttribute("pjPrivilege", indexDao.pjPrivilege);
                // セッションにマスタメンテの権限を設定。
                session.setAttribute("masterPrivilege", indexDao.masterPrivilege);
                session.setAttribute("privilegeNo", indexDao.privilegeNo);
            }
            // 従業員番号或いはパスワードが違う。
            else {
                // エラーメッセージの追加。
                errors.add("", new ActionMessage("E015"));
                // エラーメッセージを保存する。
                saveErrors(request, errors);
                // 従業員番号テキストボックスが赤になって、フォーカスを合わせる。
                user.setHidError(user.svnmError("SVNM0110Bean", "password"));
                // ログを出力する。
                log.printLog("INFO", "SVNM0110Action", String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", "End");
                // 登録画面に遷移する。
                return mapping.getInputForward();
            }
        } else {
            // 全てセッションの値を取得する。
            Enumeration e = session.getAttributeNames();
            // 次の値の有無のチェック
            while (e.hasMoreElements()) {
                // 次の値を取得する。
                String sessionName = (String) e.nextElement();
                // セッションの値をクリア。
                session.removeAttribute(sessionName);
            }
            // ログを出力する。
            log.printLog("INFO", "SVNM0110Action", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "execute", "End");
            // 登録画面に遷移。
            return mapping.getInputForward();
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0110Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 画面の遷移。
        return mapping.findForward(forward);
    }
}