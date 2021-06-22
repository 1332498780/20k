package cn.hzy.demo.classloader;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings(value = "unchecked")
@Deprecated
public class ClassObject {

    public String name;

    private int age;

    private ClassObject(){}

    public ClassObject(String info){}

    public ClassObject(String info,int age){}

    public void commonMethod(){}

    private void commonMethod(String str){}

    public int commonMethod(String str,int a){
        return 1;
    }

    static class InnerClass{

    }

    public static void main(String[] args) {
        Class clazz = ClassObject.class;

        //构造器 public
        Constructor[] publicConstructor = clazz.getConstructors();
        for(Constructor c : publicConstructor){
            System.out.println(c.toString());
        }
        //构造器 私有的
        try {
            Constructor privateConstructor = clazz.getDeclaredConstructor();
            System.out.println(privateConstructor.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //方法
        try {
            Method method = clazz.getDeclaredMethod("commonMethod",String.class,int.class);
            System.out.println(method.toGenericString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //属性
        Field[] fields = clazz.getDeclaredFields();
        for(Field f:fields){
            System.out.println(f.getType());
        }

        //注解
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for(Annotation a:annotations){
            System.out.println(a);
        }

        Annotation[] publicAnno = clazz.getAnnotations();
        for (Annotation a:publicAnno){
            System.out.println(a);
        }

        Class[] classes = clazz.getDeclaredClasses();
        for(Class c:classes){
            System.out.println(c);
        }

        try {
            Class parentClazz = Class.forName("cn.hzy.demo.classloader.ClassObject$InnerClass");
            System.out.println(parentClazz.getPackage());
            System.out.println(parentClazz.getSuperclass());
            System.out.println(parentClazz.getDeclaringClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
