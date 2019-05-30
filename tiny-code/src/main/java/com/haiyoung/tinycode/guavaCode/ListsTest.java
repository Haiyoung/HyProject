package com.haiyoung.tinycode.guavaCode;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-05-30 11:14
 * @Version 1.0
 */
public class ListsTest {


    public static void main(String[] args) {

        List<String> stringList = Lists.newArrayList("1","2","3","4","5","6","7");

        if(!CollectionUtils.isEmpty(stringList)){

            List<List<String>> subList = Lists.partition(stringList, 3);

            for(List<String> item : subList){
                System.out.println(item);
            }
        }
    }
}
