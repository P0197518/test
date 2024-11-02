package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MResource;

public interface MResourceMapper {
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
    int insert(MResource record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MResource record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return MResource
     */
    MResource selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MResource record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MResource record);
}