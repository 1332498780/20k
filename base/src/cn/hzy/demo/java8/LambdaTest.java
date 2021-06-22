package cn.hzy.demo.java8;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

/***
 * lambda使用条件：函数式接口;
 * 简写：可以省略参数的类型；当参数只有一个的时候，可以省略一对括号；方法体只有一句代码时，可以省略一对大括号，有return，也可以一起省略
 *
 * 常用的几个函数式接口：
 * Consumer<T> accept(t)
 * Supplier<T> t get()
 * Function<T,R> t apply(t)
 * Predicate<T> boolean test(t)
 */
public class LambdaTest {

    /***
     * 无参 无返回值
     */
    @Test
    public void test1(){
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("123");
            }
        };
        runnable.run();
        print();
        Runnable runnable1 = () -> System.out.println("234");
        runnable1.run();
    }

    /***
     * 无参有返回值
     */
    @Test
    public void test2(){
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "123";
            }
        };
        System.out.println(supplier.get());
        print();
        Supplier<String> supplier1 =  () -> "234";
        System.out.println(supplier1.get());

    }

    /***
     * 有参无返回值
     */
    @Test
    public void test3(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("123");
        print();
        Consumer<String> consumer1 = s -> System.out.println(s);
        consumer1.accept("234");
    }

    /***
     * 有参 有返回值
     */
    @Test
    public void test4(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator.compare(12,21));
        print();
        Comparator<Integer> comparator1 = (o1,o2) -> o1.compareTo(o2);
        System.out.println(comparator1.compare(12,21));
    }

    /***
     * 方法引用
     * 使用条件：引用方法的参数和返回值与函数式接口的参数和返回值类型一致(除了类::实例方法)
     *
     * 类::静态方法
     * 实例::实例方法
     * 类::实例方法(难理解)
     */
    @Test
    public void test5(){
        //实例::实例方法
        Consumer<String> consumer = System.out::println;
        consumer.accept("123");
    }

    @Test
    public void test6(){
        //类::类方法
        Function<Double,Long> function =  Math::round;
        System.out.println(function.apply(3.14));
    }

    @Test
    public void test7(){
        //类::类方法
        //当形参的第一个参数做为实例对象去执行方法，并把第二个参数做为参数。这时可以用这种写法
        Comparator<Integer> comparator = Integer::compareTo;
        System.out.println(comparator.compare(12,13));
    }

    @Test
    public void test8(){
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test("abc"));
    }

    /***
     * 构造器引用
     */
    @Test
    public void test9(){
        Supplier<Person> supplier = Person::new;
        System.out.println(supplier.get());
    }

    @Test
    public void test10(){
        BiFunction<Integer,String,Person> biFunction = Person::new;
        System.out.println(biFunction.apply(1,"zs"));
    }

    /***
     * 数组引用
     * 第一个参数是数组长度
     */
    @Test
    public void test11(){
        Function<Integer, String[]> biFunction = String[] :: new;
        System.out.println(Arrays.toString(biFunction.apply(10)));
    }

    private void print(){
        System.out.println("*****************");
    }


}

class Person{
    private int id;
    private String name;

    public Person(){}

    public Person(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(o instanceof Person){
            Person p = (Person) o;
            return p.id == id && name.equals(p.name);
        }
        return false;
    }

    @Override
    public String toString(){
        return "[id:"+id+",name:"+name+"]";
    }


}
