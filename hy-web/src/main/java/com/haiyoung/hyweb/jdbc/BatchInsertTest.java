package com.haiyoung.hyweb.jdbc;

import com.haiyoung.hyweb.util.SnowFlakeUtil;
import com.haiyoung.hyweb.web.controller.FileApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Author: Haiyang
 * @Date: 2018/12/25 下午6:54
 */
public class BatchInsertTest {

    private static final Logger log = LoggerFactory.getLogger(BatchInsertTest.class);

    @Resource
    private DataSource dataSource;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    public void jdbc_test() {

        Connection connection = null;
        PreparedStatement preparedStatement =null;

        try{
            connection = dataSource.getConnection();
            String sql = "insert into kyp_task_item_value (id, account_id, work_task_id, task_item_id, hotel_id) values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);

            long start = System.currentTimeMillis();

            for (int i = 0; i < 5000; i++) {

                preparedStatement.setString(1, snowFlakeUtil.nextId());
                preparedStatement.setInt(2,101);
                preparedStatement.setString(3, "101");
                preparedStatement.setString(4, "101");
                preparedStatement.setString(5, "101");

                preparedStatement.addBatch();
                if(i != 0 && i%500 == 0){
                    preparedStatement.executeBatch();
                    connection.commit();
                    preparedStatement.clearBatch();
                }
            }
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.clearBatch();


            long end = System.currentTimeMillis();

            System.out.println("batch_insert_1:"+(end - start));

        }catch (Exception e){
            log.info(e.getMessage());
        }finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (Exception e){
                log.info(e.getMessage());
            }
        }
    }
}
