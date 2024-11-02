/*******************************************************************
 * システム名        :SVN管理システム
 * サブシステム名     :プロジェクト管理
 * プログラム名       :プロジェクト新規画面
 * モジュール名       :SVNM0211Action.java
 * 担当者           :FBSE)張志明
 * 作成日           :2008.12.17
 * 機能概要         :プロジェクト情報の新規操作を行う。
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
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.bean.SVNM0211Bean;
import com.fbse.svnm.dao.SVNM0211Dao;

/**
 * <HR>
 * SVNM0211アクションの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0211画面の初期表示を行う</li>
 * <li>新規するプロジェクト情報を処理</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0211Action extends BaseAction {

    /**
     * プロジェクト新規画面の初期化と新規するプロジェクト情報を処理。
     * <p>
     * <b>処理概要:</b><br>
     * <ul>
     * <li>プロジェクト新規画面の初期化を行う</li>
     * <li>プロジェクトの新規操作を行う</li>
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
        log.printLog("INFO", "SVNM0211Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");
        // 権限の判断とセッションに設定した時間を超えるかどうかのチェック
        if (request.getSession().getAttribute("sysUserId") == null ||
                request.getSession().getAttribute("pjPrivilege") == null ||
                !request.getSession().getAttribute("pjPrivilege").equals("2")) {
            // ログ出力
            log.printLog("INFO", "SVNM0211Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", "End");
            // 登録画面へ遷移する。
            return mapping.findForward("logoff");
        }
        try {
            // SVNM0211Beanオブジェクトの取得
            SVNM0211Bean bean = (SVNM0211Bean) form;
            // 画面IDの取得
            String[] pageId = bean.getPageId().split(",");
            // 画面IDのクリア
            bean.setPageId("");
            // 他の画面から遷移した場合
            if (!pageId[0].equals("SVNM0211")) {
                if (request.getAttribute("lastPage") == null) {
                    request.setAttribute("SVNM0211Bean", new SVNM0211Bean());
                }
                // ログ出力
                log.printLog("INFO", "SVNM0211Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                // 本画面へ遷移する。
                return mapping.getInputForward();
            }
            // 本画面の確定ボタンを押下した場合。
            if (pageId[1].equals("ok")) {
                // SVNM0211Daoオブジェクトの宣言
                SVNM0211Dao dao = new SVNM0211Dao();
                // SVNフォルダ名の存在性のチェック
                if (dao.folderExist(bean.getSvnFolderName())) {
                    // エラーメッセージの設定
                    ActionMessages error = new ActionMessages();
                    error.add("", new ActionMessage("E007", "SVNフォルダ名"));
                    saveErrors(request, error);
                    // フォーカスの合わせる
                    bean.setHidError(bean.svnmError("SVNM0211Bean",
                            "svnFolderName"));
                    // ログ出力
                    log.printLog("INFO", "SVNM0211Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "execute", "End");
                    // 本画面へせんいする
                    return mapping.getInputForward();
                }
                // 画面IDとプロジェクト情報を保存して、SVNM0213プロジェクト確認画面へ遷移する。
                request.setAttribute("lastPage", "SVNM0211");
                // ログ出力
                log.printLog("INFO", "SVNM0211Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                return mapping.findForward("SVNM0213");
            }
            // 本画面の戻るボタンを押下した場合
            if (pageId[1].equals("return")) {
                // ログ出力
                log.printLog("INFO", "SVNM0211Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                // SVNM0210プロジェクト検索画面へ遷移する
                return mapping.findForward("SVNM0210");
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "SVNM0211Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", e.toString());
            // 異常が発生場合、エラー画面へ遷移する
            return mapping.findForward("error");
        }
        // ログ出力
        log.printLog("INFO", "SVNM0211Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 本画面へ遷移する
        return mapping.getInputForward();
    }
}