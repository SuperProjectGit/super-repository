import com.common.concurrent.CommonThreadPool;
import com.common.concurrent.MultiJob;
import org.junit.Test;

import java.awt.*;

/**
 * @author super
 * Create time 2017/12/13 21:45
 */
public class ThreadPoolTest extends BaseTest {

    @Test
    public void test() {
        //CommonThreadPool.asyncExecute(() -> System.out.println("test"));
        CommonThreadPool.submit(() -> {
            System.out.println("submit task");
            Thread.sleep(5000);
            return "submit";
        });
    }

    public static void main(String[] args) {
        MultiJob<String> multiJob = new MultiJob<>();
        multiJob.submit(() -> {
            System.out.println("multi job");
            Thread.sleep(5000);
            return "mutiljob task";
        });
    }
}
