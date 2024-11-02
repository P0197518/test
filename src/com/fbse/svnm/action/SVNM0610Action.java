/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名     :パスワード変更
 * プログラム名       :システムユーザパスワード編集
 * モジュール名       :SVNM0610Action.java
 * 担当者             :FBSE)王志龍
 * 作成日             :2008.12.17
 * 機能概要           :パスワードの変更
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)王志龍
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.action;

import java.util.ArrayList;
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
import com.fbse.svnm.bean.SVNM0610Bean;
import com.fbse.svnm.dao.SVNM0610Dao;

/**
 * <HR>
 * SVNM0610アクションの処理。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0610画面の初期表示を行う</li>
 * <li>パスワードの変更を行う</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)王志龍
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0610Action extends BaseAction {

    /** クラス名の宣言と初期化 */
    private static String className = "SVNM0610Action";

    /**
     * SVNM0610パスワード変更画面の初期化とパスワードの変更を行う。
     * <p>
     * <b>処理概要:</b><br>
     * 画面の初期化とパスワードの変更。<br>
     *
     * @param mapping    マッピング
     * @param form    form
     * @param request    リクエスト
     * @param response    レスポンス
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");

        // セッションオブジェクトの宣言と初期化
        HttpSession session = request.getSession();
        // SVNM0610Daoオブジェクトの宣言と初期化
        SVNM0610Dao svnm0610Dao = new SVNM0610Dao();
        // SVNM0610Beanの対象svnm0610Beanの宣言と初期化
        SVNM0610Bean svnm0610Bean = (SVNM0610Bean)form;
        // ActionMessagesオブジェクトの宣言と初期化
        ActionMessages error = new ActionMessages();
        // セッションタイムアウトの場合
        if (session.getAttribute("sysUserId") == null) {
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "execute", "End");
            // ログイン画面に遷移する
            return mapping.findForward("logoff");
        }
        // セッションから従業員番号を取得する
        String sysUserId = session.getAttribute("sysUserId").toString();
        // 本画面へ遷移した場合、画面の初期化をする
        if (request.getParameter("sousa") == null) {
            // 画面にシステムユーザー番号を設定する
            svnm0610Bean.setSysUserNo(sysUserId);
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "execute", "End");
            // 本画面に遷移する
            return mapping.getInputForward();
        }
        // 確定ボタンを押下した場合
        if (request.getParameter("sousa").toString().equals("kakutei")) {
            // 元のパスワードの値を取得する
            String sysUserOldPsw = svnm0610Dao.getOldPassword(sysUserId);
            // データベース異常あるいは該当システムユーザーが削除された場合
            if (sysUserOldPsw == null) {
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", "End");
                // エラー画面に遷移する
                return mapping.findForward("error");
            }
            // 入力した現行パスワードとデータベースに保存したパスワードが一致しない場合
            if (!sysUserOldPsw.equals(
                    Common.convertToMD5(svnm0610Bean.getOldPassword().toString()))) {
                // エラーメッセージを表示する
                error.add("", new ActionMessage("E014", "現行パスワード"));
                saveErrors(request, error);
                // 画面で該当現行パスワードを赤くする
                svnm0610Bean.setHidError(svnm0610Bean.svnmError("SVNM0610Bean", "oldPassword"));
                // 画面でテキストボックスの値をクリアする
                svnm0610Bean.setOldPassword(null);
                svnm0610Bean.setNewPassword(null);
                svnm0610Bean.setKakuninnPassword(null);
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", "End");
                // 本画面に遷移する
                return mapping.getInputForward();
            }
            // 入力した内容は全て正しい場合、パスワードを変更する、パスワードを変更する
            else {
                // パラメータを設定
                ArrayList para = new ArrayList();
                // 新パスワードを入れる
                para.add(Common.convertToMD5(svnm0610Bean.getNewPassword().toString()));
                // 更新者
                para.add(sysUserId);
                // システム日付
                String nowTime = Common.getCurrentTime("yyyyMMddHHmmssSSS");
                para.add(nowTime);
                // 登録者
                para.add(sysUserId);
                // 更新操作を実行する
                int updateFlg = svnm0610Dao.updateSysPassword(para);
                // データベース異常時
                if (updateFlg == -1) {
                    // ログの出力
                    log.printLog("INFO", className, String.valueOf((new Throwable())
                            .getStackTrace()[0].getLineNumber()), "execute", "End");
                    // エラー画面に遷移する
                    return mapping.findForward("error");
                }
                // 更新に成功した場合
                else if (updateFlg == 1) {
                    // メッセージを画面に表示する
                    error.add("", new ActionMessage("I004","パスワード"));
                    saveErrors(request, error);
                    // テキストボックスの色のクリア
                    svnm0610Bean.setHidError("");
                    // テキストボックスの値のクリア
                    svnm0610Bean.setOldPassword(null);
                    // テキストボックスの値のクリア
                    svnm0610Bean.setNewPassword(null);
                    // テキストボックスの値のクリア
                    svnm0610Bean.setKakuninnPassword(null);
                    // ログの出力
                    log.printLog("INFO", className, String.valueOf((new Throwable())
                            .getStackTrace()[0].getLineNumber()), "execute", "End");
                    // 本画面に遷移する
                    return mapping.getInputForward();
                }
            }
        }
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 本画面に遷移する
        return mapping.getInputForward();
    }
}