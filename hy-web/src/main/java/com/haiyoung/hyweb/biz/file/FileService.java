package com.haiyoung.hyweb.biz.file;

import com.haiyoung.hyweb.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haiyoung on 2017/11/19.
 */
@Service
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    /**
     * 获取目录下所有文件
     * @param realPath
     * @param files
     * @return
     */
    public List<File> getFiles(String realPath, List<File> files) {
        File realFile = new File(realPath);
        if (realFile.isDirectory()) {
            File[] subFiles = realFile.listFiles();
            for (File file : subFiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**
     * 获取文件的绝对路径
     * @param files
     * @return
     */
    public List<String> getFilePath(List<File> files){
        List<String> pathList = new ArrayList<>();
        if(files != null && !files.isEmpty()){
            for(File file:files){
                if(file.exists()){
                    pathList.add(file.getAbsolutePath());
                }
            }
        }
        return pathList;
    }

    /**
     * 单文件上传
     * @param filePath
     * @param file
     * @return
     */
    public String fileUpload(String filePath, MultipartFile file){

        if(file !=null && !file.isEmpty()){
            if(StringUtils.isEmpty(filePath)){
                filePath = "./file/";
            }
            try {
                File targetFile = new File(filePath);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath+file.getOriginalFilename()));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return "uploading success!";
            }catch (Exception e){
                log.error("uploading failure!");
                return "uploading failure!";
            }

        }else{
            return "file is not exist!";
        }

    }

    /**
     * 多文件上传
     * @param request
     * @param filePath
     * @return
     */
    public String multiFileUpload(HttpServletRequest request,String filePath){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        if(files != null && !files.isEmpty()){
            for(MultipartFile file:files){
                fileUpload(filePath, file);
            }
            return "uploading success!";
        }else{
            return "files are not exist!";
        }
    }

    /**
     * 文件下载
     * @param fileAbPath 文件的绝对路径
     * @return
     * @throws IOException
     */
    public ResponseEntity<byte[]> fileDownload(String fileAbPath) throws IOException {
        if(StringUtils.isEmpty(fileAbPath)){
            fileAbPath = "./file/linux.txt";
        }
        File file = new File(fileAbPath);
        HttpHeaders headers = new HttpHeaders();
        String[] temp = fileAbPath.split("/");
        String fileName = new String(temp[temp.length-1].getBytes("UTF-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment",fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    /**
     *文件下载
     * @param request
     * @param fileAbPath
     * @return
     * @throws IOException
     */
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String fileAbPath) throws IOException{
        if(StringUtils.isEmpty(fileAbPath)){
            fileAbPath = "./file/linux.txt";
        }
        byte[] body = null;
        String[] temp = fileAbPath.split("/");
        File file = new File(fileAbPath);
        InputStream in = new FileInputStream(fileAbPath);
        body = new byte[in.available()];
        in.read(body);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String(temp[temp.length-1].getBytes("UTF-8"),"iso-8859-1");
        headers.add("Content-Disposition", "attachment;filename=" + fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        in.close();
        return response;
    }

}
