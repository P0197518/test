/*******************************************************************
 * システム名          :SVN管理システム
 * サブシステム名       :マスタメンテ
 * プログラム名         :権限マスタ
 * モジュール名         :権限編集画面
 * 担当者             :FBSE)宋福明
 * 作成日             :2008.12.18
 * 機能概要           :権限情報を編集する
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *           V1.0:  2008.12.18 :FBSE)宋福明
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.util.ArrayList;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.base.Common;

/**
 * <HR>
 * SVNM0312Daoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>データを編集する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)宋福明
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0312Dao extends BaseDao {

    public String pjPrivilege = "";

    public String masterPrivilege = "";

    /** クラス名の宣言と初期化 */
    private static String className = "SVNM0312Dao";

    /**
     * 権限情報を検索する。
     * <p>
     * <b>処理概要:</b><br>
     * 権限情報を検索する。<br>
     * @param    privilegeNo    権限番号
     * @return    FBSEDataResultSet    権限情報。
     * @exception    Exception    異常
     */
    public FBSEDataResultSet selectPrivilege(String privilegeNo)
    throws Exception {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "selectPrivilege", "Start");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil=new FBSEDBHandler();
        // パラメータを設定
        ArrayList<String> parameter = new ArrayList<String>();
        // 結果コレクションの宣言
        FBSEDataResultSet result = new FBSEDataResultSet();
        // パラメータに引数のセット
        parameter.add(privilegeNo);
        try{
            // sql文を取得
            String sql=FBSEDBHandler.getSql("SVNM0312S001",parameter);
            // sql文を実行,それから権限情報結果コレクションを获得する
            result=dbUtil.executeSelect(sql);
        }catch(Exception e){
            // ログ出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "selectPrivilege", e.toString());
            // 異常をスローする
            throw e;
        }
        // 権限情報結果コレクションの結果を戻す
        return result;
    }

    /**
     * 権限情報の更新を行う。<p>
     * <b>処理概要:</b><br>
     * 権限情報の更新を行う。
     * @param    privilegeNo    権限番号
     * @param    privilegeName    権限名前
     * @param    projectPrivilegeCode    プロジェクト管理権限
     * @param    masterPrivilegeCode    マスタメンテ権限
     * @param    privilegeComment    コメント
     * @param    updateUserId    更新者
     * @param    updateDate    元更新時間
     * @param    nowUpdateDate    今更新時間
     * @return     int
     *               1    権限名が重複。
     *               2    更新することが成功する。
     *               3    権限情報もう更新した。
     * @exception    Exception    異常
     */
    public int updatePrivilege(String privilegeNo,
            String privilegeName,
            String projectPrivilegeCode,
            String masterPrivilegeCode,
            String privilegeComment,
            String updateUserId,
            String updateDate,
            String nowUpdateDate)
    throws Exception {
        // ログを出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "updatePrivilege", "Start");
        // 入力する権限名の存在性の判断するFlag
        int checkPrivilegeFlag = 1;
        // 更新操作成功かどうかFlag
        int updateSuccessFlag = 1;
        try {
            // 存在かどうか判断する
            checkPrivilegeFlag = checkPrivilege(privilegeName,privilegeNo);
            // データベースに存在しない場合
            if (checkPrivilegeFlag == 0) {
                // パラメータをセットする
                ArrayList<String> para = new ArrayList<String>();
                // パラメータに権限名前をセット
                para.add(updateSqlReplace(Common.trim(privilegeName)));
                // パラメータにプロジェクト管理権限コードをセット
                para.add(updateSqlReplace(projectPrivilegeCode));
                // パラメータにマスタメンテ権限コードをセット
                para.add(updateSqlReplace(masterPrivilegeCode));
                // パラメータにコメントをセット
                para.add(updateSqlReplace(Common.trim(privilegeComment)));
                // パラメータに更新者をセット
                para.add(updateUserId);
                // パラメータに新規者をセット
                para.add(nowUpdateDate);
                // パラメータに今更新時間をセット
                para.add(privilegeNo);
                // パラメータに元更新時間をセット
                para.add(updateDate);
                // sql文を宣言すると取得する
                String sqlUpdate = FBSEDBHandler.getSql("SVNM0312U001",para);
                // FBSEDBHandlerオブジェクトを宣言する
                FBSEDBHandler dbUtil = new FBSEDBHandler();
                // sql文を実行,それから権限情報結果コレクションを获得する
                updateSuccessFlag = dbUtil.executeUpdate(sqlUpdate);
                // 成功に新規した場合
                if(updateSuccessFlag>0){
                    // データベースにデータを成功に新規するフラグを戻す
                    updateSuccessFlag = 2;
                }
                // データの新規に失敗した場合
                else{
                    updateSuccessFlag = 3;
                }
            }
            // データベースに既に存在している場合
            else {
                updateSuccessFlag = 1;
            }
            if (updateSuccessFlag == 2 && !updateUserId.equals("F0000")) {
                // 検索条件を設定する。
                ArrayList list = new ArrayList();
                // ユーザー番号を設定する。
                list.add(updateUserId);
                // sql文を取得する。
                String sql = FBSEDBHandler.getSql("SVNM0312S003", list);
                FBSEDBHandler dbUtil = new FBSEDBHandler();
                // sql文を実行する。
                FBSEDataResultSet result = dbUtil.executeSelect(sql);
                // 検索データが存在する場合。（番号とパスワードが正しい。）
                if(result.getNext("table1")) {
                    // プロジェクト管理の権限を取得する。
                    pjPrivilege = (String)result.getObject("PJPRIVILEGE");
                    // マスタメンテの権限を取得する。
                    masterPrivilege = (String)result.getObject("MASTERPRIVILEGE");
                }
            }
        }catch (Exception e) {
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
        // 実行する結果を返す
        return updateSuccessFlag;
    }

    /**
     * 権限名重複のチェックを行う。<p>
     * <b>処理概要:</b><br>
     * 権限名重複性のチェックを行う。
     * @param    privilegeName    権限名
     * @param    privilegeNo    権限番号
     * @return    int
     *               0 権限名がデータベースにない。
     *               1 権限名がデータベースにある。
     * @exception    Exception    異常
     */
    public int checkPrivilege(String privilegeName,
            String privilegeNo)
    throws Exception {
        // ログを出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "checkPrivilege", "Start");
        try {
            // 引数を設定する
            ArrayList<String> list = new ArrayList<String>();
            // 権限名を設定する
            list.add(selectSqlReplace(privilegeName));
            // 権限番号を設定する
            list.add(privilegeNo);
            // sql文を取得する
            String sqlNo = FBSEDBHandler.getSql("SVNM0312S002",list);
            // FBSEDBHandlerオブジェクトを宣言する
            FBSEDBHandler dbUtil = new FBSEDBHandler();
            // sql文を実行する
            FBSEDataResultSet result = dbUtil.executeSelect(sqlNo);
            // 結果コレクションを获得する
            result.getNext("table1");
            // 権限名が重複だ
            if(result.getObject("NUM").toString().equals("1")){
                // ログを出力
                log.printLog("INFO", className, String
                        .valueOf((new Throwable()).getStackTrace()[0]
                             .getLineNumber()), "checkPrivilege", "End");
                // 権限名がデータベースにある場合1を返す
                return 1;
            }
        }catch(Exception e) {
            // ログを出力
            log.printLog("ERROR", className, String
                     .valueOf((new Throwable()).getStackTrace()[0]
                          .getLineNumber()), "checkPrivilege", "End");
            // 異常をスローする
            throw e;
        }
        // ログを出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                     .getLineNumber()), "checkPrivilege", "End");
        // 権限名がデータベースにない場合0を返す
        return 0;
    }
}