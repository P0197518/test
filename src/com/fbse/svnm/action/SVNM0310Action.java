/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :権限検索画面
 * モジュール名        :SVNM0310Action.java
 * 担当者            :FBSE)宋福明
 * 作成日            :2008.12.17
 * 機能概要          :権限の検索を行う
 *                   権限の削除を行う
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.17 :FBSE)宋福明
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
import com.fbse.svnm.bean.SVNM0310Bean;
import com.fbse.svnm.dao.SVNM0310Dao;

/**
 * <HR>
 * SVNM0310アクションの処理である。<P>
 * <li>このクラスでは以下の処理を行う。</li>
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0310画面の初期表示を行う。</li>
 * <li>権限の情報の検索を行う。</li>
 * <li>権限の情報の削除を行う。</li>
 * <li>権限の情報が更新されたかどうか判断を行う。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)宋福明
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0310Action extends BaseAction {

    /** クラス名の宣言と初期化 */
    private String className = "SVNM0310Action";

    /**
     * 権限検索画面の初期化と権限情報検索を行う。<p>
     * <b>処理概要:</b><br>
     * 権限検索画面の初期化。<br>
     * 権限情報検索を行う。<br>
     * @param    mapping    マッピング
     * @param    form    SVNM0310Bean
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    なし
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "execute", "Start");
        // SVNM0310Beanをインスタンスする
        SVNM0310Bean svnm0310Bean  = (SVNM0310Bean)form;
        // セッションの声明
        HttpSession session = request.getSession();
        // ActionForwardの宣言
        ActionForward forward = new ActionForward();
        // 非法登録の場合登録画面へ遷移する
        if(session.getAttribute("sysUserId") == null){
            // 登録画面へ遷移する
            forward = mapping.findForward("logoff");
        }
        // 該当システムユーザーの権限は存在ではない場合
        else if(session.getAttribute("masterPrivilege") == null){
            // 登録画面へ遷移する
            forward = mapping.findForward("logoff");
        }
        // 該当システムユーザーの権限は’なし’である場合
        else if(session.getAttribute("masterPrivilege").equals("0")){
            // 登録画面へ遷移する
            forward = mapping.findForward("logoff");
        }
        // 合法登録
        else{
            // システムユーザー操作タイプの宣言と値の得る
            String buttonNo = svnm0310Bean.getButtonNo();
            // 画面初期化または検索ボタンを押下した場合
            if (buttonNo.equals("privilegeSearch")||buttonNo.equals("")) {
                forward = executeSelect(mapping, form, request, response);
            }
            // 削除アンカーをクリックした場合
            if (buttonNo.equals("privilegeDelete")) {
                forward = executeDelete(mapping, form, request, response);
            }
            // 新規ボタンを押下した場合
            if (buttonNo.equals("privilegeInsert")) {
                // 新規画面へ遷移する
                forward = mapping.findForward("SVNM0311");
            }
            // 権限名アンカーをクリックした場合
            if (buttonNo.equals("privilegeEdit")) {
                forward = executeEdit(mapping, form, request, response);
            }
            // 前画面へ戻るアンカーをクリックする
            if(buttonNo.equals("SVNM0510")){
                // マスタメンテ画面へ遷移した場合
                forward = mapping.findForward("SVNM0510");
            }
        }
        // ログを出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "End");
        // ActionForwardを返す
        return forward;
    }

    /**
     * 権限の情報の検索を行う。<p>
     * <b>処理概要:</b><br>
     * 権限の情報の検索を行う。<br>
     * @param    mapping    マッピング
     * @param    form    SVNM0310Bean
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    なし
     */
    public ActionForward executeSelect(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "executeSelect", "Start");
        // SVNM0310Daoの宣言
        SVNM0310Dao svnm0310Dao = new SVNM0310Dao();
        // SVNM0310Beanの宣言
        SVNM0310Bean svnm0310Bean = (SVNM0310Bean)form;
        // 操作種類:buttonNoはsysUserSearchである場合データベースにデータを検索する
        //        buttonNoはsysUserDeleteだ時データベースにデータを削除する
        String buttonNo = svnm0310Bean.getButtonNo();
        // セッションの声明
        HttpSession session = request.getSession();
        // ActionMessagesの宣言
        ActionMessages message = new ActionMessages();
        // 権限検索画面の権限変数の宣言と取得
        String masterPrivilege = session.getAttribute("masterPrivilege").toString();
        //  システムユーザーは読権限である場合
        if(masterPrivilege.equals("1")){
            request.setAttribute("0310Look", "look");
        }
        // システムユーザーは管理権限（検索、新規、編集、削除）である場合
        else if(masterPrivilege.equals("2")){
            request.setAttribute("0310Manage", "manage");
        }
        try {
            // 画面から権限名を宣言する。
            String privilegeName = "";
            // 検索ボタンを押下する場合
            if(buttonNo.equals("privilegeSearch")){
                // 画面から権限名を取得する。
                privilegeName = svnm0310Bean.getPrivilegeName().trim();
            }else{
                svnm0310Bean.setPrivilegeName("");
            }
            // 権限情報結果コレクションの結果を取得する
            FBSEDataResultSet result = svnm0310Dao.selectPrivilege(privilegeName);
            // 権限情報結果コレクションにデータの数を取得する
            int count = result.getRecordCount("table1");
            // 権限情報結果コレクションにデータがない場合
            if(count==0){
                // 権限検索画面の初期化または権限検索画面で検索ボタンを押下する
                if(buttonNo.equals("privilegeSearch")||buttonNo.equals("")){
                    // 画面にメッセージを表示する。
                    message.add("", new ActionMessage("I002"));
                    // 情報を保存する
                    saveErrors(request, message);
                }
            }
            // 権限情報結果コレクションにデータである場合
            else{
                // 新規画面または編集画面から遷移した場合
                if(request.getAttribute("sousaMsg")!=null){
                    String sousaMsg = request.getAttribute("sousaMsg").toString();
                    String messageParam[] = sousaMsg.split(",");
                    // 画面にメッセージを表示する。
                    message.add("", new ActionMessage(messageParam[0],messageParam[1]));
                    // 情報を保存する
                    saveErrors(request, message);
                }
                // 検索画面から遷移した場合
                else if(buttonNo.equals("privilegeSearch")||buttonNo.equals("")){
                    // 画面にメッセージを表示する。
                    message.add("", new ActionMessage("I001",String.valueOf(count)));
                    // 情報を保存する
                    saveErrors(request, message);
                }
                // requestオブジェクトを設定
                request.setAttribute("result", result);
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "executeSelect", e.toString());
            // エラー画面へ戻る
            return mapping.findForward("error");
        }
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "executeSelect", "End");
        // 権限検索画面へ遷移フラグを戻る
        return mapping.getInputForward();
    }

    /**
     * 権限の情報の削除を行う。<p>
     * <b>処理概要:</b><br>
     * 権限の情報の削除を行う。<br>
     * @param    mapping    マッピング
     * @param    form    SVNM0310Bean
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    なし
     */
    public ActionForward executeDelete(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "executeDelete", "Start");
        // セッションの声明
        HttpSession session = request.getSession();
        // ActionForwardの宣言
        ActionForward forward = new ActionForward();
        try {
            // SVNM0310Beanの宣言
            SVNM0310Bean svnm0310Bean = (SVNM0310Bean)form;
            // SVNM0310Daoの宣言
            SVNM0310Dao svnm0310Dao = new SVNM0310Dao();
            // 画面から権限引数の取得
            String param[] = svnm0310Bean.getParamValue().split(",");
            // 画面から権限番号の宣言、まだ引数の取得
            String pirvilegeno = param[0];
            // 画面から権限更新時間の宣言、まだ引数の取得
            String updatedate = param[1];
            // 画面から権限名の宣言、まだ引数の取得
            String privilegeName = param[2];
            // システムユーザーのIDの宣言とシステムユーザーのIDを取る
            String userId = session.getAttribute("sysUserId").toString();
            // 権限情報削除結果を取得する
            int result = svnm0310Dao.deletePrivilege(updatedate, pirvilegeno, userId);
            // 権限情報結果コレクションにデータがない場合
            if(result == 0){
                // ActionMessagesの宣言
                ActionMessages message = new ActionMessages();
                // 画面にメッセージを表示する。
                message.add("", new ActionMessage("I007"));
                // 情報を保存する
                saveErrors(request, message);
            }else{
                // ActionMessagesの宣言
                ActionMessages message = new ActionMessages();
                // 画面にメッセージを表示する。
                message.add("", new ActionMessage("I005",privilegeName));
                // 情報を保存する
                saveErrors(request, message);
            }
            // 権限検索画面へ遷移する
            forward = executeSelect(mapping, form, request, response);
            // ログ出力
            log.printLog("INFO", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "executeDelete", "End");
        } catch (Exception e) { 
            // エラー画面へ遷移する
            forward = mapping.findForward("error");
            // ログ出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "executeDelete", e.toString());
        }
        // 画面の遷移フラグを戻る
        return forward;
    }

    /**
     * 権限の情報を更新するかどうか判断を行う。<p>
     * <b>処理概要:</b>
     * <li>権限の情報を更新するかどうか判断を行う。</li>
     * @param    mapping    マッピング
     * @param    form    SVNM0310Bean
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    なし
     */
    public ActionForward executeEdit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "executeDelete", "Start");
        // ActionForwardの宣言
        ActionForward forward = new ActionForward();
        try {
            // SVNM0310Beanの宣言
            SVNM0310Bean svnm0310Bean = (SVNM0310Bean)form;
            // SVNM0310Daoの宣言
            SVNM0310Dao svnm0310Dao = new SVNM0310Dao();
            // 画面から権限引数の取得
            String param[] = svnm0310Bean.getParamValue().split(",");
            // 画面から権限番号の宣言、まだ引数の取得
            String pirvilegeno = param[0];
            // 画面から権限更新時間の宣言、まだ引数の取得
            String updatedate = param[1];
            // 権限情報が更新されたかどうか判断して、結果を取得する
            int result = svnm0310Dao.editPrivilegeCheck(updatedate, pirvilegeno);
            // 権限情報もうすでに更新されていた場合
            if(result == 0){
                // ActionMessagesの宣言
                ActionMessages message = new ActionMessages();
                // 画面にメッセージを表示する。
                message.add("", new ActionMessage("I007"));
                // 情報を保存する
                saveErrors(request, message);
                // 権限検索画面の遷移フラグに権限情報を選択して保存する
                forward = executeSelect(mapping, form, request, response);
            }
            // 権限情報もを更新しない
            else{
                // requestに頁フラグを保存する
                request.setAttribute("SVNM0310Page", "SVNM0310");
                // requestに権限番号を保存する
                request.setAttribute("privilegeNo", pirvilegeno);
                // 権限編集画面へ遷移する
                forward = mapping.findForward("SVNM0312");
            }
            // ログ出力
            log.printLog("INFO", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "executeDelete", "End");
        } catch (Exception e) { 
            // エラー画面へ遷移する
            forward = mapping.findForward("error");
            // ログ出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "executeDelete", e.toString());
        }
        // 画面の遷移フラグを戻る
        return forward;
    }
}