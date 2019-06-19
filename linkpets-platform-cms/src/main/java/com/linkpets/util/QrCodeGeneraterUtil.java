package com.linkpets.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QrCodeGeneraterUtil {


    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    // 图片默认边框颜色
    public final static Color DEFAULT_BORDERCOLOR = Color.BLACK;
    // 图片默认边框宽度
    public static final int DEFAULT_BORDER = 1;

    // 二维码表示的内容
    private static String content = "https://www.memorychilli.com/linkpets/activityplan/index.html";

    // 存放图片的文件夹
    private static String path = "/usr/local/project/acv/img";


    public static InputStream generatePost(String tempPath,String userId) throws IOException, WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        @SuppressWarnings("rawtypes")
        Map hints = new HashMap();

        // 设置UTF-8， 防止中文乱码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置二维码四周白色区域的大小
        hints.put(EncodeHintType.MARGIN, 01);
        // 设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        // width:图片完整的宽;height:图片完整的高
        int width = 152;
        int height = 152;

        // 画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
        BitMatrix bitMatrix = multiFormatWriter.encode(content+"?speUserid="+userId, BarcodeFormat.QR_CODE, width, height, hints);

        // 用来存放生成的二维码图片
        File picFile = new File(path, "pic.png");

        // 开始画二维码
        BufferedImage barCodeImage = writeToFile(bitMatrix, "jpg");

        // 在图片中加入二维码
        BufferedImage image = drawQRCodeToPic(picFile, barCodeImage);

        return saveImage(path + "/"+tempPath, image);

    }



    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static BufferedImage writeToFile(BitMatrix matrix, String format) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        return image;
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage drawQRCodeToPic(File Pic, BufferedImage barCodeImage) {
        try {
            if (!Pic.isFile()) {
                System.out.print("file not find !");
                throw new IOException("file not find !");
            }

            /**
             * 读取Logo图片
             */
            Image src = Toolkit.getDefaultToolkit().getImage(Pic.getPath());
            BufferedImage picImage = toBufferedImage(src);// Image to BufferedImage

            /**
             * 读取二维码图片，并构建绘图对象
             */
            Graphics2D g = picImage.createGraphics();

            // 计算图片放置位置
            int x = (picImage.getWidth() - 152) / 2;
            int y = (picImage.getHeight() - 152) / 2;

            // 开始绘制图片
            g.drawImage(barCodeImage, x, y + 10, 152, 152, null);
            g.dispose();

            return picImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream saveImage(String newImg, BufferedImage image) {

        try {

            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
            out.close();
            System.out.println("image press success");

            InputStream inputStream=new FileInputStream(newImg);
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }
}
