package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.entity.Gender;
import cn.edu.sgu.www.easyui.service.GenderService;
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
@Api(tags = "性别管理")
@RequestMapping(path = "/gender", produces="application/json; charset=utf-8")
public class GenderController {

    private final GenderService genderService;

    @Autowired
    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @ApiOperation("查询全部性别")
    @RequestMapping(path = "/selectAll", method = RequestMethod.GET)
    public List<Gender> selectAll() {
        return genderService.selectAll();
    }

    @ApiOperation("通过ID查询性别")
    @RequestMapping(path = "/selectById", method = RequestMethod.GET)
    public Gender selectById(@RequestParam Integer id) {
        return genderService.selectById(id);
    }

}