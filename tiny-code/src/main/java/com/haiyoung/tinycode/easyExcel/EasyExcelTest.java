package com.haiyoung.tinycode.easyExcel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-12-12 15:19
 * @Version 1.0
 */
public class EasyExcelTest {

    public static void main(String[] args) {

        String fileName = "/Users/haiyoung/Desktop/工时单/审批人对应信息.xlsx";


        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

    }
}
