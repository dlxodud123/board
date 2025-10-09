package com.taeyoung.board.config.mybatis;

import com.taeyoung.board.repository.mybatis.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final BoardMapper boardMapper;

}
