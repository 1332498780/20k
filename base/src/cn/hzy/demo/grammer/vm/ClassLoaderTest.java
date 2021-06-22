package cn.hzy.demo.grammer.vm;

import java.net.URL;

public class ClassLoaderTest {


    public static void main(String[] args) {

        //bootstrape
        System.out.println(String.class.getClassLoader());
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL u:urls){
            System.out.println(u);
        }
        //extension
        System.out.println(ClassLoaderTest.class.getClassLoader());
        String exts = System.getProperty("java.ext.dirs");
        for(String str:exts.split(";")){
            System.out.println(str);
        }
        //system/app
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
    }


}
