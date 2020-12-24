package base;

public class Single {
    private static Single S;
    private Single() {

    }
    public static Single GetSingle() {
        if (S == null) {
            synchronized (Single.class) {
                if (S == null) {
                    S = new Single();
                    return S;
                }
            }
        }

        return S;
    }
    public void A() {
        System.out.println("我是Single");
    }
}

//// 饿汉式
//public class Single {
//    private static final Single S = new Single();
//    private Single() {
//
//    }
//    public static Single GetInstance() {
//        return S;
//    }
//
//    public void A() {
//        System.out.println("我是Single");
//    }
//}
