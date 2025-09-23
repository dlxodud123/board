package com.taeyoung.board.controller;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

}