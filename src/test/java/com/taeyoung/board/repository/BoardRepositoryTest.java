package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    MemoryBoardRepository repository;

    @Test
    void save() {
        Board board = new Board("안녕", "뭐해", "이태영");
        repository.save(board);
    }

    @Test
    void findAll() {
        List<Board> boardList = repository.findAll();
        Assertions.assertThat(boardList).isNotEmpty();
    }

    @Test
    void deleteById() {
        List<Board> beforeBoardList = repository.findAll();
        repository.deleteById(7L);
        List<Board> afterBoardList = repository.findAll();
        Assertions.assertThat(afterBoardList.size()).isEqualTo(beforeBoardList.size() - 1);
    }
}