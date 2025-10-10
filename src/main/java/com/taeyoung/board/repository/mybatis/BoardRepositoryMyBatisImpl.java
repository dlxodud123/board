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
//@Primary
public class BoardRepositoryMyBatisImpl implements BoardRepository {

    private final BoardMapper boardMapper;

    @Override
    public void save(Board board) {
        boardMapper.save(board);
    }

    @Override
    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    @Override
    public void deleteById(Long id) {
        boardMapper.deleteById(id);
    }

    @Override
    public Board findById(Long id) {
        return boardMapper.findById(id);
    }

    @Override
    public void update(Board board) {
        boardMapper.update(board);
    }

}
