import com.alibaba.fastjson.JSONObject;
import com.pojo.User;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

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
        System.out.println(list.stream().filter(t -> t.equals("11")).map(t -> Integer.valueOf(t)).findFirst().orElse(1));
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
        System.out.println(Arrays.asList(1, 3, 2, 4, 2, 3).stream().sorted().distinct().collect(Collectors.toList()));
        User tom = new User();
        tom.setAge("12");
        tom.setName("tom");
        User jerry = new User();
        jerry.setAge("13");
        jerry.setName("jerry");
        User max = new User();
        max.setAge("12");
        max.setName("max");
        User tom1 = new User();
        tom1.setAge("15");
        tom1.setName("tom");
        User jerry1 = new User();
        jerry1.setAge("9");
        jerry1.setName("jerry");
        List<User> users = Arrays.asList(tom, jerry, max, tom1, jerry1);
        System.out.println(users.stream().collect(Collectors.groupingBy(User::getName)));
        System.out.println(users.stream().collect(Collectors.groupingBy(User::getName, Collectors.counting())));
        try {
            Map<String, String> map = BeanUtils.describe(tom);
            map.forEach((k,v) -> System.out.println("key=" + k + " value=" + v));
            Map<String, String> map1 = JSONObject.parseObject(JSONObject.toJSONString(tom), Map.class);
            map1.forEach((k,v) -> System.out.println("key=" + k + " value=" + v));
            BeanMap.create(tom).forEach((k,v) -> System.out.println("key=" + k + " value=" + v));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("============================================");
        Stream.iterate(new int[]{1, 2, 1}, t -> new int[]{t[1] - t[2],t[1] << 1, t[2] << 1}).limit(4).forEach(t -> System.out.println(JSONObject.toJSONString(t)));
        Tax tax1 = Stream.iterate(new Tax(), tax -> {
            tax.setSumSalary(tax.getSumSalary() + tax.getSalary());
            tax.setSumReduced(tax.getSumReduced() + tax.getReduced());
            tax.setTax(tax.getSumSalary() - tax.getSumReduced());
            return tax;
        }).limit(4).max(Comparator.comparingInt(Tax::getSumSalary)).get();
        System.out.println(JSONObject.toJSONString(tax1));
        System.out.println(new BigDecimal("0.210").setScale(2, RoundingMode.HALF_UP).toPlainString());
        System.out.println("======================================");
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");
        System.out.println(list1.removeIf(t -> {return t.equals("5");}));
        System.out.println(list1);
    }

    public static class Tax {
        private int salary = 20;

        private int reduced = 10;

        private int tax;

        private int sumSalary = 20;

        private int sumReduced = 10;

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public int getReduced() {
            return reduced;
        }

        public void setReduced(int reduced) {
            this.reduced = reduced;
        }

        public int getTax() {
            return tax;
        }

        public void setTax(int tax) {
            this.tax = tax;
        }

        public int getSumSalary() {
            return sumSalary;
        }

        public void setSumSalary(int sumSalary) {
            this.sumSalary = sumSalary;
        }

        public int getSumReduced() {
            return sumReduced;
        }

        public void setSumReduced(int sumReduced) {
            this.sumReduced = sumReduced;
        }
    }
}
