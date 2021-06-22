package cn.hzy.demo.classloader;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 功能：自定义类加载器，可以实现动态加载 *.java源文件并执行这个源文件。
 * 比如有一个Hello.java的文件，我希望程序在运行时可以实现加载这个类，并执行Hello文件的main方法。
 *
 * 为了模拟效果，通过命令行传参执行的方式 来获得待执行的源文件。
 * 执行：java cn.hzy.demo.classloader Hello
 */
public class CompileClassLoader extends ClassLoader{

    public static boolean compile(String filepath) throws ClassNotFoundException {
        File file = new File(filepath);
        if(!file.isFile()){
            throw new ClassNotFoundException(filepath+" is not file");
        }
        try {
            Process process = Runtime.getRuntime().exec("javac "+filepath);
            process.waitFor();
            int ret = process.exitValue();
            return ret == 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public byte[] getFileBytes(File file){
        try(FileInputStream inputStream = new FileInputStream(file)) {
            long length = file.length();
            byte[] bytes = new byte[(int)length];
            int res = inputStream.read(bytes);
            if(res != length){
                System.err.println("无法读出全部文件");
            }
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Deprecated
    public static String exe(String qualityName,String[] args) throws ClassNotFoundException, IOException {

        Process process = Runtime.getRuntime().exec("java "+qualityName,args);
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(process.exitValue()!=0){
            throw new ClassNotFoundException("class 运行失败");
        }
        StringBuilder sb = new StringBuilder();
        try(InputStream inputStream = process.getInputStream()){
            byte[] bytes = new byte[1024];
            int readCounts = -1;
            while((readCounts = inputStream.read(bytes))!= -1){
                sb.append(new String(bytes,0,readCounts,"utf-8"));
            }
        }
        return sb.toString();
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        String packagePath = CompileClassLoader.class.getPackage().getName();
        File javaFile = new File(packagePath.replace(".","\\")+"\\"+className+".java");
        File classFile = new File(packagePath.replace(".","\\")+"\\"+className+".class");

        if(!javaFile.isFile()){
            throw new ClassNotFoundException(javaFile.getAbsolutePath()+" 不存在");
        }

        //class文件不存在或者和源文件在class文件之后又做了修改，就编译原文件
        if(!classFile.isFile() || javaFile.lastModified() > classFile.lastModified()){
            System.out.println("开始编译："+javaFile.getAbsolutePath());
            compile(javaFile.getAbsolutePath());
        }
        byte[] bytes = getFileBytes(classFile);
        System.out.println(bytes.length);
        System.out.println(className);
        //MARK: 如果不加包限定名，执行这句会报：
        // Exception in thread "main" java.lang.NoClassDefFoundError: Hello (wrong name: cn/hzy/demo/classloader/Hello)
        Class targetClass =  defineClass("cn.hzy.demo.classloader."+className,bytes,0,bytes.length);
        if(targetClass == null){
            throw new ClassNotFoundException(className+" 加载失败");
        }
        return targetClass;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if(args.length < 1){
            System.err.println("请输入需要编译的文件");
            return;
        }
        CompileClassLoader compileClassLoader = new CompileClassLoader();

        String className = args[0];
        Class targetClass = compileClassLoader.loadClass(className);

        try {
            String[] params = Arrays.copyOfRange(args,1,args.length);
            Method mainMethod = targetClass.getMethod("main",(new String[0]).getClass());
            //不用object[]来包装params会出现警告
            Object[] argvs = {params};
            mainMethod.invoke(null,argvs);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
