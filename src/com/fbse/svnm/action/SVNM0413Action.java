/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :従業員検索（ポップアップ）画面
 * モジュール名        :SVNM0413Action.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.18
 * 機能概要          :従業員情報の検索
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.18  :FBSE)張建君
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.bean.SVNM0413Bean;
import com.fbse.svnm.bean.SelectBean;
import com.fbse.svnm.dao.SVNM0413Dao;

/**
 * <HR>
 * SVNM0413アクションの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>従業員情報の検索を行う。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0413Action extends BaseAction {

    /**
     * 従業員情報の検索を行う。<p>
     * <b>処理概要:</b><br>
     * <li>従業員情報の検索を行う。</li><br>
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
        log.printLog("INFO", "SVNM0413Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");
        // SVNM0413Daoオブジェクトの宣言。
        SVNM0413Dao svnm0413Dao = new SVNM0413Dao();
        // メッセージの宣言。
        ActionMessages messages = new ActionMessages();
        // sessionの宣言。
        HttpSession session = request.getSession();
        // システムユーザ番号を取得する。
        Object sysUserId = session.getAttribute("sysUserId");
        // 違法登録の場合、登録画面に遷移する。
        if(sysUserId == null) {
            // 登録画面へ遷移する.
            request.setAttribute("error", 
            forward("window.opener.top.top.location='" + request.getContextPath() + "';", request));
            return mapping.getInputForward();
        }
        // 検索条件を取得する。
        SVNM0413Bean svnm0413Bean = (SVNM0413Bean)form;
        try {
            // 従業員情報の検索を行う。
            SelectBean[] selectBean = svnm0413Dao.selectSVNUser(svnm0413Bean);
            // 従業員情報を保存する。
            svnm0413Bean.setSelectBean(selectBean);
            // 検索するデータが存在する場合。
            if(selectBean.length > 0) {
                // メッセージの追加。
                  messages.add("nai", new ActionMessage("I001", selectBean.length));
                  // エラーメッセージが保存する。
                  saveErrors(request, messages);
            } else {
                // エラーメッセージの追加。
                messages.add("nai", new ActionMessage("I002"));
                // エラーメッセージが保存する。
                saveErrors(request, messages);
            }
            // ログを出力する。
            log.printLog("INFO", "SVNM0413Action", String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "execute", "End");
            // 従業員検索（ポップアップ）画面へ遷移する。
            return mapping.getInputForward();
        }catch (Exception e) {
            // ログを出力する。
            log.printLog("error", "SVNM0413Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", e.toString());
            // エラー画面へ遷移する.
            request.setAttribute("error", 
            forward("window.opener.top.top.document.getElementById('mainFrame').src='jsp/jumpError.jsp';", request));
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0413Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 従業員検索（ポップアップ）画面へ遷移する。
        return mapping.getInputForward();
    }

    /**
     * 画面遷移のJavaScriptを取得する。<p>
     * <b>処理概要:</b><br>
     * <li>画面遷移のJavaScriptを取得する。</li><br>
     * @param    path    遷移画面のパス
     * @return    String    画面遷移のJavaScript
     * @exception    なし
     */
    public String forward(String path , HttpServletRequest request) {
        // 画面遷移のJavaScript。
        String message = "<script language='javascript'> "+ 
                         "if(window.opener != null){" +
                         path +
                         "window.close();} else {location.href = '" + request.getContextPath() +"';}" +
                         "</script>";
        // 画面遷移のJavaScriptを戻る。
        return message;
    }
}