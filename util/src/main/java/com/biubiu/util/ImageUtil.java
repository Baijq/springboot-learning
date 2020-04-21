package com.biubiu.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ImageUtil
 *
 * @author baijq
 */
public class ImageUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 重命名图片 <br>
     * 1. 规则：六位随机数 + 当前时间
     *
     * @param suffix 后缀
     * @return picName
     */
    public static String generateRandomFileName(String suffix, int width, int height) {
        String randomStr = DATE_FORMAT.format(new Date()) + (int) ((Math.random() * 9 + 1) * 100000);
        if (width == 0 || height == 0) {
            return randomStr + "." + suffix;
        }
        return randomStr + "_" + width + "x" + height + "." + suffix;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名称
     * @return 扩展名
     */
    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    /**
     * 生成缩略图
     *
     * @param fromFileStr 从哪读取文件 eg: D:\test\image\111.jpg
     * @param toFilePath  缩略图保存到哪里 eg: D:\test\image\
     * @param newFileName 缩略图名称，可以为null，系统默认随机生成
     * @param suffix      后缀名 png
     * @param width       宽度 200
     * @param height      高度 200
     */
    public static void generateThumbnail(String fromFileStr, String toFilePath, String newFileName, String suffix, int width, int height) throws Exception {
        File f = new File(fromFileStr);
        if (!f.isFile()) {
            throw new Exception(f + " is not image file error in CreateThumbnail!");
        }

        String fullPathName = (newFileName == null || newFileName.trim().length() == 0) ?
                generateRandomFileName(suffix, width, height) :
                newFileName + "_" + width + "x" + height + "." + suffix;
        File nf = new File(toFilePath, fullPathName);

        //1.读取图片
        BufferedImage buffer = ImageIO.read(f);
        /*
         * 2.核心算法，计算图片的压缩比
         */
        int w = buffer.getWidth();
        int h = buffer.getHeight();

        double ratiox = 1.0d;
        double ratioy = 1.0d;

        ratiox = w * ratiox / width;
        ratioy = h * ratioy / height;
        if (ratiox >= 1) {
            if (ratioy < 1) {
                ratiox = height * 1.0 / h;
            } else {
                if (ratiox > ratioy) {
                    ratiox = height * 1.0 / h;
                } else {
                    ratiox = width * 1.0 / w;
                }
            }
        } else {
            if (ratioy < 1) {
                if (ratiox > ratioy) {
                    ratiox = height * 1.0 / h;
                } else {
                    ratiox = width * 1.0 / w;
                }
            } else {
                ratiox = width * 1.0 / w;
            }
        }
        /*
         * 对于图片的放大或缩小倍数计算完成，ratiox大于1，则表示放大，否则表示缩小
         */
        AffineTransformOp op = new AffineTransformOp(
                AffineTransform.getScaleInstance(ratiox, ratiox), null);
        buffer = op.filter(buffer, null);

        //从放大的图像中心截图
        buffer = buffer.getSubimage((buffer.getWidth() - width) / 2, (buffer.getHeight() - height) / 2, width, height);
        try {
            ImageIO.write(buffer, suffix, nf);
        } catch (Exception ex) {
            throw new Exception(" ImageIo.write error in CreatThum.: " + ex.getMessage());
        }
    }

    /**
     * 图片压缩处理
     *
     * @param toFilePath  C:\\temp\\
     * @param fromFileStr eg： C:\\temp\\pic123.jpg
     * @param width       400
     * @param height      400
     * @throws Exception 异常
     */
    public static void imgCompress(String fromFileStr, String toFilePath, String newFileName, int width, int height) throws Exception {
        //获取后缀名
        String suffix = getFileExtension(fromFileStr);
        newFileName = (newFileName == null || newFileName.trim().length() == 0) ?
                generateRandomFileName(suffix, width, height) :
                newFileName + "_" + width + "x" + height + "." + suffix;

        String toFileFullPath = toFilePath + newFileName;

        File f = new File(fromFileStr);
        if (!f.isFile()) {
            throw new Exception(f + " is not image file error in CreateThumbnail!");
        }
        Image img = ImageIO.read(f);


        resizeFix(img, toFileFullPath, width, height);
    }

    /**
     * 按照宽度还是高度进行压缩
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public static void resizeFix(Image img, String fileNameStr, int w, int h) throws IOException {
        //得到源图宽 img.getWidth(null)  得到源图长 img.getHeight(null)
        if (img.getWidth(null) / img.getHeight(null) > w / h) {
            resizeByWidth(img, fileNameStr, w);
        } else {
            resizeByHeight(img, fileNameStr, h);
        }
    }

    /**
     * 以宽度为基准，等比例放缩图片
     *
     * @param w int 新宽度
     */
    public static void resizeByWidth(Image img, String fileNameStr, int w) throws IOException {
        int h = img.getHeight(null) * w / img.getWidth(null);
        resize(img, fileNameStr, w, h);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     */
    public static void resizeByHeight(Image img, String fileNameStr, int h) throws IOException {
        int w = img.getWidth(null) * h / img.getHeight(null);
        resize(img, fileNameStr, w, h);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     *
     * @param w int 新宽度
     * @param h int 新高度
     */
    public static void resize(Image img, String fileNameStr, int w, int h) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // 绘制缩小后的图
        image.getGraphics().drawImage(img, 0, 0, w, h, null);
        File destFile = new File(fileNameStr);
        // 输出到文件流
        FileOutputStream out = new FileOutputStream(destFile);
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        // JPEG编码
        encoder.encode(image);
        out.close();
    }

//测试
//    public static void main(String[] args) throws Exception {
//        String fileName = "D:\\test\\image\\logo.com.png";
//
//        ImageUtil.generateThumbnail("C:\\Users\\wbbaijq\\Desktop\\picc\\1.jpg",
//                "C:\\Users\\wbbaijq\\Desktop\\picc\\",
//                "",
//                "jpg",
//                200,
//                200
//        );
//
//        ImageUtil.imgCompress(
//                "C:\\Users\\wbbaijq\\Desktop\\picc\\1.jpg",
//                "C:\\Users\\wbbaijq\\Desktop\\picc\\",
//                "",
//                200, 200
//        );
//
//    }
}
