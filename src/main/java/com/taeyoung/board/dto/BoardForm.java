package com.taeyoung.board.dto;

import lombok.Data;

@Data
public class BoardForm {
    private Long id;
    private String title;
    private String content;
    private String writer;
}
