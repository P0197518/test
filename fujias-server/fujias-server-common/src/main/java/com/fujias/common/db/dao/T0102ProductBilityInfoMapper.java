package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0102ProductBilityInfo;
import com.fujias.common.db.entity.T0102ProductBilityInfoKey;

public interface T0102ProductBilityInfoMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(T0102ProductBilityInfoKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(T0102ProductBilityInfo record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0102ProductBilityInfo record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return T0102ProductBilityInfo
     */
    T0102ProductBilityInfo selectByPrimaryKey(T0102ProductBilityInfoKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0102ProductBilityInfo record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0102ProductBilityInfo record);
}