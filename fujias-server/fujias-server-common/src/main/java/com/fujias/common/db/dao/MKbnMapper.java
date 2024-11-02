package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MKbn;
import com.fujias.common.db.entity.MKbnKey;

public interface MKbnMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(MKbnKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MKbn record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MKbn record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return MKbn
     */
    MKbn selectByPrimaryKey(MKbnKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MKbn record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MKbn record);
}