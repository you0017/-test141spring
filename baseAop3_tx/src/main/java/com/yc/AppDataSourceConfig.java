package com.yc;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy//启用aspectj
public class AppDataSourceConfig {

    @Value("${uname}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${url}")
    private String url;
    @Value("${driverClassName}")
    private String driverClassName;

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
