package com.haiyoung.oyotest.poi;

import org.apache.http.entity.ContentType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author: Haiyang
 * @Date: 2018/12/29 上午11:36
 */
public class PoiExport_2 {

    public static void main(String[] args) throws Exception{

        //读取文件
        File file = new File("/Users/haiyoung/Desktop/（原始素材）酒店业主信息.xlsx");

        File moban = new File("/Users/haiyoung/Desktop/aaa.xlsx");

        FileInputStream fileInputStream = new FileInputStream(moban);
        MultipartFile multipartFile = new MockMultipartFile(moban.getName(), moban.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

        Workbook hssfWorkbook = new XSSFWorkbook(new FileInputStream(file));

        Sheet sheet = hssfWorkbook.getSheetAt(0);

        int total = sheet.getLastRowNum();

        int count = 0;

        while(count*90 < total){
            count++;
            exportExcel(sheet, count);
        }
        exportExcel(sheet,count);
    }

    public static void exportExcel(Sheet sheet, int i) throws Exception{

        File moban = new File("/Users/haiyoung/Desktop/aaa.xlsx");

        FileInputStream fileInputStream = new FileInputStream(moban);
        MultipartFile multipartFile = new MockMultipartFile(moban.getName(), moban.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

        File file_dest = new File("/Users/haiyoung/Desktop/moban/aaa("+i+").xlsx");


        multipartFile.transferTo(file_dest);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(file_dest));
        XSSFSheet sheet_1 = xssfWorkbook.getSheetAt(0);
        for(int j = (i-1)*90; j<i*90; j++){
            Row row = sheet.getRow(j);
            //CRS_ID	酒店名称	城市	业主姓名	业主电话
            if(null != row){
                System.out.println(j);
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

        FileOutputStream fileOutputStream = new FileOutputStream(file_dest);
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

}
