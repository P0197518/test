/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名     :マスタメンテ（従業員管理）
 * プログラム名       :従業員新規画面
 * モジュール名       :SVNM0411Action.java
 * 担当者             :FBSE)王志龍
 * 作成日             :2008.12.17
 * 機能概要           :従業員情報の新規操作、SVNユーザーファイルに該当する
 *                    SVN登録名とパスワードを新規する
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
import com.fbse.svnm.base.FileOperator;
import com.fbse.svnm.bean.SVNM0411Bean;
import com.fbse.svnm.bean.SelectBean;
import com.fbse.svnm.dao.SVNM0411Dao;

/**
 * <HR>
 * SVNM0411アクションの処理。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0411画面の初期表示を行う</li>
 * <li>システムユーザーの情報の新規を行う</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)王志龍
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0411Action extends BaseAction {

    /** クラス名の宣言と初期化*/
    private String className = "SVNM0411Action";

    /**
     * 従業員新規画面の初期化と従業員の新規を行う。<p>
     *<b>処理概要:</b><br>
     * 画面の初期化とパスワードの変更
     * @param    mapping    マッピング
     * @param    form    form
     * @param    request    リクエスト
     * @param    response    レスポンス
     * @return    ActionForward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // ログの出力
        log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");

        // セッションオブジェクトの宣言と初期化
        HttpSession session = request.getSession();
        // SVNM0411Daoオブジェクトの宣言と初期化
        SVNM0411Dao svnm0411Dao = new SVNM0411Dao();
        // SVNM0411Beanの対象svnm0411Beanの宣言と初期化
        SVNM0411Bean svnm0411Bean = (SVNM0411Bean)form;
        // ActionMessagesオブジェクトの宣言と初期化
        ActionMessages error = new ActionMessages();

        // セッションにシステムユーザー番号がない時
        if (session.getAttribute("sysUserId") == null) {
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "execute", "End");
            // ログイン画面に遷移する
            return mapping.findForward("logoff");
        }
        // 登録者ＩＤを取得する
        String sysUserId = session.getAttribute("sysUserId").toString();

        // 権限の判断
        // マスタメンテ権限がヌルの場合、あるいはマスタメンテ権限が「2」ではない場合
        if (session.getAttribute("masterPrivilege") == null ||
                !session.getAttribute("masterPrivilege").toString().equals("2")) {
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
            // 登録画面へ遷移する
            return mapping.findForward("logoff");
        }

        // 本画面へ遷移した場合、画面の初期化を行う
        if (request.getParameter("sousa") == null) {
            // 画面の入力項目をクリアする
            svnm0411Bean = new SVNM0411Bean();
            request.setAttribute("SVNM0411Bean", svnm0411Bean);
            // 権限コンボボックス値の取得
            try{
                // 権限コンボボックス値の取得
                SelectBean[] privilegeSelect = svnm0411Dao.getCommbox();
                // 権限コンボボックス値を設定する
                svnm0411Bean.setPrivilegeSelect(privilegeSelect);
            // 異常時
            }catch (Exception e){
                // ログの出力
                log.printLog("ERROR", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", e.toString());
                // エラー画面に遷移する
                return mapping.findForward("error");
            }
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "execute", "End");
            // 本画面に遷移する
            return mapping.getInputForward();
        }

        // 戻るボタンを押下した場合、あるいは「前画面へ戻る」リンクをクリックした場合、SVNM0410画面に遷移する
        if (request.getParameter("sousa").toString().equals("modoru")) {
            // ログの出力
            log.printLog("INFO", className, String.valueOf((new Throwable())
                    .getStackTrace()[0].getLineNumber()), "execute", "End");
            // SVNM0410従業員検索画面に遷移する
            return mapping.findForward("SVNM0410");
        }

        // 確定ボタンを押下した場合
        if (request.getParameter("sousa").toString().equals("kakutei")) {
            // 入力した従業員番号が存在するかどうかの判断
            Boolean isSysNoExist =
                svnm0411Dao.isSysUserNoExist(svnm0411Bean.getSysUserNo());
            // データベース異常時
            if (isSysNoExist == null) {
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", "End");
                // エラー画面に遷移する
                return mapping.findForward("error");
            }
            // 入力した従業員番号はデータベースにすでに存在した場合
            if (isSysNoExist) {
                // エラーメッセージを表示する
                error.add("", new ActionMessage("E007", "従業員番号"));
                saveErrors(request, error);
                // 画面で該当する従業員番号テキストボックスを赤く表示する
                svnm0411Bean.setHidError(svnm0411Bean.svnmError("SVNM0411Bean", "sysUserNo"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", "End");
                // 本画面に遷移する
                return mapping.getInputForward();
            }
            // 入力したSVN登録名が存在するかどうかの判断
            Boolean isSvnNameExist =
                svnm0411Dao.isSvnLoginNameExist(svnm0411Bean.getSvnLoginName());
            // データベース異常時
            if (isSvnNameExist == null) {
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", "End");
                // エラー画面に遷移する
                return mapping.findForward("error");
            }
            // 入力したSVN登録名はデータベースにすでに存在した場合
            if (isSvnNameExist) {
                // エラーメッセージを表示する
                error.add("", new ActionMessage("E007", "SVN登録名"));
                saveErrors(request, error);
                // 画面で該当するSVN登録名テキストボックスを赤く表示する
                svnm0411Bean.setHidError(svnm0411Bean.svnmError("SVNM0411Bean", "svnLoginName"));
                // ログの出力
                log.printLog("INFO", className, String.valueOf((new Throwable())
                        .getStackTrace()[0].getLineNumber()), "execute", "End");
                // 本画面に遷移する
                return mapping.getInputForward();
            }
            // 入力した内容は全部正しい場合、システムユーザーを新規する
            if(isSysNoExist == false && isSvnNameExist == false){
                // パラメータを格納する配列を宣言する
                ArrayList para = new ArrayList();
                // 新規の従業員番号を配列にいれる
                para.add(Common.trim(svnm0411Bean.getSysUserNo()));
                // 従業員名前
                para.add(svnm0411Dao.updateSqlReplace(Common.trim(svnm0411Bean.getSysUserName())));
                // システムパスワード
                para.add(Common.convertToMD5(Common.trim(svnm0411Bean.getSysLoginPsw())));
                // SVN登録名
                para.add(Common.trim(svnm0411Bean.getSvnLoginName()));
                // SVNパスワード
                try{
                    para.add(FileOperator.getFilePassword(Common.trim(svnm0411Bean.getSvnLoginName())
                            , Common.trim(svnm0411Bean.getSvnPassword())));
                // SVNパスワードを暗号化するとき、異常が発生した
                }catch(Exception e){
                    // ログの出力
                    log.printLog("ERROR", className, String.valueOf((new Throwable())
                            .getStackTrace()[0].getLineNumber()), "execute", e.toString());
                    // エラー画面に遷移する
                    return mapping.findForward("error");
                }
                // メール
                para.add(Common.trim(svnm0411Bean.getMail()));
                // 権限
                para.add(svnm0411Bean.getPrivilege());
                // コメント
                para.add(svnm0411Dao.updateSqlReplace(Common.trim(svnm0411Bean.getComment())));
                // 作成者
                para.add(sysUserId);
                // システム日付
                String nowTime = Common.getCurrentTime("yyyyMMddHHmmssSSS");
                // 作成時間 
                para.add(nowTime);
                // 更新者
                para.add(sysUserId);
                // 更新時間 
                para.add(nowTime);
                // 新規操作を実行する
                int insertFlg = svnm0411Dao.insertSysUser(para);
                // システム異常が発生した場合
                if (insertFlg == -1) {
                    // ログの出力
                    log.printLog("INFO", className, String.valueOf((new Throwable())
                            .getStackTrace()[0].getLineNumber()), "execute", "End");
                    // エラー画面に遷移する
                    return mapping.findForward("error");
                }
                // 新規に成功した場合
                else if (insertFlg == 1) {
                    // メッセージを取得する
                    String message = "I003,"+ svnm0411Bean.getSysUserNo();
                    // 操作成功のメッセージをパラメータとして次画面に伝送する
                    request.setAttribute("sousaMsg", message);
                    // ログの出力
                    log.printLog("INFO", className, String.valueOf((new Throwable())
                            .getStackTrace()[0].getLineNumber()), "execute", "End");
                    // SVNM0410従業員検索画面に遷移する
                    return mapping.findForward("SVNM0410");
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