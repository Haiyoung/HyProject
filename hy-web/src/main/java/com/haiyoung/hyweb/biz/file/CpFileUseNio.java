package com.haiyoung.hyweb.biz.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CpFileUseNio {

    public static void main(String[] args) throws Exception{

        FileInputStream fi = new FileInputStream(new File("/home/haiyoung/nioDir/input.txt"));
        FileOutputStream fo = new FileOutputStream(new File("/home/haiyoung/nioDir/output.txt"));

        FileChannel inChannel = fi.getChannel();
        FileChannel outChannel = fo.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while(true){
            int isEnd = inChannel.read(byteBuffer);

            if(isEnd == -1){
                break;
            }

            byteBuffer.flip();
            outChannel.write(byteBuffer);

            byteBuffer.clear();
        }

        inChannel.close();
        outChannel.close();

        fi.close();
        fo.close();

    }
}
