package com.biubiu.shiro.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 图形验证码生成工具类
 *
 * @author baijq
 */
public class CaptchaUtil {

    /**
     * width
     **/
    private static final int WIDTH = 90;
    /**
     * height
     **/
    private static final int HEIGHT = 20;
    /**
     * captcha number on image
     **/
    private static final int CODE_COUNT = 4;
    private static final int FONT_HEIGHT = 18;
    private static final int XX = 20;
    private static final int CODE_Y = 31;
    /**
     * source code
     **/
    private static char[] codeSequence = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static Map<String, Object> generateCodeAndPic(int width, int height, int fontHeight) {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        Random random = new Random();
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        // 随机产生4个数字的验证码。
        for (int i = 0; i < CODE_COUNT; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * XX, CODE_Y);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }

        Map<String, Object> map = new HashMap<>(2);
        map.put("code", randomCode);
        map.put("codePic", buffImg);
        return map;
    }

    public static Map<String, Object> generateCodeAndPic() {
        return generateCodeAndPic(WIDTH, HEIGHT, FONT_HEIGHT);
    }

    /**
     * 测试
     **/
    public static void main(String[] args) throws Exception {
        //创建文件输出流对象
        OutputStream out = new FileOutputStream("D://img/" + System.currentTimeMillis() + ".jpg");
        Map<String, Object> captchaMap = CaptchaUtil.generateCodeAndPic();
        ImageIO.write((RenderedImage) captchaMap.get("codePic"), "jpg", out);
        System.out.println("验证码的值为：" + captchaMap.get("code"));
    }
}
