package com.fujias.business.service.s09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujias.business.forms.s09.S0900101PasswordForm;
import com.fujias.common.db.dao.MUsersMapper;
import com.fujias.common.db.entity.MUsers;
import com.fujias.common.util.Sha256Util;

/**
 * 密码修改Service类
 * 
 * @author chenqiang
 *
 */
@Service
public class S0900101PasswordService {

    @Autowired
    private MUsersMapper usersMapper;

    /**
     * 密码更新
     *
     * @param formData formData
     * @param username username
     * @return 更新状态
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean update(S0900101PasswordForm formData, String username) {

        MUsers user = new MUsers();
        user.setUsername(username);
        user.setPassword(Sha256Util.encode(formData.getPassword()));

        int updatecount = usersMapper.updateByPrimaryKeySelective(user);

        return updatecount > 0;
    }
}
