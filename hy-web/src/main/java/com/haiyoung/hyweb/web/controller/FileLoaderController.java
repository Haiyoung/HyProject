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

    @RequestMapping("/doMultiUpload")
    @ResponseBody
    public String multiUploading(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
//        MultipartFile file = null;
//        FileOutputStream out = null;
        if(files != null && !files.isEmpty()){
            for(MultipartFile file:files){
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

                    }catch (Exception e){
                        log.error("uploading failure!");
                    }
                }
            }
            return "multiUploading success!";
        }else{
            return "multiUploading failure!";
        }

    }

    /*文件下载*/
    @RequestMapping("/fileDownload")
    public ResponseEntity<byte[]> download() throws IOException{
        String path = "./file/linux.txt";
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("download-linux.txt".getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment",fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }

    //用ResponseEntity<byte[]> 返回值完成文件下载
    @RequestMapping("/fileDown")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, @RequestParam(value = "path",required = false) String path)
            throws Exception {
        byte[] body = null;
        // ServletContext servletContext = request.getServletContext();
        String fileName = "邵海洋.jpg"; //从uuid_name.jpg中截取文件名
        // String path = servletContext.getRealPath("/WEB-INF/res/" + fileName);
//        path = "E:\\图片\\姐姐_20170907223722.jpg";
//        path = "E:\\\\sql\\\\"+path;
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        body = new byte[in.available()];
        in.read(body);
        HttpHeaders headers = new HttpHeaders();
        fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        in.close();
        return response;
    }

    //文件列表的显示
    /*https://www.cnblogs.com/lixiang1993/p/7443246.html*/
    @RequestMapping(value = "/showFile")
    public String showFile(HttpServletRequest request,Map<String,Object> map){
//        ServletContext servletContext = request.getServletContext();
//        String path=servletContext.getRealPath("E://图片");
        File file = new File("/opt");
        File[] fileList = null;
        if(file.exists()){
            fileList = file.listFiles();
        }
//        File[] fileList = new File(path).listFiles();
//        m.addAttribute("fileList", fileList);
        map.put("fileList",fileList);
        if(fileList.length > 0){
            for(File obj:fileList){
                System.out.println(obj.getName());
            }
        }

//        System.out.println(fileList.toString());
        return "showFile";
    }
}