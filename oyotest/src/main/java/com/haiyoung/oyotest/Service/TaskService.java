package com.haiyoung.oyotest.Service;

import com.haiyoung.oyotest.biz.PO.Task;
import com.haiyoung.oyotest.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-01-12 12:21
 * @Version 1.0
 */

@Slf4j
@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public int createTask(Task task){
        return taskMapper.insert(task);
    }


    public List<Task> query_task(){
        return taskMapper.queryAllTask();
    }
}
