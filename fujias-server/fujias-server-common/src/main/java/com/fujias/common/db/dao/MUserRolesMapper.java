package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MUserRoles;
import com.fujias.common.db.entity.MUserRolesKey;

public interface MUserRolesMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(MUserRolesKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MUserRoles record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MUserRoles record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return MUserRoles
     */
    MUserRoles selectByPrimaryKey(MUserRolesKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MUserRoles record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MUserRoles record);
}