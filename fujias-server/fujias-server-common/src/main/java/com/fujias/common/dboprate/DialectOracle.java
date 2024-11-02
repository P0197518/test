package com.fujias.common.dboprate;

/**
 * Oraclの改ページSQL処理クラス。
 * 
 * @author 陳強
 * 
 */
public class DialectOracle implements DialectI {

    /**
     * Oraclの改ページ処理
     * 
     * @param sql 検索SQL
     * @param pageNo 開始位置
     * @param pageSize 取得件数
     * @return 取得SQL
     */
    @Override
    public String getPaginationSql(String sql, int pageNo, int pageSize) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("SELECT * ");
        strSql.append("  FROM ( ");
        strSql.append("         SELECT rownum rn,");
        strSql.append("                t.* ");
        strSql.append("          FROM  ( ");
        strSql.append(sql);
        strSql.append("                ) t ");
        strSql.append("         WHERE  rownum <= ");
        strSql.append((pageNo * pageSize));
        strSql.append("        ) t1 ");
        strSql.append(" WHERE t1.rn > ");
        strSql.append(((pageNo - 1) * pageSize));
        return strSql.toString();
    }
}
