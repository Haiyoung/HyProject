package com.haiyoung;

/**
 * Created by Haiyoung on 2018/8/23.
 */
public class ArrTest {

    public static void main(String[] args){

        System.out.println(test());
    }

    public static boolean test(){
        int[] intArr = new int[]{1,2,3,4,5,2,6};
        boolean flag = false;
        boolean[] bArr = new boolean[intArr.length+1];
        for (int i = 0; i<intArr.length;i++){
            if(bArr[intArr[i]] == false){
                bArr[intArr[i]] = true;
            }else{
                flag = true;
            }
        }
        return flag;
    }
}
