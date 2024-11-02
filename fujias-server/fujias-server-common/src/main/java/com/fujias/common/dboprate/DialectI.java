package com.fujias.common.dboprate;

/**
 * DB種類ごとの取得SQLを取得するインタフェイス
 * 
 * @author 陳強
 * 
 */
public interface DialectI {
    /**
     * 改ページSQLを取得する
     * 
     * @param sql 検索sql
     * @param offset 開始位置
     * @param limit 取得件数
     * @return DB種類ごとの取得SQL
     */
    String getPaginationSql(String sql, int offset, int limit);
}
