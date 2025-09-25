package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    MemoryBoardRepository repository;

    @Test
    void save() {
        Board board = new Board("안녕", "뭐해", "이태영");
        repository.save(board);

        List<Board> boards = repository.findAll();
        assertThat(boards).isNotEmpty();
        assertThat(boards.get(boards.size() - 1).getTitle()).isEqualTo("안녕");
    }

    @Test
    void findAll() {
        List<Board> boardList = repository.findAll();
        assertThat(boardList).isNotEmpty();
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