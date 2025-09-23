package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;

import java.util.List;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    void deleteById(Long id);
    Board findById(Long id);
    void update(Board board);
}
