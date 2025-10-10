package com.taeyoung.board.service;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.repository.BoardRepository;
import com.taeyoung.board.repository.search.BoardSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardSearchRepository searchRepository;

    // 글 작성
    @Transactional
    public void createBoard(BoardForm form) {
        Board board = new Board(form.getTitle(), form.getContent(), form.getWriter());
        boardRepository.save(board);
    }

    // 글 목록 + 검색
    @Transactional
    public List<Board> findAll(String title) {
        if (title == null || title.isEmpty()) {
            return boardRepository.findAll();
        }
        return searchRepository.findByTitleContaining(title);
    }

    // 글 삭제
    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // 글 찾기(id)
    @Transactional
    public Board findById(Long id){
        return boardRepository.findById(id);
    }

    // 글 수정
    @Transactional
    public void update(BoardForm form){
        Board board = new Board(form.getId(), form.getTitle(), form.getContent(), form.getWriter());
        boardRepository.update(board);
    }
}
