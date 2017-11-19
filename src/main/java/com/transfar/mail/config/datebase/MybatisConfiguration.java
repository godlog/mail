package com.transfar.mail.config.datebase;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.naming.factory.DataSourceLinkFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author TF016519
 * @description: 必须要
 * @date 2017/11/18 18:59
 * @version:1.0.0
 */
@Configuration
//必须在DataSourceConfiguration加载完毕后再加载这个配置文件
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {


    @Resource(name = "masterDataSource")
    private DataSource masterDataSource;

    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;

    @Bean(name ="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return super.sqlSessionFactory(roundRobinDataSourceProxy());
    }

    public AbstractRoutingDataSource  roundRobinDataSourceProxy(){
        ReadWriteSplitRoutingDataSource proxy =new ReadWriteSplitRoutingDataSource();

        ClassLoaderRepository.SoftHashMap targetDataSource =new ClassLoaderRepository.SoftHashMap();
        targetDataSource.put(DataBaseContextHolder.DataBaseType.MASTER,masterDataSource);
        targetDataSource.put(DataBaseContextHolder.DataBaseType.SLAVE,slaveDataSource);
        //默认数据源
        proxy.setDefaultTargetDataSource(masterDataSource);
        //装入两个主从数据源
        proxy.setTargetDataSources(targetDataSource);
        return proxy;
    }
}
