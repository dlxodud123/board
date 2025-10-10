package com.taeyoung.board.repository.jpa;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
@Primary
public class BoardRepositorySpringDataJpaImpl implements BoardRepository {

    private final BoardRepositorySdj repositorySdj;

    @Override
    public void save(Board board) {
        repositorySdj.save(board);
    }

    @Override
    public List<Board> findAll() {
        return repositorySdj.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repositorySdj.deleteById(id);
    }

    @Override
    public Board findById(Long id) {
        return repositorySdj.findById(id).orElse(null);
    }

    @Override
    public void update(Board board) {
        repositorySdj.findById(board.getId()).ifPresent(findBoard -> {
            findBoard.setTitle(board.getTitle());
            findBoard.setContent(board.getContent());
            findBoard.setWriter(board.getWriter());
            repositorySdj.save(findBoard);
        });
    }
}
