package com.ly.blogapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author zhuxuchen
 * @since 2023-03-03
 */
@Configuration
@MapperScan("com.ly.blogapi.mapper")
public class MybatisPlusConfig {
}
