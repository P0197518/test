package com.fujias.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fujias.common.constants.DownloadFileTypes;
import com.fujias.common.form.FileInfoFrom;

/**
 * 文件操作工具类
 * 
 * @author fujias
 */
public class FileUtils {

    private static Logger log = org.apache.log4j.Logger.getLogger(FileUtils.class);
    private static final String CHUNK_SEPARATOR = "-";

    /**
     * 获取扩展名
     * 
     * @param filePath filePath
     * @return 扩展名
     */
    public static String getFileExtension(String filePath) {
        if (StringUtil.isEmpty(filePath) || filePath.indexOf(".") < 0) {
            return "";
        }
        return filePath.substring(filePath.lastIndexOf("."));
    }

    /**
     * 生成文件的保存路径
     * 
     * @param uploadFolder 保存路径
     * @param fileName fileName
     * @return 生成结果
     * @throws IOException IOException
     */
    public static String generatePath(String uploadFolder, String fileName) throws IOException {
        // 判断uploadFolder/identifier 路径是否存在，不存在则创建
        if (!Files.isWritable(Paths.get(uploadFolder))) {
            log.info("path not exist,create path: " + uploadFolder);
            try {
                Files.createDirectories(Paths.get(uploadFolder));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw e;
            }
        }

        return FileUtils.concatPath(uploadFolder, fileName);

    }

    /**
     * 生成文件的保存路径
     * 
     * @param filePath filePath
     * @throws IOException IOException
     */
    public static void generateDirectoryByFilePath(String filePath) throws IOException {

        String directoryPath = filePath.substring(0, filePath.lastIndexOf(File.separator));

        // 判断uploadFolder/identifier 路径是否存在，不存在则创建
        if (!Files.isWritable(Paths.get(directoryPath))) {
            log.info("path not exist,create path: " + directoryPath);
            try {
                Files.createDirectories(Paths.get(directoryPath));

            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw e;
            }
        }
    }

