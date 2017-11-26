package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.biz.file.FileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page/file")
public class FileLoaderController{

    private static final Logger log = LoggerFactory.getLogger(FileLoaderController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public String fileupload(Map<String,Object> map){

        return "fileupload";
    }

   /* 文件上传*/
    @RequestMapping("/doUpload")
    @ResponseBody
    public String uploading(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        if (file != null && !file.isEmpty()){
            fileService.fileUpload(null, file);
            return "single file upload success!";
        }else{
            return "file is not exist!";
        }

    }

    @RequestMapping("/doMultiUpload")
    @ResponseBody
    public String multiUploading(HttpServletRequest request){

        fileService.multiFileUpload(request,"E:/图片/");
        return "multiFile upload success!";

    }

    /*文件下载*/
    @RequestMapping("/fileDownload")
    public ResponseEntity<byte[]> download() throws IOException{
        return fileService.fileDownload("E:/图片/邵海洋.jpg");
    }

    //用ResponseEntity<byte[]> 返回值完成文件下载
    @RequestMapping("/fileDown")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, @RequestParam(value = "path",required = false) String path)
            throws Exception {
//        path = "E:\\图片\\"+path;
        path = request.getParameter("path");
        return fileService.fileDownload(request, path);
    }

    //文件列表的显示
    /*https://www.cnblogs.com/lixiang1993/p/7443246.html*/
    @RequestMapping(value = "/showFile")
    public String showFile(HttpServletRequest request,Map<String,Object> map){
        File file = new File("E:/图片");
        File[] fileList = null;
        if(file.exists()){
            fileList = file.listFiles();
        }

        map.put("fileList",fileList);
        if(fileList.length > 0){
            for(File obj:fileList){
                System.out.println(obj.getName());
                System.out.println(obj.getAbsolutePath());
            }
        }

        System.out.println(fileList.toString());
        return "showFile";
    }

    @RequestMapping(value = "filesPath")
    @ResponseBody
    public List<String> getFilePath(){
        String path = "E:/图片/";
        List<File> files = new ArrayList<>();
        files = fileService.getFiles(path, files);
        return fileService.getFilePath(files);
    }
}