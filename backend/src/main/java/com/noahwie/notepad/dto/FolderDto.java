package com.noahwie.notepad.dto;

import java.time.LocalDateTime;
import java.util.List;

public class FolderDto {
    public Long id;
    public String name;
    public LocalDateTime createdAt;
    public List<NoteDto> notes;
}
