package com.fujias.common.security.springsecurity.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fujias.common.component.SpringUtil;
import com.fujias.common.constants.CommonConstants;
import com.fujias.common.dao.CommonRoleResourceMapper;
import com.fujias.common.form.CommonResourceRoles;

/**
 * 权限资源缓存工具类
 * 
 * @author chenqiang
 *
 */
public final class RoleResourceCache {

    private static HashMap<String, CommonResourceRoles> cacheMap;

    private static HashMap<String, CommonResourceRoles> whiteUrlsCacheMap;

    private static RoleResourceCache cuscache;

    /**
     * 构造函数私有化
     */
    private RoleResourceCache() {

    }

    /**
     * 创建和获取缓存实例
     * 
     * @return 缓存实例
     */
    public static RoleResourceCache getInstance() {
        if (cuscache == null) {
            cuscache = new RoleResourceCache();
        }
        return cuscache;
    }

    /**
     * 加载所有缓存类
     */
    public synchronized void loadAll() {
        if (cacheMap == null) {
            cacheMap = new HashMap<String, CommonResourceRoles>();
            reloadAll();
        }

        System.out.println("===========" + "得到缓存完毕===================");
    }

    /**
     * 从缓存中获取白名单以外的角色权限列表Map
     * 
     * @return 白名单以外的角色权限列表
     */
    public static synchronized HashMap<String, CommonResourceRoles> getAll() {
        if (cacheMap == null || cacheMap.isEmpty()) {
            reloadAll();
        }
        return cacheMap;
    }

    /**
     * 从缓存中获取白名单的角色权限列表Map
     * 
     * @return 白名单角色权限列表
     */
    public static synchronized HashMap<String, CommonResourceRoles> getAllWhite() {
        if (whiteUrlsCacheMap == null || whiteUrlsCacheMap.isEmpty()) {
            reloadAllWhite();
        }
        return whiteUrlsCacheMap;
    }

    /**
     * 从缓存中获取白名单以外的角色权限列表List
     * 
     * @return 白名单以外的角色权限列表
     */
    public static synchronized List<CommonResourceRoles> getAllWhiteList() {
        if (whiteUrlsCacheMap == null) {
            reloadAllWhite();
        }
        List<CommonResourceRoles> resourceRoles = new ArrayList<CommonResourceRoles>();
        for (String key : whiteUrlsCacheMap.keySet()) {
            CommonResourceRoles item = whiteUrlsCacheMap.get(key);
            resourceRoles.add(item);
        }

        return resourceRoles;
    }

    /**
     * 从缓存中获取白名单以外的角色权限列表List
     * 
     * @return 白名单以外的角色权限列表
     */
    public static synchronized List<CommonResourceRoles> getAllList() {
        if (cacheMap == null) {
            reloadAll();
        }
        List<CommonResourceRoles> resourceRoles = new ArrayList<CommonResourceRoles>();
        for (String key : cacheMap.keySet()) {
            CommonResourceRoles item = cacheMap.get(key);
            resourceRoles.add(item);
        }

        return resourceRoles;
    }

    /**
     * 从数据库中加载所有白名单以外的角色权限列表
     */
    public static synchronized void reloadAll() {

        if (cacheMap == null) {
            cacheMap = new HashMap<String, CommonResourceRoles>();
        }

        CommonRoleResourceMapper commonRoleResourceMapper = (CommonRoleResourceMapper) SpringUtil.getBean("commonRoleResourceMapper");
        System.out.println("===========" + "重新得到缓存===================");
        cacheMap.clear();
        List<CommonResourceRoles> roleResource = commonRoleResourceMapper.getResourceRoles();

        for (CommonResourceRoles item : roleResource) {
            cacheMap.put(item.getPath(), item);
        }

        System.out.println("===========" + "重新得到缓存===================");
    }

    /**
     * 从数据库中加载所有白名单的角色权限列表
     */
    public static synchronized void reloadAllWhite() {

        if (whiteUrlsCacheMap == null) {
            whiteUrlsCacheMap = new HashMap<String, CommonResourceRoles>();
        }

        System.out.println("===========" + "重新得到白名单缓存===================");
        whiteUrlsCacheMap.clear();

        List<String> allRole = new ArrayList<String>();
        allRole.add(CommonConstants.ALL_ROLE);
        whiteUrlsCacheMap.put("/login/*", new CommonResourceRoles("/login/*", allRole));
        whiteUrlsCacheMap.put("/user/info", new CommonResourceRoles("/user/info", allRole));
        // whiteUrlsCacheMap.put("/error", new CommonResourceRoles("/error", allRole));
        whiteUrlsCacheMap.put("/static/**", new CommonResourceRoles("/static/**", allRole));
        whiteUrlsCacheMap.put("/images/**", new CommonResourceRoles("/images/**", allRole));
        whiteUrlsCacheMap.put("/js/**", new CommonResourceRoles("/js/**", allRole));
        whiteUrlsCacheMap.put("/css/**", new CommonResourceRoles("/css/**", allRole));
        whiteUrlsCacheMap.put("/boot.js", new CommonResourceRoles("/boot.js", allRole));
        whiteUrlsCacheMap.put("/upload/**", new CommonResourceRoles("/upload/**", allRole));
        whiteUrlsCacheMap.put("/favicon.ico", new CommonResourceRoles("/favicon.ico", allRole));
        whiteUrlsCacheMap.put("/notRole/**", new CommonResourceRoles("/notRole/**", allRole));
        whiteUrlsCacheMap.put("/fileUpload/**", new CommonResourceRoles("/fileUpload/**", allRole));
        whiteUrlsCacheMap.put("/error/**", new CommonResourceRoles("/error/**", allRole));
        System.out.println("===========" + "重新得到白名单缓存===================");
    }

    /**
     * 添加角色权限
     * 
     * @param item 角色权限
     */
    public static synchronized void add(CommonResourceRoles item) {
        cacheMap.put(item.getPath(), item);
    }

    /**
     * 更新角色权限
     * 
     * @param item 角色权限
     */
    public static synchronized void update(CommonResourceRoles item) {
        cacheMap.put(item.getPath(), item);
    }

    /**
     * whiteUrlsCacheMap的取得方法。
     * 
     * @return the whiteUrlsCacheMap
     */
    public static HashMap<String, CommonResourceRoles> getWhiteUrlsCacheMap() {
        return whiteUrlsCacheMap;
    }

    /**
     * whiteUrlsCacheMap的设定方法。
     * 
     * @param whiteUrlsCacheMap the whiteUrlsCacheMap to set
     */
    public static void setWhiteUrlsCacheMap(HashMap<String, CommonResourceRoles> whiteUrlsCacheMap) {
        RoleResourceCache.whiteUrlsCacheMap = whiteUrlsCacheMap;
    }

}
