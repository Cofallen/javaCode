// 不同的变量类型

public class Dog{
    String breed;  // 实例变量
    int size;
    String colour;
    int age;

    public final float PI = 3.141592F; // 可引用常量，大写
    static int classVar;  // 类变量，属于类，不是实例，所有实例共享一个类变量的值

    void eat(){
        int variableName = 3; // 局部变量在方法内/语句块内 必须声明后赋值初始化
        // exemple, restore in JAM, automatically recycle.
        int ex;
        ex = 3;
    }
    void run(){
        extra test = new extra();
        System.out.println(test.INT);
        System.out.println(Math.sqrt((double) test.INT));
    }
    void sleep(){

    }
    void name(){

    }
}

class extra{
    protected int INT = 65535; // 常量
}