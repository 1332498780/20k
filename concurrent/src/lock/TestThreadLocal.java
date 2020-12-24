package lock;

public class TestThreadLocal {
    static ThreadLocal<Person> tl = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(() -> {
            Person t = new Person();
            t.name = "kht";
            tl.set(t);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Person p = tl.get();
            System.out.println(p);
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person {
        public String name;
    }

}


