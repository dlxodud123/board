package com.taeyoung.board.service;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class MemoryBoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void commitTest() {
        // given
        BoardForm form = new BoardForm();
        form.setTitle("commit test3");
        form.setContent("commit test3");
        form.setWriter("commit test3");

        // when
        boardService.createBoard(form);

        // then
        List<Board> boards = boardRepository.findAll();
        assertThat(boards).isNotEmpty();
        assertThat(boards.get(boards.size() - 1).getTitle()).isEqualTo("commit test3");
    }

    @Test
    void rollbackTest() {
        // given
        BoardForm form = new BoardForm();
        form.setTitle("rollback test");
        form.setContent("rollback test");
        form.setWriter("rollback test");

        // then
        assertThatThrownBy(() -> {
            // when
            boardService.createBoard(form);
            throw new RuntimeException("강제 예외");
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void AopCheck(){
        log.info("memberService class={}", boardService.getClass());
        log.info("memberRepository class={}", boardRepository.getClass());
        assertThat(AopUtils.isAopProxy(boardService)).isTrue();
    }
}
