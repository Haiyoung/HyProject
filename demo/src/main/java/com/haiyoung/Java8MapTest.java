package com.haiyoung;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by Haiyoung on 2018/7/14.
 *
 * java8 中, map新增的默认方法
 */
public class Java8MapTest {
    public static void main(String[] args){
        Map<String, String> map = new HashMap<>();
        map.put("AAA", "111");
        map.put("BBB", "222");
        map.put("NNN", null);
        System.out.println(map);//{AAA=111, BBB=222, NNN=null}

        //BiFunction
        BiFunction<String, String, String> BiF = new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                if(s2 != null){
                    return s +"-"+ s2;
                }else{
                    return "the value is null";
                }
            }
        };

        /**
         * map.merge(K key, V V, BiFunction BiF)
         * 当get(key)不为null时，以get(key), v 作为参数计算返回结果，如果结果为null,则remove(k),反之则替换旧值
         * 当get(key)为 null 时，直接用 v 进行替换
         */

        map.merge("AAA", "999", BiF);
        System.out.println(map);//{AAA=111-999, BBB=222, NNN=null}
        map.merge("NNN", "nnn999", BiF);
        System.out.println(map);//{AAA=111-999, BBB=222, NNN=nnn999}
    }
}
