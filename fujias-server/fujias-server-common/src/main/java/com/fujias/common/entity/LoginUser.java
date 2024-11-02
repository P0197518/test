package com.fujias.common.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 検証用ユーザー情報Entity
 * 
 * @author 陳強
 *
 */
public class LoginUser implements UserDetails {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String name;
    private String password;

    private List<String> depids;

    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        if (roles == null) {
            roles = new ArrayList<>();
        }

        for (String role : roles) {
            auths.add(new SimpleGrantedAuthority(role));
        }
        return auths;
    }

    /**
     * usernameを設定する。
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * passwordを取得する。
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * passwordを設定する。
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * rolesを取得する。
     * 
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * rolesを設定する。
     * 
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getDepids() {
        return depids;
    }

    public void setDepids(List<String> depids) {
        this.depids = depids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取营业部门列表
     * 
     * @return 营业部门列表
     */
    public List<String> getOrderDepids() {
        List<String> orderDepids = new ArrayList<String>();
        if (depids == null || depids.isEmpty()) {
            return orderDepids;
        }
        for (String depid : depids) {
            if (depid.startsWith("2")) {
                orderDepids.add(depid);
            }
        }
        return orderDepids;
    }

    /**
     * 获取商品部门列表
     * 
     * @return 商品部门列表
     */
    public List<String> getPurchaseDepids() {
        List<String> orderDepids = new ArrayList<String>();
        if (depids == null || depids.isEmpty()) {
            return orderDepids;
        }
        for (String depid : depids) {
            if (depid.startsWith("3")) {
                orderDepids.add(depid);
            }
        }
        return orderDepids;
    }
}
