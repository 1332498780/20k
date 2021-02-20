package cn.hzy.demo.grammer;

public class Main {

//    int a = b+1;
//    int b = 1;

//    int static int a = b+1;
//    int static int b = 1;

    int a = 20;
    {
        a = 10;
    }

    public Main(){
        System.out.println("hello");
    }

    public Main(int temp){
        a = temp;
    }

//
    public static void main(String[] args){
        Main main = new Main();
        System.out.println(main.a);
    }
}
