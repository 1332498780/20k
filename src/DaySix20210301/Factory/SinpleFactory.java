package DaySix20210301.Factory;

public class SinpleFactory {

    public static Product getProductInstance(Class cls) throws Exception{
        Product product=null;
        product=(Product) Class.forName(cls.getName()).newInstance();
        return product;
    }

}
