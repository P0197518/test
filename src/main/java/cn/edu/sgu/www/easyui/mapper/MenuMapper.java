package cn.edu.sgu.www.easyui.mapper;

import cn.edu.sgu.www.easyui.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询父级菜单下所有子菜单中的最小排序号
     * @param parentId 父级菜单ID
     * @return int 最小排序号
     */
    int selectMinPxh(String parentId);

    /**
     * 查询用户的菜单
     * @param userId 用户ID
     * @return List<Menu>
     */
    List<Menu> selectMenus(String userId);

    /**
     * 通过父级菜单ID查询菜单列表
     * @param parentId 父级菜单ID
     * @return List<Menu>
     */
    List<Menu> selectByParentId(String parentId);

    /**
     * 查询角色所有子菜单
     * @param roleId 角色ID
     * @return List<Menu>
     */
    List<Menu> selectRoleMenus(Integer roleId);

    /**
     * 查询用户显示的菜单
     * @param userId 用户ID
     * @return List<Menu>
     */
    List<Menu> selectDisplayMenus(String userId);
}