/*******************************************************************
 * システム名          :SVN管理システム
 * サブシステム名       :マスタメンテ 
 * プログラム名         :従業員検索画面
 * モジュール名         :SVNM0410Action.java
 * 担当者            :FBSE)馬春晶
 * 作成日            :2008.12.17
 * 機能概要           :従業員の検索、削除、修復
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:   2008.12.17   :FBSE)馬春晶
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.base.Common;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.bean.SVNM0410Bean;
import com.fbse.svnm.bean.SelectBean;
import com.fbse.svnm.dao.SVNM0410Dao;

/**
 * <HR>
 * SVNM0410アクションの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0410画面の初期表示を行う</li>
 * <li>従業員の情報の検索を行う</li>
 * <li>従業員の情報の削除を行う</li>
 * <li>従業員の情報の修復を行う</li>
 * <li>従業員情報の従業員編集画面への遷移を行う</li>
 * <li>従業員情報の従業員新規画面への遷移を行う</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0410Action extends BaseAction {

    // 検索ボタンの押下するフラグ
    String selectFlag = "";
    /**
     * SVNM0410従業員検索画面の初期化と各メソッドの呼び出し。<p>
     * <b>処理概要:</b><br>
     *　画面の初期化と各メソッドの呼び出し
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
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "Start");
        // エラーメッセージオブジェクトを宣言する
        ActionMessages error = new ActionMessages();
        // SVNM0410Beanプロジェクトを取得する
        SVNM0410Bean bean = (SVNM0410Bean)form;
        // 違法登録の場合登録画面へ遷移する
        if(request.getSession().getAttribute("sysUserId") == null ) {
            // ログを出力
            log.printLog("INFO", "SVNM0410Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "execute", "End");
            // 登録画面へ遷移する
            return mapping.findForward("logoff");
        }
        try {
            //初期化の時権限を取得してマスタメンテ権限はヌルではない場合     
            if(request.getSession().getAttribute("masterPrivilege") != null){
                // 権限変数を宣言して権限値を設定する
                String master = request.getSession().getAttribute("masterPrivilege").toString();
                // マスタメンテ権限がない場合
                if (master.equals("0")) {
                    // ログを出力
                    log.printLog("INFO", "SVNM0412Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 登録画面へ遷移する。
                    return mapping.findForward("logoff");
                }
                // マスタメンテ権限は読み取り専用である場合
                if (master.equals("1")) {
                    // リクエストに読みだけ権限を保存する
                    request.setAttribute("rPrivilege", "1");
                }
                // マスタメンテ権限は管理権限がない場合
                if (master.equals("2")) {
                    // リクエストに管理権限を保存する
                    request.setAttribute("rwPrivilege", "2");
                }
            }
            // 権限と状態プルダウンリストを設定する
            setDownList(form);
            // ボタンのフィドンフィールドの値を取得する
            String buttonValue = bean.getButtonValue();
            // 検索ボタンを押下する
            if (buttonValue.equals("select")) {
                // ボタンのフィドンフィールドの値を取得する
                bean.setButtonValue("");
                // 検索フラグを設定する
                selectFlag = "select";
                // データを検索するメソッドを呼び出す
                selectUser(mapping, form, request, response);
            }
            // 検索ボタンを押下しない
            else {
                // 検索フラグを設定する
                selectFlag = "";
            }
            // 新規ボタンを押下する
            if (buttonValue.equals("insert")) {
                // ボタンのフィドンフィールドの値を取得する
                bean.setButtonValue("");
                // requestに頁フラグを保存する
                request.setAttribute("SVNM0410Page", "SVNM0410");
                // ログを出力
                log.printLog("INFO", "SVNM0410Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                             .getLineNumber()), "execute", "End");
                // 新規画面へ遷移する
                return mapping.findForward("SVNM0411");
            }
            // 削除アンカーをクリックした場合
            if (buttonValue.equals("delete")) {
                // ボタンのフィドンフィールドの値を取得する
                bean.setButtonValue("");
                // 削除操作を呼び出す
                boolean deleteState = deleteUser(form,request);
                // 削除することが成功だ
                if(deleteState) {
                    if(request.getSession().getAttribute("sysUserId").equals(bean.getParamValue().split(",")[1])) {
                        return mapping.findForward("logoff");
                    }
                    // 検索フラグを設定する
                    selectFlag = "select";
                    // 新しいデータを検索する
                    selectUser(mapping,form,request,response);
                    // 削除することが成功のメッセージを設定する
                    error.add("reg",
                            new ActionMessage("I005",
                            request.getParameter("paramValue").split(",")[1]));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // ログを出力
                    log.printLog("INFO", "SVNM0410Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 本画面へ遷移する
                    return mapping.getInputForward();
                }
                // 削除に失敗した場合
                else {
                    // 新しいデータを検索する
                    selectUser(mapping, form, request, response);
                    // エラーメッセージを保存する
                    error.add("reg",new ActionMessage("I007"));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // ログを出力
                    log.printLog("INFO", "SVNM0410Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 本画面へ遷移する
                    return mapping.getInputForward();
                }
            }
            // 修復アンカーをクリックする
            if (buttonValue.equals("backUp")) {
                // ボタンのフィドンフィールドの値を取得する
                bean.setButtonValue("");
                // 修復する結果を保存する
                boolean backUpState = backUpUser(form,request);
                // 修復に成功した場合
                if(backUpState) {
                    // 検索フラグを設定する
                    selectFlag = "select";
                    // 新しいデータを検索する
                    selectUser(mapping, form, request, response);
                    // エラーメッセージを保存する
                    error.add("reg",
                            new ActionMessage("I006",
                            request.getParameter("paramValue").split(",")[1]));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // ログを出力
                    log.printLog("INFO", "SVNM0410Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 本画面へ遷移する
                    return mapping.getInputForward();
                }
                // 修復することが失敗だ
                else {
                    // 検索フラグを設定する
                    selectFlag = "select";
                    // 新しいデータを検索する
                    selectUser(mapping, form, request, response);
                    // エラーメッセージを保存する
                    error.add("reg",new ActionMessage("I007"));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // ログを出力
                    log.printLog("INFO", "SVNM0410Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 本画面へ遷移する
                    return mapping.getInputForward();
                }
            }
            // 従業員番号アンカーをクリックする
            if(buttonValue.equals("update")) {
                // ボタンのフィドンフィールドの値を取得する
                bean.setButtonValue("");
                // 更新しているデータは他の人が更新されない
                if (updateCheck(form,request)) {
                    // 従業員番号をrequestに設定する。
                    request.setAttribute("SVNM0412Page", request.getParameter("paramValue").split(",")[1]);
                    // ログを出力
                    log.printLog("INFO", "SVNM0410Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    return mapping.findForward("SVNM0412");
                }
                // 更新しているデータは他の人が更新された
                else {
                    // 検索フラグを設定する
                    selectFlag = "select";
                    // 新しいデータを検索する
                    selectUser(mapping, form, request, response);
                    // エラーメッセージを保存する
                    error.add("reg",new ActionMessage("I007"));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // ログを出力
                    log.printLog("INFO", "SVNM0410Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                    // 本画面へ遷移する
                    return mapping.getInputForward();
                }
            }
            // 前画面へ戻るアンカーをクリックする
            if (buttonValue.equals("back")) {
                // ボタンのフィドンフィールドの値を取得する
                bean.setButtonValue("");
                // ログを出力
                    log.printLog("INFO", "SVNM0410Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                 .getLineNumber()), "execute", "End");
                // SVNM0511マスタメンテ画面へ遷移する
                return mapping.findForward("SVNM0510");
            }
            // 初期化の時
            return selectUser(mapping, form, request, response);
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0410Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "execute", e.toString());
            // エラー画面へ遷移する
            return mapping.findForward("error");
        }
    }

    /**
     * 権限と状態プルダウンリストを設定する。<p>
     * <b>処理概要:</b><br>
     * 権限と状態プルダウンリストを設定する。
     * @param     form    従業員
     * @return    なし
     * @exception    なし
     */
    public void setDownList(ActionForm form){
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "setDownList", "Start");
        try {
            // SVNM0410Beanプロジェクトを取得する
            SVNM0410Bean bean = (SVNM0410Bean)form;
            // 状態ダウンリストのプロジェクトを宣言する
            SelectBean[] selectBean = (new BaseDao()).getCommbox("1001");
            // 権限ダウンリストを宣言する
            SelectBean[] pjBean = (new BaseDao()).getCommbox();
            // 状態ダウンリストをSVNM0410Beanプロジェクトに設定する
            bean.setSelectBean(selectBean);
            // 権限プルダウンリストをSVNM0410Beanプロジェクトに設定する
            bean.setPrivilegeBean(pjBean);
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0410Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "setDownList", e.toString());
        }
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "setDownList", "End");
    }

    /**
     * 従業員を検索する。<p>
     * <b>処理概要:</b><br>
     * 入力した検索条件によって従業員情報をデータベースから検索する。
     * @param    mapping    マッピング
     * @param     form    従業員
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    Exception    異常
     */
    public ActionForward selectUser (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) 
                                     throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectUser", "Start");
        // エラーメッセージオブジェクトを宣言する
        ActionMessages error = new ActionMessages();
        // 削除フラグの存在するかどうかの判断（requestにある場合空白にする）
        if (request.getAttribute("deleted") != null) {
            // リクエストから削除フラグを空白にする
            request.removeAttribute("deleted");
        }
        // SVNM0410Beanを宣言する
        SVNM0410Bean bean = (SVNM0410Bean)form;
        // 初期化の場合
        if (selectFlag.equals("")) {
            // 従業員番号を設定する
            bean.setUserNo("");
            // 従業員名前を設定する
            bean.setUserName("");
            // 登録名を設定する
            bean.setSvnLoginName("");
            // 権限を設定する
            bean.setPrivilege("");
            // 削除フラグを設定する
            bean.setStateValue("0");
        }
        // requestにresult検索結果がある場合
        if (request.getAttribute("result") != null) {
            // requestからresult検索結果を削除する
            request.removeAttribute("result");
        }
        // 削除フラグの内容を設定
        String delFlag = bean.getStateValue();
        // 従業員番号を取得する
        String svnUserNo = bean.getUserNo();
        // 従業員名前を取得する
        String svnUserName = bean.getUserName();
        // 従業員登録名を取得する
        String svnLoginName = bean.getSvnLoginName();
        // 権限を取得する
        String privilege = bean.getPrivilege();
        // SVNM0410Daoオブジェクトを宣言する
        SVNM0410Dao dao = new SVNM0410Dao();
        // 検索する結果をresultに保存する
        FBSEDataResultSet result = dao.selectUser(svnUserNo, svnUserName, svnLoginName, privilege, delFlag);
        // 検索する結果がある場合
        if (result.getRecordCount("table1") != 0) {
            // requestオブジェクトを設定する
            request.setAttribute("result", result);
            // 削除フラグが「削除済」の場合
            if(delFlag.equals("1")) {
                // 削除フラグを設定する
                request.setAttribute("deteted", "1");
            }
        }
        // 初期化のデータの検索すると検索ボタンを押下する時検索操作を呼び出す
        int num = result.getRecordCount("table1");
        // データベースから検索したデータがある
        if (num != 0) {
            // 検索したデータの数値を保存する
            error.add("reg", new ActionMessage("I001", String.valueOf(num)));
        }
        // データベースから検索したデータがない
        else {
            // 検索したデータの数値を保存する
            error.add("reg", new ActionMessage("I002"));
        }
        // (SVNM0411)従業員新規画面から遷移する時表示するメッセージ
        if (request.getAttribute("sousaMsg") != null) {
            // エラーメッセージを空白にする
            error.clear();
            // requestからメッセージ番号と新規従業員番号を取得する
            String message[] = request.getAttribute("sousaMsg").toString().split(",");
            // 表示するメッセージを保存する
            error.add("reg", new ActionMessage(message[0], message[1]));
        }
        // エラーメッセージを保存する
        saveErrors(request, error);
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "selectUser", "End");
        request.setAttribute("SVNM0410Bean", bean);
        // SVNM0410検索画面へ遷移する
        return mapping.getInputForward();
    }

    /**
     * 更新する前に排他処理を行う。<p>
     * <b>処理概要:</b><br>
     * 更新する前に排他処理を行う。<br>
     * @param    form    従業員
     * @param    request    リクエスト
     * @return    boolean    排他の結果
     *                   true    更新されない
     *                   false   更新された
     * @exception    Exception    異常
     */
    public boolean updateCheck (ActionForm form,
                                HttpServletRequest request)
                                throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "updateCheck", "Start");
        // FBSEDBHandlerオブジェクトを宣言する
        FBSEDBHandler util = new FBSEDBHandler();
        // 従業員番号
        String svnNo = "";
        // 更新日
        String svnUpdate = "";
        // paramValueが存在する場合
        if(request.getParameter("paramValue") != null) {
            //従業員番号を設定する
            svnNo = request.getParameter("paramValue").split(",")[1];
            //従業員更新日を設定する
            svnUpdate = request.getParameter("paramValue").split(",")[0];
        }
        // 引数を保存する
        ArrayList uList = new ArrayList();
        // ArrayListに従業員番号を設定する
        uList.add(svnNo);
        // ArrayListに更新日を設定する
        uList.add(svnUpdate);
        // sql文を取得する
        String sqlU = FBSEDBHandler.getSql("SVNM0410S002",uList);
        // sql文を実行する
        FBSEDataResultSet resultU = util.executeSelect(sqlU);
        // 更新しているデータは他の人が更新されない
        if (resultU.getRecordCount("table1") > 0) {
            // ログを出力
            log.printLog("INFO", "SVNM0410Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "updateCheck", "End");
            // 結果を戻す
            return true;
        }
        // 更新しているデータは他の人が更新された
        else {
            // ログを出力
            log.printLog("INFO", "SVNM0410Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "updateCheck", "End");
            // 結果を戻す
            return false;
        }
    }

    /**
     * 従業員を削除する。<p>
     * <b>処理概要:</b><br>
     * 選択された従業員によって従業員の情報をデータベースから削除する。<br>
     * 選択された従業員によって対応する従業員の情報を従業員ファイルから削除する。<br>
     * 選択された従業員によって対応する従業員の情報を権限ファイルから削除する。<br>
     * @param    form    従業員
     * @param    request    リクエスト
     * @return   boolean
     *                 true    削除することが成功だ
     *                 false   削除することが失敗だ
     * @exception    Exception    異常
     */
    public boolean deleteUser (ActionForm form,
                               HttpServletRequest request) 
                               throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "deleteUser", "Start");
        // SVNM0410Daoのプロジェクトを宣言する
        SVNM0410Dao dao = new SVNM0410Dao();
        // 引数を宣言して取得する(ｓｖｎユーザー番号,更新時間,登録名)
        String paramValue = ((SVNM0410Bean)form).getParamValue();
        // 更新時間を取得する
        String updateDate = paramValue.split(",")[0];
        // 従業員番号を取得する
        String svnUserNo = paramValue.split(",")[1];
        // SVNパスワードを取得する
        String svnPass = paramValue.split(",")[2];
        // 更新者を取得する
        String updateUser = request.getSession().getAttribute("sysUserId").toString();
        // 登録名を取得する
        String svnLoginName = paramValue.split(",")[3];
        // 日付を取得する
        String strNowTime = Common.getCurrentTime("yyyyMMddHHmmssSSS");
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "deleteUser", "End");
        // 削除操作を行う、行う結果を返す
        return dao.deleteUser(svnUserNo      // 従業員番号
                              ,updateUser    // 修正者
                              ,strNowTime    // 今の修正時間
                              ,updateDate    // 元の修正時間
                              ,svnLoginName  // 登録名
                              ,svnPass);     // svnパスワード
    }

    /**
     * 従業員を修復する。<p>
     * <b>処理概要:</b><br>
     * 選択された従業員によって従業員の情報をデータベースに修復する。<br>
     * 選択された従業員によって対応する従業員情報を従業員ファイルに修復する。<br>
     * 選択された従業員によって対応する従業員情報を権限ファイルに修復する。<br>
     * @param    form    従業員
     * @param    request    リクエスト
     * @return   boolean
     *                 true    削除することが成功だ
     *                 false   削除することが失敗だ
     * @exception    Exception    異常
     */
    public boolean backUpUser (ActionForm form,
                               HttpServletRequest request) 
                               throws Exception {
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "backUpUser", "Start");
        // SVNM0410Daoのプロジェクトを宣言する
        SVNM0410Dao dao = new SVNM0410Dao();
        // 引数を宣言して取得する(従業員番号と更新時間)
        String paramValue = ((SVNM0410Bean)form).getParamValue();
        // 更新時間を取得する
        String updateDate = paramValue.split(",")[0];
        // 従業員番号を取得する
        String userNo = paramValue.split(",")[1];
        // SVNパスワードを取得する
        String svnLoginPassword = paramValue.split(",")[2];
        // 登録名を取得する
        String svnLoginName = paramValue.split(",")[3];
        // 更新者を取得する
        String updateUser = request.getSession().getAttribute("sysUserId").toString();
        // 日付を取得する
         String strNowTime =  Common.getCurrentTime("yyyyMMddHHmmssSSS");
        // ログを出力
        log.printLog("INFO", "SVNM0410Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "backUpUser", "End");
        // 削除操作を行う、行う結果を返す
        return dao.backUpUser(userNo              // 従業員番号
                              ,updateUser         // 修正者
                              ,strNowTime         // 今の修正時間
                              ,updateDate         // 元の修正時間
                              ,svnLoginPassword   // SVN登録パスワード
                              ,svnLoginName);     // 登録名
    }
}