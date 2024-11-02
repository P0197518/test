package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0202Workers;
import com.fujias.common.db.entity.T0202WorkersKey;

public interface T0202WorkersMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(T0202WorkersKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0202Workers record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0202Workers record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return T0202Workers
     */
    T0202Workers selectByPrimaryKey(T0202WorkersKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0202Workers record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0202Workers record);
}