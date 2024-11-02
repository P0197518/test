package com.fujias.common.db.dao;

import com.fujias.common.db.entity.TNumber;

public interface TNumberMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param ID ID
     * @return int
     */
    int deleteByPrimaryKey(String ID);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(TNumber record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(TNumber record);

    /**
     * selectByPrimaryKey処理です。
     * @param ID ID
     * @return TNumber
     */
    TNumber selectByPrimaryKey(String ID);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(TNumber record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(TNumber record);
}