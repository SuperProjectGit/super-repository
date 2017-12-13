import com.common.concurrent.CommonThreadPool;
import org.junit.Test;

/**
 * @author super
 * Create time 2017/12/13 21:45
 */
public class ThreadPoolTest extends BaseTest {

    @Test
    public void test() {
        CommonThreadPool.asyncExecute(() -> System.out.println("test"));
    }
}
