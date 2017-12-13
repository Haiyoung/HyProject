package sort;

import util.ArrayUtil;

import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] intArr){
        int length = intArr.length;
        if(length <= 1){
            return;
        }

        for(int i = 0; i < length-1; i++){
            for(int j = 0; j<length-1-i; j++){
                if(intArr[j] > intArr[j+1]){
                    ArrayUtil.swap(intArr,j,j+1);
                }
            }

        }
    }

    public static void main(String[] args){
        int[] intArr = {2,4,9,8,7,4,6,-9,5,2,1,3,0,-1};
        System.out.println(Arrays.toString(intArr));
        bubbleSort(intArr);//冒泡排序
        System.out.println(Arrays.toString(intArr));
    }
}
