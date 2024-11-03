package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.dto.SongDTO;
import cn.edu.sgu.www.easyui.entity.Song;
import cn.edu.sgu.www.easyui.restful.JsonPage;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.service.SongService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author heyunlin
 * @version 1.0
 */
@Api(tags = "歌曲管理")
@RestController
@RequestMapping(path = "/song", produces="application/json;charset=utf-8")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @ApiOperation("从excel导入歌曲")
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public JsonResult<Void> importData(MultipartFile file) throws IOException {
        songService.importData(file);

        return JsonResult.success("导入成功");
    }

    @ApiOperation("导出歌曲列表到excel表格")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        songService.export(response);
    }

    @ApiOperation("添加歌曲")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult<Void> insert(@Validated SongDTO songDTO) {
        songService.insert(songDTO);

        return JsonResult.success("添加成功");
    }

    @ApiOperation("通过ID删除歌曲")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.POST)
    public JsonResult<Void> deleteById(@PathVariable("id") String id) {
        songService.deleteById(id);

        return JsonResult.success("删除成功");
    }

    @ApiOperation("通过ID修改歌曲信息")
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public JsonResult<Void> updateById(@Valid SongDTO songDTO) {
        songService.updateById(songDTO);

        return JsonResult.success("修改成功");
    }

    @ApiOperation("分页查询歌曲列表")
    @RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
    public JsonResult<JsonPage<Song>> selectByPage(Pager<Song> pager) {
        Page<Song> page = songService.selectByPage(pager);

        return JsonResult.restPage(page);
    }

}