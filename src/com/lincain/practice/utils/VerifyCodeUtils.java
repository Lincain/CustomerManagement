package com.lincain.practice.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeUtils {

    private static int IMAGE_WIDTH = 170;
    private static int IMAGE_HEIGHT = 34;

    /**
     * 生成随机验证码
     * @return
     */
    public static String getVerifyCode(){
        Random random = new Random();
        String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char ch = code.charAt(random.nextInt(code.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 生成随机验证码图片
     * @param verifyCode
     * @return
     */
    public static BufferedImage getVerifyCodeImage(String verifyCode){
        BufferedImage image =
                new BufferedImage(IMAGE_WIDTH,IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB);

        // 获取画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0 ,0, IMAGE_WIDTH, IMAGE_HEIGHT);
        graphics.drawRect(0 ,0, IMAGE_WIDTH - 1,IMAGE_HEIGHT - 1);

        // 画验证码
        Font font = new Font("宋体", Font.BOLD,25);
        graphics.setFont(font);
        graphics.setColor(Color.BLUE);
        for (int i = 0; i < verifyCode.length(); i++) {
            char ch = verifyCode.charAt(i);
            graphics.drawString(ch + "", IMAGE_WIDTH/5*(i + 1), IMAGE_HEIGHT/2 + 10);
        }

        // 画干扰线
        Random random = new Random();
        graphics.setColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(IMAGE_WIDTH);
            int x2 = random.nextInt(IMAGE_WIDTH);
            int y1 = random.nextInt(IMAGE_HEIGHT);
            int y2 = random.nextInt(IMAGE_HEIGHT);

            graphics.drawLine(x1, y1, x2, y2);
        }

        return image;
    }
    
    
    public static String getRandomQq(){
        Random random = new Random();
        String number1 = "123456789";
        String number2 = "1234567890";
        
        StringBuilder sb = new StringBuilder();
        int index1 = random.nextInt(number2.length());
        sb.append(number1.charAt(index1));
        for (int i = 0; i < 8; i++) {
            sb.append(number2.charAt(random.nextInt(number2.length())));
        }

        return sb.toString();
    }
}
