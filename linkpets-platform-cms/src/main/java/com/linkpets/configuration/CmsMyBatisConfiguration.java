package com.linkpets.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created by Fade on 2016/12/22.
 */



@Configuration
public class CmsMyBatisConfiguration{

    private static final Logger log= LoggerFactory.getLogger(CmsMyBatisConfiguration.class);

    @Value("${linkpets.jdbc.url}")
    private String jdbcUrl;

    @Value("${linkpets.jdbc.username}")
    private String jdbcUsername;

    @Value("${linkpets.jdbc.password}")
    private String jdbcPassword;


    @Bean(value = "cmsDatasource")
    public DataSource dataSource(){
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setJdbcUrl(jdbcUrl);
        hikariDataSource.setUsername(jdbcUsername);
        hikariDataSource.setPassword(jdbcPassword);
        hikariDataSource.setReadOnly(false);
        hikariDataSource.setConnectionTimeout(30000);
        hikariDataSource.setIdleTimeout(30000);
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setMinimumIdle(5);
        hikariDataSource.setAutoCommit(true);

        hikariDataSource.setConnectionTestQuery("SELECT 1");
        hikariDataSource.setMaximumPoolSize(15);
        hikariDataSource.setMaxLifetime(1800000);
        hikariDataSource.setValidationTimeout(3000);
        return hikariDataSource;
    }




    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(this.dataSource());
        return dataSourceTransactionManager;
    }

}
