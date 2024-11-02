package com.fujias.common.dboprate;

import java.util.Properties;
import java.util.UUID;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import com.fujias.common.component.SpringUtil;
import com.fujias.common.util.ReflectionUtil;
import com.fujias.common.util.StringUtil;

/**
 * 共通参数设置的拦截器
 * 
 * @version 1.0
 * @author 陳強
 */

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Component
public class ParameterInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        String methodName = ((MappedStatement) invocation.getArgs()[0]).getSqlCommandType().name();

        if ("INSERT".equals(methodName) || "UPDATE".equals(methodName)) {
            if (invocation.getArgs().length < 2) {
                return invocation.proceed();
            }
            Object param = invocation.getArgs()[1];
            interceptorInsertUpdate(methodName, param);
        }

        return invocation.proceed();
    }

    /**
     * 拦截insert 和update处理，设置参数
     * 
     * @param mappedStatement mappedStatement
     * @param metaStatementHandler metaStatementHandler
     */
    private void interceptorInsertUpdate(String methodName, Object entity) {
        String userId = SpringUtil.getLoginUserId();
        if ("INSERT".equals(methodName)) {

            if (ReflectionUtil.existsField(entity.getClass(), "id")) {
                Object idValue = ReflectionUtil.getFieldValue(entity, "id");
                if ((idValue == null || StringUtil.isEmpty(idValue.toString()))) {
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    ReflectionUtil.setFieldValue(entity, "id", uuid);
                }
            }

            if (ReflectionUtil.existsField(entity.getClass(), "createuser")) {
                ReflectionUtil.setFieldValue(entity, "createuser", userId);
            }
            if (ReflectionUtil.existsField(entity.getClass(), "createtime")) {
                ReflectionUtil.setFieldValue(entity, "createtime", new java.util.Date());
            }
            if (ReflectionUtil.existsField(entity.getClass(), "createUser")) {
                ReflectionUtil.setFieldValue(entity, "createUser", userId);
            }
            if (ReflectionUtil.existsField(entity.getClass(), "createTime")) {
                ReflectionUtil.setFieldValue(entity, "createTime", new java.util.Date());
            }
        }

        if (ReflectionUtil.existsField(entity.getClass(), "updateuser")) {
            ReflectionUtil.setFieldValue(entity, "updateuser", userId);
        }
        if (ReflectionUtil.existsField(entity.getClass(), "updatetime")) {
            ReflectionUtil.setFieldValue(entity, "updatetime", new java.util.Date());
        }
        if (ReflectionUtil.existsField(entity.getClass(), "updateUser")) {
            ReflectionUtil.setFieldValue(entity, "updateUser", userId);
        }
        if (ReflectionUtil.existsField(entity.getClass(), "updateTime")) {
            ReflectionUtil.setFieldValue(entity, "updateTime", new java.util.Date());
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
