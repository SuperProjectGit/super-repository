import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 集合测试类
 *
 * @author subo
 * @create 2018-03-26 15:23
 **/
public class CollectionsTest extends BaseTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        System.out.println(list.stream().filter(t -> Integer.valueOf(t) > 6).count());
        int size = list.size();
        System.out.println((int)Math.ceil(0.111));
        int times = (int)Math.ceil((double)size/5);
        System.out.println(times);
        System.out.println(list.subList(2,5));
        System.out.println(list);
        for (int i= 0; i< times; i++) {
            System.out.println(list.subList(i*5, (i + 1)*5 > size ? size : (i + 1)*5));
            System.out.println(((i + 1)*5 > size ? size : (i + 1)*5) - i*5);
        }
        String ss = "";
        System.out.println(ArrayUtils.contains(ss.split(","), "12"));
        System.out.println("----------------------------------------------------------");
        String temp = "中国招商银行(0sdfdsf)";
        System.out.println(StringUtils.stripStart(StringUtils.substringBefore(temp, "银行"), "中国"));

        System.out.println("------------------------");
        System.out.println(2 << 3);
        BigDecimal bigDecimal = new BigDecimal(100.23);
        System.out.println(bigDecimal.compareTo(BigDecimal.ZERO));

        int[] x = new int[25];
        System.out.println(x[24]);
    }
}
