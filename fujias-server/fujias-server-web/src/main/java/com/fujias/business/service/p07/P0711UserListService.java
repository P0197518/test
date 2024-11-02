package com.fujias.business.service.p07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.dao.p07.P0711UserListMapper;
import com.fujias.business.forms.p07.P0711UserListForm;
import com.fujias.common.constants.MessageCodes;
import com.fujias.common.db.dao.MUserDepartmentsMapper;
import com.fujias.common.db.dao.MUserRolesMapper;
import com.fujias.common.db.dao.MUsersMapper;
import com.fujias.common.db.entity.MUserDepartments;
import com.fujias.common.db.entity.MUserRoles;
import com.fujias.common.db.entity.MUsers;
import com.fujias.common.exception.BusinessException;
import com.fujias.common.util.BeanUtilsExtends;
import com.fujias.common.util.DateUtil;
import com.fujias.common.util.Sha256Util;

/**
 * 用户管理的Service类
 * 
 * @author wul
 *
 */
@Service
public class P0711UserListService {

    @Autowired
    private MUsersMapper usersMapper;

    @Autowired
    private MUserRolesMapper userRolesMapper;

    @Autowired
    private MUserDepartmentsMapper userDepsMapper;

    @Autowired
    private P0711UserListMapper s0100501UsersMapper;

    /**
     * 删除用户
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public int delete(P0711UserListForm formData) {
        MUsers updateEntity = new MUsers();
        updateEntity.setUsername(formData.getUsername());
        updateEntity.setDelFlg("1");
        return usersMapper.updateByPrimaryKeySelective(updateEntity);
    }

    /**
     * 还原用户
     * 
     * @param formData formData
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public int recover(P0711UserListForm formData) {
        MUsers updateEntity = new MUsers();
        updateEntity.setUsername(formData.getUsername());
        updateEntity.setDelFlg("0");
        return usersMapper.updateByPrimaryKeySelective(updateEntity);
    }

    /**
     * 添加用户
     * 
     * @param formData formData
     * @return 更新状态
     * @throws BusinessException BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(P0711UserListForm formData) throws BusinessException {

        MUsers updateUserEntity = new MUsers();
        updateUserEntity.setEmployeeId(formData.getEmployeeId());
        updateUserEntity.setUsername(formData.getUsername());
        updateUserEntity.setPassword(formData.getPassword());
        updateUserEntity.setName(formData.getName());
        updateUserEntity.setGender("nan1");
        updateUserEntity.setDelFlg("0");
        /*BeanUtilsExtends.copyProperties(updateUserEntity, formData);*/

        // 更新用户角色
        MUserRoles updateUserRoleEntity = new MUserRoles();
        updateUserRoleEntity.setRoleId(formData.getRoleid());
        updateUserRoleEntity.setUserId(formData.getUsername());
        updateUserRoleEntity.setDelFlg("0");
        if (userRolesMapper.insertSelective(updateUserRoleEntity) == 0) {
            throw new BusinessException(MessageCodes.EC0010);
        }

        updateUserEntity.setPassword(
                        Sha256Util.encode(formData.getEmployeeId() + DateUtil.formatDateToString(formData.getBirthday(), DateUtil.DATE_FORMAT8)));

        // 更新用户信息
        if (usersMapper.insertSelective(updateUserEntity) == 0) {
            throw new BusinessException(MessageCodes.EC0010);
        }

        // 更新用户部门
        updateUserDeps(formData);

        return true;
    }

    /**
     * 更新用户
     * 
     * @param formData formData
     * @return 更新状态
     * @throws BusinessException BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(P0711UserListForm formData) throws BusinessException {
        MUsers updateUserEntity = new MUsers();
        MUserRoles updateUserRoleEntity = new MUserRoles();
        BeanUtilsExtends.copyProperties(updateUserEntity, formData);

        updateUserRoleEntity.setRoleId(formData.getRoleid());
        updateUserRoleEntity.setUserId(formData.getUsername());
        if (s0100501UsersMapper.checkUserRoleExist(updateUserRoleEntity) > 0) {
            if (s0100501UsersMapper.updateUserRoles(updateUserRoleEntity) == 0) {
                throw new BusinessException(MessageCodes.EC0010);
            }
        } else {
            if (userRolesMapper.insertSelective(updateUserRoleEntity) == 0) {
                throw new BusinessException(MessageCodes.EC0010);
            }
        }

        usersMapper.updateByPrimaryKeySelective(updateUserEntity);

        // 更新用户部门
        updateUserDeps(formData);
        return true;
    }

    /**
     * 更新用户部门
     * 
     * @param formData formData
     * @return 更新状态
     * @throws BusinessException BusinessException
     */
    public boolean updateUserDeps(P0711UserListForm formData) throws BusinessException {

        String[] deps = formData.getDepid().split(",");

        s0100501UsersMapper.deleteDepsByUserId(formData.getUsername());

        for (String depid : deps) {
            MUserDepartments updateEntity = new MUserDepartments();
            updateEntity.setDepId(depid);
            updateEntity.setUserId(formData.getUsername());
            updateEntity.setDelFlg("0");
            if (userDepsMapper.insertSelective(updateEntity) == 0) {
                throw new BusinessException(MessageCodes.EC0010);
            }
        }

        return true;
    }

}
