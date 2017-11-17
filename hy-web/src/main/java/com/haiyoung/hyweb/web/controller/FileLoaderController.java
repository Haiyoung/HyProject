package com.haiyoung.hyweb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

@Controller
@RequestMapping("/page/file")
public class FileLoaderController{

    private static final Logger log = LoggerFactory.getLogger(FileLoaderController.class);

    @RequestMapping("/upload")
    public String fileupload(Map<String,Object> map){

        return "fileupload";
    }


    @RequestMapping("/doUpload")
    @ResponseBody
    public String uploading(HttpServletRequest request, @RequestParam("file") MultipartFile file){

        if(!file.isEmpty()){
            try {
//                String filePath = request.getSession().getServletContext().getRealPath("file/");
                String filePath = "./file/";
                File targetFile = new File(filePath);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(filePath+file.getOriginalFilename());
                out.write(file.getBytes());
                out.flush();
                out.close();
                System.out.println(filePath);
                return "uploading success!";

            }catch (Exception e){
                log.error("uploading failure!");
                return "uploading failure!";
            }
        }else{
            return "uploading failure!";
        }


    }
}