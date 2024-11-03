package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.entity.Word;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.WordService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyunlin
 * @version 1.0
 */
@RestController
@Api(tags = "性别管理")
@RequestMapping(path = "/word", produces="application/json; charset=utf-8")
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ApiOperation("查询全部性别")
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public JsonResult<Void> insert(Word word) {
        wordService.insert(word);

        return JsonResult.success("添加成功");
    }

    @ApiOperation("通过ID查询性别")
    @RequestMapping(path = "/deleteById", method = RequestMethod.POST)
    public JsonResult<Void> deleteById(@RequestParam Integer id) {
        wordService.deleteById(id);

        return JsonResult.success("删除成功");
    }

    @ApiOperation("查询全部性别")
    @RequestMapping(path = "/updateById", method = RequestMethod.POST)
    public JsonResult<Void> updateById(Word word) {
        wordService.updateById(word);

        return JsonResult.success("修改成功");
    }

    @ApiOperation("查询全部性别")
    @RequestMapping(path = "/selectByPage", method = RequestMethod.POST)
    public JsonResult<JsonPage<Word>> selectByPage(Pager<Word> pager) {
        Page<Word> page = wordService.selectByPage(pager);

        return JsonResult.restPage(page);
    }

}