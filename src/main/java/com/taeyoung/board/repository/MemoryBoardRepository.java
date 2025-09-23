package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryBoardRepository implements BoardRepository{

    private final DataSource dataSource;

    public MemoryBoardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 글 작성
    @Override
    public Board save(Board board) {
        String sql = "insert into board(title, content, writer) values(?, ?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getWriter());
            pstmt.executeUpdate();
            return board;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    // 글 목록
    @Override
    public List<Board> findAll() {
        String sql = "select * from board";

        List<Board> boards = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                boards.add(new Board(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("writer"))
                );
            }
            return boards;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    // 글 삭제
    public void deleteById(Long id) {
        String sql = "delete from board where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    // connection 연결
    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        return con;
    }
    // JDBC 리소스 종료
    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeConnection(con);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeResultSet(rs);
    }
}
