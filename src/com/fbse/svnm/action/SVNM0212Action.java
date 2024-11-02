/*******************************************************************
 * システム名        :SVN管理システム
 * サブシステム名     :プロジェクト管理
 * プログラム名       :プロジェクト編集画面
 * モジュール名       :SVNM0212Action.java
 * 担当者           :FBSE)張志明
 * 作成日           :2008.12.17
 * 機能概要         :プロジェクト情報の編集操作を行う。
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
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseAction;
import com.fbse.svnm.bean.SVNM0212Bean;
import com.fbse.svnm.dao.SVNM0212Dao;

/**
 * <HR>
 * SVNM0212アクションの処理である。
 * <P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>画面の処理内容。</li>
 * <li>SVNM0212画面の初期表示を行う</li>
 * <li>編集するプロジェクト情報の処理</li>
 * </ul>
 * 
 * @version V1.0
 * @author  FBSE)張志明
 * @see     com.fbse.svnm.base.BaseAction
 */
public class SVNM0212Action extends BaseAction {

    /**
     * プロジェクト編集画面の初期化と編集するプロジェクト情報の処理。
     * <p>
     * <b>処理概要:</b><br>
     * <ul>
     * <li>プロジェクト編集画面の初期化を行う</li>
     * <li>プロジェクトの編集操作を行う</li>
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
        log.printLog("INFO", "SVNM0212Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "Start");
        // SVNM0212Daoオブジェクトの宣言
        SVNM0212Dao dao = new SVNM0212Dao();
        // 権限の判断とセッションに設定した時間を超えるかどうかのチェック
        if (request.getSession().getAttribute("sysUserId") == null ||
                request.getSession().getAttribute("pjPrivilege") == null ||
                !request.getSession().getAttribute("pjPrivilege").equals("2")) {
            // ログ出力
            log.printLog("INFO", "SVNM0212Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", "End");
            // 登録画面へ遷移する。
            return mapping.findForward("logoff");
        }
        try {
            // SVNM0212Beanオブジェクトの取得
            SVNM0212Bean bean = (SVNM0212Bean) form;
            // 画面IDの取得
            String[] pageId = bean.getPageId().split(",");
            // 画面IDのクリア
            bean.setPageId("");
            // 他の画面から遷移した場合
            if (!pageId[0].equals("SVNM0212")) {
                // プロジェクトＩＤは存在しない場合
                if (request.getAttribute("projectNo") == null) {
                    // プロジェクト確認画面から遷移した場合
                    if (request.getAttribute("lastPage") != null) {
                        // ログ出力
                        log.printLog("INFO", "SVNM0212Action", String
                                .valueOf((new Throwable()).getStackTrace()[0]
                                        .getLineNumber()), "execute",
                                "End");
                        // 本画面へ遷移する
                        return mapping.getInputForward();
                    }
                    // ログ出力
                    log.printLog("INFO", "SVNM0212Action", String
                            .valueOf((new Throwable()).getStackTrace()[0]
                                    .getLineNumber()), "execute", "End");
                    // 登録画面へ遷移する。
                    return mapping.findForward("logoff");
                }
                // プロジェクトＩＤは存在している場合
                else {
                    // プロジェクトＩＤを取得
                    bean.setProjectNo(request.getAttribute("projectNo").toString());
                }
                // プロジェクト情報を検索
                FBSEDataResultSet result = dao.projectSelect(bean.getProjectNo());
                // プロジェクト情報を取得
                if (result.getNext("table1")) {
                    // フォルダー名を設定
                    bean.setSvnFolderName(result.getObject("SVNFOLDERNAME").toString());
                    // プロジェクト名を設定
                    bean.setProjectName(result.getObject("PROJECTNAME").toString());
                    try {
                        // ＰＪ責任者を設定
                        bean.setPjMaster(result.getObject("PJMASTER").toString());
                    } catch (Exception e) {
                        // ＰＪ責任者を設定
                        bean.setPjMaster("");
                    }
                    try {
                        // ＰＪ責任者番号を設定
                        bean.setPjMasterNo(result.getObject("PJMASTERNO").toString());
                    } catch (Exception e) {
                        // ＰＪ責任者番号を設定
                        bean.setPjMasterNo("");
                    }
                    try {
                        // ＰＪリーダを設定
                        bean.setPjLeader(result.getObject("PJLEADER").toString());
                    } catch (Exception e) {
                        // ＰＪリーダを設定
                        bean.setPjLeader("");
                    }
                    try {
                        // ＰＪリーダ番号を設定
                        bean.setPjLeaderNo(result.getObject("PJLEADERNO").toString());
                    } catch (Exception e) {
                        // ＰＪリーダ番号を設定
                        bean.setPjLeaderNo("");
                    }
                    // プロジェクト開始日を設定
                    bean.setPjStartDate(result.getObject("PJSTARTDATE").toString());
                    // プロジェクト完了日を設定
                    bean.setPjEndDate(result.getObject("PJENDDATE").toString());
                    // プロジェクト転出日を設定
                    bean.setPjRemoveDate(result.getObject("PJREMOVEDATE").toString());
                    try {
                        // バックアップ時間間隔を設定
                        bean.setPjBackupTime(result.getObject("PJBACKUPTIME").toString());
                    } catch (Exception e) {
                        // バックアップ時間間隔を設定
                        bean.setPjBackupTime("");
                    }
                    try {
                        // コメントを設定
                        bean.setComment(result.getObject("COMMENT").toString());
                    } catch (Exception e) {
                        // コメントを設定
                        bean.setComment("");
                    }
                    // 更新日時を設定
                    bean.setUpdateDate(result.getObject("UPDATEDATE").toString());
                    // メンバーとメンバー番号を宣言
                    String member = "";
                    String memberNo = "";
                    // メンバーとメンバー番号を取得
                    while (result.getNext("table2")) {
                        //メンバーとメンバーの間に「、」或いは「,」を利用して分割する。
                        if (!FBSEChecker.isEmpty(memberNo)) {
                            member += "、";
                            memberNo += ",";
                        }
                        member += result.getObject("USERNAME").toString();
                        memberNo += result.getObject("USERNO").toString();
                    }
                    // メンバーとメンバー番号を設定
                    bean.setMember(member);
                    bean.setMemberNo(memberNo);
                }
                // ログ出力
                log.printLog("INFO", "SVNM0212Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                // 本画面へ遷移する。
                return mapping.getInputForward();
            }
            // 本画面の確定ボタンを押下した場合。
            if (pageId[1].equals("ok")) {
                // SVNフォルダー名の存在性のチェック
                if (dao.folderExist(bean.getSvnFolderName(), bean.getProjectNo())) {
                    // エラーメッセージの設定
                    ActionMessages error = new ActionMessages();
                    error.add("", new ActionMessage("E007", "SVNフォルダ名"));
                    saveErrors(request, error);
                    // フォーカスの合わせる
                    bean.setHidError(bean.svnmError("SVNM0212Bean", "svnFolderName"));
                    // 本画面へせんいする
                    return mapping.getInputForward();
                }
                // 画面IDとプロジェクト情報を保存して、SVNM0213プロジェクト確認画面へ遷移する。
                request.setAttribute("lastPage", "SVNM0212");
                // ログ出力
                log.printLog("INFO", "SVNM0212Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                return mapping.findForward("SVNM0213");
            }
            // 本画面の戻るボタンをおうかする
            if (pageId[1].equals("return")) {
                // ログ出力
                log.printLog("INFO", "SVNM0212Action", String
                        .valueOf((new Throwable()).getStackTrace()[0]
                                .getLineNumber()), "execute", "End");
                // SVNM0210プロジェクト検索画面へ遷移する
                return mapping.findForward("SVNM0210");
            }
        } catch (Exception e) {
            // ログ出力
            log.printLog("ERROR", "SVNM0212Action", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "execute", e.toString());
            // 異常が発生場合、エラー画面へ遷移する
            return mapping.findForward("error");
        }
        // ログ出力
        log.printLog("INFO", "SVNM0212Action", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "execute", "End");
        // 本画面へ遷移する
        return mapping.getInputForward();
    }
}