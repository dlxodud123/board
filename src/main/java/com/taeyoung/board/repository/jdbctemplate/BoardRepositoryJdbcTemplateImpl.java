package com.taeyoung.board.repository.jdbctemplate;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRepositoryJdbcTemplateImpl implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    // 글 작성
    @Override
    public Board save(Board board) {
        String sql = "insert into board(title, content, writer) values(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setString(3, board.getWriter());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        board.setId(id); // 생성된 id를 넣어줌
        return board;
    }

    // 글 목록
    @Override
    public List<Board> findAll() {
        String sql = "select * from board";
        return jdbcTemplate.query(sql, boardRowMapper());
    }

    // 글 삭제
    public void deleteById(Long id) {
        String sql = "delete from board where id = ?";
        jdbcTemplate.update(sql, id);
    }

    // 글 찾기(id)
    public Board findById(Long id){
        String sql = "select * from board where id = ?";
        return jdbcTemplate.queryForObject(sql, boardRowMapper(), id);
    }

    // 글 수정
    public void update(Board board){
        String sql = "update board set title = ?, content = ?, writer = ? where id = ?";
        jdbcTemplate.update(sql, board.getTitle(), board.getContent(), board.getWriter(), board.getId());
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> new Board(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("writer")
        );
    }
}