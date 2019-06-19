package com.linkpets.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Xie Chenxi
 * @date 2019-03-29 11:20
 */
@Configuration
public class AuthMyBatisConfiguration {

    @Bean(value = "authDatasource")
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://116.62.60.203:3306/pokedata_test?useSSL=false&autoReconnect=true");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("PokePet123456!");
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
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(this.dataSource());
        return dataSourceTransactionManager;
    }

}
