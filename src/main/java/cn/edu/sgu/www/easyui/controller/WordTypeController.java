package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.entity.WordType;
import cn.edu.sgu.www.easyui.service.WordTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author heyunlin
 * @version 1.0
 */
@RestController
@Api(tags = "词语类型管理")
@RequestMapping(path = "/word_type", produces = "application/json;charset=utf-8")
public class WordTypeController {

    private final WordTypeService wordTypeService;

    @Autowired
    public WordTypeController(WordTypeService wordTypeService) {
        this.wordTypeService = wordTypeService;
    }

    @ApiOperation("查询全部词语类型")
    @RequestMapping(path = "/selectAll", method = RequestMethod.GET)
    public List<WordType> selectAll() {
        return wordTypeService.selectAll();
    }

    @ApiOperation("通过ID查询词语类型")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public WordType selectById(@RequestParam Integer id) {
        return wordTypeService.selectById(id);
    }

}