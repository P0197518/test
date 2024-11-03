package cn.edu.sgu.www.easyui.mapper;

import cn.edu.sgu.www.easyui.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author heyunlin
 * @version 1.0
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户ID查询角色ID
     * @param userId 用户ID
     * @return 用户角色信息
     */
    UserRole selectByUserId(String userId);
}