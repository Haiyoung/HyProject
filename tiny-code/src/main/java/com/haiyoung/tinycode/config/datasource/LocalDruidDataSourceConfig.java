package com.haiyoung.tinycode.config.datasource;

import com.haiyoung.tinycode.Constants;
import com.haiyoung.tinycode.utils.DataSourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-01 17:15
 * @Version 1.0
 */

//@Configuration
//@EnableConfigurationProperties(LocalDruidDataSource.class)
//@MapperScan(basePackages = Constants.MAPPER_MYSQL_PATH_LOCAL, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
//@EnableTransactionManagement
@Slf4j
public class LocalDruidDataSourceConfig {

    @Autowired
    private LocalDruidDataSource localDruidDataSource;

    @Bean(name = "localMysqlDataSource")
    public DataSource mysqlDataSource() {
        DataSource dataSource = DataSourceUtils.getDataSource(localDruidDataSource);
        return dataSource;
    }


    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mysqlDataSource());
        bean.setTypeAliasesPackage(Constants.DO_MAIN_MYSQL_PATH);

        bean.setPlugins(new Interceptor[]{DataSourceUtils.getPageInterceptor()});

        /**
         * 添加XML目录
         */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources(Constants.MAPPER_MYSQL_XML_PATH));
            return bean.getObject();
        } catch (Exception e) {
            log.error("build mysql SqlSessionFactory has error !");
            throw new RuntimeException(e);
        }
    }

    /**
     * 配置事务管理器
     */
    @Primary
    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    /**
     * 配置模板
     */
    @Primary
    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
