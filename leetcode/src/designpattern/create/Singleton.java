package designpattern.create;

public class Singleton {
    public static void main(String[] args) {
        System.out.println(String.format("**{}**","我去"));
    }
    private Singleton() {
    }
    private static volatile Singleton singleton;
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
