package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MId;

public interface MIdMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param idType idType
     * @return int
     */
    int deleteByPrimaryKey(String idType);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MId record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MId record);

    /**
     * selectByPrimaryKey処理です。
     * @param idType idType
     * @return MId
     */
    MId selectByPrimaryKey(String idType);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MId record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MId record);
}