package cn.hzy.demo.grammer.vm;

import java.lang.ref.SoftReference;
import java.util.Arrays;

public class SoftReferenceTest {

    public static class Person{

        public  int age;
        public String name;

        public Person(int age,String str){
            this.age = age;
            this.name = str;
        }
        @Override
        public String toString(){
            return "[age:"+age+",name:"+name+"]";
        }
    }

    public static void main(String[] args) {

        Person person = new Person(11,"张三");
        SoftReference<Person> softReference = new SoftReference<>(person);
        person = null; //去掉强引用，否则有强软2个引用指向，那么软引用不起作用了


        System.gc();
        System.out.println("进行垃圾回收了");
        System.out.println(softReference.get());

        byte[] bytes = new byte[1024*13824 - 652*1024];
        System.gc();
        System.out.println("第二次进行垃圾回收了");
        System.out.println(softReference.get());
    }
}
