package cn.hzy.demo.grammer;

public class SuperDemo {

    public static void main(String[] args){
        System.out.println(new Student().judge());
    }
}
class Person{

    public Person getThis(){
        return this;
    }
}
class Student extends Person{

    public Person getSuper(){
        return super.getThis();
    }

    public boolean judge(){
        return this == getSuper();
    }
}


