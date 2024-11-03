package cn.edu.sgu.www.easyui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 匿名访问注解
 * @author heyunlin
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymityAccess {
    /**
     * 是否允许匿名访问（默认允许）
     * @return boolean
     */
    boolean value() default true;
}