package search;

public class BinarySearchTest {

    public static void main(String[] args){

        BinarySearchTest test = new BinarySearchTest();
        int[] intArr = {0,1,6,2,3,4,5,6,7,8,9};
//        System.out.println(test.binarySearch(intArr, 6));
//        System.out.println(test.binarySearchRecur(intArr, 6, 0, intArr.length - 1));
        System.out.println(test.firstOccurence(intArr, 6));
    }

    /*工作前提，元素空间没有重复值*/
    //有序数组中二分查找指定值
    public int binarySearch(int[] nums, int target){
        int low = 0, high = nums.length - 1;
        while (low <= high){
                int mid = low + (high - low)/2;
                if(nums[mid] < target){ low = mid + 1;}
                if(nums[mid] > target){ high = mid - 1;}
                if(nums[mid] == target){ return mid;}
        }
        return low;
    }

    /*工作前提，元素空间没有重复值*/
    //递归实现二分查找
    public int binarySearchRecur(int[] nums, int target, int low, int high){
        if(low > high){ return low;}
        int mid = low + (high - low)/2;
        if(nums[mid] < target){
            return binarySearchRecur(nums, target, mid + 1, high);
        }else if(nums[mid] > target){
            return binarySearchRecur(nums,target, low, mid - 1);
        }else{
            return mid;
        }
    }

    public int firstOccurence(int[] nums, int target){
        int low = 0, high = nums.length - 1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if(nums[mid] < target){ low = mid + 1;}
            if(nums[mid] > target){ high = mid - 1;}
        }
        return low;
    }
}
