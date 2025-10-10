package com.taeyoung.board.controller;

import com.taeyoung.board.domain.Board;
import com.taeyoung.board.dto.BoardForm;
import com.taeyoung.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 글 작성
    @GetMapping("/write")
    public String writeForm() {
        return "write";
    }
    @PostMapping("/board/save")
    public String saveBoard(@ModelAttribute BoardForm form) {
        boardService.createBoard(form);
        return "redirect:/board";
    }

    // 글 목록 + 검색
    @GetMapping("/board")
    public String listBoards(@RequestParam(value = "title", required = false) String title, Model model) {
        List<Board> boards = boardService.findAll(title);
        model.addAttribute("boards", boards);
        model.addAttribute("title", title); // 검색어 유지
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