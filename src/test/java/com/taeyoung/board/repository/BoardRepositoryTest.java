package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    MemoryBoardRepository repository;

    @Test
    void crud() {
        // save
        Board board = new Board("안녕", "뭐해", "이태영");
        repository.save(board);

        // findALl
    }

}