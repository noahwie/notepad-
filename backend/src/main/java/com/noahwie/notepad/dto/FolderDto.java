package com.noahwie.notepad.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FolderDto {
    private Long id;

    @NotBlank(message = "Folder name must not be empty")
    private String name;
    private LocalDateTime createdAt;
    private List<NoteDto> notes;
}
