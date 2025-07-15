package com.noahwie.notepad.controller;

import com.noahwie.notepad.dto.NoteDto;
import com.noahwie.notepad.service.FolderService;
import com.noahwie.notepad.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing notes.
 * Provides endpoints to create, read, update and delete notes in folders.
 */
@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * GET /folders/{id}/notes
     * Returns all notes inside a specific folder.
     * @param id ID of the folder
     * @return list of notes as DTOs
     */
    @GetMapping("/folders/{id}/notes")
    public List<NoteDto> getNotesInFolder(@PathVariable long id) {
        return noteService.getNotesInFolder(id);
    }

    /**
     * POST /folders/{id}/notes
     * Creates a new note inside a given folder.
     * @param id folder ID
     * @param noteDto note data
     * @return created note
     */
    @PostMapping("/folders/{id}/notes")
    public NoteDto createNoteInFolder(@PathVariable long id, @RequestBody @Valid NoteDto noteDto) {
        return noteService.createNote(id, noteDto);
    }

    /**
     * GET /notes/{id}
     * Returns a single note by its ID.
     * @param id note ID
     * @return the note as DTO
     */
    @GetMapping("/notes/{id}")
    public NoteDto getNote(@PathVariable long id) {
        return noteService.getNoteById(id);
    }

    /**
     * PUT /notes/{id}
     * Updates an existing note with new data.
     * @param id note ID
     * @param noteDto new title and/or content
     * @return updated note
     */
    @PutMapping("/notes/{id}")
    public NoteDto updateNoteById(@PathVariable long id, @RequestBody NoteDto noteDto) {
        return noteService.updateNoteById(id, noteDto);
    }

    /**
     * DELETE /notes/{id}
     * Deletes a note by its ID.
     * @param id note ID to delete
     */
    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNoteById(@PathVariable long id) {
        noteService.deleteNote(id);
    }
}

