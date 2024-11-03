package cn.edu.sgu.www.easyui.redis;

import cn.edu.sgu.www.easyui.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 封装了StringRedisTemplate的redis工具类
 * @author heyunlin
 * @version 1.0
 */
@Component
public class StringRedisUtils implements RedisUtils {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public StringRedisUtils(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String get(String key) {
        return getValueOperations().get(key);
    }

    @Override
    public void set(String key, String value) {
        getValueOperations().set(key, value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        getValueOperations().set(key, value, timeout, timeUnit);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void deleteByPattern(String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern);

        if (CollectionUtils.isNotEmpty(keys)) {
            stringRedisTemplate.delete(keys);
        }
    }

    @Override
    public Long incrBy(String key) {
        return getValueOperations().increment(key);
    }

    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void expire(String key, long timeout, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获取ValueOperations对象
     * @return ValueOperations<String, String>
     */
    private ValueOperations<String, String> getValueOperations() {
        return stringRedisTemplate.opsForValue();
    }

}