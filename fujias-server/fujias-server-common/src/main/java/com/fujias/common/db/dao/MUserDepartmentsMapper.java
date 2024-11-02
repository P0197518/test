package com.fujias.common.db.dao;

import com.fujias.common.db.entity.MUserDepartments;
import com.fujias.common.db.entity.MUserDepartmentsKey;

public interface MUserDepartmentsMapper {
    /**
     * deleteByPrimaryKey処理です。
     * @param key key
     * @return int
     */
    int deleteByPrimaryKey(MUserDepartmentsKey key);

    /**
     * insert処理です。
     * @param record record
     * @return int
     */
    int insert(MUserDepartments record);

    /**
     * insertSelective処理です。
     * @param record record
     * @return int
     */
    int insertSelective(MUserDepartments record);

    /**
     * selectByPrimaryKey処理です。
     * @param key key
     * @return MUserDepartments
     */
    MUserDepartments selectByPrimaryKey(MUserDepartmentsKey key);

    /**
     * updateByPrimaryKeySelective処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKeySelective(MUserDepartments record);

    /**
     * updateByPrimaryKey処理です。
     * @param record record
     * @return int
     */
    int updateByPrimaryKey(MUserDepartments record);
}