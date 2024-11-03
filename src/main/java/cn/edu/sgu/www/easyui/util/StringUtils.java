package cn.edu.sgu.www.easyui.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author heyunlin
 * @version 1.0
 */
public class StringUtils {

    /**
     * 判断字符串是否为null或""
     * 字符串为""或null返回true，否则返回false
     * @param str 要判断的字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return isNullOrEmpty(str);
    }

    /**
     * 判断字符串是否为""或null
     * 字符串为""或null返回false，否则返回true
     * @param str 要判断的字符串
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为null或""
     * 字符串为""或null返回true，否则返回false
     * @param str 要判断的字符串
     * @return boolean
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 驼峰命名转下划线命名
     * @param str 待转换的字符串
     * @return String
     */
    public static String toLower(String str) {
        // 小写和大写紧挨一起的地方加上分隔符_，然后全部转为小写
        str = str.replaceAll("([a-z])([A-Z])", "$1_$2");

        return str.toLowerCase();
    }

    /**
     * 根据当前时间生成UUID
     * @return String
     */
    public static String uuid() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDate = LocalDateTime.now();

        return localDate.format(formatter);
    }

}