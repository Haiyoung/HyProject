package com.haiyoung.hyweb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/page/file")
public class FileLoaderController{

    private static final Logger log = LoggerFactory.getLogger(FileLoaderController.class);

    @RequestMapping("/upload")
    public String fileupload(Map<String,Object> map){

        return "fileupload";
    }
}