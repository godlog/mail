package com.transfar.mail.config.datebase;


import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/18 18:09
 * @version:1.0.0
 */
@Configuration
//开启事物
@EnableTransactionManagement
public class DataSourceConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;


    @Bean(name ="masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.master")
    public DataSource  masterDataSource() throws SQLException{
        DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        LOGGER.info("=============master{}====================",masterDataSource);
        return masterDataSource;
    }


    @Bean(name ="slaveDataSource")
    @ConfigurationProperties(prefix = "druid.slave")
    public DataSource  slaveDataSource() throws SQLException{
        DataSource slaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        LOGGER.info("=============slave{}====================",slaveDataSource);
        return slaveDataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow","localhost");
        reg.addInitParameter("deny","/deny");
        reg.addInitParameter("loginUsername","lishijin");
        reg.addInitParameter("loginPassword","lishijin");
        LOGGER.info("druid info{}",reg);
        return reg;
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ion,/druid/*");
        LOGGER.info("druid filter{}",filterRegistrationBean);
        return  filterRegistrationBean;
    }



}
