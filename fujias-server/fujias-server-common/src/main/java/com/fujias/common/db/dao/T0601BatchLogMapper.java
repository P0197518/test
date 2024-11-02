package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0601BatchLog;

public interface T0601BatchLogMapper {
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
    int insert(T0601BatchLog record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0601BatchLog record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return T0601BatchLog
     */
    T0601BatchLog selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0601BatchLog record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0601BatchLog record);
}