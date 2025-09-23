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
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "edit";
    }
    @PostMapping("/board/update/{id}")
    public String updateBoard(@ModelAttribute BoardForm form) {
        boardService.update(form);
        return "redirect:/board";
    }
}