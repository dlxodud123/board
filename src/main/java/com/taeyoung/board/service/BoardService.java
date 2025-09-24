package com.taeyoung.board.service;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    private final TransactionTemplate txTemplate;
    private final BoardRepository boardRepository;

    public BoardService(PlatformTransactionManager transactionManager, TransactionTemplate txTemplate, BoardRepository boardRepository) {
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.boardRepository = boardRepository;
    }

    // 글 작성
    public void createBoard(BoardForm form) {
        txTemplate.executeWithoutResult((status) -> {
            Board board = new Board(form.getTitle(), form.getContent(), form.getWriter());
            boardRepository.save(board);
        });
    }

    // 글 목록
    public List<Board> findAll() {
        return txTemplate.execute((status) -> boardRepository.findAll());
    }

    // 글 삭제
    public void delete(Long id) {
        txTemplate.executeWithoutResult((status) -> boardRepository.deleteById(id));
    }

    // 글 찾기(id)
    public Board findById(Long id){
        return txTemplate.execute((status) -> boardRepository.findById(id));
    }

    // 글 수정
    public void update(BoardForm form){
        txTemplate.executeWithoutResult((status) -> {
            Board board = new Board(form.getId(), form.getTitle(), form.getContent(), form.getWriter());
            boardRepository.update(board);
        });
    }
}
