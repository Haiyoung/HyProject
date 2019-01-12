package com.haiyoung.oyotest;

import com.haiyoung.oyotest.Service.TaskService;
import com.haiyoung.oyotest.biz.PO.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void insertData(){

        Task task_0 = new Task();
        task_0.setTaskId(0L);
        task_0.setUserId(0L);

        Task task_1 = new Task();
        task_1.setTaskId(1L);
        task_1.setUserId(0L);

        Task task_2 = new Task();
        task_2.setTaskId(2L);
        task_2.setUserId(0L);

        Task task_3 = new Task();
        task_3.setTaskId(3L);
        task_3.setUserId(1L);

        Task task_4 = new Task();
        task_4.setTaskId(4L);
        task_4.setUserId(1L);

        Task task_5 = new Task();
        task_5.setTaskId(5L);
        task_5.setUserId(1L);

        taskService.createTask(task_0);
        taskService.createTask(task_1);
        taskService.createTask(task_2);
        taskService.createTask(task_3);
        taskService.createTask(task_4);
        taskService.createTask(task_5);

    }
}
