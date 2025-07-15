package com.noahwie.notepad.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * Data Transfer Object (DTO) representing a single note.
 * Used to transfer note data between backend and frontend.
 */
@Getter
@Setter
public class NoteDto {
    /** ID of the note (matches database primary key). */
    private Long id;

    /** Title of the note; must not be blank. */
    @NotBlank(message = "Note name must not be empty")
    private String title;

    /** Text content of the note. */
    private String content;

    /** Timestamp when the note was created. */
    private LocalDateTime createdAt;
}