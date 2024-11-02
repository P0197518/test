package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MUsers;

public interface MUsersMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param username username
     * @return int
     */
    int deleteByPrimaryKey(String username);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MUsers record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MUsers record);

    /**
     * selectByPrimaryKey処理です。
     * @param username username
     * @return MUsers
     */
    MUsers selectByPrimaryKey(String username);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MUsers record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MUsers record);
}