package com.fujias.common.dboprate.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * Char型をString型変更のHandlerクラスです。
 * 
 * @author fujias
 *
 */
@MappedJdbcTypes({JdbcType.CHAR})
@MappedTypes(value = String.class)
public class TkCharTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String userStr, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, userStr);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String code = resultSet.getString(s);
        if (code != null) {
            code = code.trim();
        }
        return code;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String code = resultSet.getString(i);
        if (code != null) {
            code = code.trim();
        }
        return code;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String code = callableStatement.getString(i);
        if (code != null) {
            code = code.trim();
        }
        return code;
    }

}
