package cn.allbs.utils;

import cn.allbs.constant.ParamConstant;
import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片转为ASCII码
 *
 * @author ChenQi
 */
@UtilityClass
public class ImageToAsciiUtil {

    /**
     * 将图片转为ASCII码并打印
     *
     * @param image BufferedImage
     */
    public void printImage(BufferedImage image) {
        if (image == null) {
            return;
        }
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(j, i);
                Color color = new Color(rgb);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                // 一个用于计算RGB像素点亮度的公式
                double lu = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
                double index = lu / (Math.ceil(255 / (float) ParamConstant.PIXEL.length) + 0.5);
                System.out.print(ParamConstant.PIXEL[(int) (Math.floor(index))]);
            }
            System.out.println();
        }
    }

    /**
     * 缩略图片
     * BufferedImage bufferedImage = ImageToAsciiUtil.makeSmallImage("图片路径", 期望最大高度, 为防止图片扭曲宽度放大比例);
     * ImageToAsciiUtil.printImage(bufferedImage);
     *
     * @param srcImageName 图片路径
     * @return BufferedImage
     */
    public BufferedImage makeSmallImage(String srcImageName) {
        return makeSmallImage(srcImageName, 25, 1);
    }

    /**
     * 缩略图片
     * BufferedImage bufferedImage = ImageToAsciiUtil.makeSmallImage("图片路径", 期望最大高度默认25, 为防止图片扭曲宽度放大比例默认1);
     * ImageToAsciiUtil.printImage(bufferedImage);
     *
     * @param srcImageName  图片路径
     * @param dstMaxSize    预计最大高度
     * @param widthBlowRate 宽度放大比例
     * @return BufferedImage
     */
    public BufferedImage makeSmallImage(String srcImageName, int dstMaxSize, int widthBlowRate) {
        File srcImageFile = new File(srcImageName);
        BufferedImage tagImage = null;
        Image srcImage = null;
        try (FileOutputStream fileOutputStream = null) {
            srcImage = ImageIO.read(srcImageFile);
            // 原图片宽度
            int srcWidth = srcImage.getWidth(null);
            // 原图片高度
            int srcHeight = srcImage.getHeight(null);
            // 缩略图宽度
            int dstWidth = srcWidth;
            // 缩略图高度
            int dstHeight = srcHeight;
            if (srcHeight > dstMaxSize) {
                float rate = (float) dstMaxSize / (float) srcHeight;
                dstHeight = dstMaxSize;
                dstWidth = Math.round((float) dstWidth * rate) * widthBlowRate;
            }
            // 生成缩略图
            tagImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
            tagImage.getGraphics().drawImage(srcImage, 0, 0, dstWidth, dstHeight, null);
            return tagImage;
        } catch (Exception e) {
            System.out.println("转换失败" + e.getLocalizedMessage());
        } finally {
            System.gc();
        }
        return null;
    }
}
