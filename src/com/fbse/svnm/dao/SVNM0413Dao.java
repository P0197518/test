/*******************************************************************
 * システム名         :SVN管理システム
 * サブシステム名      :マスタメンテ
 * プログラム名        :従業員検索（ポップアップ）画面
 * モジュール名        :SVNM0413Dao.java
 * 担当者            :FBSE)張建君
 * 作成日            :2008.12.18
 * 機能概要          :従業員情報の検索
 *******************************************************************
 * 変更履歴    版数:    変更日    :変更者
 *             V1.0:  2008.12.18  :FBSE)張建君
 * 変更内容
 *    新規作成
 *
 *******************************************************************/
package com.fbse.svnm.dao;

import java.util.ArrayList;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.svnm.base.BaseDao;
import com.fbse.svnm.bean.SVNM0413Bean;
import com.fbse.svnm.bean.SelectBean;

/**
 * <HR>
 * SVNM0413Daoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>従業員情報を検索する。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張建君
 * @see     com.fbse.svnm.base.BaseDao
 */
public class SVNM0413Dao extends BaseDao {

    /**
     * 従業員情報を検索する。<P>
     * <b>処理概要:</b><br>
     * 検索条件により従業員情報を検索する。<br>
     * @param    bean    検索条件
     * @return    SelectBean[]    従業員情報
     * @exception    Exception    異常
     */
    public SelectBean[] selectSVNUser(SVNM0413Bean bean) throws Exception {
        // ログを出力する。
        log.printLog("INFO", "SVNM0413Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "selectSVNUser", "Start");
        // 検索条件を設定する。 
        ArrayList list = new ArrayList();
        // sql文の宣言。
        StringBuffer sqlBuffer = new StringBuffer("");
        // 従業員番号を入力した場合。
        if(!bean.getSvnUserId().equals("")) {
            // 従業員番号の検索条件を追加する。
            sqlBuffer.append(" AND SM_USERMASTER.USERNO LIKE '%" + selectSqlReplace(bean.getSvnUserId()) + "%' ");
        }
        // 従業員名前を入力した場合。
        if(!bean.getSvnUserName().equals("")) {
            // 従業員名前の検索条件を追加する。
            sqlBuffer.append(" AND SM_USERMASTER.USERNAME LIKE '%" + selectSqlReplace(bean.getSvnUserName()) + "%' ");
        }
        // 検索条件を追加する。
        list.add(sqlBuffer.toString());
        // 従業員検索sql文を取得する。
        String sql = FBSEDBHandler.getSql("SVNM0413S001",list);
        // FBSEDBHandlerオブジェクトを宣言する。
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // sql文を実行する。
        FBSEDataResultSet resultSelect = dbUtil.executeSelect(sql);
        // 従業員配列の宣言と初期化。
        SelectBean[] selectBeans = new SelectBean[resultSelect.getRecordCount("table1")];
        // 配列下付き。
        int i = 0;
        // 検索する従業員が存在する場合。
        while (resultSelect.getNext("table1")) {
            // SelectBeanオブジェクトを宣言する。
            SelectBean selectBean = new SelectBean();
            // SVNユーザー番号と名前を設定する。
            selectBean.setCode(((String)resultSelect.getObject("USERNO")) + "," + 
                    ((String)resultSelect.getObject("USERNAME")));
            // SVNユーザー番号と名前を設定する。
            selectBean.setName(((String)resultSelect.getObject("USERNO")) + 
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + 
            ((String)resultSelect.getObject("USERNAME")));
            // 従業員情報を保存する。
            selectBeans[i++] = selectBean;
        }
        // ログを出力する。
        log.printLog("INFO", "SVNM0413Dao", String.valueOf((new Throwable())
                .getStackTrace()[0].getLineNumber()), "selectSVNUser", "End");
        // 従業員情報を戻る。
        return selectBeans;
    }
}