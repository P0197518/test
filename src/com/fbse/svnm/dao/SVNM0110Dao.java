/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :ログイン
 * プログラム名        :登録画面
 * モジュール名        :SVNM0110Dao.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.17
 * 機能概要          :システムユーザーの登録
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.17  :FBSE)張建君
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.util.ArrayList;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseDao;

/**
 * <HR>
 * SVNM0110Daoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>システムユーザーを検索する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0110Dao extends BaseDao {

    // データベース異常タグ。
    public boolean flag = true;

    // システムユーザ名。
    public String userName = "";

    // プロジェクト管理の権限。
    public String pjPrivilege = "";

    // マスタメンテの権限。
    public String masterPrivilege = "";

    public String privilegeNo = "";

    /**
     * システムユーザーを検索する。<P>
     * <b>処理概要:</b><br>
     * <li>システムユーザーを検索する。</li><br>
     * <li>システムユーザー登録のチェックを行う。</li>
     * @param    userId    システムユーザー番号
     * @param    password    システムユーザーパスワード
     * @return    なし
     * @exception    なし
     */
    public void loginCheck(String userId, String password) {
        // ログを出力する。
        log.printLog("INFO", "SVNM0110Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "loginCheck", "Start");
        // FBSEDBHandlerオブジェクトを宣言。
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        try {
            // 検索条件を格納用の配列を宣言する
            ArrayList list = new ArrayList();
            // ユーザー番号を入れる
            list.add(userId);
            // ユーザーパスワードを設定する。
            list.add(password);
            // sql文を取得する。
            String sql = FBSEDBHandler.getSql("SVNM0110S001", list);
            // sql文を実行する。
            FBSEDataResultSet result = dbUtil.executeSelect(sql);
            // 検索データが存在する場合。（番号とパスワードが正しい。）
            if(result.getNext("table1")) {
                // システムユーザ名を取得する。
                userName = (String)result.getObject("USERNAME");
                try {
                    // プロジェクト管理の権限を取得する。
                    pjPrivilege = (String)result.getObject("PJPRIVILEGE");
                    // マスタメンテの権限を取得する。
                    masterPrivilege = (String)result.getObject("MASTERPRIVILEGE");
                    privilegeNo = result.getObject("PRIVILEGENO").toString();
                } catch(Exception e) {}
            }
        } catch (Exception e) {
            // 異常が発生した場合、flagにfalseを設定する。
            flag = false;
            // ログを出力する。
            log.printLog("ERROR", "SVNM0110Dao", String
                    .valueOf((new Throwable()).getStackTrace()[0]
                            .getLineNumber()), "loginCheck", e.toString());
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0110Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "loginCheck", "End");
    }
}