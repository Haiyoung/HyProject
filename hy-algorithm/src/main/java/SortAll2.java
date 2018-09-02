/**
 * Created by Haiyoung on 2018/9/2.
 */
public class SortAll2 {

    public void quick_Sort(int[] intArr, int low, int high){
        if(intArr.length < 2){
            return;
        }
        if(low > high){
            return;
        }
        int i = low;
        int j = high;
        int pivot = intArr[i];

        while (i < j){

            while(i < j && intArr[j] >= pivot){
                j--;
            }
            if(i < j){
                intArr[i] = intArr[j];
            }

            while (i < j && intArr[i] <= pivot){
                i++;
            }
            if (i < j){
                intArr[j] = intArr[i];
            }

            intArr[i] = pivot;
        }

        quick_Sort(intArr, low, i-1);
        quick_Sort(intArr, i+1, high);
    }
}
