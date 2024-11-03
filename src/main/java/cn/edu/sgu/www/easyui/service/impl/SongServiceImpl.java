package cn.edu.sgu.www.easyui.service.impl;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.dto.SongDTO;
import cn.edu.sgu.www.easyui.entity.Song;
import cn.edu.sgu.www.easyui.exception.GlobalException;
import cn.edu.sgu.www.easyui.mapper.SongMapper;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import cn.edu.sgu.www.easyui.restful.ResponseCode;
import cn.edu.sgu.www.easyui.service.SongService;
import cn.edu.sgu.www.easyui.util.StringUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heyunlin
 * @version 1.0
 */
@Service
public class SongServiceImpl implements SongService {

    private final SongMapper songMapper;

    @Autowired
    public SongServiceImpl(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    @Override
    public void importData(MultipartFile file) throws IOException {
        // 查询全部歌曲信息
        List<Song> list = songMapper.selectList(null);

        // 把歌曲信息以ID为key保存到map中
        Map<String, Song> map = new HashMap<>(list.size());

        for (Song song : list) {
            map.put(song.getId(), song);
        }

        // 读excel表
        EasyExcel.read(file.getInputStream(), Song.class, new ReadListener<Song>() {
            @Override
            public void invoke(Song data, AnalysisContext context) {
                if (map.containsKey(data.getId())) {
                    songMapper.updateById(data);
                } else {
                    songMapper.insert(data);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        }).sheet().doRead();
    }

    @Override
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = URLEncoder.encode("歌曲列表.xlsx", "utf-8");

        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        try {
            List<Song> songs = songMapper.selectList(null);

            EasyExcel.write(response.getOutputStream(), Song.class).sheet("歌曲列表").doWrite(songs);
        } catch (Exception e) {
            e.printStackTrace();

            response.reset();
            response.setContentType("application/json;charset=utf-8");
            JsonResult<Void> jsonResult = JsonResult.error("数据导出异常");

            try {
                response.getWriter().write(JSON.toJSONString(jsonResult));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void insert(SongDTO songDTO) {
        Song song = new Song();

        song.setId(StringUtils.uuid());
        song.setName(songDTO.getName());
        song.setNote(songDTO.getNote());
        song.setSinger(songDTO.getSinger());

        songMapper.insert(song);
    }

    @Override
    public void deleteById(String id) {
        songMapper.deleteById(id);
    }

    @Override
    public void updateById(SongDTO songDTO) {
        String songId = songDTO.getId();

        if (StringUtils.isEmpty(songId)) {
            throw new GlobalException(ResponseCode.BAD_REQUEST, "歌曲编号不允许为空！");
        }

        Song song = new Song();

        song.setId(songDTO.getId());
        song.setName(songDTO.getName());
        song.setNote(songDTO.getNote());
        song.setSinger(songDTO.getSinger());
        song.setLastUpdateTime(LocalDateTime.now());

        songMapper.updateById(song);
    }

    @Override
    public Page<Song> selectByPage(Pager<Song> pager) {
        QueryWrapper<Song> wrapper = Pager.getQueryWrapper(pager);
        Page<Song> page = Pager.ofPage(pager);

        return songMapper.selectPage(page, wrapper);
    }

}