package com.haiyoung.tinycode.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.haiyoung.tinycode.config.base.BaseDruid;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-01 15:54
 * @Version 1.0
 */
public class DataSourceUtils {

    /**
     * 获取druid连接池
     *
     * @param properties
     * @return
     */
    public static DataSource getDataSource(BaseDruid properties) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClass());

        if (properties.getInitialSize() > 0) {
            dataSource.setInitialSize(properties.getInitialSize());
        }
        if (properties.getMinIdle() > 0) {
            dataSource.setMinIdle(properties.getMinIdle());
        }
        if (properties.getMaxActive() > 0) {
            dataSource.setMaxActive(properties.getMaxActive());
        }
        if(properties.getRemoveAbandonedTimeout() > 0){
            dataSource.setRemoveAbandonedTimeout(properties.getRemoveAbandonedTimeout());
        }
        if(properties.getMaxWait() > 0){
            dataSource.setMaxWait(properties.getMaxWait());
        }

        dataSource.setRemoveAbandoned(properties.getRemoveAbandoned());
        dataSource.setLogAbandoned(properties.getLogAbandoned());
        dataSource.setTestOnBorrow(properties.getTestOnBorrow());

        dataSource.setValidationQuery(properties.getValidationQuery());

        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    /**
     * https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
     *
     * 5.0版本之前，配置PageHelper；5.0版本之后，要配置成 PageInterceptor
     *
     * PageHelper pageHelper = DataSourceUtils.getPageHelper();
     *
     * 添加拦截器
     *
     * return PageInterceptor
     */
    public static PageInterceptor getPageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        /**
         * 启用合理化时，如果pageNum<1 会查询第一页，如果 pageNum>pages会查询最后一页
         * 禁用合理化时，如果pageNum<1 或 pageNum>pages会返回空数据
         */
        properties.setProperty("reasonable", "false");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
