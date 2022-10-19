package designpattern.behavior.observer;

public class Client {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();
        subject.registerObserver(observerA);
        subject.registerObserver(observerB);
        subject.notifyObservers("hello");

        subject.removeObserver(observerA);
        subject.notifyObservers("hi");
    }
}
