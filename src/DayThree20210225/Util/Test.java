package DayThree20210225.Util;

import java.lang.reflect.Method;

public class Test {

    public static void main(String args[]) throws Exception{
        Class parent=Parent.class;
        Method[] methods=parent.getDeclaredMethods();
        for(Method method : methods){
            boolean methodHasAnno = method.isAnnotationPresent(MyAnnotion.class);
            if(methodHasAnno){
                //得到注解
                MyAnnotion methodAnno = method.getAnnotation(MyAnnotion.class);
                //输出注解属性
                Class item= methodAnno.actionName();

                Object obj=item.newInstance();

                Method excute= item.getMethod("excute");

                excute.invoke(obj);

            }
        }

    }
}
