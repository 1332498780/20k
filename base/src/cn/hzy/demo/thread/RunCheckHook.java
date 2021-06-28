package cn.hzy.demo.thread;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/***
 * 测试shutdown 钩子
 *
 * 说明：这个案例是开发避免程序多次启动的功能。当程序启动创建状态文件，程序退出时，通过shutdown钩子，执行删除状态文件；当状态文件存在，就不能再启动程序。
 */
public class RunCheckHook {

    private static final Path path = Paths.get("C:\\Users\\ZCG04000034\\Desktop\\lock.lock");
    private static final String permission = "rw-------";
    public static void main(String[] args) {
        createRunFile();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            deleteRunFile();
        },"runHook"));

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void createRunFile(){
        File file = path.toFile();
        if(file.exists()){
            System.out.println("程序已经在运行！");
            System.exit(1);
        }
        try {
            Set<PosixFilePermission> param = PosixFilePermissions.fromString(permission);
            Files.createFile(path,PosixFilePermissions.asFileAttribute(param));
            System.out.println("正在启动...");
        } catch (IOException e) {
            System.out.println("启动失败");
            e.printStackTrace();
        }
    }
    private static void deleteRunFile(){
        if(path.toFile().exists()){
            path.toFile().delete();
        }
    }
}

