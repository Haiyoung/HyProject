package util;

public class ArrayUtil {

    //交换数组中的两个值
    public static void swap(int[] intArr, int i, int j){
        int temp = intArr[i];
        intArr[i] = intArr[j];
        intArr[j] = temp;
    }
}
