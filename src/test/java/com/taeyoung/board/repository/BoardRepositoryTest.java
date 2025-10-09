package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.repository.jdbctemplate.BoardRepositoryJdbcTemplateImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
public class BoardRepositoryTest {

    @Autowired
    BoardRepositoryJdbcTemplateImpl repository;

    @Test
    void save() {
        // given
        Board board = new Board("test", "test", "test");

        // when
        repository.save(board);

        // then
        Board findBoard = repository.findById(board.getId());
        assertThat(findBoard).isEqualTo(board);
    }

    @Test
    void findAll() {
        // given
        Board board = new Board("test2", "test2", "test2");
        repository.save(board);

        // when
        List<Board> boardList = repository.findAll();

        // then
        assertThat(boardList).hasSize(1);
        assertThat(boardList.get(0).getTitle()).isEqualTo("test2");
    }

    @Test
    void deleteById() {
        // given
        Board board = new Board("test3", "test3", "test3");
        repository.save(board);

        // when
        repository.deleteById(board.getId());

        // then
        List<Board> boardList = repository.findAll();
        assertThat(boardList).isEmpty();
    }

    @Test
    void update() {
        // given
        Board board = new Board("test4", "test4", "test4");
        repository.save(board);

        // when
        Board updateBoard = new Board("test44", "test44","test44");
        updateBoard.setId(board.getId());
        repository.update(updateBoard);

        // then
        Board findBoard = repository.findById(board.getId());
        assertThat(findBoard.getTitle()).isEqualTo("test44");
        assertThat(findBoard.getContent()).isEqualTo("test44");
        assertThat(findBoard.getWriter()).isEqualTo("test44");
    }
}