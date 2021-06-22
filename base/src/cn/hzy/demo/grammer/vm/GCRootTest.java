package cn.hzy.demo.grammer.vm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GCRootTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        Date date = new Date();

        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.next());

        list = null;
        date = null;

        System.out.println(scanner.next());
    }
}
