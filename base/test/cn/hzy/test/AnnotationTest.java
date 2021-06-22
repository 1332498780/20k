package cn.hzy.test;

import cn.hzy.demo.annotation.Journal;
import cn.hzy.demo.annotation.TestController;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {

    @Test
    public void annotationTest(){
        Class<TestController> clazz = TestController.class;
        Journal journal = clazz.getAnnotation(Journal.class);
        System.out.println(journal.module());

        Method[] methods = clazz.getDeclaredMethods();
        for(Method m:methods){
            Annotation[] annotations = m.getDeclaredAnnotations();
            for(Annotation a:annotations){
                if( a.annotationType() == Journal.class ){
                    Journal ope = (Journal)a;
                    System.out.println(ope.operation());
                }
            }
        }
    }
}
