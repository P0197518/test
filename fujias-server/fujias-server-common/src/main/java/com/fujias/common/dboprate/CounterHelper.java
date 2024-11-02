package com.fujias.common.dboprate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQLの総件数を取得するに使う共通クラスです。
 * 
 * @author : 陳強
 * @date: 2018/08/11
 */
public final class CounterHelper {

    private static Logger logger = LoggerFactory.getLogger(CounterHelper.class);

    private CounterHelper() {

    }

    /**
     * 検索対象SQLの総件数を取得する
     * 
     * @param sql 検索対象SQL
     * @param statementHandler statementHandler
     * @param configuration configuration
     * @param boundSql boundSql
     * @param connection connection
     * @throws SQLException SQLException
     */
    static int getCount(String sql, Object entity, MappedStatement mappedStatement, Configuration configuration, BoundSql boundSql,
                    Connection connection) throws SQLException {

        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(sql);
            final BoundSql countBs = new BoundSql(configuration, sql, boundSql.getParameterMappings(), entity);

            DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement, entity, countBs);
            handler.setParameters(countStmt);

            rs = countStmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Total count: {}", count);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } finally {
                if (countStmt != null) {
                    countStmt.close();
                }
            }
        }
    }
}