    /**
     * 文件合并
     *
     * @param targetFile targetFile
     * @param folder folder
     * @param filename filename
     * @throws IOException IOException
     */
    public static void merge(String targetFile, String folder, String filename) throws IOException {
        try {

            Files.createFile(Paths.get(targetFile));
            Files.list(Paths.get(folder))
                            .filter(path -> !path.getFileName().toString().equals(filename) && path.getFileName().toString().contains(filename))
                            .sorted((o1, o2) -> {
                                String p1 = o1.getFileName().toString();
                                String p2 = o2.getFileName().toString();
                                int i1 = p1.lastIndexOf(CHUNK_SEPARATOR);
                                int i2 = p2.lastIndexOf(CHUNK_SEPARATOR);
                                return Integer.valueOf(p2.substring(i2)).compareTo(Integer.valueOf(p1.substring(i1)));
                            }).forEach(path -> {
                                try {
                                    // 以追加的形式写入文件
                                    Files.write(Paths.get(targetFile), Files.readAllBytes(path), StandardOpenOption.APPEND);
                                    // 合并后删除该块
                                    Files.delete(path);
                                } catch (IOException e) {
                                    log.error(e.getMessage(), e);
                                }
                            });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 判断文件是否存在
     * 
     * @param path 路径
     * @return 是否存在
     */
    public static boolean fileExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 路径拼接
     * 
     * @param rootPath 根路径
     * @param paths 其他路径
     * @return 拼接后路径
     */
    public static String concatPath(String rootPath, String... paths) {
        StringBuilder sb = new StringBuilder(rootPath);

        for (String item : paths) {
            if (sb.lastIndexOf(File.separator) != sb.length() - 1 && item.indexOf(File.separator) != 0) {
                sb.append(File.separator);
            }
            sb.append(item);
        }

        return sb.toString();
    }

    /**
     * 复制文件或文件夹
     * 
     * @param srcPath srcPath
     * @param destDir 目标文件所在的目录
     * @return 复制结果
     * @throws IOException IOException
     */
    public static boolean copyGeneralFile(String srcPath, String destDir) throws IOException {
        boolean flag = false;
        File file = new File(srcPath);
        if (!file.exists()) {
            System.out.println("源文件或源文件夹不存在!");
            return false;
        }
        if (file.isFile()) { // 源文件
            System.out.println("下面进行文件复制!");
            flag = copyFile(srcPath, destDir);
        } else if (file.isDirectory()) {
            System.out.println("下面进行文件夹复制!");
            flag = copyDirectory(srcPath, destDir);
        }

        return flag;
    }

    /**
     * 移动文件
     * 
     * @param srcPath 源文件
     * @param destPath 目标文件
     * @return 移动结果
     * @throws IOException IOException
     */
    public static boolean moveFile(String srcPath, String destPath) throws IOException {
        File srcFile = new File(srcPath);
        File destFile = new File(destPath);
        FileUtils.generateDirectoryByFilePath(destPath);
        if (destFile.exists()) {
            destFile.delete();
        }
        return srcFile.renameTo(destFile);
    }

    /**
     * 复制文件
     * 
     * @param srcPath 源文件绝对路径
     * @param destDir 目标文件所在目录
     * @return boolean
     * @throws IOException IOException
     */
    private static boolean copyFile(String srcPath, String destDir) throws IOException {
        boolean flag = false;

        File srcFile = new File(srcPath);
        if (!srcFile.exists()) { // 源文件不存在
            System.out.println("源文件不存在");
            return false;
        }
        // 获取待复制文件的文件名
        File file = new File(srcPath);
        String fileName = file.getName();

        File destFile = new File(destDir);
        String destPath = destDir;
        if (destFile.isDirectory()) {
            destPath = destDir + fileName;
        }
        destFile = new File(destPath);

        if (destPath.equals(srcPath)) { // 源文件路径和目标文件路径重复
            System.out.println("源文件路径和目标文件路径重复!");
            return false;
        }
        if (destFile.exists() && destFile.isFile()) { // 该路径下已经有一个同名文件
            System.out.println("目标目录下已有同名文件!");
            return false;
        }

        FileUtils.generateDirectoryByFilePath(destDir);

        try {
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            fis.close();
            fos.close();

            flag = true;
        } catch (IOException e) {
            //
        }

        if (flag) {
            System.out.println("复制文件成功!");
        }

        return flag;
    }

    /**
     * 
     * @param srcPath 源文件夹路径
     * @param destPath 目标文件夹所在目录
     * @return 执行结果
     * @throws IOException IOException
     */
    private static boolean copyDirectory(String srcPath, String destDir) throws IOException {
        System.out.println("复制文件夹开始!");
        boolean flag = false;

        File srcFile = new File(srcPath);
        if (!srcFile.exists()) { // 源文件夹不存在
            System.out.println("源文件夹不存在");
            return false;
        }
        // 获得待复制的文件夹的名字，比如待复制的文件夹为"E://dir"则获取的名字为"dir"
        String dirName = getDirName(srcPath);
        // 目标文件夹的完整路径
        String destPath = destDir + File.separator + dirName;
        // System.out.println("目标文件夹的完整路径为：" + destPath);

        if (destPath.equals(srcPath)) {
            System.out.println("目标文件夹与源文件夹重复");
            return false;
        }
        File destDirFile = new File(destPath);
        if (destDirFile.exists()) { // 目标位置有一个同名文件夹
            System.out.println("目标位置已有同名文件夹!");
            return false;
        }
        destDirFile.mkdirs(); // 生成目录

        File[] fileList = srcFile.listFiles(); // 获取源文件夹下的子文件和子文件夹
        if (fileList.length == 0) { // 如果源文件夹为空目录则直接设置flag为true，这一步非常隐蔽，debug了很久
            flag = true;
        } else {
            for (File temp : fileList) {
                if (temp.isFile()) { // 文件
                    flag = copyFile(temp.getAbsolutePath(), destPath);
                } else if (temp.isDirectory()) { // 文件夹
                    flag = copyDirectory(temp.getAbsolutePath(), destPath);
                }
                if (!flag) {
                    break;
                }
            }
        }

        if (flag) {
            System.out.println("复制文件夹成功!");
        }

        return flag;
    }

    /**
     * 获取待复制文件夹的文件夹名
     * 
     * @param dir dir
     * @return String
     */
    private static String getDirName(String dir) {
        if (dir.endsWith(File.separator)) { // 如果文件夹路径以"//"结尾，则先去除末尾的"//"
            dir = dir.substring(0, dir.lastIndexOf(File.separator));
        }
        return dir.substring(dir.lastIndexOf(File.separator) + 1);
    }

    /**
     * 删除文件或文件夹
     * 
     * @param path 待删除的文件的绝对路径
     * @return boolean
     */
    public static boolean deleteGeneralFile(String path) {
        boolean flag = false;

        File file = new File(path);
        if (!file.exists()) { // 文件不存在
            System.out.println("要删除的文件不存在！");
        }

        if (file.isDirectory()) { // 如果是目录，则单独处理
            flag = deleteDirectory(file.getAbsolutePath());
        } else if (file.isFile()) {
            flag = deleteFile(file);
        }

        if (flag) {
            System.out.println("删除文件或文件夹成功!");
        }

        return flag;
    }

    /**
     * 删除文件
     * 
     * @param file file
     * @return boolean
     */
    private static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 删除目录及其下面的所有子文件和子文件夹，注意一个目录下如果还有其他文件或文件夹 则直接调用delete方法是不行的，必须待其子文件和子文件夹完全删除了才能够调用delete
     * 
     * @param path path为该目录的路径
     */
    private static boolean deleteDirectory(String path) {
        boolean flag = true;
        File dirFile = new File(path);
        if (!dirFile.isDirectory()) {
            return flag;
        }
        File[] files = dirFile.listFiles();
        for (File file : files) { // 删除该文件夹下的文件和文件夹
            // Delete file.
            if (file.isFile()) {
                flag = deleteFile(file);
            } else if (file.isDirectory()) {
                // Delete folder
                flag = deleteDirectory(file.getAbsolutePath());
            }
            if (!flag) { // 只要有一个失败就立刻不再继续
                break;
            }
        }
        flag = dirFile.delete(); // 删除空目录
        return flag;
    }

    /**
     * 下载文件
     * 
     * @param request request
     * @param response response
     * @param fileInfo fileInfo
     * @param type type
     * @throws IOException IOException
     */
    public static void responseFile(HttpServletRequest request, HttpServletResponse response, FileInfoFrom fileInfo, DownloadFileTypes type)
                    throws IOException {

        // 设置文件路径
        File file = new File(fileInfo.getSavePath());
        if (file.exists()) {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            if (DownloadFileTypes.图片展示 == type) {
                response.setHeader("content-type", "image/png");
                response.setContentType("image/png");
            } else {
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/x-download");
            }

            if (!StringUtil.isEmpty(fileInfo.getFileName())) {
                response.setHeader("Content-Disposition", "attachment;filename=" + RequestUtil.getEncodeFileName(fileInfo.getFileName()));
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
