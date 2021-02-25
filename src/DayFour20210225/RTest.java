package DayFour20210225;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class RTest {

    public static void main(String[] args) throws Exception{
        //创建配置文件类
        Properties pro =new Properties();

        //获取配置文件
        ClassLoader classLoader=RTest.class.getClassLoader();

        InputStream is=classLoader.getResourceAsStream("pro.properties");

        pro.load(is);

        //获取配置文件中的值
        String className=pro.getProperty("className");
        String methodName=pro.getProperty("methodName");

        //第一种方式   通过路径
        Class student=Class.forName(className);

        //第二中方式   通过类名
        Class student1=Student.class;

        //第三种方式    通过new
        Student stu=new Student();
        Class student2=stu.getClass();

        Method method=student.getMethod(methodName);

        method.invoke(student.newInstance());
    }
}
