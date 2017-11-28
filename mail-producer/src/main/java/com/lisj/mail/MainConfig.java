package com.lisj.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author TF016519
 * @description:
 * @date 2017/11/18 17:32
 * @version:1.0.0
 */
@EnableWebMvc //启用spring mvc
@Configuration // 项目启动时启动当前配置类
//扫描注解
@ComponentScan ({"com.lisj.mail.*"})
//扫描mapper
@MapperScan(basePackages = "com.lisj.mail.mapper")
public class MainConfig {

}
