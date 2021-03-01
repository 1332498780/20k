package DaySix20210301.Singleton;

public class Singleton2 {

    public static  Singleton2 singleton2=null;

    public Singleton2(){

    }

    //线程安全
    public synchronized static Singleton2 getSingleton2(){
        if (singleton2==null)
            singleton2=new Singleton2();
        return  singleton2;
    }
}
