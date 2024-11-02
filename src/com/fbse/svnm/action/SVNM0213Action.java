/*******************************************************************
 * システム名        :SVN管理システム
 * サブシステム名     :プロジェクト管理
 * プログラム名       :プロジェクト確認画面
 * モジュール名       :SVNM0213Action.java
 * 担当者           :FBSE)張志明
 * 作成日           :2008.12.17
 * 機能概要         :プロジェクト情報の確認とプロジェクト情報をデータベースに保存
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *            V1.0:  2008.12.17:FBSE)張志明
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
import com.fbse.common.FBSEChecker;
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.base.SendMail;
import com.fbse.svnm.bean.SVNM0213Bean;
import com.fbse.svnm.dao.SVNM0213Dao;

/**
 * <HR>
 * SVNM0213アクションの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0213画面の初期表示を行う</li>
 * <li>プロジェクト情報の確認を行う</li>
 * <li>プロジェクトの情報をデータベースに保存する</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0213Action extends BaseAction {

    /**
     * プロジェクト確認画面の初期化とプロジェクト情報をデータベースに保存する。
     * <p>
     * <b>処理概要:</b><br>
     * <ul>
     * <li>プロジェクト確認画面の初期化を行う</li>
     * <li>プロジェクト情報をデータベースに保存する</li>
     * </ul>
     * 
     * @param    mapping    マッピング
     * @param    form    ActionFormオブジェクト
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    ActionForwardオブジェクト
     * @exception    なし
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // ログ出力
        log.printLog("INFO", "SVNM0213Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");
        // セッションに設定した時間を超えるかどうかのチェック
        if (request.getSession().getAttribute("sysUserId") == null) {
            // ログ出力
            log.printLog("INFO", "SVNM0213Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", "End");
            // 登録画面へ遷移する。
            return mapping.findForward("logoff");
        }
        // ユーザー番号を取得。
        String userId = request.getSession().getAttribute("sysUserId").toString();
        // 本画面のbeanとdaoオブジェクトを取得
        SVNM0213Bean bean = (SVNM0213Bean) form;
        SVNM0213Dao dao = new SVNM0213Dao();
        ActionMessages error;
        // 画面IDの取得
        String[] pageId = bean.getPageId().split(",");
        // 遷移元の画面はプロジェクト新規画面とプロジェクト編集画面ではない場合
        if (request.getAttribute("lastPage") == null
                && !pageId[0].equals("SVNM0213")) {
            // ログ出力
            log.printLog("INFO", "SVNM0213Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", "End");
            // 登録画面へ遷移する。
            return mapping.findForward("logoff");
        }
        // 新規画面或いは編集画面から遷移した場合
        if (!pageId[0].equals("SVNM0213")) {
            // ログ出力
            log.printLog("INFO", "SVNM0213Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", "End");
            // 本画面へ遷移する
            return mapping.getInputForward();
        }
        try {
            // 本画面で確認ボタンを押下した場合
            if (pageId[1].equals("ok")) {
                // プロジェクト新規画面から遷移した場合
                if (FBSEChecker.isEmpty(bean.getProjectNo())) {
                    // ＳＶＮフォルダー名は存在していない場合
                    if (!dao.folderExist(bean.getSvnFolderName(), "", false)) {
                        // プロジェクト情報を新規する
                        dao.projectInsert(bean, userId);
                        // メールを送信するオブジェクトを宣言
                        SendMail mail = new SendMail();
                        // メールを送信する
                        mail.sendMail(bean.getProjectNo(),0);
                        // メッセージを保存する
                        request.setAttribute("sousaMsg", new ActionMessage(
                                "I003", bean.getProjectName()));
                        // ログ出力
                        log.printLog("INFO", "SVNM0213Action", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "execute",
                                "End");
                        // プロジェクト検索画面へ遷移する
                        return mapping.findForward("SVNM0210");
                    }
                    // ＳＶＮフォルダー名は存在する
                    else {
                        // エラーメッセジーを設定
                        error = new ActionMessages();
                        error.add("", new ActionMessage("E007", "SVNフォルダ名"));
                        // エラーメッセジーを保存する
                        saveErrors(request, error);
                    }
                }
                // プロジェクト編集画面から遷移した
                else {
                    // ＳＶＮフォルダー名は存在しない
                    if (!dao.folderExist(bean.getSvnFolderName(), bean
                            .getProjectNo(), true)) {
                        // プロジェクト情報を更新する
                        if (dao.projectUpdate(bean, userId)) {
                            // メッセージを保存する
                            request.setAttribute("sousaMsg", new ActionMessage(
                                    "I012", bean.getProjectName()));
                            // ログ出力
                            log.printLog("INFO", "SVNM0213Action",
                                    String.valueOf((new Throwable()).getStackTrace()[0]
                                            .getLineNumber()), "execute", "End");
                            // プロジェクト検索画面へ遷移する
                            return mapping.findForward("SVNM0210");
                        }
                        // プロジェクトは他の人で更新した
                        else {
                            // エラーメッセジーを設定
                            error = new ActionMessages();
                            error.add("", new ActionMessage("I007"));
                            // エラーメッセジーを保存する
                            saveErrors(request, error);
                        }
                        // ＳＶＮフォルダー名は存在する
                    } else {
                        // エラーメッセジーを設定
                        error = new ActionMessages();
                        error.add("", new ActionMessage("E007", "フォルダー名"));
                        // エラーメッセジーを保存する
                        saveErrors(request, error);
                    }
                }
            }
            // 本画面の戻るボタンを押下する
            else {
                request.setAttribute("lastPage", "SVNM0213");
                // 新規画面から遷移した
                if (FBSEChecker.isEmpty(bean.getProjectNo())) {
                    // ログ出力
                    log.printLog("INFO", "SVNM0213Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "execute", "End");
                    // 新規画面へ遷移する
                    return mapping.findForward("SVNM0211");
                }
                // ログ出力
                log.printLog("INFO", "SVNM0213Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                // 編集画面へ遷移する
                return mapping.findForward("SVNM0212");
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "SVNM0213Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", e.toString());
            // エラー画面へ遷移する
            return mapping.findForward("error");
        }
        // ログ出力
        log.printLog("INFO", "SVNM0213Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 本画面へ遷移する
        return mapping.getInputForward();
    }
}