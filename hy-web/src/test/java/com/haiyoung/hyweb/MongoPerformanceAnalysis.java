package com.haiyoung.hyweb;

import com.haiyoung.hyweb.util.CodecUtils;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.InsertOptions;
import com.mongodb.client.MongoCollection;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoPerformanceAnalysis {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void performanceAnalysis(){

        List<Document> documentList = new ArrayList<>(5000);
        for(int i=0;i<2500;i++){
            Document document = new Document();
            document.append("id", CodecUtils.generateGUID());
            document.append("runtimeId", CodecUtils.generateGUID());
            document.append("date", new Date());
            List<String> keys = new ArrayList<>(5000);
            List<Double> values = new ArrayList<>(5000);
            for(int j=0;j<3000;j++){
                keys.add("000001.EQSZ");
                values.add(13d);
            }
            document.append("keys", keys);
            document.append("values", values);

            documentList.add(document);
        }

        long start = System.currentTimeMillis();
        mongoTemplate.insert(documentList, "insert-001");
        long end = System.currentTimeMillis();
        System.out.println("mongoTemplate.insert插入耗时："+ (end - start) +"ms");

    }
}
