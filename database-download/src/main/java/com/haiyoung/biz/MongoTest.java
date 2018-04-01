package com.haiyoung.biz;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haiyoung on 2018/4/1.
 */
@Service
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加测试数据
     */
    public void addData(){
        List<Document> list = new ArrayList<>();
        int count = 0;
        for(int i=0;i<10000;i++){
            Document doc = new Document();
            doc.append("key","key"+i);
            list.add(doc);
            count++;
            if(count % 300 == 0){//每 300 调条插入一次数据
                mongoTemplate.insert(list, "collection_test");
                list.clear();
            }
        }
        if(!list.isEmpty()){
            mongoTemplate.insert(list, "collection_test");
        }
    }
}
