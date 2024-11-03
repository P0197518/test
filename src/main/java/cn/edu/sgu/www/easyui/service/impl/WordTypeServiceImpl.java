package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.entity.WordType;
import cn.edu.sgu.www.easyui.mapper.WordTypeMapper;
import cn.edu.sgu.www.easyui.service.WordTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class WordTypeServiceImpl implements WordTypeService {

    private final WordTypeMapper wordTypeMapper;

    @Autowired
    public WordTypeServiceImpl(WordTypeMapper wordTypeMapper) {
        this.wordTypeMapper = wordTypeMapper;
    }

    @Override
    public List<WordType> selectAll() {
        return wordTypeMapper.selectList(null);
    }

    @Override
    public WordType selectById(Integer id) {
        return wordTypeMapper.selectById(id);
    }

}