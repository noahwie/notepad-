package com.noahwie.notepad.controller;

import com.noahwie.notepad.model.Note;
import com.noahwie.notepad.repository.NoteRepository;
import com.noahwie.notepad.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/folders/{id}/notes")
    public List<Note> getNotesInFolder(@PathVariable int id) {

    }

    @PostMapping("/folders/{id}/notes")
    public Note addNoteInFolder(@PathVariable int id, @RequestBody Note note) {

    }

    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable int id) {}

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable int id, @RequestBody Note note) {}

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable int id) {}
}

