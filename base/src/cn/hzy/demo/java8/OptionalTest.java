package cn.hzy.demo.java8;

import org.junit.Test;

import java.util.Optional;

/***
 * of(T t) t必须是非空的
 * ofNullable(T t) t运行是空的
 *
 * get() 必须是非空的
 * orElse(T t) 如果是空，返回一个备胎
 */
public class OptionalTest {

    @Test
    public void test1(){
        Person person = new Person(1,"mm");

        Optional<Person> optionalPerson = Optional.ofNullable(person);
//        Optional.of(person);

        optionalPerson.get();
        optionalPerson.orElse(new Person(2,"mg"));
    }
}
