/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :プロジェクト管理
 * プログラム名        :プロジェクト検索画面
 * モジュール名        :SVNM0210Action.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.17
 * 機能概要          :プロジェクト情報の検索、削除、修復
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)張建君
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
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.base.Common;
import com.fbse.svnm.bean.SVNM0210Bean;
import com.fbse.svnm.dao.SVNM0210Dao;

/**
 * <HR>
 * SVNM0210アクションの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>プロジェクト情報を検索する。</li>
 * <li>プロジェクトを削除する。</li>
 * <li>プロジェクトを修復する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0210Action extends BaseAction {

    // システムユーザー番号の宣言と初期化。
    private Object sysUserId = "";

    // プロジェクト管理の権限。
    private Object pjPrivilege = "";

    // 遷移ページID。
    private String forward = "logoff";

    // 検索するデータの件数。
    private String count = "";

    /**
     * プロジェクト検索画面ロジックの制御。
     * <p>
     * <b>処理概要:</b><br>
     * プロジェクト検索画面論理の制御。<br>
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
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");
        // メッセージの宣言。
        ActionMessages messages = new ActionMessages();
        // セッションの宣言。
        HttpSession session = request.getSession();
        // SVNM0210Beanオブジェクトを取得する。
        SVNM0210Bean bean = (SVNM0210Bean) form;
        // SVNM0210Dao対象の宣言。
        SVNM0210Dao svnm0210Dao = new SVNM0210Dao();
        try {
            // システムユーザ番号を取得する。
            sysUserId = session.getAttribute("sysUserId");
            // プロジェクト管理の権限をを取得する。
            pjPrivilege = session.getAttribute("pjPrivilege");
            // 違法登録の場合、登録画面に遷移する。
            if (sysUserId == null) {
                // 登録画面へ遷移する.
                forward = "logoff";
            }
            // プロジェクト管理の権限は「なし」である場合。
            else if (pjPrivilege != null && ((String)pjPrivilege).equals("0")) {
                // 登録画面へ遷移する。
                forward = "logoff";
            }
            // 検索ボタンを押下した場合。
            else if (bean.getSousaStart().equals("select")) {
                // プロジェクトの検索を行う。
                selectProject(mapping, form, request, response);
            }
            // 新規ボタンを押下した場合。
            else if (bean.getSousaStart().equals("add")) {
                // 新規画面へ遷移する。
                forward = "SVNM0211";
            }
            // 「SVNフォルダ名」アンカーをクリックした場合。
            else if(bean.getSousaStart().equals("edit")) {
                // 操作状態を削除する。
                bean.setSousaStart("");
                // プロジェクト情報最新のチェック。
                boolean saisin = svnm0210Dao.saisinCheck(bean.getProjectNo(), bean.getUpdateData());
                // プロジェクト情報が最新する場合。
                if(saisin) {
                    // プロジェクト番号をリクエストに設定する。
                    request.setAttribute("projectNo", bean.getProjectNo());
                    // 編集画面へ遷移する。
                    forward = "SVNM0212";
                }
                // プロジェクト情報が最新ではない場合。
                else {
                    // プロジェクトの検索を行う。
                    selectProject(mapping, form, request, response);
                    // 排他エラーメッセージを追加する。
                    messages.add("", new ActionMessage("I007"));
                    // エラーメッセージを保存する。
                    saveErrors(request, messages);
                    // プロジェクト検索画面へ遷移する。
                    forward = "SVNM0210";
                }
            }
            // 復元
            else if(bean.getSousaStart().equals("fukuGen")) {
                // 操作状態を削除する。
                bean.setSousaStart("");
                SVNM0210Dao dao =new SVNM0210Dao();
                if (dao.fukuGen(bean.getProjectNo(), bean.getUpdateData(), sysUserId.toString(),
                        Common.getCurrentTime("yyyyMMddHHmmssSSS"))) {
                    // プロジェクトの検索を行う。
                    selectProject(mapping, form, request, response);
                    // エラーメッセージの追加。
                    messages.add("", new ActionMessage("I004", bean.getDeleteProjectName()));
                    // エラーメッセージが保存する。
                    saveErrors(request, messages);
                } else {
                    // プロジェクトの検索を行う。
                    selectProject(mapping, form, request, response);
                    // エラーメッセージの追加。
                    messages.add("", new ActionMessage("I007"));
                    // エラーメッセージが保存する。
                    saveErrors(request, messages);
                }
            }
            // 削除アンカーをクリックする場合。
            else if(bean.getSousaStart().equals("delete")) {
                // 操作状態を削除する。
                bean.setSousaStart("");
                // プロジェクトの削除を行う。
                deleteProject(mapping, form, request, response);
            }
            // 修復アンカーをクリックする場合。
            else if(bean.getSousaStart().equals("recovery")) {
                // 操作状態を削除する。
                bean.setSousaStart("");
                // プロジェクトの修復を行う。
                recoveryProject(mapping, form, request, response);
            } 
            // 以上条件に適わない場合、画面初期化。
            else {
                // プロジェクトの検索を行う。
                selectProject(mapping, form, request, response);
                // プロジェクト検索画面から遷移しない場合。
                if(!bean.getGamenId().equals("SVNM0210")) {
                    // beanをリセット。
                    bean.reset(mapping, request);
                }
                // sousaMsgを存在する場合。
                if(request.getAttribute("sousaMsg") != null) {
                    // messagesをクリア。
                    messages.clear();
                    // メッセージの追加。
                    messages.add("", (ActionMessage)request.getAttribute("sousaMsg"));
                    // エラーメッセージを保存する。
                    saveErrors(request, messages);
                }
                // 画面の遷移。
                forward = "SVNM0210";
            }
        } catch (Exception e) {
            // ログを出力する。
            log.printLog("ERROR", "SVNM0210Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", e.toString());
            // エラー画面に遷移する。
            forward = "error";
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 画面の遷移。
        return mapping.findForward(forward);
    }

    /**
     * プロジェクト情報を検索する。
     * <p>
     * <b>処理概要:</b><br>
     * 検索条件によってプロジェクト情報を検索する。<br>
     * @param    mapping    マッピング
     * @param    form    フォーム
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    なし
     * @exception    Exception    異常
     */
    public void selectProject(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
                              throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "selectProject", "Start");
        // メッセージの宣言。
        ActionMessages messages = new ActionMessages();
        // SVNM0210Dao対象の宣言。
        SVNM0210Dao svnm0210Dao = new SVNM0210Dao();
        // SVNM0410Beanプロジェクトを取得する。
        SVNM0210Bean bean = (SVNM0210Bean)form;
        // 検索状態を取得する。
        String flg = bean.getDelflgSql();
        // 未削除状態の場合。
        if(flg.equals("0")) {
            // リクエストに設定する。
            request.setAttribute("delete", "delete");
        }
        // プロジェクト管理の権限は「管理権限」がある場合。
        if (pjPrivilege != null && ((String)pjPrivilege).equals("2")) {
            // 管理権限の設定。
            request.setAttribute("kanri", "kanri");
        }
        // プルダウンリストをSVNM0410Beanプロジェクトに設定する。
        bean.setSelectBean(svnm0210Dao.getCommbox("1001"));
        // プロジェクト情報を検索する。
        FBSEDataResultSet result = svnm0210Dao.selectProject((SVNM0210Bean) form,
                                  (String)pjPrivilege, (String)sysUserId);
        // 検索する結果がある場合。
        if (result.getRecordCount("table1") != 0) {
            // requestオブジェクトを設定する。
            request.setAttribute("result", result);
            // 検索するデータの件数を取得する。
            count = String.valueOf(result.getRecordCount("table1"));
            // メッセージの追加。
            messages.add("", new ActionMessage("I001", count));
            // メッセージが保存する。
            saveErrors(request, messages);
        }
        // 検索した結果がない場合。
        else {
            // エラーメッセージの追加。
            messages.add("", new ActionMessage("I002"));
            // エラーメッセージが保存する。
            saveErrors(request, messages);
        }
        // 画面の遷移。
        forward = "SVNM0210";
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "selectProject", "End");
    }

    /**
     * プロジェクトを削除する。
     * <p>
     * <b>処理概要:</b><br>
     * 選択したプロジェクトを削除する。
     * 対応するバージョンライブラリは削除済フォルダーに移動する。<br>
     * SVN権限ファイルを更新する。<br>
     * @param    mapping    マッピング
     * @param    form    フォーム
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    なし
     * @exception    Exception    異常
     */
    public void deleteProject(ActionMapping mapping,
                              ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
                              throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "deleteProject", "Start");
        // メッセージの宣言。
        ActionMessages messages = new ActionMessages();
        // SVNM0210Beanオブジェクトを取得する。
        SVNM0210Bean bean = (SVNM0210Bean) form;
        // SVNM0210Daoオブジェクトの宣言。
        SVNM0210Dao svnm0210Dao = new SVNM0210Dao();
        // セッションの宣言。
        HttpSession session = request.getSession();
        // プロジェクトの削除を行う。
        int startFlag = svnm0210Dao.updateProject(bean.getProjectNo(), bean.getUpdateData(), 
                (String)session.getAttribute("sysUserId"),Common.getCurrentTime("yyyyMMddHHmmssSSS") ,
                bean.getUpdateSvnFolder(), true);
        // 成功にプロジェクトを削除した場合。
        if(startFlag == 0) {
            // プロジェクトの検索を行う。
            selectProject(mapping, form, request, response);
            // メッセージの追加。
            messages.add("", new ActionMessage("I005", bean.getDeleteProjectName()));
            // エラーメッセージが保存する。
            saveErrors(request, messages);
        } 
        // プロジェクトの削除に失敗する場合。（異常が発生した場合）
        else if(startFlag == 1) {
            // 画面の遷移。
            forward = "error";
        }
        // 排他が発生した場合。
        else if(startFlag == 2) {
            // プロジェクトの検索を行う。
            selectProject(mapping, form, request, response);
            // エラーメッセージの追加。
            messages.add("", new ActionMessage("I007"));
            // エラーメッセージが保存する
            saveErrors(request, messages);
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "deleteProject", "End");
    }

    /**
     * プロジェクトを修復する。
     * <p>
     * <b>処理概要:</b><br>
     * 選択したプロジェクトを修復する。<br>
     * 対応するバージョンライブラリはSVNフォルダーに移動する。<br>
     * SVN権限ファイルを更新する。<br>
     * @param    mapping    マッピング
     * @param    form    フォーム
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    なし
     * @exception    Exception    異常
     */
    public void recoveryProject(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
                                throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "recoveryProject", "Start");
        // メッセージの宣言。
        ActionMessages messages = new ActionMessages();
        // SVNM0210Beanオブジェクトを取得する。
        SVNM0210Bean bean = (SVNM0210Bean) form;
        // SVNM0210Daoオブジェクトの宣言。
        SVNM0210Dao svnm0210Dao = new SVNM0210Dao();
        // セッションの宣言。
        HttpSession session = request.getSession();
        // プロジェクトの修復を行う。
        int startFlag = svnm0210Dao.updateProject(bean.getProjectNo(), bean.getUpdateData(), 
                (String)session.getAttribute("sysUserId"), Common.getCurrentTime("yyyyMMddHHmmssSSS"), 
                bean.getUpdateSvnFolder(), false); 
        // 成功にプロジェクトを修復した場合。
        if(startFlag == 0) {
            // プロジェクトの検索を行う。
            selectProject(mapping, form, request, response);
            // メッセージの追加。
            messages.add("", new ActionMessage("I006", bean.getDeleteProjectName()));
            // メッセージが保存する。
            saveErrors(request, messages);
        } 
        // プロジェクトの修復に失敗した場合。（異常が発生した場合）
        else if(startFlag == 1) {
            // 画面の遷移。
            forward = "error";
        }
        // 排他が発生した場合。
        else if(startFlag == 2) {
            // プロジェクトの検索を行う。
            selectProject(mapping, form, request, response);
            // エラーメッセージの追加。
            messages.add("", new ActionMessage("I007"));
            // エラーメッセージが保存する。
            saveErrors(request, messages);
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0210Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "recoveryProject", "End");
    }
}