package com.noahwie.notepad.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class FolderDto {
    public Long id;

    @NotBlank(message = "Folder name must not be empty")
    public String name;
    public LocalDateTime createdAt;
    public List<NoteDto> notes;
}
