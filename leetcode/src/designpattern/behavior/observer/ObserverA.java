package designpattern.behavior.observer;

public class ObserverA implements Observer{

    @Override
    public void handle(String msg) {
        System.out.println("ObserverA receive msg = " + msg);
    }
}
