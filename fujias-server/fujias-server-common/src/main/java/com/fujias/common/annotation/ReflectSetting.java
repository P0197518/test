package com.fujias.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reflectに使う設定アノテーションです。
 * 
 * @author 陳強
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReflectSetting {

    /**
     * Reflectを行うかどうかを判定する。
     * 
     * @return 判定結果
     */
    boolean isReflect() default true;
}
