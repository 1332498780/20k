package cn.hzy.demo.thread.pool;

public class RunnableDenyException extends RuntimeException{

    public RunnableDenyException(String msg){
        super(msg);
    }
}
