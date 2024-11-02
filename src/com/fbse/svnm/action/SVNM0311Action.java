/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :権限新規画面
 * モジュール名        :SVNM0311Action.java
 * 担当者            :FBSE)張志明
 * 作成日            :2008.12.18
 * 機能概要           :権限の新規を行う
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.18 :FBSE)張志明
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
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.bean.SVNM0311Bean;
import com.fbse.svnm.dao.SVNM0311Dao;

/**
 * <HR>
 * SVNM0311アクションの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理。</li>
 * <li>SVNM0311画面の初期表示を行う。</li>
 * <li>権限情報の新規を行う。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0311Action extends BaseAction {

    // クラス名の宣言と初期化
    private String className = "SVNM0311Action";

    /**
     * 権限新規画面の初期化と権限情報の新規を行う。<p>
     * <b>処理概要:</b><br>
     * 権限新規画面の初期化。<br>
     * 権限情報の新規を行う。<br>
     * @param    mapping    マッピング
     * @param    form    SVNM0311Bean
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward    画面の遷移
     * @exception    なし
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");
        // 権限の判断とセッションタイムアウトあるいは権限はヌル、2ではない場合
        if (request.getSession().getAttribute("sysUserId") == null ||
                request.getSession().getAttribute("masterPrivilege") == null ||
                !request.getSession().getAttribute("masterPrivilege").equals("2")) {
            // ログ出力
            log.printLog("INFO", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", "End");
            // 登録画面へ遷移する。
            return mapping.findForward("logoff");
        }
        // ユーザーIDを取得する
        String userId = request.getSession().getAttribute("sysUserId").toString();
        try {
            // SVNM0311Beanオブジェクトの取得
            SVNM0311Bean bean = (SVNM0311Bean) form;
            // 画面IDの取得
            String[] pageId = bean.getSubmitValue().split(",");
            // 画面IDのクリア
            bean.setSubmitValue("");
            // 他の画面から遷移した場合
            if (!pageId[0].equals("SVNM0311")) {
                // SVNM0311Beanオブジェクトを宣言
                bean = new SVNM0311Bean();
                // コンボボックスの初期化
                bean.setPrivilegeBean(new BaseDao().getCommbox("1002"));
                request.setAttribute("SVNM0311Bean", bean);
                // ログ出力
                log.printLog("INFO", className, String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                // 本画面へ遷移する。
                return mapping.getInputForward();
            }
            // 本画面の確定ボタンを押下した場合
            if (pageId[1].equals("ok")) {
                // SVNM0311Daoオブジェクトの宣言
                SVNM0311Dao dao = new SVNM0311Dao();
                // 権限名の存在性のチェック
                if (dao.checkPrivilege(bean.getSvnm0311PrivilegeName())) {
                    // 権限を新規する
                   dao.insertPrivilege(userId, bean);
                   request.setAttribute("sousaMsg", "I003," + bean.getSvnm0311PrivilegeName());
                   // ログ出力
                   log.printLog("INFO", className, String
                           .valueOf((new Throwable()).getStackTrace()[0]
                                   .getLineNumber()), "execute", "End");
                   // 本画面へせんいする
                   return mapping.findForward("SVNM0310");
                }
                else {
                     // エラーメッセージの設定
                    ActionMessages error = new ActionMessages();
                    // エラーメッセージ：「入力した権限名がもう存在しています。」
                    error.add("", new ActionMessage("E007", "権限名"));
                    saveErrors(request, error);
                    // フォーカスの合わせる
                    bean.setHidError(bean.svnmError("SVNM0311Bean", "svnm0311PrivilegeName"));
                    // ログ出力
                    log.printLog("INFO", className, String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "execute", "End");
                    // 本画面へせんいする
                    return mapping.getInputForward();
                }
            }
            // 本画面の戻るボタンを押下した場合
            if (pageId[1].equals("return")) {
                // ログ出力
                log.printLog("INFO", className, String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                // SVNM0310プロジェクト検索画面へ遷移する
                return mapping.findForward("SVNM0310");
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", e.toString());
            // 異常が発生した場合、エラー画面へ遷移する
            return mapping.findForward("error");
        }
        // ログ出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 本画面へ遷移する
        return mapping.getInputForward();
    }
}