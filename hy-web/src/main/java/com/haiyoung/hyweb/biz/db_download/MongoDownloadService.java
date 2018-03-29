package com.haiyoung.hyweb.biz.db_download;

import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDownloadService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public interface MongoCollectionPrinter{
        void print(List<Object> returnList);

        void close();
    }

    public void export(String collectionName, int batchNum, MongoCollectionPrinter printer){

        List<Object> returnList = new ArrayList<>();
        Query query = new Query();
        mongoTemplate.executeQuery(query, collectionName, new DocumentCallbackHandler() {

            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                returnList.add(dbObject);
                if(returnList.size() % batchNum == 0){
                    printer.print(returnList);
                    returnList.clear();
                }
            }
        });
        if(!returnList.isEmpty()){
            printer.print(returnList);
            returnList.clear();
        }
    }

}
