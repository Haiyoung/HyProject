package com.haiyoung.oyotest.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Slf4j
public class ExcelUtil {

    /**
     * 未定义的字段
     */
    public static String NO_DEFINE = "no_define";

    /**
     * 默认日期格式
     */
    public static String DEFAULT_DATE_PATTERN = "yyyy年MM月dd日";

    /**
     * 默认列宽
     */
    public static int DEFAULT_COLOUMN_WIDTH = 17;

    /**
     * 导出Excel 97(.xls)格式 ，少量数据
     *
     * @param title       标题行
     * @param headMap     属性-列名
     * @param jsonArray   数据集
     * @param datePattern 日期格式，null则用默认日期格式
     * @param colWidth    列宽 默认 至少17个字节
     * @param out         输出流
     */
    public static void exportExcel(String title, Map<String, String> headMap, JSONArray jsonArray, String datePattern, int colWidth, OutputStream out) {
        if (datePattern == null) {
            datePattern = DEFAULT_DATE_PATTERN;
        }

        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        workbook.createInformationProperties();

        // workbook.getDocumentSummaryInformation().setCompany("*****公司");
        // SummaryInformation si = workbook.getSummaryInformation();
        // si.setAuthor("JACK");  //填加xls文件作者信息
        // si.setApplicationName("导出程序"); //填加xls文件创建程序信息
        // si.setLastAuthor("最后保存者信息"); //填加xls文件最后保存者信息
        // si.setComments("JACK is a programmer!"); //填加xls文件作者信息
        // si.setTitle("POI导出Excel"); //填加xls文件标题信息
        // si.setSubject("POI导出Excel");//填加文件主题信息
        // si.setCreateDateTime(new Date());

        //表头样式
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 22);
        titleFont.setBoldweight((short) 700);
        titleStyle.setFont(titleFont);

        // 列头样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 20);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);

        // 单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);

