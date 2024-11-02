package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0201Worktime;
import com.fujias.common.db.entity.T0201WorktimeKey;

public interface T0201WorktimeMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(T0201WorktimeKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0201Worktime record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0201Worktime record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return T0201Worktime
     */
    T0201Worktime selectByPrimaryKey(T0201WorktimeKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0201Worktime record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0201Worktime record);
}