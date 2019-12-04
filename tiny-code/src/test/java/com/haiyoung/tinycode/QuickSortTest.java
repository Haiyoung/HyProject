package com.haiyoung.tinycode;

import java.util.Arrays;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-04 14:08
 * @Version 1.0
 */
public class QuickSortTest {

    public static void quick_sort(int[] intArr, int low, int high){
        if(null  ==  intArr || intArr.length == 0){
            return;
        }
        if(low > high){
            return;
        }
        int i = low;
        int j =  high;
        int temp =  intArr[i];

        while (i < j){

            while(i < j && intArr[j] >= temp){
                j--;
            }
            if(i < j){
                intArr[i] = intArr[j];
            }

            while (i < j && intArr[i] <= temp){
                i++;
            }
            if(i < j){
                intArr[j] =  intArr[i];
            }

            intArr[i] = temp;
        }

        quick_sort(intArr, low, i-1);
        quick_sort(intArr, low+1, high);
    }


    public static void main(String[] args) {
        int[] intArr = {2,4,9,8,7,4,6,-9,5,2,1,3,0,-1};
        System.out.println(Arrays.toString(intArr));
        quick_sort(intArr, 0, intArr.length - 1);
        System.out.println(Arrays.toString(intArr));
    }
}
