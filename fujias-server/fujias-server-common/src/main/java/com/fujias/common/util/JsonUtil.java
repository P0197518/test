package com.fujias.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fujias.common.component.SpringUtil;

/**
 * Json処理の共通クラスです。
 * 
 * @author 陳強
 *
 */
public final class JsonUtil {

    private JsonUtil() {

    }

    /**
     * ObjectをJson文字列に変換する
     * 
     * @param object object
     * @return Json文字列
     */
    public static String toJson(Object object) {
        ObjectMapper mapper = SpringUtil.getObjectManager();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ListをJson文字列に変換する
     * 
     * @param <T> T
     * @param list list
     * @return Json文字列
     */
    public static <T> String toJson(List<T> list) {
        ObjectMapper mapper = SpringUtil.getObjectManager();
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Json文字列を単一Objectに変換する
     * 
     * @param <T> T
     * @param json json
     * @param clazz Objectの型
     * @return 単一Object
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }

        ObjectMapper mapper = SpringUtil.getObjectManager();
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return clazz.newInstance();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
                return null;
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }

    /**
     * Json文字列をHashMapに変換する
     * 
     * @param jsonString jsonString
     * @return HashMap
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toHashMap(String jsonString) {
        if (jsonString == null || jsonString.length() < 0) {
            return null;
        }
        ObjectMapper mapper = SpringUtil.getObjectManager();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, Object.class);
        Map<String, Object> hashMap = null;
        try {
            hashMap = (Map<String, Object>) mapper.readValue(jsonString, javaType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * Json文字列をHashMapに変換する
     * 
     * @param jsonString jsonString
     * @param listType listType
     * @param <T> 泛型
     * @return HashMap
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, List<T>> toHashMapList(String jsonString, Class<T> listType) {
        if (jsonString == null || jsonString.length() < 0) {
            return null;
        }
        ObjectMapper mapper = SpringUtil.getObjectManager();

        JavaType jsonListType = mapper.getTypeFactory().constructParametricType(ArrayList.class, listType);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, jsonListType.getRawClass());
        Map<String, List<T>> hashMap = null;
        try {
            hashMap = (Map<String, List<T>>) mapper.readValue(jsonString, javaType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * Json文字列をListに変換する
     * 
     * @param <T> T
     * @param jsonString jsonString
     * @param clazz clazz
     * @return List
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        List<T> lst = null;
        try {
            lst = (List<T>) mapper.readValue(jsonString, javaType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

}
