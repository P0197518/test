package com.fujias.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.fujias.common.component.ApacheBigDecimalConverterConfig;
import com.fujias.common.component.ApacheDateConverterConfig;

/**
 * Beanの間の値設定共通です。
 * 
 * @author 陳強
 *
 */
public final class BeanUtilsExtends extends BeanUtils {

    private BeanUtilsExtends() {

    }

    /**
     * Beanの値コピーを行う
     * 
     * @param dest コピー先
     * @param orig コピー元
     */
    public static void copyProperties(Object dest, Object orig) {
        try {
            ConvertUtils.register(new ApacheDateConverterConfig(), java.util.Date.class);
            ConvertUtils.register(new ApacheBigDecimalConverterConfig(), java.math.BigDecimal.class);
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 判断entity是否是空白
     * 
     * @param obj obj
     * @return 判断结果
     * @throws Exception Exception
     */
    public static boolean isEmpty(Object obj) {

        Class<?> tempClass = obj.getClass();
        while (tempClass != null && !tempClass.getName().toLowerCase().equals("java.lang.object")) {
            // 当父类为null的时候说明到达了最上层的父类(Object类).
            try {

                Field[] fs = tempClass.getDeclaredFields();// 得到属性集合

                for (Field f : fs) {
                    f.setAccessible(true);
                    Object val = null;
                    try {
                        val = f.get(obj);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                        return true;
                    } // 得到此属性的值

                    if (val == null) {
                        continue;
                    }
                    if (f.getType() == String.class) {
                        if (!StringUtil.isEmpty(val.toString())) {
                            return false;
                        }
                    } else if (f.getType() == Integer.class) {
                        if (0 != Integer.valueOf(val.toString())) {
                            return false;
                        }
                    }
                }

            } catch (SecurityException e) {
                e.printStackTrace();
            }
            tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
        }

        Field[] fs = obj.getClass().getDeclaredFields();// 得到属性集合

        for (Field f : fs) {
            f.setAccessible(true);
            Object val = null;
            try {
                val = f.get(obj);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
                return true;
            } // 得到此属性的值

            if (val == null) {
                continue;
            }
            if (f.getType() == String.class) {
                if (!StringUtil.isEmpty(val.toString())) {
                    return false;
                }
            } else if (f.getType() == Integer.class) {
                if (0 != Integer.valueOf(val.toString())) {
                    return false;
                }
            }
        }
        return true;
    }

}
