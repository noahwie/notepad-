package com.noahwie.notepad.controller;

import com.noahwie.notepad.dto.NoteDto;
import com.noahwie.notepad.service.FolderService;
import com.noahwie.notepad.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/folders/{id}/notes")
    public List<NoteDto> getNotesInFolder(@PathVariable long id) {
        return noteService.getNotesInFolder(id);
    }

    @PostMapping("/folders/{id}/notes")
    public NoteDto createNoteInFolder(@PathVariable long id, @RequestBody @Valid NoteDto noteDto) {
        return noteService.createNote(id, noteDto);
    }

    @GetMapping("/notes/{id}")
    public NoteDto getNote(@PathVariable long id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/notes/{id}")
    public NoteDto updateNoteById(@PathVariable long id, @RequestBody NoteDto noteDto) {
        return noteService.updateNoteById(id, noteDto);
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNoteById(@PathVariable long id) {
        noteService.deleteNote(id);
    }
}

