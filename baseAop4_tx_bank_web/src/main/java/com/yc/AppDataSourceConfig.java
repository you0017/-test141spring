package com.yc;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy//启用aspectj
@EnableTransactionManagement(
        proxyTargetClass = false,
        mode = AdviceMode.PROXY,
        order = Integer.MAX_VALUE
)//开启事务管理
@EnableCaching//开启缓存
@EnableMBeanExport//启用待监控的类的机制，导出这些类的信息
public class AppDataSourceConfig {

    @Value("${uname}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;
    @Value("${driverClassName}")
    private String driverClassName;

    // 声明缓存管理器--》给Dao层加入缓存管理器
    @Bean
    public CacheManager cacheManager() {
        // 返回一个ConcurrentMapCacheManager的实例，其中包含一个名为"users"的缓存区域，用于存储和管理相关的数据
        return new ConcurrentMapCacheManager("bank_web");
    }

    //创建事务管理器(给业务层加入事务处理代码
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(initMethod = "init")//另外要注意:idea会对这个方法的返回值进行解析，判断是否有initMethod指定方法，所以类型要一致
    public DataSource druidDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setDriverClassName(driverClassName);

        //数据源的更多参数配置
        ds.setInitialSize(5);
        ds.setMaxActive(20);
        ds.setMinIdle(5);
        return ds;
    }
}
