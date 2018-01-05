package com.haiyoung.hyweb;

import com.haiyoung.hyweb.biz.user.User;
import com.haiyoung.hyweb.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTest {

    @Autowired
    private MongoTemplate mongoTemplate;

/*    @Autowired
    private getTestResult getTests;*/

    @Test
    public void saveUser(){
//        测试数据添加
/*        User user = new User();
        user.setId(2);
        user.setUserId("test1");
        user.setPassword("1234");
        user.setUserName("test1");
        mongoTemplate.save(user);
        mongoTemplate.insert(user, "mongoTest");*/
//TEST
/*        int i = 0;
        while(i<10){
            TEST test = new TEST();
            test.setName("test00"+i);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -i);
            System.out.println("第 "+i+" 次："+ calendar.getTime());
            test.setDate(calendar.getTime());
            mongoTemplate.save(test);
            i++;
        }*/

//测试数据获取
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(calendar.getTime());
        Query query = new Query(Criteria.where("date").lte(calendar.getTime()));
        List<TEST>  list = mongoTemplate.find(query,TEST.class, "tEST");
//        List<TEST>  list = getTests.getTests(calendar.getTime());
        System.out.println(list.size());

        for (TEST test:list) {

            TESTDTO dto = new TESTDTO(test.getName(), test.getDate());
            System.out.println(dto.toString());
        }

//        System.out.println(calendar.getTimeZone().getID());
//        System.out.println(TimeZone.getTimeZone("CST"));
//        System.out.println(TimeZone.getTimeZone("UTC"));
    }

}

class TESTDTO{
    private String name;
    private String date;

    public TESTDTO(String name, Date date){
        this.name = name;
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        df1.setTimeZone(TimeZone.getTimeZone("CST"));
//        this.date = DateUtils.format(date,"yyyy-MM-dd");
        this.date = df1.format(date);
    }

    @Override
    public String toString() {
        return "TESTDTO{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

class TEST{
    private String name;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TEST{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}

/*@Service
interface getTestResult{

    default List<TEST> getResult(Date date) {
        return getTests(date);
    }

    @org.springframework.data.mongodb.repository.Query(value = "{'date' : {$lte: ?0}}")
    List<TEST> getTests(Date date);
}*/
