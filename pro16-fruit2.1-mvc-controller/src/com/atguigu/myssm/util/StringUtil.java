package com.atguigu.myssm.util;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-07-04-[19:43]-周一
 */
public class StringUtil {
    //判断字符串是否为null或者““
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
