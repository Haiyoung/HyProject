package com.haiyoung.hyweb.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Java8_DateUtils {

    //获取当前日期
    public static LocalDate getDate(){
        return LocalDate.now();
    }

    //获取当前日期的前n月
    public static LocalDate getSpecifiedMonth(int n){
        return LocalDate.now().minusMonths(n);
    }

    //获取当前日期的前n天
    public static LocalDate getDaysBefore(int n){
        return LocalDate.now().minusDays(n);
    }

    //获取当前日期的后n天
    public static LocalDate getDaysAfter(int n){
        return LocalDate.now().plusDays(n);
    }

    //获取当前时间
    public static LocalDateTime getTime(){
        return LocalDateTime.now();
    }


    public static void main(String[] args){

//        System.out.println(getDate());
//        System.out.println(getTime().getHour());
//        System.out.println(getDaysBefore(3));
//        System.out.println(getDaysAfter(0));
//        System.out.println(getDaysAfter(5));
//        System.out.println(getSpecifiedMonth(13));
//        System.out.println(getSpecifiedMonth(-13));
//
//        System.out.println(LocalDate.now().isAfter(LocalDate.now()));
//        System.out.println(LocalDate.now().isBefore(LocalDate.now()));
//        System.out.println(LocalDate.now().isEqual(LocalDate.now()));
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

//        int i = 7;
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(Integer.toBinaryString(i >> 1));
//        System.out.println(Integer.toBinaryString(i >>> 1));
//        System.out.println(Integer.toBinaryString(i << 1));

        Date date = new Date();
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        System.out.println(date.toString());


    }

}
