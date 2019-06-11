package com.haiyoung.tinycode;

import com.haiyoung.tinycode.bean.po.OrderPO;
import com.haiyoung.tinycode.bean.po.SourceTypePO;
import com.haiyoung.tinycode.multiDatasource.MultiDatabaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-02 17:27
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiDatabaseTest {

    @Autowired
    private MultiDatabaseService multiDatabaseService;

    @Test
    public void queryOrderByIdTest(){
        OrderPO orderPO = multiDatabaseService.queryOrderById(0L);
        System.out.println(orderPO);
    }

    @Test
    public void insertOrderTest(){
        OrderPO orderPO = new OrderPO();
        orderPO.setOrderId(999L);
        orderPO.setUserId(888L);

        int count = multiDatabaseService.insertOrder(orderPO);
        System.out.println(count);
    }

    @Test
    public void querySourceByIdTest(){
        SourceTypePO sourceTypePO = multiDatabaseService.querySourceById("1");
        System.out.println(sourceTypePO);
    }

    @Test
    public void insertSourceTypeTest(){
        SourceTypePO sourceTypePO = new SourceTypePO();
        sourceTypePO.setId("222");
        sourceTypePO.setSort(2);
        sourceTypePO.setSourceId("222");
        sourceTypePO.setSourceName("222");

        int count = multiDatabaseService.insertSourceType(sourceTypePO);
        System.out.println(count);

        System.out.println(".....................");
    }

}
