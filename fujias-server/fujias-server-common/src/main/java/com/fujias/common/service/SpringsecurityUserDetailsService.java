package com.fujias.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fujias.common.dao.CommonUserMapper;
import com.fujias.common.db.entity.MRoles;
import com.fujias.common.db.entity.MUsers;
import com.fujias.common.entity.LoginUser;

/**
 * 検証ユーザー情報を取得する
 * 
 * @author 陳強
 *
 */
public class SpringsecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private CommonUserMapper commonUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        MUsers userget = commonUserMapper.getUserInfoById(username);
        if (userget == null) {
            return null;
        }
        LoginUser user = new LoginUser();
        user.setUsername(userget.getUsername());
        user.setPassword(userget.getPassword());
        user.setName(userget.getName());

        List<MRoles> roles = commonUserMapper.getUserRoles(userget.getUsername());
        List<String> roleIds = new ArrayList<String>();
        for (MRoles roleItem : roles) {
            roleIds.add(roleItem.getId());
        }
        user.setRoles(roleIds);
        List<String> depids = commonUserMapper.getUserDepids(userget.getUsername());
        user.setDepids(depids);
        return user;
    }

}
