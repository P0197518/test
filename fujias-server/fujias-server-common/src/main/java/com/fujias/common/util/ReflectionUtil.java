package com.fujias.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fujias.common.annotation.IgnoreField;
import com.fujias.common.annotation.ReflectSetting;
import com.fujias.common.utils.report.format.DateFormat;
import com.fujias.common.utils.report.format.MoneyFormat;
import com.fujias.common.utils.report.format.NumberFormat;

/**
 * Reflectの共通処理クラスです。
 * 
 * @author 陳強
 *
 */
public final class ReflectionUtil {
    private ReflectionUtil() {

    }

    /**
     * 対処のフィールドの値を取得する
     * 
     * @param object 対象
     * @param propertyName フィールド
     * @return 取得値
     */
    public static Object invokeGetterMethod(Object object, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        try {
            Method getterMethod = object.getClass().getMethod(getterMethodName);
            return getterMethod.invoke(object);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 対処のフィールドの値を設定する
     * 
     * @param object 対象
     * @param propertyName フィールド
     * @param propertyValue 設定値
     */
    public static void invokeSetterMethod(Object object, String propertyName, Object propertyValue) {
        if (propertyValue == null) {
            return;
        }
        Class<?> setterMethodClass = propertyValue.getClass();
        invokeSetterMethod(object, propertyName, propertyValue, setterMethodClass);
    }

    /**
     * 対処のフィールドの値を設定する
     * 
     * @param object 対象
     * @param propertyName フィールド
     * @param propertyValue 設定値
     * @param setterMethodClass setterのパラメータの型
     */
    public static void invokeSetterMethod(Object object, String propertyName, Object propertyValue, Class<?> setterMethodClass) {
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        try {
            Method setterMethod = object.getClass().getMethod(setterMethodName, setterMethodClass);
            setterMethod.invoke(object, propertyValue);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<Class<?>, Class<?>> mapClass = new HashMap<Class<?>, Class<?>>();
        mapClass.put(Integer.class, int.class);
        mapClass.put(Double.class, double.class);
        mapClass.put(Float.class, float.class);
        mapClass.put(Boolean.class, boolean.class);
        mapClass.put(Byte.class, byte.class);

        try {
            Method setterMethod = object.getClass().getMethod(setterMethodName, mapClass.get(setterMethodClass));
            setterMethod.invoke(object, propertyValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 対処のフィールドの値を取得する(private/protected/getter友取得できる)
     * 
     * @param object 対象
     * @param fieldName フィールド
     * @return 取得値
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getAccessibleField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field " + fieldName);
        }
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 対処のフィールドの値を設定する(private/protected/setter友設定できる)
     * 
     * @param object 対象
     * @param fieldName フィールド
     * @param value 設定値
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getAccessibleField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field " + fieldName);
        }
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fieldの存在性をチェックする
     * 
     * @param clz clz
     * @param fieldName fieldName
     * @return 存在性
     * @throws NoSuchFieldException NoSuchFieldException
     */
    public static Field getFieldWithSuper(Class<?> clz, String fieldName) throws NoSuchFieldException {
        Class<?> tempClass = clz;
        while (tempClass != null && !tempClass.getName().toLowerCase().equals("java.lang.object")) {
            // 当父类为null的时候说明到达了最上层的父类(Object类).
            try {
                Field resultFiled = tempClass.getDeclaredField(fieldName);
                if (resultFiled != null) {
                    return resultFiled;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
        }
        throw new NoSuchFieldException("指定filed找不到");
    }

    /**
     * Fieldの存在性をチェックする
     * 
     * @param clz clz
     * @param fieldName fieldName
     * @return 存在性
     */
    public static boolean existsFieldWithSuper(Class<?> clz, String fieldName) {
        Class<?> tempClass = clz;
        while (tempClass != null && !tempClass.getName().toLowerCase().equals("java.lang.object")) {
            // 当父类为null的时候说明到达了最上层的父类(Object类).
            try {
                if (tempClass.getDeclaredField(fieldName) != null) {
                    return true;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                return false;
            }
            tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
        }
        return false;
    }

    /**
     * Fieldの存在性をチェックする
     * 
     * @param clz clz
     * @param fieldName fieldName
     * @return 存在性
     */
    public static boolean existsField(Class<?> clz, String fieldName) {
        try {
            if (clz.getDeclaredField(fieldName) != null) {
                return true;
            }
        } catch (NoSuchFieldException | SecurityException e) {
            return false;
        }
        return false;
    }

    /**
     * フィールドを取得する
     * 
     * @param object 対象
     * @param fieldName フィールド名
     * @return フィールド
     */
    private static Field getAccessibleField(final Object object, final String fieldName) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 対処のフィールドをHashMapの形に変換する
     * 
     * @param <T> T
     * @param t t
     * @return HashMap
     * @throws Exception Exception
     */
    public static <T> HashMap<String, Object> getMap(T t) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (t == null) {
            return map;
        }
        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if ("serialVersionUID".equals(f.getName())) {
                continue;
            }
            ReflectSetting setting = f.getAnnotation(ReflectSetting.class);
            if (setting != null && !setting.isReflect()) {
                continue;
            }
            map.put(f.getName(), invokeGetterMethod(t, f.getName()));
        }
        if (!map.containsKey("id")) {
            map.put("id", invokeGetterMethod(t, "id"));
        }
        return map;
    }

    /**
     * 対処のフィールドをHashMapの形に変換する
     * 
     * @param <T> T
     * @param tList tList
     * @return HashMap
     */
    public static <T> List<HashMap<String, Object>> getMapList(List<T> tList) {
        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
        if (tList == null || tList.size() == 0) {
            return mapList;
        }

        Class<?> clazz = tList.get(0).getClass();
        List<Field> fields = new ArrayList<Field>();
        fields = getBeanFields(clazz, fields);

        for (T t : tList) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            for (Field f : fields) {
                ReflectSetting setting = f.getAnnotation(ReflectSetting.class);
                if (setting != null && !setting.isReflect()) {
                    continue;
                }

                map.put(f.getName(), invokeGetterMethod(t, f.getName()));
            }

            if (!map.containsKey("id")) {
                map.put("id", invokeGetterMethod(t, "id"));
            }
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 根据list生成String类型map
     * 
     * @param tList tList
     * @param <T> T
     * @return String类型map
     */
    public static <T> List<HashMap<String, Object>> getStringMapList(List<T> tList) {
        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
        if (tList == null || tList.size() == 0) {
            return mapList;
        }

        Class<?> clazz = tList.get(0).getClass();
        List<Field> fields = new ArrayList<Field>();
        fields = getBeanFields(clazz, fields);

        for (T t : tList) {

            HashMap<String, Object> map = getStringMapBean(t);

            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 根据单条数据生成String类型map
     * 
     * @param bean bean
     * @param <T> T
     * @return String类型map
     */
    public static <T> HashMap<String, Object> getStringMapBean(T bean) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (bean == null) {
            return map;
        }

        Class<?> clazz = bean.getClass();
        List<Field> fields = new ArrayList<Field>();
        fields = getBeanFields(clazz, fields);

        for (Field f : fields) {
            ReflectSetting setting = f.getAnnotation(ReflectSetting.class);
            if (setting != null && !setting.isReflect()) {
                continue;
            }

            Object value = "";
            Object objValue = invokeGetterMethod(bean, f.getName());
            if (objValue == null) {
                value = "";
            } else if (f.getType() == Date.class) {
                DateFormat dateFormat = f.getAnnotation(DateFormat.class);
                if (dateFormat != null && !StringUtil.isEmpty(dateFormat.format())) {
                    value = DateUtil.formatDateToString((Date) objValue, dateFormat.format());
                } else {
                    value = DateUtil.formatDateToString((Date) objValue, DateUtil.DATE_FORMAT10);
                }
            } else if (f.getType() == BigDecimal.class) {

                if (f.getAnnotation(NumberFormat.class) != null) {
                    value = DecimalFormatUtil.parseDecimalToString((BigDecimal) objValue, DecimalFormatUtil.NUMBER_FORMAT4);
                } else if (f.getAnnotation(MoneyFormat.class) != null) {
                    value = DecimalFormatUtil.parseDecimalToString((BigDecimal) objValue, DecimalFormatUtil.MONEY_FORMAT);
                } else if (objValue.getClass() == BigDecimal.class) {
                    value = (BigDecimal) objValue;
                } else {
                    value = objValue.toString();
                }
            } else {
                value = objValue.toString();
            }

            map.put(f.getName(), value);
        }
        return map;
    }

    /**
     * 対象のすべてのフィールドを取得する
     * 
     * @param clazz clazz
     * @return fieldList
     */
    public static List<Field> getFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<Field>();
        getBeanFields(clazz, fieldList);
        return fieldList;
    }

    private static List<Field> getBeanFields(Class<?> clazz, List<Field> fieldList) {

        Field[] classField = clazz.getDeclaredFields();
        for (int i = 0; i < classField.length; i++) {

            IgnoreField ignoreAnnotation = classField[i].getAnnotation(IgnoreField.class);
            if (ignoreAnnotation != null) {
                continue;
            }
            fieldList.add(classField[i]);
        }

        if (clazz.getSuperclass() != null) {
            Class<?> clsSup = clazz.getSuperclass();
            fieldList = getBeanFields(clsSup, fieldList);
        }
        return fieldList;
    }

    /**
     * Formデータ検証のアノテーションのメッセージパラメータを取得する
     * 
     * @param clazz clazz
     * @param fieldName fieldName
     * @param annotationName annotationName
     * @return メッセージパラメータのリスト
     */
    public static List<String> getMessageArgsValue(Class<?> clazz, String fieldName, String annotationName) {
        Field targetField;
        try {
            targetField = clazz.getDeclaredField(fieldName);
            Annotation[] annotations = targetField.getDeclaredAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().getSimpleName().equals(annotationName)) {
                    Method me = item.annotationType().getDeclaredMethod("messageArgs", new Class[] {});
                    String[] messageArgArr = ((String[]) me.invoke(item, new Object[] {}));
                    return Arrays.asList(messageArgArr);
                } else {
                    continue;
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
