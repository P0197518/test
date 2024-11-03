package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.entity.WordType;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface WordTypeService {

    List<WordType> selectAll();

    WordType selectById(Integer id);
}