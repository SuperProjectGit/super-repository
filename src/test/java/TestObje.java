import com.alibaba.fastjson.JSONObject;
import com.pojo.User;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.SocketHandler;

/**
 * 测试
 *
 * @author subo
 * @create 2017-10-31 18:29
 **/
public class TestObje {

    private final static Logger logger = LoggerFactory.getLogger(TestObje.class);

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void tt(TestObje obje) {
        TestObje t = obje;
        t.setId(11);
        t = null;
        System.out.println("t: " + t);
    }

    public static void main(String[] args) {
        /*TestObje obj = new TestObje();
        obj.tt(obj);
        System.out.println(obj.getId());

        User user = new User();
        user.setId(1);
        BeanMap beanMap = new BeanMap(user);
        beanMap.entrySet().stream().forEach(entry -> System.out.println("key: " + entry.getKey() + " value: " + entry.getValue()));
        beanMap.entrySet().stream().filter(entry -> entry.getValue() != null).forEach(entry -> System.out.println("key: " + entry.getKey() + "value: " + entry.getValue()));

        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", null);
        System.out.println(map);

        String ss = "{\"loanStatus\":[\"AUTHORIZE_CONTACTS\"],\"lendRequestId\":[\"10001563\"],\"userId\":[\"463\"],\"loanId\":[\"835\"]}";
        JSONObject jsonObject = JSONObject.parseObject(ss);
        Boolean t = jsonObject.getBoolean("Bind");
        System.out.println(t);*/
        String s = "safddsfds/user/updateClientVersion";
        System.out.println(s.contains("/user/updateClientVersion"));
        logger.error("dfsfsd", new NullPointerException(null));
        String ss = "4.1.0";
        String temp = "4.1.0";
        System.out.println(ss.compareTo(temp));

        String t1 = "12312*****1dfdsfds";
        System.out.println(t1.indexOf("*"));
        System.out.println(t1.lastIndexOf("*"));
        System.out.println(t1.substring(0, t1.indexOf("*")));
        System.out.println(t1.length() - (t1.lastIndexOf("*") + 1));
        System.out.println(t1.substring(t1.length() - (t1.length() - (t1.lastIndexOf("*") + 1)), t1.length()));
    }
}
