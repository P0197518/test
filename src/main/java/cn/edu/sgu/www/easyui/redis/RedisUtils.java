package cn.edu.sgu.www.easyui.redis;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author heyunlin
 * @version 1.0
 */
public interface RedisUtils {

    /**
     * 获取key的值：get key
     * @param key redis的key
     * @return key的值
     */
    String get(String key);

    /**
     * 设置key：set key value
     * @param key redis的key
     * @param value key的值
     */
    void set(String key, String value);

    /**
     * 设置key：set key value ex timeout = set key value + expire key timeout
     * @param key redis的key
     * @param value key的值
     * @param timeout 过期时间
     * @param timeUnit 时间单位
     */
    void set(String key, String value, long timeout, TimeUnit timeUnit);

    /**
     * 删除key：del key
     * @param key redis的key
     */
    void delete(String key);

    /**
     * 根据pattern删除key：del keys pattern
     * @param pattern String
     */
    void deleteByPattern(String pattern);

    /**
     * 让key自增：incrby key
     * @param key redis的key
     * @return 自增后的值
     */
    Long incrBy(String key);

    /**
     * 判断key是否存在
     * @param key redis的key
     * @return key存在则返回true，否则返回false
     */
    Boolean hasKey(String key);

    /**
     * 设置key的过期时间：expire key seconds
     * @param key redis的key
     * @param timeout 过期时间
     * @param timeUnit 时间单位
     */
    void expire(String key, long timeout, TimeUnit timeUnit);
}