package cn.edu.sgu.www.easyui.mapper;

import cn.edu.sgu.www.easyui.entity.Permission;
import cn.edu.sgu.www.easyui.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 删除用户对应角色的权限
     * @param userId 用户ID
     */
    void deleteByUserId(String userId);

    /**
     * 通过角色id查询
     * @param roleId 角色id
     * @return List<Permission>
     */
    List<Permission> selectByRoleId(Integer roleId);
}