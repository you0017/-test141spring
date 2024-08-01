利用spring的ioc，di建立一个mysql的连接，查询一个表中的数据

IOC机制创建数据源
利用DI机制将数据源bean注入dao层中

业务层->dao层->数据源(数据库连接池)->数据库
            c3p0
            dbcp
            druid ***
            ....

            url,username,password,驱动
            连接数，最大连接数，最小连接数，最大等待时间