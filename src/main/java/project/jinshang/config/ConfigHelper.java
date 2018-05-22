package project.jinshang.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.servlet.resource.ResourceResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by ycj on 2017/2/22.
 * 配置 辅助类
 */
public class ConfigHelper {

    public static DataSource genDefaultDruidDataSource(String driver,String url,String username,String pwd){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(pwd);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1");
//        dataSource.setDefaultAutoCommit(false); // 默认事务管理器提交的,没用

        try {
            dataSource.setFilters("stat,wall,log4j");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    public static SqlSessionFactory genDefaultSqlSessionFactory(DataSource rdsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(rdsDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setLazyLoadingEnabled(true);
//        configuration.setAggressiveLazyLoading(false);
        configuration.setCacheEnabled(true);

        //返回map结果集时为Null 也要显示
        configuration.setCallSettersOnNulls(true);

        sessionFactory.setConfiguration(configuration);


        ResourcePatternResolver resolver =  new PathMatchingResourcePatternResolver();

        //扫描mapper文件
        try {
           sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }


        return sessionFactory.getObject();
    }

}
