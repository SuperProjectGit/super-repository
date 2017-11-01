import com.common.quartz.QuartzSpring;
import org.springframework.beans.factory.annotation.Autowired;

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
        System.out.println(3*0.1);
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
        System.out.println(s.equals(s = "1"));

        t(1);
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
