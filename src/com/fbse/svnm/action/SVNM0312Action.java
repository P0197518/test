/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :権限マスタ
 * モジュール名        :権限編集画面
 * 担当者            :FBSE)宋福明
 * 作成日            :2008.12.18
 * 機能概要           :権限の編集を行う
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.18 :FBSE)宋福明
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
import com.fbse.svnm.bean.SVNM0310Bean;
import com.fbse.svnm.bean.SVNM0312Bean;
import com.fbse.svnm.bean.SelectBean;
import com.fbse.svnm.dao.SVNM0312Dao;

/**
 * <HR>
 * SVNM0312アクションの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0312画面の初期表示を行う</li>
 * <li>権限情報の編集を行う</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)宋福明
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0312Action extends BaseAction {

    // クラス名の宣言と初期化
    private String className = "SVNM0312Action";

    /**
     * 権限編集画面の初期化と権限情報の編集を行う。<p>
     * <b>処理概要:</b><br>
     * 権限編集画面の初期化。<br>
     * 権限情報の編集を行う。<br>
     * @param    mapping    マッピング
     * @param    form    SVNM0312Bean
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    なし
     */
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        // ログを出力
        log.printLog("INFO", className , String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "Start");
        // ActionForwardの宣言
        ActionForward forward = new ActionForward();
        // SVNM0311Beanの宣言
        SVNM0312Bean svnm0312Bean = (SVNM0312Bean)form;
        // エラーメッセージオブジェクトを宣言する
        ActionMessages error = new ActionMessages();
        //セッションに設定した時間を超えるかどうかのチェック
        if(request.getSession().getAttribute("sysUserId")==null) {
            //登録画面へ遷移する。
            forward = mapping.findForward("logoff");
        }
        // 該当システムユーザーはマスタメンテ管理権限がない場合
        else if(request.getSession().getAttribute("masterPrivilege")==null){
            //登録画面へ遷移する。
            forward = mapping.findForward("logoff");
        }
        // 該当システムユーザーのマスタメンテ管理権限は’なし’である場合
        else if(request.getSession().getAttribute("masterPrivilege").equals("0")||
                request.getSession().getAttribute("masterPrivilege").equals("1")){
            //登録画面へ遷移する。
            forward = mapping.findForward("logoff");
        }
        // 合法ログイン
        else{
            //ユーザー番号を取得。
            String userId=request.getSession().getAttribute("sysUserId").toString();
            try{
                // 編集画面の初期化処理
                if (request.getAttribute("SVNM0310Page") != null) {
                    // 頁フラグを削除する
                    request.removeAttribute("SVNM0310Page");
                    // 権限検索画面からリクエストに権限番号がある場合
                    if(request.getAttribute("privilegeNo") != null){
                        // 権限番号の宣言と初期化
                        String privilegeNo = request.getAttribute("privilegeNo").toString();
                        // 権限情報を得る
                        svnm0312Bean = executeLoad(privilegeNo,svnm0312Bean,"upload");
                        // SVNM0312編集画面へ遷移する
                        forward = mapping.getInputForward();
                    }
                }
                // その他画面から遷移した場合
                else {
                    // 登録画面へ遷移する。
                    forward = mapping.findForward("logoff");
                }
                // 戻るボタンを押下する
                if (svnm0312Bean.getSubmitValue().equals("back")) {
                    // 検索画面へ遷移する
                    forward = mapping.findForward("SVNM0310");
                }
                // 確認ボタンを押下する。編集操作を行う
                if (svnm0312Bean.getSubmitValue().equals("edit")){
                    // 編集メッソドを呼び出す
                    int state = updatePrivilege(form,request,userId);
                    // 権限名が重複した場合
                    if (state == 1) {
                        // 権限名が重複したというメッセージを保存する
                        error.add("reg",
                                new ActionMessage("E007",svnm0312Bean.getSvnm0312PrivilegeName()));
                        // 権限名のバックグラウンドを赤くしてフォーカスをあわせる
                        svnm0312Bean.setHidError(
                                svnm0312Bean.svnmError("SVNM0312Bean", "svnm0312PrivilegeName"));
                        // エラーメッセージを保存する
                        saveErrors(request, error);
                        // 権限情報を得る
                        svnm0312Bean = executeLoad(
                                svnm0312Bean.getPrivilegeNo(),svnm0312Bean,"notUpdated");
                        // SVNM0312編集画面へ遷移する
                        forward = mapping.getInputForward();
                    }
                    // 編集に成功した場合
                    if (state == 2) {
                        // 編集することが成功するメッセージを保存する
                        request.setAttribute("sousaMsg","I004"+","
                                +((SVNM0312Bean)form).getSvnm0312PrivilegeName());
                        // SVNM0310検索画面へ遷移する
                        forward = mapping.findForward("SVNM0310");
                    }
                    // 権限情報がすでに更新された場合
                    if (state == 3) {
                        // 権限情報もう更新した。更新したというメッセージを保存する
                        error.add("reg",
                                new ActionMessage("I007",svnm0312Bean.getSvnm0312PrivilegeName()));
                        // エラーメッセージを保存する
                        saveErrors(request, error);
                        // SVNM0312編集画面へ遷移する
                        forward = mapping.getInputForward();
                    }
                }
            }catch(Exception e){
                // ログを出力
                log.printLog("ERROR", className, String
                        .valueOf((new Throwable()).getStackTrace()[0]
                             .getLineNumber()), "execute", e.toString());
                // エラー画面へ遷移する
                forward = mapping.findForward("error");
            }
        }
        // ログを出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "End");
        // 画面へ遷移するフラグを戻る
        return forward;
    }

    /**
     * 権限編集画面の初期化。<p>
     * <b>処理概要:</b><br>
     * 権限編集画面の初期化。<br>
     * @param    privilegeNo    権限番号
     * @param    svnm0312Bean    SVNM0312Bean
     * @param    flag    権限編集画面の初期化かどうかフラグ
     * @return    SVNM0312Bean    SVNM0312Bean
     * @throws    Exception    データベース異常
     */
    public SVNM0312Bean executeLoad(String privilegeNo,
            SVNM0312Bean svnm0312Bean,
            String flag)
    throws Exception {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "executeLoad", "Start");
        // SVNM0312Daoの宣言
        SVNM0312Dao svnm0312Dao = new SVNM0312Dao();
        // 権限編集画面の初期化フラグを宣言する
        String updateFlag = flag;
        // SVNM0312からシステムユーザーは権限名に入力する権限名を一時的に保存する
        String privilegeName = svnm0312Bean.getSvnm0312PrivilegeName();
        try{
            // プロジェクト管理権限リストの宣言と初期化
            SelectBean[] projectPrivilegeBean = svnm0312Dao.getCommbox("1002");
            // マスタメンテ権限リストの宣言と初期化
            SelectBean[] masterPrivilegeBean = svnm0312Dao.getCommbox("1002");
            // 権限情報結果コレクションの結果を获得する
            FBSEDataResultSet result = svnm0312Dao.selectPrivilege(privilegeNo);
            // 権限情報結果コレクションの値を获得する
            result.getNext("table1");
            // 権限名を获得する
            String svnm0312PrivilegeName = result.getObject("PRIVILEGENAME").toString();
            // プロジェクト権限を获得する
            String projectPrivilegeCode = result.getObject("PJPRIVILEGE").toString();
            // システムユーザー権限を获得する
            String masterPrivilegeCode = result.getObject("MASTERPRIVILEGE").toString();
            // コメントの宣言
            String privilegeComment = "";
            // コメントの値を設定する   
            try{
                // コメントを获得する
                privilegeComment = result.getObject("COMMENT").toString();
            }
            // コメントはヌルにする
            catch(Exception e){
                // コメントの初期化
                privilegeComment = "";
            }
            // 更新時間を获得する
            String updateDate = result.getObject("UPDATEDATE").toString();
            // svnm0311Beanに権限番号を入れる
            svnm0312Bean.setPrivilegeNo(privilegeNo);
            // SVNM0312権限編集画面の初期化の場合
            if(updateFlag.equals("upload")){
                // svnm0311Beanに権限名を入れる
                svnm0312Bean.setSvnm0312PrivilegeName(svnm0312PrivilegeName);
            }
            // SVNM0312権限編集画面の初期化ではない場合
            else if(updateFlag.equals("notUpdated")){
                // svnm0311Beanに権限名を入れる
                svnm0312Bean.setSvnm0312PrivilegeName(privilegeName);
            }
            // svnm0311Beanにプロジェクト管理権限コードを代入
            svnm0312Bean.setProjectPrivilegeCode(projectPrivilegeCode);
            // svnm0311Beanにプロジェクト管理権限リスト
            svnm0312Bean.setProjectPrivilegeBean(projectPrivilegeBean);
            // svnm0311Beanにマスタメンテ権限コードを代入
            svnm0312Bean.setMasterPrivilegeCode(masterPrivilegeCode);
            // svnm0311Beanにマスタメンテ権限リストを代入
            svnm0312Bean.setMasterPrivilegeBean(masterPrivilegeBean);
            // svnm0311Beanに権限コメンを代入
            svnm0312Bean.setPrivilegeComment(privilegeComment);
            // svnm0311Beanに権限更新時間を代入
            svnm0312Bean.setUpdateDate(updateDate);
        }catch(Exception e){
            // ログを出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "executeLoad", e.toString());
            // 異常をスローする
            throw e;
        }
        // ログを出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "executeLoad", "End");
        // svnm0311Beanを戻る
        return svnm0312Bean;
    }

    /**
     * 権限を編集する。<p>
     * <b>処理概要:</b><br>
     * 権限をデータベースに編集する。<br>
     * 権限の登録名の正確性のチェック。<br>
     * @param    form    SVNM0312Bean
     * @param    request    リクエスト
     * @param    userId    システムユーザー番号
     * @return    int
     *               1    権限名が重複。
     *               2    更新に成功した。
     *               3    権限情報もう更新した。
     * @exception    Exception    データベース異常
     */
    public int updatePrivilege (ActionForm form,
            HttpServletRequest request,
            String userId) throws Exception {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "updatePrivilege", "Start");
        // SVNM0311Daoプロジェクトを宣言する
        SVNM0312Dao dao = new SVNM0312Dao();
        // SVNM0311Beanプロジェクトを宣言する
        SVNM0312Bean svnm0312Bean = (SVNM0312Bean)form;
        String privilegeNo = svnm0312Bean.getPrivilegeNo();
        // 入力した権限名を取得する
        String privilegeName = svnm0312Bean.getSvnm0312PrivilegeName().trim();
        // 入力した権限モジュール権限を取得する
        String projectPrivilegeCode = svnm0312Bean.getProjectPrivilegeCode();
        // 入力した権限モジュール権限を取得する
        String masterPrivilegeCode = svnm0312Bean.getMasterPrivilegeCode();
        // 入力したコメントを取得する
        String privilegeComment = svnm0312Bean.getPrivilegeComment().trim();
        // 更新者
        String updateUser = userId;
        // 元更新時間
        String updateDate = svnm0312Bean.getUpdateDate();
        // 今更新時間
        String nowUpdateDate = Common.getCurrentTime("yyyyMMddHHmmssSSS");
        // 実行の結果を宣言する
        int state = 0;
        try{
            // 入力したデータを編集する操作を呼び出す
            state = dao.updatePrivilege(privilegeNo, privilegeName,
                    projectPrivilegeCode,masterPrivilegeCode,
                    privilegeComment, updateUser, updateDate, nowUpdateDate);
            if(request.getSession().getAttribute("privilegeNo") != null){
                if(state == 2 && request.getSession().getAttribute("privilegeNo")
                        .equals(svnm0312Bean.getPrivilegeNo())) {
                    // セッションにプロジェクト管理の権限を設定。
                    request.getSession().setAttribute("pjPrivilege", dao.pjPrivilege);
                    // セッションにマスタメンテの権限を設定。
                    request.getSession().setAttribute("masterPrivilege", dao.masterPrivilege);
                    SVNM0310Bean svnm0310Bean = new SVNM0310Bean();
                    svnm0310Bean.setHidError("<script language='javascript'>top.frames['mainFrame']" +
                            ".frames['TABLE_toubu'].location.reload();</script>");
                    request.setAttribute("SVNM0310Bean", svnm0310Bean);
                }
            }
        }catch(Exception e){
            // ログを出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "updatePrivilege", e.toString());
            // 異常をスローする
            throw e;
        }
        // ログを出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "updatePrivilege", "End");
        // 実行の結果
        return state;
    }
}