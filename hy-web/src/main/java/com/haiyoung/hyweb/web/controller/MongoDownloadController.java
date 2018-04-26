package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.biz.db_download.MongoDownloadService;
import com.haiyoung.hyweb.biz.db_download.MongoDownloadService.MongoCollectionPrinter;
import com.haiyoung.hyweb.util.JsonUtils;
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

    private final MongoDownloadService mongoDownloadService;

    private static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};

    @Autowired
    public MongoDownloadController(MongoDownloadService mongoDownloadService) {
        this.mongoDownloadService = mongoDownloadService;
    }

    private static boolean isMSBrowser(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        for(String signal : IEBrowserSignals){
            if(userAgent.contains(signal)){
                return true;
            }
        }
        return false;
    }

    public static class DownloadPrinter implements MongoCollectionPrinter{

        private OutputStream output = null;

        private DownloadPrinter(HttpServletResponse response, String fileName) {
            response.reset();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\""+fileName+"\"");

            try {
                output = response.getOutputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void print(List<Object> returnList) {
            try {
                output.write(JsonUtils.toJson(returnList).getBytes("UTF-8"));
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
                closeQuietly(output);
            }
        }

        private void closeQuietly(OutputStream output){
            if (output !=null){
                try {
                    output.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
