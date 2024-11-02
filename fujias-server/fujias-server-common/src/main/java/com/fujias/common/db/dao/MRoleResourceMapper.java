package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MRoleResource;
import com.fujias.common.db.entity.MRoleResourceKey;

public interface MRoleResourceMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(MRoleResourceKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MRoleResource record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MRoleResource record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return MRoleResource
     */
    MRoleResource selectByPrimaryKey(MRoleResourceKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MRoleResource record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MRoleResource record);
}