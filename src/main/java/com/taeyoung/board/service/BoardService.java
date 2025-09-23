package com.taeyoung.board.service;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 글 작성
    public void createBoard(BoardForm form){
        Board board = new Board(form.getTitle(), form.getContent(), form.getWriter());
        boardRepository.save(board);
    }

    // 글 목록
    
}
