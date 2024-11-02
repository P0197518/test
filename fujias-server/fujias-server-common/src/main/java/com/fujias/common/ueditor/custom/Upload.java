package com.fujias.common.ueditor.custom;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fujias.common.service.NoCreator;
import com.fujias.common.util.DateUtil;
import com.fujias.common.util.FileUtils;

/**
 * Upload
 * 
 * @author fujias
 *
 */
public class Upload {

    /**
     * 上传文件
     * 
     * @param upfile upfile
     * @param path path
     * 
     * @return 文件路径
     * @throws IOException IOException
     * @throws IllegalStateException IllegalStateException
     */
    public static String upload(MultipartFile upfile, String path) throws IllegalStateException, IOException {
        String extension = upfile.getOriginalFilename().substring(upfile.getOriginalFilename().lastIndexOf(".") + 1);
        // 文件名
        String fileName = NoCreator.getUuid() + "." + extension;
        String subDirPath = DateUtil.getNowDateString(DateUtil.DATE_FORMAT6_YEARMONTH) + File.separator;
        String saveDir = path + File.separator + subDirPath;

        // 相对路径
        String relaPath = saveDir + fileName;

        FileUtils.generateDirectoryByFilePath(relaPath);

        File file = new File(relaPath);
        upfile.transferTo(file);

        return fileName + ";" + subDirPath + fileName + ";";

    }
}
