package sort;

import java.util.Arrays;

public class sortTest {


    //快速排序
    public static void quickSort(int[] intArr, int low, int high){
        if(low > high){
            return;
        }
        int i = low;
        int j = high;
        int pivot = intArr[i];
        while(i < j){

            while(i < j && intArr[j] >= pivot){
                j--;
            }
            if(i < j){
                intArr[i] = intArr[j];
            }

            while(i < j && intArr[i] <= pivot){
                i++;
            }
            if(i < j){
                intArr[j] = intArr[i];
            }
        }
        intArr[i] = pivot;

        quickSort(intArr, low, i-1);
        quickSort(intArr, i+1, high);
    }

    public static void main(String[] args){
        int[] intArr = {2,4,9,8,7,4,6,-9,5,2,1,3,0,-1};
//        int[] intArr = {1,3,0,-1};
        System.out.println(Arrays.toString(intArr));
        quickSort(intArr, 0, intArr.length-1);//快排
        System.out.println(Arrays.toString(intArr));
    }
}
