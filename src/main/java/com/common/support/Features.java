package com.common.support;

import com.google.common.collect.Lists;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java8新特性
 *
 * @author subo
 * @create 2017-09-07 18:10
 **/
public class Features {
    public static void main(String[] args) {
        // stream
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.stream().filter(s->list.contains("1")).forEach(s -> System.out.println(s));
        list.stream().filter(s -> s.equals("2")).collect(Collectors.toList()).forEach(s -> System.out.println(s));
        Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13), new Task(Status.CLOSED, 8));
        tasks.forEach(task -> System.out.println(task.toString()));
        tasks.stream().filter(task -> task.getStatus().equals(Status.OPEN) && task.getPoints().equals(5)).collect(Collectors.toList()).forEach(task -> System.out.println(task.toString()));
        long totalPointsOfOpenTasks = tasks.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(Task::getPoints).sum();
        System.out.println("open tasks total points: " + totalPointsOfOpenTasks);
        double totalPointsOfAllTasks = tasks.stream().parallel().mapToInt(Task::getPoints).reduce(0, Integer::sum);
        System.out.println("all tasks total points: " + totalPointsOfAllTasks);
        System.out.println("group by tasks: " + tasks.stream().collect(Collectors.groupingBy(Task::getStatus)));
        System.out.println("result: " + tasks.stream().mapToInt(Task::getPoints).asLongStream().mapToDouble(points -> points / totalPointsOfAllTasks).boxed().mapToLong(weight -> (long)(weight*100)).mapToObj(percentage -> percentage + "%").collect(Collectors.toList()));

        // 使用Stream静态方法来创建Stream
        // 1. of方法：有两个overload方法，一个接受变长参数，一个接口单一值
        Stream<Integer> integerStream = Stream.of(1,2,3,5);
        Stream<String> stringStream = Stream.of("super");
        // 2. generator方法：生成一个无限长度的Stream，其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象）
        // 三条语句的作用都是一样的，只是使用了lambda表达式和方法引用的语法来简化代码。
        // 每条语句其实都是生成一个无限长度的Stream，其中值是随机的。
        // 这个无限长度Stream是懒加载，一般这种无限长度的Stream都会配合Stream的limit()方法来用。
        Stream.generate(new Supplier<Object>() {
            @Override
            public Object get() {
                return Math.random();
            }
        });
        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);
        // 3. iterate方法：也是生成无限长度的Stream，和generator不同的是，
        // 其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。
        // 其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
        // 这段代码就是先获取一个无限长度的正整数集合的Stream，然后取出前10个打印。千万记住使用limit方法，不然会无限打印下去。
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

        // peek: 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数；
        // skip: 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num* 2).peek(System.out::println).skip(2).limit(4).sum());
        /**
         * 对于一个Stream进行多次转换操作，每次都对Stream的每个元素进行转换，而且是执行多次，
         * 这样时间复杂度就是一个for循环里把所有操作都做掉的N（转换的次数）倍啊。
         * 其实不是这样的，转换操作都是lazy的，多个转换操作只会在汇聚(reduce)操作的时候融合起来，一次循环完成。
         * 我们可以这样简单的理解，Stream里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，
         * 在汇聚操作的时候循环Stream对应的集合，然后对每个元素执行所有的函数。
         */

        // optional
        Optional<String> optional = Optional.ofNullable(null);
        System.out.println("optional is set ? " + optional.isPresent());
        System.out.println("optional: " + optional.orElseGet(() -> "[none]"));
        System.out.println(optional.map(s -> "Hey" + s + "!").orElse("Hey Stranger"));

        Optional<String> fullName = Optional.of("Tom");
        System.out.println("Full name is set ? " + fullName.isPresent());
        System.out.println("Full name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey" + s + "!").orElse("Hey Stranger"));

        // Date/Time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.getZone());
        System.out.println(clock.millis());
        System.out.println(sdf.format(clock.millis()));
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------------Date/Time------------------");
        LocalDate localDate = LocalDate.now();
        LocalDate localDateFromClock = LocalDate.now(clock);
        System.out.println(localDate);
        System.out.println(localDateFromClock);

        LocalTime localTime = LocalTime.now();
        LocalTime localTimeFromClock = LocalTime.now(clock);
        System.out.println(localTime);
        System.out.println(localTimeFromClock);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTimeFromClock = LocalDateTime.now(clock);
        System.out.println(localDateTime);
        System.out.println(localDateTimeFromClock);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTimeFromClock = ZonedDateTime.now(clock);
        ZonedDateTime zonedDateTimeFromZone = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTimeFromClock);
        System.out.println(zonedDateTimeFromZone);

        LocalDateTime from = LocalDateTime.of(2016, Month.APRIL, 16, 8, 32, 10);
        LocalDateTime to = LocalDateTime.of(2017, Month.JANUARY, 20, 9, 12, 9);
        Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " +  duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());

        // javascript engine
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");
            System.out.println(scriptEngine.getClass().getName());
            System.out.println("Result: " + scriptEngine.eval("function f(){return 1;} f() + 1;"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        // Base64
        String text = "Base64 finally in Java 8!";
        String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);
        String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(decoded);

        // parallel
        long[] arrayOfLong = new long[20000];
        Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();

        // 接口的默认方法和静态方法
        Defaultable defaultable = DefaultableFactory.create(DefaultableImpl::new);
        System.out.println(defaultable.notRequired());
        Defaultable overridden = DefaultableFactory.create(OverridableImpl::new);
        System.out.println(overridden.notRequired());

        // 方法引用
        // 第一种方法引用是构造器引用，它的语法是Class::new，或者更一般的Class< T >::new。请注意构造器没有参数。
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);
        // 第二种方法引用是静态方法引用，它的语法是Class::static_method。请注意这个方法接受一个Car类型的参数。
        cars.forEach(Car::collide);
        // 第三种方法引用是特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。
        cars.forEach(Car::repair);
        // 第四种方法引用是特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个Car类型的参数
        Car police = Car.create(Car::new);
        cars.forEach(police::follow);

        // 重复注解使用注解的一个限制是相同的注解在同一位置只能声明一次，不能声明多次。Java 8打破了这条规则，引入了重复注解机制，这样相同的注解可以在同一地方声明多次。
        // 重复注解机制本身必须用@Repeatable注解。事实上，这并不是语言层面上的改变，更多的是编译器的技巧，底层的原理保持不变。
        for (Filter filter : filterable.class.getDeclaredAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
        Arrays.stream(filterable.class.getDeclaredAnnotationsByType(Filter.class)).forEach(filter -> System.out.println(filter.value()));

        // 更好的类型推测机制
        Value<String> value = new Value<>();
        //Value.defaultValue()的参数类型可以被推测出，所以就不必明确给出。在Java 7中，相同的例子将不会通过编译，正确的书写方式是 Value.< String >defaultValue()。
        // jdk8
        value.getOrDefault("22", Value.defaultValue());
        // jdk7
        value.getOrDefault("11", Value.<String>defaultValue());

        // 扩展注解的支持
        // Java 8扩展了注解的上下文。现在几乎可以为任何东西添加注解：局部变量、泛型类、父类与接口的实现，就连方法的异常也能添加注解。
        Holder<String> holder = new @notEmpty Holder<>();
        @notEmpty Collection<@notEmpty String> strings = new ArrayList<>();

        // 参数名字
        try {
            Method method = Features.class.getMethod("main", String[].class);
            Arrays.stream(method.getParameters()).forEach(parameter -> System.out.println("Parameter: " + parameter.getName()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    private enum Status {
        /**
         * 打开
         */
        OPEN,

        /**
         * 关闭
         */
        CLOSED
    }

    private static final class Task {

        private Status status;

        private Integer points;

        public Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        public Status getStatus() {
            return status;
        }

        public Integer getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return String.format("[%s,%d]", status, points);
        }
    }

    private interface Defaultable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaultable {

    }

    private static class OverridableImpl implements Defaultable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaultableFactory {
        // Interfaces now allow static methods
        static Defaultable create(Supplier<Defaultable> supplier) {
            return supplier.get();
        }
    }

    private static class Car {
        public static Car create(Supplier<Car> carSupplier) {
            return carSupplier.get();
        }

        public static void collide(Car car) {
            System.out.println("Collide: " + car.toString());
        }

        public void follow(Car car) {
            System.out.println("Follow the: " + car.toString());
        }

        public void repair() {
            System.out.println("Repair: " + this.toString());
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    @Filter("filter1")
    @Filter("filter2")
    public interface filterable {

    }

    private static class Value<T> {
        public static<T> T defaultValue() {
            return null;
        }

        public T getOrDefault(T value, T defaultValue) {
            return (value == null) ? defaultValue : value;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface notEmpty {

    }

    public static class Holder<@notEmpty T> extends @notEmpty Object {
        public void method() throws @notEmpty Exception {

        }
    }
}
