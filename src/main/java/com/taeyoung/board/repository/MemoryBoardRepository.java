package com.taeyoung.board.repository;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.exception.MyDbException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryBoardRepository implements BoardRepository{

    private final DataSource dataSource;
    private final SQLExceptionTranslator exTranslator;

    public MemoryBoardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
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
            throw exTranslator.translate("save", sql, e);
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
            throw exTranslator.translate("findAll", sql, e);
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
            throw exTranslator.translate("deleteById", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    // 글 찾기(id)
    public Board findById(Long id){
        String sql = "select * from board where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return new Board(rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("writer"));
            } else {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw exTranslator.translate("findById", sql, e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    // 글 수정
    public void update(Board board){
        String sql = "update board set title = ?, content = ?, writer = ? where id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getWriter());
            pstmt.setLong(4, board.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw exTranslator.translate("update", sql, e);
        } finally {
            close(con, pstmt, null);
        }
    }

    private Connection getConnection() {
        try{
            Connection con = DataSourceUtils.getConnection(dataSource);
            return con;
        } catch (Exception e){
            throw new MyDbException(e);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeResultSet(rs);
        DataSourceUtils.releaseConnection(con, dataSource);
    }
}