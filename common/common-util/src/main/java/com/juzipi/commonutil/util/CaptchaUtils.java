package com.juzipi.commonutil.util;

import java.util.Random;

/**
 * @Author juzipi
 * @Date 2021/4/12 18:05
 * @Info 生成验证码工具类
 */
public class CaptchaUtils {


    /**
     * 获取长度为6的验证码
     * @return 验证码
     */
    public static String getCaptcha6(){
        return captcha(6);
    }

    /**
     * 获取长度为4的验证码
     * @return 验证码
     */
    public static String getCaptcha4(){
        return captcha(4);
    }


    /**
     * 随机验证码
     * @param charCount 长度
     * @return 随机数字验证码
     */
    public static String captcha(int charCount){
        StringBuilder charValue = new StringBuilder();
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue.append(c);
        }
        return charValue.toString();
    }

    /**
     * 随机数
     */
    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }


}
