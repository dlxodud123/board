package com.taeyoung.board.controller;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 글 작성
    @GetMapping("/write")
    public String writeForm() {
        return "write";
    }
    @PostMapping("/board/save")
    public String saveBoard(@ModelAttribute BoardForm form) {
        boardService.createBoard(form);
        return "redirect:/";
    }

    // 글 목록
    @GetMapping("/board")
    public String listBoards(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "boardList";
    }

    // 글 삭제
    @GetMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board";
    }

    // 글 수정
    @GetMapping("/board/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Board board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + id));

        model.addAttribute("board", board); // 모델에 담아서 edit.html에 전달
        return "edit"; // edit.html 렌더링
    }
    @PostMapping("/board/update/{id}")
    public String updateBoard(@PathVariable Long id,
                              @RequestParam String title,
                              @RequestParam String content,
                              @RequestParam String writer) {
        Board board = boardService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + id));

        // 값 수정
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);

        boardService.update(board); // update 쿼리 실행
        return "redirect:/board"; // 수정 후 목록으로 리다이렉트
    }
}