import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.Random; // random generator

public class Sort {
}

class func{
    // 交换两个Comparable, 所有都对象可视为Object类型
    public static void swap(Object[] arr, int a, int b){
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    // 随机Integer生成器
    public static Integer[] randomGenerator(int N, int maxInt){
        Random random = new Random();
        Integer[] arrRandom = new Integer[N];
        for (int i = 0; i < N; i++){
            arrRandom[i] = random.nextInt(maxInt + 1); // 左闭右开
        }
        return arrRandom;
    }
}
// 插入排序
// 少量有效，平均时间复杂度O(n^2),空间O(1)
// 当有序时，最优，比较N-1次，时间复杂度O(N)
// 逆序时，  最坏，O(n^2).
// @hint 选第i个数，依次向前比较，小的话就换 i [0,length-1]
class insertionSort{
    static int N = 20;  // 测试数组长度
    static Integer[] arr = new Integer[N];

    public static Comparable[] sort(Comparable[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++){
            // j > 0 第一个不用比
            for (int j = i; j > 0; j--){
                if (arr[j].compareTo(arr[j - 1]) < 0){
                    func.swap(arr, j, j-1);
                }else {
                    break;  // 前面已经排好序，i++
                }
            }
        }
        return arr;
    }
//    public static Comparable[] sort(Comparable[] arr){
//        int n = arr.length;
//        for (int i = 1; i < n; i++){
//            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--){
//                func.swap(arr, j, j - 1);
//            }
//        }
//        return arr;
//    }
    public static void end(){
        arr = func.randomGenerator(N, 100000);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if ((i + 1) % 5 == 0)
                System.out.println(" ");
        }
        arr = (Integer[]) insertionSort.sort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if ((i + 1) % 5 == 0)
                System.out.println(" ");
        }
    }
}
