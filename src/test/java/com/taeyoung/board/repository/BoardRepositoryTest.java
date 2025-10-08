package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;
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
    MemoryBoardRepository repository;

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
        List<Board> beforeBoardList = repository.findAll();
        repository.deleteById(8L);
        List<Board> afterBoardList = repository.findAll();
        assertThat(afterBoardList.size()).isEqualTo(beforeBoardList.size() - 1);
    }

    @Test
    void update() {
        Board board = new Board(8L,"테스트 수정", "뭐해", "이태영");
        repository.update(board);
    }
}