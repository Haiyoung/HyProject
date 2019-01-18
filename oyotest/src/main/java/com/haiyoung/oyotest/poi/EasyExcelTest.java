package com.haiyoung.oyotest.poi;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.tomcat.util.file.ConfigFileLoader.getInputStream;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-01-17 16:56
 * @Version 1.0
 */
public class EasyExcelTest {

    public static void main(String[] args) throws Exception {



        InputStream inputStream = getInputStream("/Users/haiyoung/Desktop/201902.xlsx");
        try {
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, new AnalysisEventListener() {

                String month;
                @Override
                public void invoke(Object o, AnalysisContext analysisContext) {
                    System.out.println("当前sheet" + analysisContext.getCurrentSheet().getSheetNo() + " 当前行：" + analysisContext.getCurrentRowNum()
                            + " data:" + o);
                    if (0 == analysisContext.getCurrentRowNum()){
                        System.out.println(o);
                        month = o.toString();
                    }else{

                    }

                    System.out.println(analysisContext.getExcelHeadProperty());

                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    System.out.println("----------------------------"+analysisContext.getCurrentRowNum());
                }
            });
            reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
