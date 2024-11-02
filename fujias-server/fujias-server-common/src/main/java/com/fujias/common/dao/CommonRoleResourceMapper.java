package com.fujias.common.dao;

import java.util.List;

import com.fujias.common.form.CommonResourceRoles;

/**
 * 角色资源共同Mapper
 * 
 * @author chenqiang
 *
 */
public interface CommonRoleResourceMapper {

    /**
     * 获取角色资源列表
     * 
     * @return 角色资源列表
     */
    List<CommonResourceRoles> getResourceRoles();

}
