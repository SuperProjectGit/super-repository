package com.common.algorithm;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 长链变短链
 * 可以用MD5 算法对原始链接进行加密（这里使用的MD5 加密后的字符串长度为32 位），
 * 然后对加密后的字符串进行处理以得到短链接的地址。
 * 当我们生成短链接之后，只需要在表中（数据库或者NoSql ）存储原始链接与短链接的映射关系即可。
 * 当我们访问短链接时，只需要从映射关系中找到原始链接，即可跳转到原始链接。
 * 缺点:可能会出现域名冲撞
 * @author subo
 * @create 2018-01-25 15:18
 **/
public class ShortUrl {

    /**
     * 长链转短链
     * @param url
     * @return
     */
    public static List<String> shortUrl(String url) {
        // MD5加密使用的key
        String key = "short";
        // 要生成URL的字符
        String[] chars = {"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        // 对长连接加密生成32位十六进制字符
        String md5Result = DigestUtils.md5Hex(key + url);
        List<String> results = new ArrayList<>();
        for (int i= 0; i< 4; i++) {
            // 把加密字符8位一组十六进制与0x3FFFFFFF 进行位于运算
            String subString = md5Result.substring(i* 8, i* 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long ，则会越界
            long hexLong = 0x3FFFFFFF & Long.parseLong(subString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & hexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                hexLong = hexLong >> 5;
            }
            // 把生成的字符串放入集合中
            results.add(outChars);
        }
        return results;
    }

    public static void main(String[] args) {
        // 长连接
        String longUrl = "http://data.13322.com/basket/team/27_0_1.html";
        List<String> results = shortUrl(longUrl);
        System.out.println(results);
    }

}
