package com.noahwie.notepad.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoteDto {
    private Long id;

    @NotBlank(message = "Note name must not be empty")
    private String title;
    private String content;
    private LocalDateTime createdAt;
}