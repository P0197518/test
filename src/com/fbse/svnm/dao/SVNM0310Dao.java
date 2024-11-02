/*******************************************************************
 * システム名          :SVN管理システム
 * サブシステム名       :権限管理
 * プログラム名         :権限検索画面
 * モジュール名         :SVNM0310Dao.java
 * 担当者             :FBSE)宋福明
 * 作成日             :2008.12.18
 * 機能概要           :権限情報の取得と権限情報の削除。
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
 * SVNM0310Daoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>データを検索する。</li>
 * <li>データを削除する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)宋福明
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0310Dao extends BaseDao {

    /** クラス名の宣言と初期化 */
    private static String className = "SVNM0310Dao";

    /**
     * 権限情報を検索する。
     * <p>
     * <b>処理概要:</b><br>
     * 権限情報を検索する。<br>
     * @param    privilegeName    権限名
     * @return    FBSEDataResultSet    権限情報
     * @exception    Exception    異常
     */
    public FBSEDataResultSet selectPrivilege(String privilegeName) throws Exception {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "selectPrivilege", "Start");
        // 権限名引数の宣言
        String myPrivilegeName = privilegeName;
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // パラメータを設定
        ArrayList<String> parameter = new ArrayList<String>();
        // 結果コレクションの宣言
        FBSEDataResultSet result = new FBSEDataResultSet();
        // パラメータに引数のセット
        parameter.add("×");
        parameter.add("△");
        parameter.add("〇");
        parameter.add("×");
        parameter.add("△");
        parameter.add("〇");
        parameter.add(selectSqlReplace(myPrivilegeName));
        try{
            // sql文を取得
            String sql=FBSEDBHandler.getSql("SVNM0310S001",parameter);
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
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "selectPrivilege", "End");
        // 権限情報結果コレクションの結果を戻す
        return result;
    }

    /**
     * 権限情報を削除する。
     * <p>
     * <b>処理概要:</b><br>
     * 権限情報を削除する。<br>
     * @param    updateDate    元更新時間
     * @param    privilegeNo    権限番号
     * @param    systemUserNo    システムユーザー番号
     * @return    int    1    削除成功
     *                   0    削除失敗
     * @exception    Exception    異常
     */
    public int deletePrivilege(String updateDate,
            String privilegeNo,
            String systemUserNo)
    throws Exception {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "deletePrivilege", "Start");
        // 元更新時間の获得
        String updatedate = updateDate;
        // 権限番号の获得
        String myPrivilegeNo = privilegeNo;
        // システムユーザー番号の获得
        String userId = systemUserNo;
        // システム時間の宣言と获得
        String nowTime =  Common.getCurrentTime("yyyyMMddHHmmssSSS");
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil=new FBSEDBHandler();
        // 権限詳細情報のパラメータを設定
        ArrayList<String> parameterPrivlege = new ArrayList<String>();
        // システムユーザーの番号のセット
        parameterPrivlege.add(userId);
        // システム日付をセットする
        parameterPrivlege.add(nowTime);
        // 権限番号のセット
        parameterPrivlege.add(myPrivilegeNo);
        // 元更新時間のセット
        parameterPrivlege.add(updatedate);
        // 結果コレクションの宣言
        int result = 0;
        try{
            // sql文を取得
            String sql = FBSEDBHandler.getSql("SVNM0310D001",parameterPrivlege);
            // sql文を実行,それから権限詳細情報を削除する結果resultを获得する。
            // resultは0:削除失敗,非0:削除成功
            result = dbUtil.executeUpdate(sql);
        }catch(Exception e){
            // ログ出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "deletePrivilege", e.toString());
            // 異常をスローする
            throw e;
        }
        // 権限詳細情報を成功に削除するログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "deletePrivilege", "End");
        // 権限詳細情報を成功に削除するかどうかのフラグを戻る
        return result;
    }

    /**
     * 権限情報を更新するかどうか判断を行う。
     * <p>
     * <b>処理概要:</b><br>
     * 権限情報を更新するかどうか判断を行う。<br>
     * @param    updateDate    元更新時間
     * @param    privilegeNo    権限番号
     * @return    int    1    権限情報を更新しない
     *                   0    権限情報もう更新した
     * @exception    Exception    異常
     */
    public int editPrivilegeCheck(String updateDate, String privilegeNo)
    throws Exception {
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "editPrivilegeCheck", "Start");
        // 元更新時間の获得
        String updatedate = updateDate;
        // 権限番号の获得
        String myPrivilegeNo = privilegeNo;
        // FBSEDBHandlerオブジェクトの宣言
        FBSEDBHandler dbUtil=new FBSEDBHandler();
        // 権限詳細情報のパラメータを設定
        ArrayList<String> parameterPrivlege = new ArrayList<String>();
        // 権限番号のセット
        parameterPrivlege.add(myPrivilegeNo);
        // 元更新時間のセット
        parameterPrivlege.add(updatedate);
        // 結果コレクションの宣言
        FBSEDataResultSet result = new FBSEDataResultSet();
        // 権限情報を更新するかどうかフラグ
        int number = 0;
        try{
            // sql文を取得
            String sql = FBSEDBHandler.getSql("SVNM0310S002",parameterPrivlege);
            // sql文を実行,それから権限詳細情報を更新するかどうか結果を获得する。
            // resultは0:権限情報もう更新した,非0:権限情報を更新しない
            result = dbUtil.executeSelect(sql);
            // 権限詳細情報を更新するかどうか結果を获得する
            if(result.getNext("table1")){
                // 権限情報を更新するかどうかフラグに権限詳細情報を更新するかどうか結果を追加する
                number = Integer.valueOf(result.getObject("NUM").toString());
            }
        }catch(Exception e){
            // ログ出力
            log.printLog("ERROR", className, String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "editPrivilegeCheck", e.toString());
            // 異常をスローする
            throw e;
        }
        // ログ出力
        log.printLog("INFO", className, String
                .valueOf((new Throwable()).getStackTrace()[0]
                        .getLineNumber()), "editPrivilegeCheck", "End");
        // 権限詳細情報を更新するかどうかのフラグを戻る
        return number;
    }
}