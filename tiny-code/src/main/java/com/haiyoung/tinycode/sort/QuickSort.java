package com.haiyoung.tinycode.sort;

import java.util.Arrays;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-03 20:42
 * @Version 1.0
 */
public class QuickSort {

    public static void quickSort(int[] intArr, int low, int high) {
        if (null == intArr || intArr.length == 0) {
            return;
        }
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int temp = intArr[i];
        while (i < j) {
            /**
             * 从右往左找到第一个比基准值小的值
             */
            while (i <= j && intArr[j] >= temp) {
                j--;
            }
            if (i < j) {
                intArr[i] = intArr[j];
            }
            System.out.println(Arrays.toString(intArr));

            /**
             * 从左往右找到第一个比基准值大的值
             */
            while (i < j && intArr[i] <= temp) {
                i++;
            }
            if (i < j) {
                intArr[j] = intArr[i];
            }
            intArr[i] = temp;
            System.out.println(Arrays.toString(intArr));
        }

        quickSort(intArr, low, i - 1);
        quickSort(intArr, i + 1, high);
    }

    public static void main(String[] args) {
        int[] intArr = {2,4,9,8,7,4,6,-9,5,2,1,3,0,-1};
        System.out.println(Arrays.toString(intArr));
        quickSort(intArr, 0, intArr.length-1);
//        System.out.println(Arrays.toString(intArr));
    }
}
