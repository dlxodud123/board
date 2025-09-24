package com.taeyoung.board.service;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.repository.BoardRepository;
import com.taeyoung.board.repository.MemoryBoardRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RequiredArgsConstructor
@SpringBootTest
public class MemoryBoardServiceTest {

    @Autowired
    private DataSource dataSource;  // Spring이 자동으로 주입

    private BoardService boardService;
    private BoardRepository boardRepository;
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate txTemplate;

    @BeforeEach
    void setup() {
        transactionManager = new DataSourceTransactionManager(dataSource);
        boardRepository = new MemoryBoardRepository(dataSource);
        boardService = new BoardService(transactionManager, txTemplate, boardRepository);
    }

    @Test
    void commitTest() {
        // given
        BoardForm form = new BoardForm();
        form.setTitle("commit test2");
        form.setContent("commit test2");
        form.setWriter("commit test2");

        // when
        boardService.createBoard(form);

        // then
        List<Board> boards = boardRepository.findAll();
        assertThat(boards).isNotEmpty();
        assertThat(boards.get(boards.size() - 1).getTitle()).isEqualTo("commit test2");
    }

    @Test
    void rollbackTest() {
        // given
        BoardForm form = new BoardForm();
        form.setTitle("rollback test");
        form.setContent("rollback test");
        form.setWriter("rollback test");

        // when
        try {
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                Board board = new Board(form.getTitle(), form.getContent(), form.getWriter());
                boardRepository.save(board);
                throw new RuntimeException("강제 예외");
            } catch (Exception e) {
                transactionManager.rollback(status);
            }
        } catch (Exception ignored) {}

        // then
        List<Board> boards = boardRepository.findAll();
        boolean exists = boards.stream()
                .anyMatch(b -> b.getTitle().equals("rollback test"));
        assertThat(exists).isFalse();
    }
}
