package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.entity.Gender;
import cn.edu.sgu.www.easyui.mapper.GenderMapper;
import cn.edu.sgu.www.easyui.service.GenderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class GenderServiceImpl implements GenderService {

    private final GenderMapper genderMapper;

    public GenderServiceImpl(GenderMapper genderMapper) {
        this.genderMapper = genderMapper;
    }

    @Override
    public List<Gender> selectAll() {
        return genderMapper.selectList(null);
    }

    @Override
    public Gender selectById(Integer id) {
        return genderMapper.selectById(id);
    }

}