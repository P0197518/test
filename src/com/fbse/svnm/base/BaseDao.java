package com.fbse.svnm.base;

import java.util.ArrayList;
import com.fbse.common.FBSEDBHandler;
import com.fbse.common.FBSEDataResultSet;
import com.fbse.common.FBSELogHandler;
import com.fbse.svnm.bean.SelectBean;

/**
 * <HR>
 * BaseDaoのデータベースの処理である。<P>
 * このクラスでは以下の処理を行う。
 * <ul>
 * <li>コンボボックス初期化情報を検索する。</li>
 * <li>特殊な文字を取替えする。</li>
 * </ul>
 * @version V1.0
 * @author  FBSE)張志明
 */
public class BaseDao {
    // ログオブジェクトを宣言
    protected FBSELogHandler log = Common.getLog();

    /**
     * 権限のコンボボックス初期化情報を検索する。<P>
     * <b>処理概要:</b><br>
     * 権限の情報を検索する。<br>
     * @param    なし
     * @return    SelectBean[]    権限の配列
     * @exception    なし
     */
    public SelectBean[] getCommbox()throws Exception {
        // FBSEDataResultSetプロジェクトを宣言する
        FBSEDataResultSet rs = null;
        // FBSEDBHandlerプロジェクトを宣言する
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // SelectBean[]プロジェクトを宣言する
        SelectBean[] bean = new SelectBean[1];
        // SQL文
        String sql = FBSEDBHandler.getSql("SVNMCOMMONS008");
        // SQL文の実行
        rs = dbUtil.executeSelect(sql);
        // データの件数を取得
        if (rs.getRecordCount("table1")==0) {
            return null;
        }
        // SelectBeanプロジェクトを宣言する
        bean = new SelectBean[rs.getRecordCount("table1")];
        // 索引の宣言
        int i = 0;
        // ループでコンボボックス初期化情報を取得
        while (rs.getNext("table1")) {
            // SelectBeanプロジェクトを宣言する
            bean[i] = new SelectBean();
            // 権限番号をセットする
            bean[i].setCode(rs.getObject("PRIVILEGENO").toString());
            // 権限内容をセットする
            bean[i].setName(i+1+":"+rs.getObject("PRIVILEGENAME").toString());
            // 索引
            i++;
        }
        // 検索結果を戻す
        return bean;
    }

    /**
     * 権限と状態のコンボボックス初期化情報を検索する。<P>
     * <b>処理概要:</b><br>
     * 権限と状態の情報を検索する。<br>
     * @param    code    区分表で番号
     * @return    SelectBean[]    配列
     * @exception    なし
     */
    public SelectBean[] getCommbox(String code) throws Exception {
        // FBSEDataResultSetプロジェクトを宣言する
        FBSEDataResultSet rs = null;
        // FBSEDBHandlerプロジェクトを宣言する
        FBSEDBHandler dbUtil = new FBSEDBHandler();
        // SelectBean[]を宣言する
        SelectBean[] bean = new SelectBean[1];
        // ArrayListプロジェクトを宣言する
        ArrayList para=new ArrayList();
        // 番号を設定する
        para.add(code);
        // SQL文
        String sql = FBSEDBHandler.getSql("SVNMCOMMONS010",para);
        // SQL文の実行
        rs = dbUtil.executeSelect(sql);
        // データの件数を取得
        if (rs.getRecordCount("table1")==0) {
            return null;
        }
        bean = new SelectBean[rs.getRecordCount("table1")];
        int i = 0;
        // ループでコンボボックス初期化情報を取得
        while (rs.getNext("table1")) {
            bean[i] = new SelectBean();
            bean[i].setCode(rs.getObject("CLASSCODE").toString());
            bean[i].setName(rs.getObject("CLASSNAME").toString());
            i++;
        }
        // 検索結果を戻す
        return bean;
    }

    /**
     * 検索する場合ｓｑｌ文の特殊な文字を取替えする。<P>
     * <b>処理概要:</b><br>
     * 検索する場合ｓｑｌ文の特殊な文字を取替えする。<br>
     * @param    str    取替えされる文字
     * @return    String    取替えされた文字
     * @exception    なし
     */
    public String selectSqlReplace(String str) {
        // "’"を取替え
        String tempStr = str.replace("'", "''");
        // "\\"を取替え
        tempStr = tempStr.replace("\\", "\\\\");
        // "%"を取替え
        tempStr = tempStr.replace("%", "\\%");
        // "_"を取替え
        tempStr = tempStr.replace("_", "\\_");
        // 取替えされた文字を戻す
        return tempStr;
    }

    /**
     * 更新する場合ｓｑｌ文の特殊な文字を取替えする。<P>
     * <b>処理概要:</b><br>
     * 更新する場合ｓｑｌ文の特殊な文字を取替えする。<br>
     * @param    str    取替えされる文字
     * @return    String    取替えされた文字
     * @exception    なし
     */
    public String updateSqlReplace(String str) {
        // "’"を取替え
        String tempStr = str.replace("'", "''");
        // "\\"を取替え
        tempStr = tempStr.replace("\\", "\\\\");
        // 取替えされた文字を戻す
        return tempStr;
    }
}