package com.fujias.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujias.common.dao.CommonUserMapper;
import com.fujias.common.db.entity.MRoles;
import com.fujias.common.db.entity.MUsers;

/**
 * 共通用户信息检索Service
 * 
 * @author 陳強
 *
 */
@Service
public class CommonUserService {

    @Autowired
    private CommonUserMapper commonUserMapper;

    /**
     * getUserInfoById処理です。
     * 
     * @param userId userId
     * @return UserInfo
     */
    public MUsers getUserInfoById(String userId) {
        return commonUserMapper.getUserInfoById(userId);
    }

    /**
     * 检索用户的角色
     * 
     * @param userId userId
     * @return 角色
     */
    public List<String> getUserRoles(String userId) {
        List<MRoles> rolesObj = commonUserMapper.getUserRoles(userId);
        List<String> roles = new ArrayList<String>();
        for (MRoles roleItem : rolesObj) {
            roles.add(roleItem.getId());
        }
        return roles;
    }
}
