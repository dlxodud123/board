package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;

public interface BoardRepository {
    Board save(Board board);
}
