package cn.hzy.test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassTest {
    public static void main(String[] args) {

        classLoader();
    }

    public static void bootstrapClassLoader(){
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url:urls){
            System.out.println(url.toExternalForm());
        }
        ClassTest.class.getClassLoader();
    }

    public static void classLoader(){

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("SystemClassLoader:"+systemClassLoader);
        try {
            Enumeration<URL> urls =  systemClassLoader.getResources("");
            while(urls.hasMoreElements()){
                System.out.println(urls.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClassLoader extensionClassLoader = systemClassLoader.getParent();
        System.out.println("ExtensionClassLoader:"+extensionClassLoader);
        System.out.println("扩展加载器的加载路径："+System.getProperty("java.ext.dirs"));
        System.out.println("BootStrapClassLoader:"+extensionClassLoader.getParent());
    }
}
