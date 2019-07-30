import com.common.quartz.QuartzSpring;
import com.pojo.User;
import java.math.BigDecimal;
import java.util.Optional;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Test
 *
 * @author subo
 * @create 2017-09-21 14:30
 **/
public class Test extends BaseTest {

    @Autowired
    private QuartzSpring quartzSpring;

    public static void main(String[] args) {
       /* System.out.println(3*0.1);
        String str = "sfdfsd";
        if (str.contains("s")) {
            System.out.println("contains s");
        } else if (str.contains("f")) {
            System.out.println("contains f");
        } else {
            System.out.println("else");
        }
        int t = 0;
        System.out.println(t != (t = 2));
        System.out.println(1 >> 31);
        System.out.println(t);

        int a = 0;
        int b = 1;
        a = b + (b = a)*0;
        System.out.println("a:" + a + " b:" + b);

        String s = "0";
        System.out.println(s.equals(s = "1"));*/

        //t(1);

        /*String s1 = "sdafjdsmoBiLe";
        System.out.println((s1.toLowerCase()).contains("mobile"));

        Integer t = new Integer(1024);
        int t1 = 1024;
        Integer t2 = new Integer(1024);
        System.out.println(t == t1);
        System.out.println(t == t2);*/

        /*User user = new User();
        user.setAge("12");
        user.setDesc("desc");
        user.setId(1);
        user.setName("name");
        Map map = BeanMap.create(user);
        System.out.println(map);*/

        /*Map<String, Object> map = new HashMap<>();
        MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
        System.out.println("map is assignable from map1:" + map.getClass().isAssignableFrom(map1.getClass()));
        System.out.println("Map is assignable from MultiValueMap:" + Map.class.isAssignableFrom(MultiValueMap.class));
        System.out.println("MultiValueMap is assignable from HashMap:" + MultiValueMap.class.isAssignableFrom(HashMap.class));
        int y = 2;
        int x = 3;
        int z = 4;
        System.out.println(y+=z--/++x);

        int[] aaa = new int[24];
        System.out.println(aaa[23]);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        System.out.println(calendar.getTime());

        System.out.println(3%5);
        System.out.println(3 & (5-1));*/

       /* String temp = "sdfdsdsf%sfdsfsfsdfds%s";
        System.out.println(String.format(temp, 1, 2));

        System.out.println(DigestUtils.md5Hex("zyf668"));*/

        //System.out.println("4.3.10".compareTo("4.3.9"));
        /*String tt = "11,112,113,134,,,";
        System.out.println(ArrayUtils.contains(tt.split(","), "11"));
        System.out.println(new BigDecimal(101).divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_UP));
        System.out.println(StringUtils.isNotBlank(null));*/
        User user = new User();
        user.setName("tom");
        System.out.println(Optional.ofNullable(user).map(value -> value.getName()).orElse("jerry"));
    }

    @org.junit.Test
    public void test() {
        System.out.println("success");
    }

    public static void t(Integer i) {
        Integer t = i;
        t = null;
        System.out.println(t.equals(1));
    }
}
