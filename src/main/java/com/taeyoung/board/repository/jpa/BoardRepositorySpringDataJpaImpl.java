package com.taeyoung.board.repository.jpa;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.repository.BoardRepository;

import java.util.List;

public class BoardRepositorySpringDataJpaImpl implements BoardRepository {



    @Override
    public void save(Board board) {

    }

    @Override
    public List<Board> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Board findById(Long id) {
        return null;
    }

    @Override
    public void update(Board board) {

    }
}
