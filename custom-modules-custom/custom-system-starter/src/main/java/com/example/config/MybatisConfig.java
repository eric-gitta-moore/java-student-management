package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author w
 */
@Configuration
@MapperScan({"org.jeecg.modules.**.mapper*", "com.example.**.mapper*", "com.diboot.core.mapper"})
public class MybatisConfig {

}
