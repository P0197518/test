package com.fujias.common.dboprate;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fujias.common.entity.Pager;
import com.fujias.common.util.ExceptionUtil;

/**
 * MybatsiInterceptorを使って、改ページを制御する。
 * 
 * @version 1.0
 * @author 陳強
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTORFACTORY = new DefaultReflectorFactory();
    private static final String DEFAULT_PAGE_SQL_ID = ".*Page$"; // SQLIDの判定ルール
    private static final String DEFAULT_DIALECT = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // デフォルトデータベース

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,
                        DEFAULT_REFLECTORFACTORY);

        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTORFACTORY);
        }
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTORFACTORY);
        }

        // 設定がない場合、デフォルトはpostgresqlを使用する。
        if (null == driverClass || "".equals(driverClass)) {
            LOGGER.warn("Property dialect is not setted,use default 'Postgresql' ");
            driverClass = DEFAULT_DIALECT;
        }

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        String methodName = mappedStatement.getSqlCommandType().name();
        if ("SELECT".equals(methodName)) {
            interceptorSelect(mappedStatement, metaStatementHandler, invocation);
        }

        return invocation.proceed();
    }

    /**
     * 拦截select处理，设置分页sql
     * 
     * @param mappedStatement mappedStatement
     * @param metaStatementHandler metaStatementHandler
     * @param invocation invocation
     * @throws NoSuchFieldException NoSuchFieldException
     */
    private void interceptorSelect(MappedStatement mappedStatement, MetaObject metaStatementHandler, Invocation invocation)
                    throws NoSuchFieldException {

        // SQLIDがPageで終了するかどうかを判定する
        if (mappedStatement.getId().matches(DEFAULT_PAGE_SQL_ID)) {

            // 改ページSQL
            DialectI dialect = null;

            if ("com.mysql.jdbc.Driver".equals(driverClass)) {
                dialect = new DialectMySql();
            } else if ("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(driverClass)) {
                dialect = new DialectSqlServer();
            } else if ("org.postgresql.Driver".equals(driverClass)) {
                dialect = new DialectPostgresql();
            } else if ("oracle.jdbc.driver.OracleDriver".equals(driverClass)) {
                dialect = new DialectOracle();
            } else {
                dialect = new DialectPostgresql();
            }

            int offset = 0;
            int limit = 65599;

            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

            String sql = boundSql.getSql();

            if (metaStatementHandler.getOriginalObject() instanceof RoutingStatementHandler) {
                RoutingStatementHandler a = (RoutingStatementHandler) metaStatementHandler.getOriginalObject();
                Object entity = a.getParameterHandler().getParameterObject();

                try {
                    Field pagerField = entity.getClass().getDeclaredField(Pager.FIELD_NAME);
                    pagerField.setAccessible(true);
                    Object pagerValue = pagerField.get(entity);
                    if (pagerValue instanceof Pager) {
                        Pager pager = (Pager) pagerValue;
                        offset = pager.getFirstRowIndex();
                        limit = pager.getPageSize();

                        if (pager.isAutoGetTotalCount()) {
                            final Object[] queryArgs = invocation.getArgs();
                            Connection connection = (Connection) queryArgs[0];

                            String countSql = MybatisSqlHelper.getCountSql(sql);
                            // mybatisのConfigurationを取得する
                            Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
                            int totalCount = CounterHelper.getCount(countSql, entity, mappedStatement, configuration, boundSql, connection);
                            pager.setTotalCount(totalCount);
                        }

                        // 改ページSQLを作成する
                        String pageSql = dialect.getPaginationSql(sql, offset, limit);
                        metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                        // 改ページのパラメータを設定する
                        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
                        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("Paging SQL : " + boundSql.getSql());
                        }
                    } else {
                        throw new NoSuchFieldException("Pagerの型が正しくないです、Pagerの型を直してください。");
                    }
                } catch (NoSuchFieldException ex) {
                    LOGGER.error(ExceptionUtil.getMessage(ex));
                    throw ex;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
