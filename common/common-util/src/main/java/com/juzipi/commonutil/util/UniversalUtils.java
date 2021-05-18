package com.juzipi.commonutil.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 通用工具类
 */
public class UniversalUtils {


    /**
     * 获取随机英文昵称
     * @param length 输入长度
     * @return 随机英文加数字组合
     */
    public static String getEnglishRandomNickname(int length) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }


    /**
     * 获取随机中文昵称
     * @param length 输入长度
     * @return 随机中文字符
     */
    public static String getChineseRandomNickname(int length) {
        StringBuilder randomName = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(hightPos).byteValue());
            b[1] = (Integer.valueOf(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            randomName.append(str);
        }
        return randomName.toString();
    }

}
