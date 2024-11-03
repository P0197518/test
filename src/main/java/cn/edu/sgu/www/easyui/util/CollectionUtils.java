package cn.edu.sgu.www.easyui.util;

import java.util.Collection;

/**
 * 集合工具类
 * @author heyunlin
 * @version 1.0
 */
public class CollectionUtils {

    /**
     * 判断指定集合是否为null或空集
     * @param collection Collection集合
     * @return 指定的集合为null或空集，则返回false；否则返回true
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断指定集合是否为null或空集
     * @param collection Collection集合
     * @return 指定的集合为null或空集，则返回true；否则返回false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}