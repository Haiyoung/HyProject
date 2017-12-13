package sort;

import util.ArrayUtil;

import java.util.Arrays;

public class InsertionSort{

    //插入排序
    public static void insertSort(int[] intArr){
        int length = intArr.length;
        if(length <= 1){
            return;
        }
        for(int i=1;i<length;i++){
            for(int j = i;j>0;j--){
                if(intArr[j] < intArr[j-1]){
                    ArrayUtil.swap(intArr,j,j-1);
                }
            }
        }
    }

    public static void main(String[] args){
        int[] intArr = {2,4,9,8,7,4,6,-9,5,2,1,3,0,-1};
        System.out.println(Arrays.toString(intArr));
        insertSort(intArr);//选择排序
        System.out.println(Arrays.toString(intArr));
    }
}
