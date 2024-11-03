package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Word;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface WordService {

    void insert(Word word);

    void updateById(Word word);

    void deleteById(Integer id);

    /**
     * 分页查询词语列表
     * @param pager 分页条件
     * @return Page<Word>
     */
    Page<Word> selectByPage(Pager<Word> pager);
}