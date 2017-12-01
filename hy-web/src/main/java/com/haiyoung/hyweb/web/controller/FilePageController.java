package com.haiyoung.hyweb.web.controller;

import com.haiyoung.hyweb.biz.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page/file")
public class FilePageController {

    private static final Logger log = LoggerFactory.getLogger(FilePageController.class);

    /**
     * 文件上传页面
     * @param map
     * @return
     */
    @RequestMapping("/upload")
    public String fileupload(Map<String,Object> map){
        return "fileupload";
    }


    /**
     * 下载列表页面
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/showFile")
    public String showFile(HttpServletRequest request,Map<String,Object> map){
        return "showFile";
    }

}