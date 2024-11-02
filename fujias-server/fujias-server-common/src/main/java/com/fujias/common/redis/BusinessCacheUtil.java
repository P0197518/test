package com.fujias.common.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fujias.common.constants.CacheKeys;

/**
 * 自定义缓存工具类
 * 
 * @author 陳強
 *
 */
@Component
public class BusinessCacheUtil {

    /**
     * 登录信息超时时间
     */
    public static final long LOGIN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7L;
    /**
     * 重复登录时间
     */
    private static final long ERROR_LOGIN_TIME = 1000 * 60 * 60;
    /**
     * 验证码超时时间
     */
    private static final long CODE_EXPIRATION_TIME = 1000 * 60 * 15;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 缓存用户token
     * 
     * @param ipAddress ipAddress
     * @param code code
     */
    public void cacheVerificationCode(String ipAddress, String code) {
        redisUtil.set(CacheKeys.LOGIN_VERIFICATIONCODE_INFO + ipAddress, code);
        redisUtil.expire(CacheKeys.LOGIN_VERIFICATIONCODE_INFO + ipAddress, (int) (CODE_EXPIRATION_TIME / 1000));
    }

    /**
     * 缓存用户token
     * 
     * @param ipAddress ipAddress
     * @param code code
     */
    public void cacheLoginErrorTimes(String ipAddress, String code) {
        redisUtil.setrange(CacheKeys.LOGIN_ERROR_TIMES + ipAddress, code);
        redisUtil.expire(CacheKeys.LOGIN_ERROR_TIMES + ipAddress, (int) (ERROR_LOGIN_TIME / 1000));
    }

    /**
     * 缓存用户token
     * 
     * @param userId userId
     * @param token token
     */
    public void cacheUserToken(String userId, String token) {
        redisUtil.set(CacheKeys.USER_TOKEN_INFO + userId, token);
        redisUtil.expire(CacheKeys.USER_TOKEN_INFO + userId, (int) (LOGIN_EXPIRATION_TIME / 1000));

    }

    /**
     * 缓存用户角色
     * 
     * @param userId userId
     * @param roles roles
     */
    public void cacheUserRoles(String userId, List<String> roles) {
        redisUtil.lpush(CacheKeys.USER_ROLES_INFO + userId, roles.toArray(new String[roles.size()]));
    }

    /**
     * 获取用户token
     * 
     * @param userId userId
     * @return 用户token
     */
    public String getCacheToken(String userId) {
        return redisUtil.get(CacheKeys.USER_TOKEN_INFO + userId);
    }

    /**
     * 缓存用户token
     * 
     * @param ipAddress ipAddress
     * @return ErrorTimes
     */
    public String getLoginErrorTimes(String ipAddress) {
        return redisUtil.get(CacheKeys.LOGIN_ERROR_TIMES + ipAddress);
    }

    /**
     * 获取缓存验证码缓存用户token
     * 
     * @param ipAddress ipAddress
     * @return 验证码
     */
    public String getCacheVerificationCode(String ipAddress) {
        return redisUtil.get(CacheKeys.LOGIN_VERIFICATIONCODE_INFO + ipAddress);
    }

    /**
     * 删除用户token
     * 
     * @param userId userId
     */
    public void deleteCachedUserToken(String userId) {
        redisUtil.del(CacheKeys.USER_TOKEN_INFO + userId);
    }

    /**
     * 获取用户角色
     * 
     * @param userId userId
     * @return roles
     */
    public List<String> getCacheUserRoles(String userId) {
        return redisUtil.lrangeAll(CacheKeys.USER_ROLES_INFO + userId);
    }

    /**
     * 删除用户token
     * 
     * @param ipAddress ipAddress
     */
    public void deleteLoginErrorTimes(String ipAddress) {
        redisUtil.del(CacheKeys.LOGIN_ERROR_TIMES + ipAddress);
    }

    /**
     * 删除用户角色
     * 
     * @param userId userId
     */
    public void deleteCachedUserRoles(String userId) {
        redisUtil.del(CacheKeys.USER_ROLES_INFO + userId);
    }

}
