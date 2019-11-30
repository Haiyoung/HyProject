package com.haiyoung.tinycode.multiDatasource;

import com.haiyoung.tinycode.bean.po.OrderPO;
import com.haiyoung.tinycode.bean.po.SourceTypePO;
import com.haiyoung.tinycode.mapper.docker.SourceTypePOMapper;
import com.haiyoung.tinycode.mapper.local.OrderPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-02 17:24
 * @Version 1.0
 */
//@Service
public class MultiDatabaseService {

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private SourceTypePOMapper sourceTypePOMapper;

    public OrderPO queryOrderById(Long id){
        return orderPOMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertOrder(OrderPO orderPO){
        return orderPOMapper.insertSelective(orderPO);
    }

    public SourceTypePO querySourceById(String id){
        return sourceTypePOMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class, value = "dockerMysqlTransactionManager")
    public int insertSourceType(SourceTypePO sourceTypePO){
        int count =  sourceTypePOMapper.insertSourceType(sourceTypePO);

        count = count/0;
        return count;
    }
}
