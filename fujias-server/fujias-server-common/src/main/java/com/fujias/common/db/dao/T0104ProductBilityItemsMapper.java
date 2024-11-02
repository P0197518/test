package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0104ProductBilityItems;
import com.fujias.common.db.entity.T0104ProductBilityItemsKey;

public interface T0104ProductBilityItemsMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(T0104ProductBilityItemsKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0104ProductBilityItems record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0104ProductBilityItems record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return T0104ProductBilityItems
     */
    T0104ProductBilityItems selectByPrimaryKey(T0104ProductBilityItemsKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0104ProductBilityItems record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0104ProductBilityItems record);
}