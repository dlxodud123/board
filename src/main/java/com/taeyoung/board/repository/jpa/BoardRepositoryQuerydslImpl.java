package com.taeyoung.board.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taeyoung.board.domain.Board;
import com.taeyoung.board.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
@Primary
public class BoardRepositoryQuerydslImpl implements BoardRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

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
