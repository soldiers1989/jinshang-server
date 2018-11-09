package project.jinshang.common.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

public class WaterMarkUtils {
    /** 水印文字内容 */
    public static final String MARK_TEXT = "紧商网";
    /** 水印文字类型 */
    public static final String FONT_NAME = "微软雅黑";
    /** 水印文字样式 */
    public static final int FONT_STYLE = Font.BOLD;
    /** 水印文字大小 */
    public static final int FONT_SIZE = 120;// 单位:像素
    /** 水印文字颜色 */
    public static final Color FONT_COLOR = Color.BLACK;
    /** 水印文字位置X轴 */
    public static final int X = 10;
    /** 水印文字位置Y轴 */
    public static final int Y = 10;
    /** 水印文字透明度*/
    public static final float ALPHA = 0.3F;

    /** 水印图片*/


    /**
     * 图片水印服务类，添加文字水印  添加单个文字水印 ,常规工具类
     * @param imageFile
     * @param imageFileName
     * @param uploadPath
     * @param realUploadPath
     * @return
     */
    public static String watermakeOneText(File imageFile, String imageFileName, String uploadPath, String realUploadPath) {

        String logoFileName = "js_" + imageFileName;
        OutputStream os = null;

        try {
            Image image = ImageIO.read(imageFile);
            int width = image.getWidth(null);// 原图宽度
            int height = image.getHeight(null);// 原图高度

            // 创建图片缓存对象
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            // 创建绘绘图工具对象
            Graphics2D g = bufferedImage.createGraphics();
            // 使用绘图工具将原图绘制到缓存图片对象
            g.drawImage(image, 0, 0, width,height,null);

            // 设置水印文字字体信息
            g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
            // 设置水印文字颜色
            g.setColor(FONT_COLOR);

            int markWidth = FONT_SIZE * getTextLength(MARK_TEXT);
            int markHeight = FONT_SIZE;

            // 水印的高度和宽度之差
            int widthDiff = width - markWidth;
            int heightDiff = height - markHeight;

            int x = X;
            int y = Y;

            // 判断设置的值是否大于图片大小
            if(x > widthDiff){
                x = widthDiff;
            }
            if(y > heightDiff){
                y =heightDiff;
            }

            // 设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            // 添加水印
            g.drawString(MARK_TEXT, x, y + FONT_SIZE);

            g.dispose();

            os = new FileOutputStream(realUploadPath + "/" + logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferedImage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return uploadPath + "/" + logoFileName;
    }

    /**
     * 功能：获取文本长度。汉字为1:1，英文和数字为2:1
     */
    private static int getTextLength(String text){
        int length = text.length();
        for(int i = 0 ; i < text.length(); i++){
            String s = String.valueOf(text.charAt(i));
            if(s.getBytes().length > 1){
                length++;
            }
        }
        length = length % 2 == 0 ? length / 2 : length / 2 + 1;
        return length;
    }


    /**
     * 添加单个图片水印 常规工具类
     * @param imageFile
     * @param imageFileName
     * @param uploadPath
     * @param realUploadPath
     * @return
     */
    public static String watermakeOneImage(File imageFile, String imageFileName, String uploadPath, String realUploadPath) {

        String logoFileName = "js_" + imageFileName;
        OutputStream os = null;

        // 图片地址
        //String logoPath = realUploadPath + "/" + LOGO;
        String logoPath = null;
        try {
            Image image = ImageIO.read(imageFile);
            int width = image.getWidth(null);// 原图宽度
            int height = image.getHeight(null);// 原图高度

            // 创建图片缓存对象
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            // 创建绘绘图工具对象
            Graphics2D g = bufferedImage.createGraphics();
            // 使用绘图工具将原图绘制到缓存图片对象
            g.drawImage(image, 0, 0, width,height,null);

            // 读取Logo图片
            File logo = new File(logoPath);
            Image imageLogo = ImageIO.read(logo);

            // 获取Logo图片的宽度和高度
            int markWidth = imageLogo.getWidth(null);
            int markHeight = imageLogo.getHeight(null);

            // 原图和Logo图片的高度和宽度之差
            int widthDiff = width - markWidth;
            int heightDiff = height - markHeight;

            int x = X;
            int y = Y;

            // 判断设置的值是否大于图片大小
            if(x > widthDiff){
                x = widthDiff;
            }
            if(y > heightDiff){
                y =heightDiff;
            }

            // 设置水印透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            // 添加水印
            g.drawImage(imageLogo, x, y, null);

            g.dispose();

            os = new FileOutputStream(realUploadPath + "/" + logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferedImage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return uploadPath + "/" + logoFileName;

    }


    /**
     * 添加单个图片水印 为jinshang项目用的
     * @param imageFile
     * @param imageFileName
     * @return
     */
    public static BufferedImage watermakeOneImage1(File imageFile, String imageFileName, InputStream inputStream) {

        String logoFileName = "js_" + imageFileName;
        OutputStream os = null;
        File file =new File("/tmp/"+logoFileName);
        // 图片地址
        //String logoPath = realUploadPath + "/" + LOGO;
        /*String logoPath = realUploadPath;
        System.out.println("realUploadPath########################"+realUploadPath);*/
        // 创建图片缓存对象
        BufferedImage bufferedImage =null;
        try {
            Image image = ImageIO.read(imageFile);
            int width = image.getWidth(null);// 原图宽度
            int height = image.getHeight(null);// 原图高度
            bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

            // 创建绘绘图工具对象
            Graphics2D g = bufferedImage.createGraphics();
            // 使用绘图工具将原图绘制到缓存图片对象
            g.drawImage(image, 0, 0, width,height,null);

            // 读取Logo图片
            // File logo = new File(logoPath);
            Image imageLogo = ImageIO.read(inputStream);

            // 获取Logo图片的宽度和高度
            int markWidth = imageLogo.getWidth(null);
            int markHeight = imageLogo.getHeight(null);

            // 原图和Logo图片的高度和宽度之差
            int widthDiff = width - markWidth;
            int heightDiff = height - markHeight;

            int x = X;
            int y = Y;

            // 判断设置的值是否大于图片大小
            if(x > widthDiff){
                x = widthDiff;
            }
            if(y > heightDiff){
                y =heightDiff;
            }

            // 设置水印透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            // 添加水印
            g.drawImage(imageLogo, x, y, null);

            g.dispose();

           /*os = new FileOutputStream( "/" + logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferedImage);*/

            String formatName = logoFileName.substring(logoFileName.lastIndexOf(".")+1);

            ImageIO.write(bufferedImage,formatName,file);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(file != null){
                if(file.exists()){
                    file.delete();
                }
            }

        }
        return bufferedImage;

    }


    /**
     * 添加多个文字水印 常规工具类
     * @param imageFile
     * @param imageFileName
     * @param uploadPath
     * @param realUploadPath
     * @return
     */
    public static String watermakeMoreText(File imageFile, String imageFileName, String uploadPath, String realUploadPath) {
        String logoFileName = "js_" + imageFileName;
        OutputStream os = null;

        try {
            Image image = ImageIO.read(imageFile);
            int width = image.getWidth(null);// 原图宽度
            int height = image.getHeight(null);// 原图高度

            // 创建图片缓存对象
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            // 创建绘绘图工具对象
            Graphics2D g = bufferedImage.createGraphics();
            // 使用绘图工具将原图绘制到缓存图片对象
            g.drawImage(image, 0, 0, width,height,null);

            // 设置水印文字字体信息
            g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
            // 设置水印文字颜色
            g.setColor(FONT_COLOR);

            int markWidth = FONT_SIZE * getTextLength(MARK_TEXT);
            int markHeight = FONT_SIZE;

            // 设置水印透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            // 旋转图片
            g.rotate(Math.toRadians(30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);

            int x = -width / 2;
            int y = -height / 2;

            int xmove = 200;// 水印之间的间隔
            int ymove = 200;// 水印之间的间隔

            // 循环添加
            while (x < width * 1.5){
                y = -height / 2;
                while(y < height * 1.5){
                    g.drawString(MARK_TEXT, x, y);

                    y += markHeight + ymove;
                }
                x += markWidth + xmove;
            }

            g.dispose();

            os = new FileOutputStream(realUploadPath + "/" + logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferedImage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return uploadPath + "/" + logoFileName;
    }


    /**
     * 图片水印服务类，添加多个图片水印 常规工具类
     * @param imageFile
     * @param imageFileName
     * @param uploadPath
     * @param realUploadPath
     * @return
     */
    public static String watermakeMoreImage(File imageFile, String imageFileName, String uploadPath, String realUploadPath) {

        String logoFileName = "js_" + imageFileName;
        OutputStream os = null;

        try {
            Image image = ImageIO.read(imageFile);
            int width = image.getWidth(null);// 原图宽度
            int height = image.getHeight(null);// 原图高度

            // 创建图片缓存对象
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            // 创建绘绘图工具对象
            Graphics2D g = bufferedImage.createGraphics();
            // 使用绘图工具将原图绘制到缓存图片对象
            g.drawImage(image, 0, 0, width,height,null);

            // 图片地址
           // String logoPath = realUploadPath + "/" + LOGO;
            String logoPath = null;
            // 读取Logo图片
            File logo = new File(logoPath);
            Image imageLogo = ImageIO.read(logo);

            // Logo图片的宽度和高度
            int markWidth = imageLogo.getWidth(null);
            int markHeight = imageLogo.getHeight(null);

            // 设置水印透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

            // 旋转图片
            g.rotate(Math.toRadians(30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);

            int x = -width / 2;
            int y = -height / 2;

            int xmove = 200;// 水印之间的间隔
            int ymove = 200;// 水印之间的间隔

            // 循环添加
            while (x < width * 1.5){
                y = -height / 2;
                while(y < height * 1.5){
                    // 添加水印
                    g.drawImage(imageLogo, x, y, null);
                    y += markHeight + ymove;
                }
                x += markWidth + xmove;
            }

            g.dispose();

            os = new FileOutputStream(realUploadPath + "/" + logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(bufferedImage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return uploadPath + "/" + logoFileName;

    }




}
