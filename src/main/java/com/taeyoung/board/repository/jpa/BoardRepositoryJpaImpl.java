package com.taeyoung.board.repository.jpa;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
@Primary
public class BoardRepositoryJpaImpl implements BoardRepository {

    private final EntityManager em;

    @Override
    public void save(Board board) {
        em.persist(board);
    }

    @Override
    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Board board = em.find(Board.class, id);
        if (board != null){
            em.remove(board);
        }
    }

    @Override
    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public void update(Board board) {
        Board findBoard = em.find(Board.class, board.getId());

        if (board != null){
            findBoard.setTitle(board.getTitle());
            findBoard.setContent(board.getContent());
            findBoard.setWriter(board.getWriter());
        }
    }
}
