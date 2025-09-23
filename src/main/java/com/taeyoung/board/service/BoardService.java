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
    public List<Board> findAll() {
        List<Board> boards = new ArrayList<>();
        boards.add(new Board("안녕하세요", "첫번째 글 내용", "홍길동"));
        boards.add(new Board( "공지사항", "공지글 내용", "관리자"));
        boards.add(new Board( "질문", "스프링 관련 질문", "이태영"));
        boards.add(new Board( "자유게시판", "자유롭게 작성한 글", "김철수"));

        return boards;
    }
}
