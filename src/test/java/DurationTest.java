import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * java 8 duration test
 *
 * @author subo
 * @create 2018-12-19 14:55
 **/
public class DurationTest {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        Instant end = instant.plusSeconds(30);
        System.out.println("duration seconds:" + Duration.between(instant, end).getSeconds());
        System.out.println("duration days:" + Duration.between(instant, end).toDays());

        LocalDate today = LocalDate.now();
        LocalDate endDay = LocalDate.of(2018, Month.DECEMBER, 22);
        System.out.println("LocalDate period days:" + Period.between(today, endDay).getDays());
        System.out.println("LocalDate chronounit days:" + ChronoUnit.DAYS.between(today, endDay));

    }

}
