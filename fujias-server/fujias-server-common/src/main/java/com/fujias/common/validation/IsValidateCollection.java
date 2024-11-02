package com.fujias.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 是否验证form中的list的注解
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface IsValidateCollection {

    /**
     * 获取集合的名字
     * 
     * @return 集合的名字
     */
    String collectionName();

}
