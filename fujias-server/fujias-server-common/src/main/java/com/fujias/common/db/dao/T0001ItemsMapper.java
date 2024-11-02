package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0001Items;

public interface T0001ItemsMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param id id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0001Items record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0001Items record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return T0001Items
     */
    T0001Items selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0001Items record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0001Items record);
}