package com.haiyoung.oyotest.poi;

import org.apache.http.entity.ContentType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Haiyang
 * @Date: 2018/12/28 下午6:34
 */
public class PoiExport {

    public static void main(String[] args) throws Exception{

        int num = 90;

        //读取文件
        File file = new File("/Users/haiyoung/Desktop/（原始素材）酒店业主信息.xlsx");

        File moban = new File("/Users/haiyoung/Desktop/aaa.xlsx");

        FileInputStream fileInputStream = new FileInputStream(moban);
        MultipartFile multipartFile = new MockMultipartFile(moban.getName(), moban.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

        Workbook hssfWorkbook = new XSSFWorkbook(new FileInputStream(file));

        Sheet sheet = hssfWorkbook.getSheetAt(0);

        File file1;
        FileOutputStream fileOutputStream_1 = null;
        XSSFWorkbook hssfWorkbook_1 = null;
        XSSFSheet sheet_1 = null;

        file1 = new File("/Users/haiyoung/Desktop/moban/aaa.xlsx");

        if(!file1.exists()){
            file1.createNewFile();
        }

        multipartFile.transferTo(file1);

        hssfWorkbook_1 = new XSSFWorkbook(new FileInputStream(file1));

        sheet_1 = hssfWorkbook_1.getSheetAt(0);
        for(int i = 1; i<= 6000; i++){
            Row row = sheet.getRow(i);
            //CRS_ID	酒店名称	城市	业主姓名	业主电话

            if(null != row){
                System.out.println(i);
                Cell CRS_ID = row.getCell(0);
                Cell hotel_name = row.getCell(1);
                Cell city = row.getCell(2);
                Cell owner_name = row.getCell(3);
                Cell owner_tel = row.getCell(4);

                Row row1 = sheet_1.createRow(sheet_1.getLastRowNum()+1);

                if(null == row1){
                    continue;
                }
                row1.createCell(0).setCellValue("");
                row1.createCell(1).setCellValue(null == owner_name?"":owner_name.toString());
                row1.createCell(2).setCellValue(null == owner_tel?"":owner_tel.toString());
                row1.createCell(3).setCellValue("");
                row1.createCell(4).setCellValue(null == hotel_name?"":hotel_name.toString());
                row1.createCell(5).setCellValue("");
                row1.createCell(6).setCellValue(null == hotel_name?"":hotel_name.toString());
                row1.createCell(7).setCellValue("【OYO】酒店业主");

            }
        }

        //输出文件
        fileOutputStream_1 = new FileOutputStream(file1);
        hssfWorkbook_1.write(fileOutputStream_1);
        fileOutputStream_1.flush();
        fileOutputStream_1.close();
    }
}
