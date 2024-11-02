package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MSearchCondation;
import com.fujias.common.db.entity.MSearchCondationKey;

public interface MSearchCondationMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(MSearchCondationKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MSearchCondation record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MSearchCondation record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return MSearchCondation
     */
    MSearchCondation selectByPrimaryKey(MSearchCondationKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MSearchCondation record);

    /**
     * updateByPrimaryKeyWithBLOBs処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(MSearchCondation record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MSearchCondation record);
}