package com.fujias.common.dao;

import java.util.List;

import com.fujias.common.db.entity.MRoles;
import com.fujias.common.db.entity.MUsers;
import com.fujias.common.form.CommonResourceFunctions;
import com.fujias.common.form.CommonUserResourceForm;

public interface CommonUserMapper {
    /**
     * getUserInfoById処理です。
     * 
     * @param userId userId
     * @return UserInfo
     */
    MUsers getUserInfoById(String userId);

    List<MRoles> getUserRoles(String userId);

    List<CommonResourceFunctions> getUserResources(String userId);

    List<CommonResourceFunctions> getUserResourcesByOne(CommonUserResourceForm param);

    List<String> getUserDepids(String userId);
}
