package com.taeyoung.board.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taeyoung.board.domain.Board;
import com.taeyoung.board.domain.QBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardSearchRepository {

    private final JPAQueryFactory query;

    public List<Board> findByTitleContaining(String title) {
        QBoard board = QBoard.board;

        return query.select(board)
                .from(board)
                .where(titleContains(title))
                .fetch();
    }

    private BooleanExpression titleContains(String title) {
        return QBoard.board.title.contains(title);
    }
}