package com.haiyoung;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Haiyoung on 2018/1/21.
 */
@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("/haha")
    public String testHi(){
        return "haha gaga";
    }

    public static void main(String[] args){
//        List<String> list = new ArrayList<>();
//        list.add("abc");
//        list.add("aaa");
//        list.add("ccc");
//
//        Iterator<String> it = list.iterator();
//        while(it.hasNext()){
//            if("abc".equals(it.next())){
//                System.out.println(it.next());
////                it.remove();
//
//            }
//        }
//        System.out.println("----------------------------");
//        Iterator<String> it2 = list.iterator();
//        while(it2.hasNext()){
//            System.out.println(it2.next());
//        }

        try {


            //BufferedReader按行读取文件
//            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Think\\Desktop\\Else\\GenericDao.java")));
//            String temp = null;
//            while((temp=br.readLine()) !=null){
//                System.out.println(temp);
//            }

            //BufferedInputStream读取文件
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(
                    new File("C:\\Users\\Think\\Desktop\\Else\\GenericDao.java")));
            byte[] buff=new byte[1024*100];
//            while(bufferedInputStream.available() > 0){
//                System.out.println(bufferedInputStream.read());
//            }
            while(-1 != bufferedInputStream.read(buff,0,buff.length)){
//                    System.out.println(new String(buff));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
