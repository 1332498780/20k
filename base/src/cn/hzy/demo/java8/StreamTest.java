package cn.hzy.demo.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    static List<Person> list;

    static {
        list = new ArrayList<>();
        Person p1 = new Person(1,"普京");
        Person p2 = new Person(2,"吴京");
        Person p3 = new Person(1,"郭京飞");
        Person p4 = new Person(4,"周杰伦");
        Person p5 = new Person(5,"张芸京");
        Person p6 = new Person(6,"张一山");
        Person p7 = new Person(1,"郭京飞");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
    }

    /***
     * 根据集合创建stream
     */
    @Test
    public void test1(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Stream<Integer> stream = list.stream();
        Stream<Integer> stream1 = list.parallelStream();
    }

    /***
     * 根据数组创建stream
     */
    @Test
    public void test2(){
        IntStream stream = Arrays.stream(new int[]{1, 2, 3, 4});
    }

    /***
     * 根据数据创建stream
     */
    @Test
    public void test3(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
    }

    /***
     * 创建stream无限流
     */
    @Test
    public void test4(){
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);
    }

    /***
     * 过滤
     */
    @Test
    public void test5(){
        list.stream().filter(e -> e.getName().contains("京")).forEach(System.out::println);
    }

    /***
     * 去重
     */
    @Test
    public void test6(){
        list.stream().distinct().forEach(System.out::println);
    }



}
