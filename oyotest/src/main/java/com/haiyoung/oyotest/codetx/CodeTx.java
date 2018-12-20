package com.haiyoung.oyotest.codetx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @Author: Haiyang
 * @Date: 2018/12/19 下午8:18
 */
@Service
public class CodeTx {

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Transactional
    public void testCodeTx(){

        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);//开启事务

        //TODO 业务逻辑

        transactionManager.commit(status);//提交事务
    }
}
