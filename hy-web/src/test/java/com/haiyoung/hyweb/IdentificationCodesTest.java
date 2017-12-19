package com.haiyoung.hyweb;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentificationCodesTest {

    @Test
    public void identificationCodeTest() throws TesseractException,IOException {
        File imageFile = new File("/home/haiyoung/下载/tess4j02.png");
        System.out.println(imageFile.getName());
        Tesseract instance = new Tesseract();
        instance.setDatapath("/usr/local/share/tessdata");
        instance.setLanguage("eng");//使用英文库
        System.out.println("开始解析...");
        //图片二值化能显著提高解析精度
        String result = instance.doOCR(ImageHelper.convertImageToBinary(ImageIO.read(imageFile)));
        System.out.println(result);
        System.out.println("解析结束...");
    }
}
