package cn.edu.sgu.www.easyui.service;

import cn.edu.sgu.www.easyui.base.Pager;
import cn.edu.sgu.www.easyui.dto.SongDTO;
import cn.edu.sgu.www.easyui.entity.Song;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface SongService {

    void importData(MultipartFile file) throws IOException;

    void export(HttpServletResponse response) throws UnsupportedEncodingException;

    void insert(SongDTO songDTO);

    void deleteById(String id);

    void updateById(SongDTO songDTO);

    Page<Song> selectByPage(Pager<Song> pager);
}