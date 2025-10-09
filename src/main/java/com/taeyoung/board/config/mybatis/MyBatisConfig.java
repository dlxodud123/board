package com.taeyoung.board.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.taeyoung.board.repository.mybatis")
public class MyBatisConfig {
}
