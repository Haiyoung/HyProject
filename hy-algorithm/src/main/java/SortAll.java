import java.util.Arrays;

/**
 * Created by Haiyoung on 2018/8/25.
 */
public class SortAll {

    public static void main(String[] args){
        int[] intArr = new int[]{2,3,1,9,6,8,4,-5,0,11,2};

//        //bubbleSort test
//        System.out.println("冒泡排序"+Arrays.toString(bubbleSort(intArr)));
//
//        //selectionSort test
//        System.out.println("选择排序"+Arrays.toString(selectionSort(intArr)));
//
//        //insertionSort test
//        System.out.println("插入排序"+Arrays.toString(insertionSort(intArr)));
        quickSort(intArr, 0, intArr.length-1);
    }

    //交换数组中的两个元素
    public static void swap(int[] intArr, int i, int j){
        int temp = intArr[i];
        intArr[i] = intArr[j];
        intArr[j] = temp;
    }

    //冒泡排序
    //需要两层循环，第一层循环i表示排序的轮数，第二层循环j表示比较的次数
    //多趟排序，每次将最大值冒到后边 O(n2) O(n2)
    public static int[] bubbleSort(int[] intArr){
        if(intArr.length <= 1){
            return intArr;
        }
        for(int i = 0; i < intArr.length - 1; i++){
            for(int j = 0; j < intArr.length - i - 1; j++){
                if(intArr[j] > intArr[j+1]){
                    swap(intArr, j, j+1);
                }
            }
        }
        return intArr;
    }

    //选择排序
/*　1、从第一个元素开始，分别与后面的元素向比较，找到最小的元素与第一个元素交换位置；

　　2、从第二个元素开始，分别与后面的元素相比较，找到剩余元素中最小的元素，与第二个元素交换；

　　3、重复上述步骤，直到所有的元素都排成由小到大为止*/
    public static int[] selectionSort(int[] intArr){
        if(intArr.length <= 1){
            return intArr;
        }

        for(int i = 0; i<intArr.length - 1;i++){
            int location = i;
            for(int j = location + 1; j<intArr.length;j++ ){
                if(intArr[location] > intArr[j]){
                    location = j;
                }
            }
            if(i != location){
                swap(intArr, i, location);
            }

        }

        return intArr;
    }


    //插入排序
/*　1、将指针指向某个元素，假设该元素左侧的元素全部有序，将该元素抽取出来，然后按照从右往左的顺序分别与其左边的元素比较，遇到比其大的元素便将元素右移，直到找到比该元素小的元素或者找到最左面发现其左侧的元素都比它大，停止；

    2、此时会出现一个空位，将该元素放入到空位中，此时该元素左侧的元素都比它小，右侧的元素都比它大；

　　3、指针向后移动一位，重复上述过程。每操作一轮，左侧有序元素都增加一个，右侧无序元素都减少一个*/
    public static int[] insertionSort(int[] intArr){
        if(intArr.length <= 1){
            return intArr;
        }
        for(int i = 1; i < intArr.length; i++){
            for(int j = i; j > 0; j--){
                if(intArr[j] < intArr[j-1]){
                    swap(intArr, j, j-1);
                }
            }
        }
        return intArr;
    }

    //希尔排序

    //快速排序
    public static void quickSort(int[] intArr, int low, int high){
        if(intArr.length < 2){
            return;
        }
        if(low > high){
            return;
        }
        int i = low;
        int j = high;
        int pivot = intArr[i];
        while(i < j){
            while(i<j && intArr[j] >= pivot){
                j--;
            }
            if(i<j){
                intArr[i] = intArr[j];
            }
            while(i<j && intArr[i] <= pivot){
                i++;
            }
            if(i<j){
                intArr[j] = intArr[i];
            }
        }
        intArr[i] = pivot;
        System.out.println(Arrays.toString(intArr));
        quickSort(intArr, low, i - 1);
        quickSort(intArr, i+1, high);

    }

    //归并排序
}
