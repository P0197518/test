package cn.edu.sgu.www.easyui.mapper;

import cn.edu.sgu.www.easyui.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 通过类型查询权限列表
     * @return List<Permission>
     */
    List<Permission> selectByType(Integer type);

    /**
     * 通过父级权限id查询权限列表
     * @param parentId 父级权限id
     * @return List<Permission>
     */
    List<Permission> selectByParentId(String parentId);

    /**
     * 通过用户名查询用户权限
     * @param username 用户名
     * @return List<String>
     */
    List<String> selectUserPermissions(String username);
}