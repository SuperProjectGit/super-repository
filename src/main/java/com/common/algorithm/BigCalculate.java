package com.common.algorithm;

/**
 * 大整数相加
 * 对于字符串是无法相加的，那就必须转换成整形数组。然后一位一位的相加。
 * 当然我们得考虑进位的情况。ok,那么负数怎么解决的，这里想到了计算机组成里面的运算方法。
 * 计算机是将整数以补码的方式保存的，这就很好的将减法变为了加法。
 * 最后只需要一个加法器就可以进行加减法了（负数相当于减法）。
 * 我们知道计算机的整数二进制保存的，首位是符号位，0代表正，1代表负。
 * 求补码是正数补码等于源码，负数补码等于除符号位外取反后加1.。
 *
 * 10进制补码
 * 这里我们的数是10进制，我们也可以模拟其求补码，
 * 具体方法是：我们符号位用0表示正，9表示负（必须是9）。正数和二进制一样，负数用9-i;最后整个数+1。
 * 正数的补码 = 原码
 * 负数的补码 = {原码符号位不变} + {数值位按位取反后+1}
 * 补码的得来：是为了让负数变成能够加的正数，所以，负数的补码=模-负数的绝对值
 *
 * 模拟加法器
 * 要保证加法器能运行，那么两个数组的长度必须一致，类似于计算机的32位只能和32位相加，而不能兼容64位。
 * 这也保证了首位是符号位。
 * 这样具体实现就很简单了，从最右边开始相加，若有进位就把进位记录下来，下一位相加时把进位加上。
 *
 * 字符串转换为数组
 * 用‘-’代表负数，先处理好‘-’，然后取字符串最大的长度+2（+2的原因是一位保存符号位，一位是用来进位。）。
 * 然后就是字符转换数字了。
 * 结果输出
 * 加法器返回的是补码的数组，那么要输出字符串，先求原码（补码的补码是原码），再处理正负，负显示‘-’。去掉前面的0。
 * @author subo
 * @create 2018-09-03 20:18
 **/
public class BigCalculate {

    /**
     * 加法器，a，b的位数必须相同，首位为符合位，0代表正，9代表负
     * @param a
     * @param b
     * @return
     */
    private static int[] add(int[] a, int[] b) {
        if(a.length<b.length){
            return null;
        }
        int size = a.length;
        int t = 0;//进位
        for (int i = 0; i < size; i++) {
            int k = a[size - i - 1] + b[size - i - 1];
            k+= t;//加进位
            if (k >= 10) {
                t = 1;
            }else{
                t = 0;
            }
            k %= 10;
            a[size - i - 1] = k;
        }
        return a;
    }

    /**
     * 10进制求补码
     * @param a
     * @return
     */
    public static int[] buma(int[] a) {
        if (a[0] == 9) {
            for (int i = 1; i < a.length; i++) {
                a[i] = 9 - a[i];
            }
            int[] tmp  = new int[a.length];
            tmp[a.length-1] = 1;
            return add(a, tmp);
        }else{
            return a;
        }
    }

    /**
     * 标准输出
     * @param a
     * @return
     */
    public static String intToString(int[] a){
        StringBuffer s = new StringBuffer();
        if(a[0]==9){
            s.append('-');
        }else{
            //s.append('0');
        }
        int tag = 0;
        for (int i = 1; i < a.length; i++) {
            if(a[i]==0){
                tag++;
            }else{
                break;
            }
        }
        for (int j = tag+1; j < a.length; j++) {
            s.append(a[j]);
        }
        if(s.length()==0){
            return "0";
        }
        return s.toString();
    }

    /**
     * 大数相加
     * @param a
     * @param b
     * @return
     */
    public static String bigAdd(String a,String b){
        if(a==null || b == null){
            throw new NullPointerException();
        }
        boolean af = true,bf =true;
        if(a.charAt(0)=='-'){
            af = false;
            a = a.substring(1);
        }
        if(b.charAt(0)=='-'){
            bf = false;
            b = b.substring(1);
        }
        int maxsize = a.length()>b.length()?a.length()+2:b.length()+2;
        int[] m = new int[maxsize];
        int[] n = new int[maxsize];
        m[0] = af?0:9;
        n[0] = bf?0:9;
        for (int i = 0; i < a.length(); i++) {
            m[maxsize-a.length()+i] = Integer.parseInt(""+a.charAt(i));
        }
        for (int i = 0; i < b.length(); i++) {
            n[maxsize-b.length()+i] = Integer.parseInt(""+b.charAt(i));
        }
        int[] result = add(buma(m),buma(n));
        return intToString(buma(result));
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        //用字符串表示大数
        String str1 = "-800000000000000000000000000000009";
        String str2 = "800000000000000000000000000000009";
        System.out.println(bigAdd(str1, str2));
    }
}
