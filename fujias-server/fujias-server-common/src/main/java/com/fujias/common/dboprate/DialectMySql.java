package com.fujias.common.dboprate;

/**
 * MySqlの改ページSQL処理クラス。
 * 
 * @author 陳強
 * 
 */
public class DialectMySql implements DialectI {

    /**
     * MySqlの改ページ処理
     * 
     * @param sql 検索SQL
     * @param offset 開始位置
     * @param limit 取得件数
     * @return 取得SQL
     */
    @Override
    public String getPaginationSql(String sql, int offset, int limit) {
        StringBuffer strSql = new StringBuffer();
        strSql.append(sql);
        strSql.append(" limit ");
        strSql.append(offset);
        strSql.append(",");
        strSql.append(limit);
        return strSql.toString();
    }
}
