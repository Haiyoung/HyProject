package sort;

import java.util.Arrays;

public class QuickSort {


    //分治法将数组以枢纽值为中心分为两部分,比枢纽值小的都在左边，比枢纽值大的都在右边
    private static void qSort(int[] intArr, int low, int high){
        if(low > high){
            return;
        }
        int i = low;
        int j = high;
        int pivot = intArr[i];//选取数组的第一个值作为枢纽值
        while(i < j){

            while(i < j && intArr[j] >= pivot){//右边的值不小于pivot时，继续向左边扫描
                j--;
            }
            if(i < j){//满足条件，则将右边比枢纽值小的值与枢纽值交换位置
                intArr[i] = intArr[j];
            }

            while(i < j && intArr[i] <= pivot){//左边的值不小于pivot时，继续向右边扫描
                i++;
            }
            if(i < j){//满足条件，则将左边比枢纽值大的值与枢纽值交换位置
                intArr[j] = intArr[i];
            }
        }
        intArr[i] = pivot;//枢纽值归位

        //递归，对字数组排序
        qSort(intArr, low, i-1);
        qSort(intArr, i+1, high);

    }

    public static void main(String[] args){
        int[] intArr = {2,4,9,8,7,4,6,-9,5,2,1,3,0,-1};
        System.out.println(Arrays.toString(intArr));
        qSort(intArr, 0, intArr.length-1);//快排
        System.out.println(Arrays.toString(intArr));
    }


}
