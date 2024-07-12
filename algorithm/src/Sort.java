import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.Arrays;
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

// 希尔排序 / 缩小增量排序
// 比较相距一定间隔的元素来进行
// 时间复杂度O(n^(1.3-2)), 空间复杂度O(1), 对中等大小规模表现良好，比O(n^2)算法快得多
// @hint 交换不相邻的元素局部排序，最终使用insertionSort排序
// @step example gap = length / 2; recurse = gap = gap / 2; 排序用插入排序
class shellSort{
    static int N = 20;
    static Integer[] arr = new Integer[N];

    public static void sort(Comparable[] arr){
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            // 下面是插入排序，步长为gap, 把 1 => gap 即可
            // 不考虑所有组第一个数字的顺序，从gap开始
            for (int i = gap; i < arr.length; i++){
                // 临时变量，(有的教程空出来arr[0]盛放), 不断比较的数，最后盛放各个组最小的数
                Comparable temp = arr[i];
                // j -= gap 本组不断向前比较，只要小就换
                for (j = i; j > gap && temp.compareTo(arr[j - gap]) < 0; j-= gap){
                    arr[j] = arr[j - gap];
                }
                // 将小的换回来，此时 j 已经是指向较小的坐标，也就是正确的位置
                arr[j] = temp;
            }
        }
    }
    public static void end(){
        arr = func.randomGenerator(N, 100000);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if ((i % 5) == 0)
                System.out.println(" ");
        }
        shellSort.sort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if ((i % 5) == 0)
                System.out.println(" ");
        }
    }
}

// 归并排序
// 建立在归并操作上的一种有效，稳定的算法，采用分治法(Divide and Conquer)典型应用
// 适用：数据量大，对稳定性由要求
// 时间复杂度 O(nlogn) 空间复杂度 O(n)
// @hint 合并两个已经排好序的数组，取两个输入数组A,B，输出数组C，三个计数器i,j,k
// @step 初始位置对应数组开始端
//       A[i] B[j] 中较小的拷贝到C下一个位置，相关计数器前进一步
//       当两个输入数组有一个用完时，将另外一个数组中剩余部分拷贝到C中
// @url  https://segmentfault.com/a/1190000037644412
class mergeSort{
    static int N = 20;
    static Integer[] arr = new Integer[N];

    // 将arr[l...mid]和arr[mid+1...r]两部分进行合并
    private static void recurse(Comparable[] arr){
        if (arr == null || arr.length <= 1){
            return;
        }
        Integer[] newArr = new Integer[arr.length];
        sort(arr, 0, arr.length - 1, newArr);
    }

    public static Comparable[] sort(Comparable[] arr, int left, int right, Comparable[] newArr) {
        // base case
        if (left >= right) {
            return null;
        }
        // 分
        int mid = left + (right - left) / 2;
        // 治
        sort(arr, left, mid, newArr);
        sort(arr, mid + 1, right, newArr);
        // 辅助的 array
        for (int i = left; i <= right; i++) {
            newArr[i] = arr[i];
        }
        // 合
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (newArr[i].compareTo(newArr[j]) <= 0) {
                arr[k++] = newArr[i++];
            } else {
                arr[k++] = newArr[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = newArr[i++];
        }
        return newArr;
    }
    public static void end() {
        arr = func.randomGenerator(N,  100000);

        //打印数组
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if (((i + 1) % 5) == 0)
                System.out.println(" ");
        }
        mergeSort.recurse(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if (((i + 1) % 5) == 0)
                System.out.println(" ");
        }
    }
}
