import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * random test
 * 如果java.util.Random是被一个具体的数字作为随机数种子而实例化的，那么该实例就会以这个随机数种子作为随机算法产生随机数的基础。
 * 如果使用同样的种子实例化的Random对象，每次运行时都将会遵循同一种模式，产生同样的序列。
 * '`'的ASCII码值是96， 第一个随机串生成的数字为：8 5 12 12 15 0  第二个随机串生成的数字为：23 15 18 12 4 0
 * 最后得到的结果
 * 8 + 96 = 104 -->h
 * 5 + 96 = 101 -->e
 * ... 类依次推生成ASCII码表里相应的字母
 * 《计算机伪随机数问题》
 * 随机数在计算机科学和计算机应用中都扮演了非常重要的角色(例如通信，安全，密码等方面)。
 * 但是，由于计算机本身的结构和原理，实际上无法产生出所谓的“绝对随机数”。
 * 也就是说，计算机所产生的随机数，实际上是有某种规律或者模式的“伪随机数”(Pseudo random number)
 * 如何用计算机程序产生高质量随机数，这是计算机理论科学中的一个非常重要的课题。尤其是在复杂的计算环境下的高质量随机数产生，
 * 需要牵涉到非常高深的计算科学和数学方面的理论研究
 * @author subo
 * @create 2018-03-27 20:29
 **/
public class RandomTest extends BaseTest {

    public static void main(String[] args) {
        char tt = '`' + 8;
        System.out.println(tt);
        System.out.println(randomString(-229985452) + " " + randomString(-147909649));
        System.out.println(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
    }

    public static String randomString(int t) {
        Random random = new Random(t);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i= 0;; i++) {
            int k = random.nextInt(27);
            if (k == 0) {
                break;
            }
            stringBuilder.append((char)( '`' + k));
        }
        return stringBuilder.toString();
    }
}
