package com.fujias.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 图片操作工具类
 * 
 * @author chenqiang
 *
 */
public class ImageUtil {

    /**
     * 按照 宽高 比例压缩
     * 
     * @param bi bi
     * @param width width
     * @param height height
     * @param filePath filePath
     * @return 执行状态
     * @throws IOException IOException
     * 
     */
    public static boolean thumbnailWithPercent(BufferedImage bi, int width, int height, String filePath) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            thumbnailWithPercent(bi, width, height, outputStream);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
    }

    /**
     * 按照 宽高 比例压缩
     * 
     * @param bi bi
     * @param width width
     * @param height height
     * @param out out
     * @throws IOException IOException
     */
    public static void thumbnailWithPercent(BufferedImage bi, int width, int height, OutputStream out) throws IOException {
        double srcWidth = bi.getWidth(); // 源图宽度
        double srcHeight = bi.getHeight(); // 源图高度

        double scale = 1;

        if (width > 0 && height > 0) {
            scale = height / srcHeight < width / srcWidth ? height / srcHeight : width / srcWidth;
        }

        thumbnail(bi, (int) (srcWidth * scale), (int) (srcHeight * scale), out);
    }

    /**
     * 按照固定宽高原图压缩
     * 
     * @param bufferImage bufferImage
     * @param width width
     * @param height height
     * @param out out
     * @throws IOException IOException
     */
    public static void thumbnail(BufferedImage bufferImage, int width, int height, OutputStream out) throws IOException {
        Image image = bufferImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = tag.getGraphics();
        graphics.setColor(Color.RED);
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        ImageIO.write(tag, "JPEG", out);
    }

    /**
     * 按照宽高裁剪
     * 
     * @param srcImageFile srcImageFile
     * @param destWidth destWidth
     * @param destHeight destHeight
     * @param out out
     */
    public static void cutWithSize(File srcImageFile, int destWidth, int destHeight, OutputStream out) {
        cutWithSize(srcImageFile, 0, 0, destWidth, destHeight, out);
    }

    /**
     * 按照宽高裁剪
     * 
     * @param srcImageFile srcImageFile
     * @param x x
     * @param y y
     * @param destWidth destWidth
     * @param destHeight destHeight
     * @param out out
     */
    public static void cutWithSize(File srcImageFile, int x, int y, int destWidth, int destHeight, OutputStream out) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            BufferedImage bi = ImageIO.read(srcImageFile);
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度

            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);

                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制截取后的图
                g.dispose();
                // 输出为文件
                ImageIO.write(tag, "JPEG", out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
