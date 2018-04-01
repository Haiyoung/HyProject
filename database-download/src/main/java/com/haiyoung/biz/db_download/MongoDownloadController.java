package com.haiyoung.biz.db_download;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping(value = "/page")
public class MongoDownloadController {

    @Autowired
    private MongoDownloadService mongoDownloadService;

    private static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};

    /**
     * 判断浏览器类型
     *
     * @param request
     * @return
     */
    private static boolean isMSBrowser(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        for(String signal : IEBrowserSignals){
            if(userAgent.contains(signal)){
                return true;
            }
        }
        return false;
    }

    /**
     * 实现 MongoCollectionPrinter 接口
     * 重写 print 和 close 方法
     */
    public static class DownloadPrinter implements MongoDownloadService.MongoCollectionPrinter {

        private OutputStream output = null;

        private DownloadPrinter(HttpServletResponse response, String fileName) {
            response.reset();// 清除 response 中的状态码以及headers
            response.setContentType("application/x-msdownload");//设置文件类型
            response.setHeader("Content-Disposition",
                    "attachment; filename=\""+fileName+"\"");// 设置文件名

            try {
                output = response.getOutputStream();// 获取输出流
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void print(List<Object> returnList) {
            try {
                output.write(new Gson().toJson(returnList).getBytes("UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void close() {
            try {
                output.flush();
                output.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                if (output !=null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @RequestMapping(value = "/download")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam(value = "collectionName") String collectionName){
        try {
            String fileName = collectionName+".txt";
            if(isMSBrowser(request)){
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }else{
                fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
            }

            DownloadPrinter printer = new DownloadPrinter(response, fileName);
            mongoDownloadService.export(collectionName, 300, printer);
            printer.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
