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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: Haiyang Shao
 * @Date: 2019-06-01 22:04
 * @Version 1.0
 */
//@Configuration
//@EnableConfigurationProperties(DockerDruidDataSource.class)
//@MapperScan(basePackages = Constants.MAPPER_MYSQL_PATH_DOCKER, sqlSessionFactoryRef = "dockerMysqlSqlSessionFactory")
//@EnableTransactionManagement
@Slf4j
public class DockerDruidDataSourceConfig {

    @Autowired
    private DockerDruidDataSource dockerDruidDataSource;

    @Bean(name = "dockerMysqlDataSource")
    public DataSource mysqlDataSource() {
        DataSource dataSource = DataSourceUtils.getDataSource(dockerDruidDataSource);
        return dataSource;
    }


    @Bean(name = "dockerMysqlSqlSessionFactory")
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
    @Bean(name = "dockerMysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    /**
     * 配置模板
     */
    @Bean(name = "dockerMysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("dockerMysqlSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
