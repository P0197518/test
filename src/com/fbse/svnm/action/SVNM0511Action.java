/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ 
 * プログラム名        :SVNパスワード定期維持画面
 * モジュール名        :SVNM0511Action.java
 * 担当者            :FBSE)馬春晶
 * 作成日            :2008.12.18
 * 機能概要          :全部SVNユーザーのSVNパスワードを変更する
 *******************************************************************
 * 変更履歴    版数:    変更日        :変更者
 *           V1.0:   2008.12.18    :FBSE)馬春晶
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
import com.fbse.svnm.base.Common;
import com.fbse.svnm.bean.SVNM0511Bean;
import com.fbse.svnm.dao.SVNM0511Dao;

/**
 * <HR>
 * SVNM0511アクションの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0511画面の初期表示を行う</li>
 * <li>全部SVNユーザーのSVNパスワードを変更する</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)馬春晶
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0511Action extends BaseAction {
     /**
     * 全部SVNユーザーのSVNパスワードを変更する。<p>
     * <b>処理概要:</b><br>
     *　全部SVNユーザーのSVNパスワードを変更する。
     * @param    mapping    マッピング
     * @param     form    パスワード
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
        log.printLog("INFO", "SVNM0511Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "Start");
        // 違法登録の場合
        if (request.getSession().getAttribute("sysUserId") == null) {
             // ログを出力
            log.printLog("INFO", "SVNM0511Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "execute", "End");
            //登録画面へ遷移する。
            return mapping.findForward("logoff");
        }
        // マスタメンテ権限の判断する
        if(request.getSession().getAttribute("masterPrivilege") != null){
            // 権限変数を宣言して権限値を設定する
            String master = request.getSession().getAttribute("masterPrivilege").toString();
            // マスタメンテ権限は管理権限がない場合
            if (!master.equals("2")) {
                // ログを出力
                log.printLog("INFO", "SVNM0511Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                             .getLineNumber()), "execute", "End");
                // 登録画面へ遷移する。
                return mapping.findForward("logoff");
            }
        }
        // 遷移先を格納用の変数を宣言する
        String forword = "";
        // エラーメッセージオブジェクトを宣言する
        ActionMessages error = new ActionMessages();
        try {
            // SVNM0511Daoプロジェクトを宣言する
            SVNM0511Dao dao = new SVNM0511Dao();
            // SVNM0511Beanプロジェクトを宣言する
            SVNM0511Bean bean = (SVNM0511Bean)form;
            // 確認ボタンを押下した場合
            if (bean.getSubButtonValue().equals("submit")) {
                // 新SVNパスワードを取得する
                String newSvnPassword = bean.getNewSvnPassword();
                // 更新者を取得する
                String updateUser = request.getSession().getAttribute("sysUserId").toString();
                //更新時間を取得する
                String updateDate = Common.getCurrentTime("yyyyMMddHHmmssSSS");
                // SVNユーザーのパスワードを更新する
                boolean state = dao.updateSvnPassword(newSvnPassword, updateUser, updateDate);
                // SVNユーザーのパスワードの更新に成功した場合
                if (state) {
                    // SVNユーザーのパスワードのメッセージを保存する
                    error.add("",new ActionMessage("I004","全ての従業員SVNパスワード"));
                    // エラーメッセージを保存する
                    saveErrors(request, error);
                    // SVNM0511Beanを対象化する
                    SVNM0511Bean newBean = new SVNM0511Bean();
                    // リクエストに新しいBeanを設定する
                    request.setAttribute("SVNM0511Bean", newBean);
                    // 遷移先を設定する
                    forword = "SVNM0511jsp";
                }
                // SVNユーザーのパスワードの更新に成功した場合
                else {
                    throw new Exception();
                }
            }
            // 戻るボタンを押下した場合
            else if(bean.getSubButtonValue().equals("back")) {
                // SVNM0510画面を設定する
                forword = "SVNM0510";
            }
            // 画面初期化の場合
            else {
                // 遷移する画面を設定する
                forword = "SVNM0511jsp";
            }
        }catch (Exception e) {
            // ログを出力
            log.printLog("ERROR", "SVNM0511Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                         .getLineNumber()), "execute", e.toString());
            // エラー画面へ遷移する
            return mapping.findForward("error");
        }
        // ログを出力
        log.printLog("INFO", "SVNM0511Action", String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "execute", "End");
        // 画面へ遷移する
        return mapping.findForward(forword);
    }
}