        // 生成一个(带标题)表格
        HSSFSheet sheet = workbook.createSheet();
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("JACK");
        //设置列宽
        int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;//至少字节数
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter
                .hasNext(); ) {
            String fieldName = iter.next();

            properties[ii] = fieldName;
            headers[ii] = fieldName;

            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
            ii++;
        }
        // 遍历集合数据，产生数据行
        int rowIndex = 0;
        for (Object obj : jsonArray) {
            if (rowIndex == 65535 || rowIndex == 0) {
                if (rowIndex != 0) {
                    sheet = workbook.createSheet();//如果数据超过了，则在第二页显示
                }

                HSSFRow titleRow = sheet.createRow(0);//表头 rowIndex=0
                titleRow.createCell(0).setCellValue(title);
                titleRow.getCell(0).setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));

                HSSFRow headerRow = sheet.createRow(1); //列头 rowIndex =1
                for (int i = 0; i < headers.length; i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                    headerRow.getCell(i).setCellStyle(headerStyle);

                }
                rowIndex = 2;//数据内容从 rowIndex=2开始
            }
            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            HSSFRow dataRow = sheet.createRow(rowIndex);
            for (int i = 0; i < properties.length; i++) {
                HSSFCell newCell = dataRow.createCell(i);

                Object o = jo.get(properties[i]);
                String cellValue;
                if (o == null) {
                    cellValue = "";
                } else if (o instanceof Date) {
                    cellValue = new SimpleDateFormat(datePattern).format(o);
                } else {
                    cellValue = o.toString();
                }

                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cellStyle);
            }
            rowIndex++;
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出Excel 2007 OOXML (.xlsx)格式
     *
     * @param title       标题行
     * @param headMap     属性-列头
     * @param jsonArray   数据集
     * @param datePattern 日期格式，传null值则默认 年月日
     * @param colWidth    列宽 默认 至少17个字节
     * @param out         输出流
     */
    public static void exportExcelX(String title, Map<String, String> headMap, JSONArray jsonArray, String datePattern, int colWidth, OutputStream out) {
        if (datePattern == null) {
            datePattern = DEFAULT_DATE_PATTERN;
        }

        // 声明一个工作薄 缓存
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        workbook.setCompressTempFiles(true);

        //表头样式
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 22);
        titleFont.setBoldweight((short) 700);
        titleStyle.setFont(titleFont);

        // 列头样式
        CellStyle headerStyle = createCellStyle(workbook, 0);

        // 单元格样式
        CellStyle cellStyle = createCellStyle(workbook, 1);

        // 生成一个(带标题)表格
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
        //设置列宽 至少字节数
        int minBytes = colWidth < DEFAULT_COLOUMN_WIDTH ? DEFAULT_COLOUMN_WIDTH : colWidth;
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter
                .hasNext(); ) {
            String fieldName = iter.next();

            properties[ii] = fieldName;
            headers[ii] = headMap.get(fieldName);

            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
            ii++;
        }

        // 遍历集合数据，产生数据行
        int rowIndex = 0;
        for (Object obj : jsonArray) {
            if (rowIndex == 65535 || rowIndex == 0) {
                if (rowIndex != 0) {
                    //如果数据超过了，则在第二页显示
                    sheet = (SXSSFSheet) workbook.createSheet();
                }

                //表头 rowIndex=0
                SXSSFRow titleRow = (SXSSFRow) sheet.createRow(0);
                titleRow.createCell(0).setCellValue(title);
                titleRow.getCell(0).setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));

                //列头 rowIndex =1
                SXSSFRow headerRow = (SXSSFRow) sheet.createRow(1);
                for (int i = 0; i < headers.length; i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                    headerRow.getCell(i).setCellStyle(headerStyle);

                }
                //数据内容从 rowIndex=2开始
                rowIndex = 2;
            }
            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            SXSSFRow dataRow = (SXSSFRow) sheet.createRow(rowIndex);
            for (int i = 0; i < properties.length; i++) {
                SXSSFCell newCell = (SXSSFCell) dataRow.createCell(i);

                Object o = jo.get(properties[i]);
                String cellValue =parseObject(o, datePattern);

                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cellStyle);
            }
            rowIndex++;
        }
        // 自动调整宽度
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        dispose(workbook, out);
    }


    /**
     * Web直接调用导出Excel(Xlsx)
     *
     * @param title    标题
     * @param headMap  属性头
     * @param ja       数据
     * @param response 返回
     */
    public static void downloadExcelFile(String title, String fileName, Map<String, String> headMap, JSONArray ja, HttpServletResponse response) {
        if(null == ja){
            return;
        }

        ByteArrayOutputStream os = null;
        ServletOutputStream outputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        InputStream is = null;

        if(StringUtils.isBlank(fileName)){
            fileName = DateUtil.dateFormat(new Date(),"yyyy-MM-dd");
        }

        try {
            os = new ByteArrayOutputStream();
            ExcelUtil.exportExcelX(title, headMap, ja, null, 0, os);
            byte[] content = os.toByteArray();
            is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            setFileDownloadHeader(response, fileName + ".xlsx");
            response.setContentLength(content.length);

            //输出Excel文件
            outputStream = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

            outputStream.flush();
        } catch (Exception e) {
            log.error("[ExcelUtil.downloadExcelFile][The file can not export ! errorMsg : ", e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }

                if (bos != null) {
                    bos.close();
                }

                if (os != null) {
                    os.close();
                }

                if (is != null) {
                    is.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                log.error("[ExcelUtil.downloadExcelFile][The file can not export ! errorMsg : ", e);
            }
        }
    }


    /**
     * 文件下载
     */
    public static void download(String fileName, String fileNameProject, String path, HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        setFileDownloadHeader(response, fileName);
        InputStream in = ExcelUtil.class.getClassLoader().getResourceAsStream(path + fileNameProject);
        OutputStream out = null;
        try {
            int len;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            log.error("[ExcelComponent.download][The file can not download ! errorMsg : {} ]", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                log.error("[ExcelComponent.download][The file can not download ! errorMsg : {} ]", e);
            }
        }
    }

    /**
     * 设置返回头
     *
     * @param response 返回
     * @param filename 文件名
     */
    public static void setFileDownloadHeader(HttpServletResponse response, String filename) {
        String headerValue = "attachment;";
        headerValue += " filename=\"" + encodeURIComponent(filename) + "\";";
        headerValue += " filename*=utf-8''" + encodeURIComponent(filename);
        response.setHeader("Content-Disposition", headerValue);
    }


    /**
     * 下载需要合并表头的Excel文件
     * @author lizhi
     * @Date 2018-12-14 19:29
     * @param
     * @return
     **/
    public static void downloadExcelFile(String title, String fileName, JsonArray data, List<String> keyColumn, List<String> headColumn, List<String> headColumnTwo,
                                         List<String> properties,List<List<Integer>> mergeRange, HttpServletResponse response){
        if(null == data){
            return;
        }
        ByteArrayOutputStream os = null;
        ServletOutputStream outputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        InputStream is = null;

        if(StringUtils.isBlank(fileName)){
            fileName = DateUtil.dateFormat(new Date(),"yyyy-MM-dd");
        }

        try {
            os = new ByteArrayOutputStream();
            ExcelUtil.exportCell(title, keyColumn, headColumn, headColumnTwo,properties, data, mergeRange, null, os);
            byte[] content = os.toByteArray();
            is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            setFileDownloadHeader(response, fileName + ".xlsx");
            response.setContentLength(content.length);

            //输出Excel文件
            outputStream = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }

            outputStream.flush();
        } catch (Exception e) {
            log.error("[ExcelUtil.downloadExcelFile][The file can not export ! errorMsg : ", e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                log.error("[ExcelUtil.downloadExcelFile][The file can not export ! errorMsg : ", e);
            }
        }
    }

    /**
     *
     * @author lizhi
     * @Date 2018-12-14 18:05
     * @param keyColumn 列的Key
     * @param headColumn 第一行表头名
     * @param headColumnTwo 第二行表头名
     * @param properties 字段
     * @param data 写入数据JSON数组
     * @param mergeRange 合并的表头
     * @param datePattern 日期格式
     * @return
     **/
    public static SXSSFWorkbook exportCell(String title, List<String> keyColumn,
                                          List<String> headColumn,
                                          List<String> headColumnTwo,
                                          List<String> properties,
                                          JsonArray data,
                                          List<List<Integer>> mergeRange,
                                           String datePattern, OutputStream out) {
        if (datePattern == null) {
            datePattern = DEFAULT_DATE_PATTERN;
        }

        // 创建一个Excel文件
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        // 创建一个工作表
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();

        // 设置表头单元格格式
        CellStyle headerCellStyle = createCellStyle(workbook, 0);
        // 设置单元格格式
        CellStyle cellStyle = createCellStyle(workbook, 1);

        // 表头 rowIndex=0
        SXSSFRow titleRow = (SXSSFRow) sheet.createRow(0);
        titleRow.createCell(0).setCellValue(title);
        titleRow.getCell(0).setCellStyle(headerCellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headColumn.size() - 1));

        // 添加表头行
        SXSSFRow sxssfRow = (SXSSFRow) sheet.createRow(1);

        // 添加表头内容
        for (int firstHead = 0; firstHead < headColumn.size(); firstHead++) {
            SXSSFCell headCell = (SXSSFCell) sxssfRow.createCell(firstHead);
            headCell.setCellValue(headColumn.get(firstHead));
            headCell.setCellStyle(headerCellStyle);
        }

        // 添加表头内容
        sxssfRow = (SXSSFRow) sheet.createRow(2);
        for (int secondHead = 0; secondHead < headColumnTwo.size(); secondHead++) {
            SXSSFCell headCell = (SXSSFCell) sxssfRow.createCell(secondHead);
            headCell.setCellValue(headColumnTwo.get(secondHead));
            headCell.setCellStyle(cellStyle);
        }

        // 合并表头
        for (List<Integer> arr : mergeRange) {
            sheet.addMergedRegion(new CellRangeAddress(arr.get(0), arr.get(1), arr.get(2), arr.get(3)));
        }

        // 把数据添加到excel
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            Object dataObj = data.get(rowIndex);
            JSONObject jo = (JSONObject) JSONObject.toJSON(dataObj);
            SXSSFRow dataRow = (SXSSFRow) sheet.createRow(rowIndex + 3);
            for (int i = 0; i < properties.size(); i++) {
                SXSSFCell newCell = (SXSSFCell) dataRow.createCell(i);
                Object o = jo.get(properties.get(i));
                String cellValue = parseObject(o, datePattern);

                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cellStyle);
            }
        }

        // 自动调整宽度
        for (int i = 0; i < headColumn.size(); i++) {
            sheet.autoSizeColumn(i);
        }
        dispose(workbook, out);
        return workbook;
    }

    /**
     * 创建单元格央视
     * @author lizhi
     * @Date 2018-12-14 18:10
     * @param type 0 表头 1 内容单元格
     * @return
     **/
    private static CellStyle createCellStyle(SXSSFWorkbook workbook, int type) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font font = workbook.createFont();
        if (type == 0) {
            font.setFontHeightInPoints((short) 20);
            font.setBoldweight((short) 700);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            cellStyle.setFont(font);
        } else {
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            Font cellFont = workbook.createFont();
            cellFont.setFontHeightInPoints((short) 16);
            cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            cellStyle.setFont(cellFont);
        }

        return cellStyle;
    }

    /**
     * 编码
     *
     * @param value 文件名
     * @return 头信息
     */
    private static String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String parseObject(Object o, String datePattern) {
        if (o == null) {
            return "";
        } else if (o instanceof Date) {
            return new SimpleDateFormat(datePattern).format(o);
        } else if (o instanceof Float || o instanceof Double) {
            return new BigDecimal(o.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } else {
            return o.toString();
        }
    }

    private static void dispose(SXSSFWorkbook workbook, OutputStream out) {
        try {
            workbook.write(out);
            workbook.dispose();
        } catch (IOException e) {
            log.error("[ExcelUtil.exportExcelX][The file can not export ! errorMsg : ", e);
        }
    }
}

