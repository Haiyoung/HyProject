package com.haiyoung.hyweb.biz.file;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created by Haiyoung on 2017/11/19.
 */
@Service
public class FileService {

    /*获取目录下所有文件*/
    public static List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

}
