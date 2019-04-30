package cn.zucc.etakeout.util;

import java.util.Random;

/**
 * @Date ：Created in 2019/4/30 21:50
 * @Description：值处理工具包
 * @Created By：bing
 */
public class ValueUtil {
    // 多线程
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer randomValue = random.nextInt(9000000 ) + 1000000;
        Long timestamp = System.currentTimeMillis();
        return timestamp + String.valueOf(randomValue);
    }
}
