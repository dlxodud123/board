package com.taeyoung.board.config.jdbctemplate;

import com.taeyoung.board.repository.BoardRepository;
import com.taeyoung.board.repository.jdbctemplate.BoardRepositoryImpl;
import com.taeyoung.board.service.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean
//    public BoardRepository boardRepository (JdbcTemplate jdbcTemplate){
//        return new BoardRepositoryImpl(jdbcTemplate);
//    }

//    @Bean
//    public BoardService boardService(BoardRepository boardRepository) {
//        return new BoardService(boardRepository);
//    }
}
