package cn.edu.sgu.www.easyui.mapper;

import cn.edu.sgu.www.easyui.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author heyunlin
 * @version 1.0
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    void insertBatch(
            @Param("roleId") Integer roleId,
            @Param("menuIds") Set<String> menuIds
    );

    List<RoleMenu> selectByCondition(
            @Param("roleId") Integer roleId,
            @Param("menuId") String menuId
    );
}