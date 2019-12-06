package com.haiyoung.tinycode.sort;

import java.util.Arrays;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-05 18:25
 * @Version 1.0
 */
public class MergeSort {


    public static void mergeSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int L, int R) {
        if(L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        sort(arr, L, mid);
        sort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int[] temp = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        // 比较左右两部分的元素，哪个小，把那个元素填入temp中
        while(p1 <= mid && p2 <= R) {
            temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 上面的循环退出后，把剩余的元素依次填入到temp中
        // 以下两个while只有一个会执行
        while(p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while(p2 <= R) {
            temp[i++] = arr[p2++];
        }
        // 把最终的排序的结果复制给原数组
        for(i = 0; i < temp.length; i++) {
            arr[L + i] = temp[i];
        }
    }

    public static int[] mergeTwoSortedArray(int[] intArr1, int[] intArr2){
        int m = intArr1.length - 1;
        int n = intArr2.length - 1;
        int length = intArr1.length + intArr2.length - 1;
        int[] intArrMerged = new int[length+1];
        while (m >=  0 || n >= 0){
            if(n <0 || m>=0 && intArr1[m] >= intArr2[n]){
                intArrMerged[length] = intArr1[m];
                --length;
                --m;
            }else{
                intArrMerged[length] = intArr2[n];
                --length;
                --n;
            }
        }
        return intArrMerged;
    }

    public static void main(String[] args) {
        int[]  intArr1 = new int[]{1,2,3,-1,0,2,5,6};
        System.out.println(Arrays.toString(intArr1));
        mergeSort(intArr1);
        System.out.println(Arrays.toString(intArr1));
    }
}
