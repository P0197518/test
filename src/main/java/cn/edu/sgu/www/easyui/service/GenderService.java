package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.entity.Gender;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface GenderService {

    /**
     * 查询全部性别
     * @return List<Gender>
     */
    List<Gender> selectAll();

    /**
     * 通过ID查询性别
     * @param id 性别ID
     * @return Gender
     */
    Gender selectById(Integer id);
}