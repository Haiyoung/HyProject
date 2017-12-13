package sort;

import util.ArrayUtil;

import java.util.Arrays;

public class SelectionSort {

    //选择排序
    public static void selectSort(int[] intArr){
        int length = intArr.length;
        if(length <= 1){
            return;
        }

        for(int i=0; i<length-1;i++){//第i趟排序
            int k = i;
            for(int j = k+1; j<length;j++){//选出最小的值
                if(intArr[j] < intArr[k]){
                    k = j;
                }
            }

            if(i != k){
                ArrayUtil.swap(intArr,i,k);
            }
        }
    }


    public static void main(String[] args){
        int[] intArr = {2,4,9,8,7,4,6,-9,5,2,1,3,0,-1};
        System.out.println(Arrays.toString(intArr));
        selectSort(intArr);//选择排序
        System.out.println(Arrays.toString(intArr));
    }
}
