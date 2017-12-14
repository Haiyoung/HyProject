package sort;

/**
 * Created by Haiyoung on 2017/12/11.
 */
public class QuickSort_1 {

    //
    public static void quickSort(int[] intArr){
        qSort(intArr, 0, intArr.length - 1);
    }

    //递归排序
    public static void qSort(int[] intArr, int low, int high){
        if(low < high){
            int pivot = partition(intArr, low, high);
            qSort(intArr, low, pivot-1);
            qSort(intArr, pivot+1, high);
        }

    }

    //根据枢纽值，将数组划分为左右两部分，较小的分在左边，较大的分在右边
    public static int partition(int[] intArr, int low, int high){
        int pivot = intArr[low]; //记录枢纽
        while(low < high){

            while(low < high && intArr[high] >= pivot){
                high--;
            }
            intArr[low] = intArr[high];//交换比枢纽小的记录到左端

            while(low < high && intArr[low] <= pivot){
                low++;
            }
            intArr[high] = intArr[low];//交换比枢纽大的记录到右端
        }

        intArr[low] = pivot;//枢纽值放到正确的位置
        return low;//返回枢纽的位置
    }
}
