package designpattern.behavior.observer;

public class ObserverB implements Observer{

    @Override
    public void handle(String msg) {
        System.out.println("ObserverB receive msg = " + msg);
    }
}
