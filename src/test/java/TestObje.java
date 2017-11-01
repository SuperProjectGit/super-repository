/**
 * 测试
 *
 * @author subo
 * @create 2017-10-31 18:29
 **/
public class TestObje {

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
        TestObje obj = new TestObje();
        obj.tt(obj);
        System.out.println(obj.getId());
    }
}
