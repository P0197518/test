package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Word;
import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.mapper.WordMapper;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.service.WordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class WordServiceImpl implements WordService {

    private final WordMapper wordMapper;

    @Autowired
    public WordServiceImpl(WordMapper wordMapper) {
        this.wordMapper = wordMapper;
    }

    @Override
    public void insert(Word word) {
        QueryWrapper<Word> wrapper = new QueryWrapper<>();

        wrapper.eq("name", word.getName());

        Long count = wordMapper.selectCount(wrapper);

        if (count > 0) {
            throw new GlobalException(ResponseCode.CONFLICT, "该词语已经存在~");
        } else {
            word.setId(null);

            wordMapper.insert(word);
        }
    }

    @Override
    public void updateById(Word word) {
        wordMapper.updateById(word);
    }

    @Override
    public void deleteById(Integer id) {
        wordMapper.deleteById(id);
    }

    @Override
    public Page<Word> selectByPage(Pager<Word> pager) {
        QueryWrapper<Word> wrapper = Pager.getQueryWrapper(pager, false);
        Page<Word> page = Pager.ofPage(pager);

        return wordMapper.selectPage(page, wrapper);
    }

}