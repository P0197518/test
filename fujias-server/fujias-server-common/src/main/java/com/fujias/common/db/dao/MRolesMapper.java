package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MRoles;

public interface MRolesMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MRoles record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MRoles record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return MRoles
     */
    MRoles selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MRoles record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MRoles record);
}