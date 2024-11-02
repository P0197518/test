package com.fujias.common.db.dao;

import com.fujias.common.db.entity.T0203AdjustSettings;

public interface T0203AdjustSettingsMapper {
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
    int insert(T0203AdjustSettings record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(T0203AdjustSettings record);

    /**
     * selectByPrimaryKey処理です。
     * @param id id
     * @return T0203AdjustSettings
     */
    T0203AdjustSettings selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(T0203AdjustSettings record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(T0203AdjustSettings record);
}