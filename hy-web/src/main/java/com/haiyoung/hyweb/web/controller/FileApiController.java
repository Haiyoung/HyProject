package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.biz.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileApiController {

    private static final Logger log = LoggerFactory.getLogger(FileApiController.class);

    @Autowired
    private FileService fileService;

   /* 文件上传*/
    @RequestMapping("/doUpload")
    public String uploading(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        if (file != null && !file.isEmpty()){
            fileService.fileUpload(null, file);
            return "single file upload success!";
        }else{
            return "file is not exist!";
        }

    }

    @RequestMapping("/doMultiUpload")
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

        return fileService.fileDownload(request, path);
    }

    @RequestMapping(value = "filesPath")
    public List<String> getFilePath(){
        String path = "E:/图片/";
        List<File> files = new ArrayList<>();
        files = fileService.getFiles(path, files);
        return fileService.getFilePath(files);
    }
}