import java.io.*; // io

public class Class {
    // 默认构造方法，必须与类同名，可以有多个方法
    public Class(String name){
        // 需要一个参数
        System.out.println("name: " + name);
    }

    int[] grade = new int[5];
    String[] student = new String[5];
    public void setStudent( String[] input){
        student[0] = input[0];
        student[1] = input[1];
        for (int i = 2; i < input.length; i++) {
            student[i] = input[i];
            if (input.length > 5){
                break;
            }
        }
    }

    public String[] getStudent(  ){
        try {
            return student;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

