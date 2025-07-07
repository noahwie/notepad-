package com.noahwie.notepad.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class NoteDto {
    public Long id;

    @NotBlank(message = "Note name must not be empty")
    public String title;
    public String content;
    public LocalDateTime createdAt;
}