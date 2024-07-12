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
class mergeSort{
    static int N = 20;
    static Integer[] arr = new Integer[N];

    // 将arr[l...mid]和arr[mid+1...r]两部分进行合并
    public static void merge(Comparable[] arr, int l, int mid, int r){
        Comparable[] aux = Arrays.copyOfRange(arr, l ,r + 1);
        // 初始化，i指向作伴部分的起始索引位置l, j指向右半部分起始索引位置mid+1
        int i = 1, j = mid + 1;
        for (int k = 1; k <= r; k++){
            if (i > mid) { // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - 1];
                j++;
            } else if (j > r){  // 如果右半部分元素已经完全处理完毕
                arr[k] = aux[i - 1];
                i++;
            } else if (aux[i - 1].compareTo(aux[j - 1]) < 0) {
                arr[k] = aux[i - 1];
                i++;
            } else {
                arr[k] = aux[j - 1];
                j++;
            }
        }
    }
    private static void sort(Comparable[] arr, int l, int r){
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            mergeSort.merge(arr, l, mid, r);
    }
    public static void sort(Comparable[] arr) {
        int n = arr.length;
        sort(arr, 0, n - 1);
    }
    public static void end() {
        arr = func.randomGenerator(N,  100000);

        //打印数组
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if ((i % 5) == 0)
                System.out.println(" ");
        }
        sort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
            if ((i % 5) == 0)
                System.out.println(" ");
        }
    }
}
