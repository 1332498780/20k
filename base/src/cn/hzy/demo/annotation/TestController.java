package cn.hzy.demo.annotation;

@Journal(module = "测试模块")
public class TestController {

    @Journal(operation = "测试方法")
    public void doSomething(){

        System.out.println("doSomething...");
    }
}
