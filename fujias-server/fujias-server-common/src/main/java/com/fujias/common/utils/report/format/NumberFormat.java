package com.fujias.common.utils.report.format;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 日期格式的注解
 *
 * @version 1.0
 * @author 陳強
 */
@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface NumberFormat {

}