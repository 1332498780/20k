package cn.hzy.demo.grammer;

import java.util.concurrent.Callable;

public class ExtendDemo {

    public static void main(String[] args){

        new Sneak("ss");

    }

}
class Creature{
    {
        System.out.println("Creature 代码块");
    }

    public Creature(){
        System.out.println("Creature 无参构造方法");
    }

    public Creature(String name){
        this();
        System.out.println("Creature 一个参构造方法");
    }
}

class Animal extends Creature{
    {
        System.out.println("Animal 代码块");
    }

    public Animal(){
        super("name");
        System.out.println("Animal 无参构造方法");
    }

    public Animal(String name,int age){
        this();
        System.out.println("Animal 二个参构造方法");
    }
}

class Sneak extends Animal{
    {
        System.out.println("Sneak 代码块");
    }

    public Sneak(){
        super("123",123);
        System.out.println("Sneak 无个参构造方法");
    }

    public Sneak(String name){
        this();
        System.out.println("Sneak 一个参构造方法");
    }
}


