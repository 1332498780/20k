package jd.link;

public class Outer {
    private int ab = 1;
    private static int sab = 2;

    /**
     * 普通内部类
     */
    public class NormalInnerClass {

//         private static int age = 22;
        private int age = 22; // 不能声明为static

        public NormalInnerClass() {
            // 可以访问外部类静态与非静态成员
            System.out.println(ab);
            System.out.println(sab);
        }
    }

    /**
     * 静态内部类
     */
    public static class StaticInnerClass {
        // 定义静态与非静态成员都是可以的
        private static int age = 22;
        private int age2 = 22;

        public void echo() {
//             System.out.println(ab);
            System.out.println(sab);// 只能访问外部类的静态成员
        }
    }

    public class A extends StaticInnerClass {

    }
}
