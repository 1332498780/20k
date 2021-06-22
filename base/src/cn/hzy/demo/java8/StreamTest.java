package cn.hzy.demo.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    /***
     * 映射
     */
    @Test
    public void test7(){
        List<String> strs = Arrays.asList("aa","bb","cc","dd");
        strs.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    /***
     * 平铺(流嵌套流)
     * 不好懂
     */
    @Test
    public void test8(){
        List<String> list = new ArrayList();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
//        List child = new ArrayList();
//        child.add(10);
//        child.add(11);
//        child.add(12);
//        child.add(13);
//        list.add(child);

        list.stream().flatMap(StreamTest::characterStream).forEach(System.out::println);
    }
    private static Stream<Character> characterStream(String strs){
        List<Character> res = new ArrayList<>();
        for(Character c:strs.toCharArray()){
            res.add(c);
        }
        return res.stream();
    }

    /***
     * 排序
     */
    @Test
    public void test9(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
//        list.stream().sorted().forEach(System.out::println);
        list.stream().sorted((e1,e2)-> -Integer.compare(e1,e2)).forEach(System.out::println);
    }

    /***
     * 一些终止操作
     */
    @Test
    public void test10(){
        boolean b = list.stream().allMatch(s -> s.getName().contains("京"));
        boolean b1 = list.stream().anyMatch(s -> s.getName().contains("张"));
        boolean b2 = list.stream().noneMatch(s -> s.getName().contains("汪"));
        Optional<Person> b3 = list.stream().findAny();
        Optional<Person> b4 = list.stream().findFirst();

        Optional<Person> min = list.stream().min((p1, p2) -> Integer.compare(p1.getId(), p2.getId()));
        Optional<Person> max = list.stream().max((p1, p2) -> Integer.compare(p1.getId(), p2.getId()));

        list.stream().forEach(System.out::println);
    }

    /***
     * 归约操作
     */
    @Test
    public void test11(){
        System.out.println(list.stream().map(Person::getId).reduce(Integer::sum));
    }

    /***
     * 收集操作
     */
    @Test
    public void test12(){
        System.out.println(list.stream().filter(person -> person.getId()>3).collect(Collectors.toList()));
    }
}
