package com.fujias.common.dboprate;

/**
 * SqlServerの改ページSQL処理クラス。
 * 
 * @author 陳強
 * 
 */
public class DialectSqlServer implements DialectI {

    /**
     * SqlServerの改ページ処理
     * 
     * @param sql 検索SQL
     * @param pageNo 開始位置
     * @param pageSize 取得件数
     * @return 取得SQL
     */
    @Override
    public String getPaginationSql(String sql, int pageNo, int pageSize) {

        int orderIndex = sql.lastIndexOf("order by") == -1 ? sql.lastIndexOf("ORDER BY") : sql.lastIndexOf("order by");
        String orderby = orderIndex <= 0 ? "" : sql.substring(orderIndex);
        String selectSql = orderIndex <= 0 ? sql : sql.substring(0, orderIndex);

        sql = selectSql.replaceFirst("(?i)select", "select row_number() over(" + orderby + ") as rownumber,");

        StringBuffer strSql = new StringBuffer();
        strSql.append("SELECT top ");
        strSql.append(pageSize);
        strSql.append(" * FROM  ( ");
        strSql.append(sql);
        strSql.append("        ) t ");
        strSql.append(" WHERE t.rownumber >  ");
        strSql.append(pageNo);
        strSql.append(" order by t.rownumber asc");

        return strSql.toString();
    }
}
