package cn.hzy.demo.util;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class FilesTest {

    public static void main(String[] args) throws IOException, InterruptedException {
//        FilesTest.attributeTest();
        PrivateClass132132.main(new String[]{});
    }

    public static void PathTest(){

        Path path = Paths.get(".");
//        Path indexPath = path.getName(0);
        int nameCount = path.getNameCount();
        Path parent  = path.getParent();
        Path root = path.getRoot();

        Path absolute = path.toAbsolutePath();
        Iterator<Path> iterator = absolute.iterator();
        while(iterator.hasNext()){
            Path next = iterator.next().normalize();
            System.out.println(next.getFileName()+","+next.getNameCount());
        }
        System.out.println(absolute.getName(5));
        System.out.println(absolute.getNameCount());
//        System.out.println(indexPath);
        System.out.println(nameCount);
        System.out.println(parent);
        System.out.println(root);

    }

    public static void FilesTest() throws IOException {

        Path path = Paths.get(".\\src\\cn\\hzy\\demo\\util\\FilesTest.java");

        Files.copy(path,new FileOutputStream("d:\\filelist\\FilesTest.java"));

        System.out.println("是否隐藏"+Files.isHidden(path));

        List<String> lines = Files.readAllLines(path);
        System.out.println("第三行："+lines.get(3));
        System.out.println("文件大小："+Files.size(path));

        List<String> writeList = new ArrayList();
        writeList.add("怅寥廓，问苍茫大地");
        writeList.add("谁主沉浮");
        Path poemPath =Paths.get("d:","filelist","poem.txt");
        Files.write(poemPath,writeList, Charset.forName("GBK"));
        System.out.println("文件大小："+Files.size(poemPath));
    }

    public static void FileVistorTest() throws IOException {

        Path path = Paths.get(".");
        Files.walkFileTree(path,new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException
            {
                System.out.println("开始访问目录："+dir);
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException
            {
                System.out.println(Files.size(file));
                return FileVisitResult.SKIP_SUBTREE;
            }
        });
    }

    public static void watchServiceTest() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Paths.get("d:/").register(watchService
                ,StandardWatchEventKinds.ENTRY_CREATE
                ,StandardWatchEventKinds.ENTRY_DELETE
                ,StandardWatchEventKinds.ENTRY_MODIFY
        );
        while(true){
            WatchKey watchKey = watchService.take();
            for(WatchEvent event:watchKey.pollEvents()){
                Path path = (Path)event;
                System.out.println(path.getNameCount()+" 发生了 "+event.kind());
            }
            boolean isValid = watchKey.reset();
            if(!isValid){
                break;
            }
        }
    }

    public static void attributeTest() throws IOException {

        Path path = Paths.get("d:\\filelist\\poem.txt");
        BasicFileAttributeView basicFileAttributeVie = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicFileAttributeVie.readAttributes();
        System.out.println(basicFileAttributes.isRegularFile());
        System.out.println(basicFileAttributes.size());
        System.out.println(basicFileAttributes.creationTime());
        System.out.println(basicFileAttributes.lastModifiedTime());

        FileOwnerAttributeView fileOwnerAttributeView = Files.getFileAttributeView(path,FileOwnerAttributeView.class);
        System.out.println(fileOwnerAttributeView.getOwner());
        UserPrincipal userPrincipal = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
//        fileOwnerAttributeView.setOwner(userPrincipal);

        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path,UserDefinedFileAttributeView.class);
        List<String> strs = userDefinedFileAttributeView.list();
        for(String str:strs){
            ByteBuffer byteBuffer = ByteBuffer.allocate(userDefinedFileAttributeView.size(str));
            userDefinedFileAttributeView.read(str,byteBuffer);
            byteBuffer.flip();
            System.out.println(str+" -> "+Charset.defaultCharset().decode(byteBuffer));
        }
        //添加一个自定义属性
        userDefinedFileAttributeView.write("我的属性",Charset.defaultCharset().encode("hzy"));

        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path,DosFileAttributeView.class);
        dosFileAttributeView.setHidden(true);
        dosFileAttributeView.setReadOnly(true);
        }
}
