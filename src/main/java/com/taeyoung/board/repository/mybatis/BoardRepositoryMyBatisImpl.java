package com.taeyoung.board.repository.mybatis;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
@Primary
public class BoardRepositoryMyBatisImpl implements BoardRepository {

    private final BoardMapper boardMapper;

    @Override
    public Board save(Board board) {
        return null;
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
