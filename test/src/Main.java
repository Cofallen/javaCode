import org.omg.PortableServer.IMPLICIT_ACTIVATION_POLICY_ID;

import java.io.*;

// 同工程文件可以相互引用，public

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        // 创建一个对象
        Class myClass = new Class("sky");

        String[] init = new String[]{"a","b","c","d","e"};
        myClass.setStudent(init);

        String[] get;
        get = myClass.getStudent();

        Dog dogTest = new Dog();

        for (String iota : get){
            System.out.println("get: " + iota);
            System.out.println(dogTest.PI);
            System.out.println(Dog.classVar);
        }
    }
}

// review

