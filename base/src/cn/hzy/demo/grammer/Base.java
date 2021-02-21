package cn.hzy.demo.grammer;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Base {

    protected int i = 20;

    public Base(){
        System.out.println(this.i);
        display();
    }

    public void display(){
        System.out.println(i);
    }
}
class Child extends Base{

    private int i = 222;
    public Child(){
        this.display();
    }
    public void display(){
        System.out.println(i);
    }

    public static void main(String[] args){
        Child child = new Child();
    }
}
