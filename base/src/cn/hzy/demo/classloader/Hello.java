package cn.hzy.demo.classloader;

public class Hello {
    public static void main(String[] args) {
        String line = "";
        for(String str:args){
            line = line + str + ",";
        }
        System.out.println("hello "+line);
    }
}
