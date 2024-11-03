package cn.edu.sgu.www.easyui.mapper;

import cn.edu.sgu.www.easyui.entity.UserMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author heyunlin
 * @version 1.0
 */
@Repository
public interface UserMenuMapper extends BaseMapper<UserMenu> {

    /**
     * 通过用户ID批量修改父级菜单ID对应的所有子菜单的排序号
     * @param userMenu 用户菜单信息
     */
    void updateByParentId(UserMenu userMenu);
}