package com.taeyoung.board.repository.jpa;

import com.taeyoung.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositorySdj extends JpaRepository<Board, Long> {

}
