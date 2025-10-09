package com.taeyoung.board.repository.mybatis;

import com.taeyoung.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    Board save(Board board);
    List<Board> findAll();
    void deleteById(Long id);
    Board findById(Long id);
    void update(Board board);
}
