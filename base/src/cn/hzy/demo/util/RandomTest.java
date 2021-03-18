package cn.hzy.demo.util;

import java.util.Random;

public class RandomTest {

    void noConstruct(){
        for(int i=0;i<5;i++){
            Random random = new Random();
            for(int j=0;j<10;j++){
                System.out.print(random.nextInt(100)+",");
            }
            System.out.println();
        }
    }
    void seedConstruct(){
        for(int i=0;i<5;i++){
            Random random = new Random(100);
            for(int j=0;j<10;j++){
                System.out.print(random.nextInt(100)+",");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        RandomTest rt = new RandomTest();
        rt.noConstruct();
    }
}
