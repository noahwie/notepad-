package com.noahwie.notepad.service;

import com.noahwie.notepad.dto.NoteDto;
import com.noahwie.notepad.mapper.NoteMapper;
import com.noahwie.notepad.model.Folder;
import com.noahwie.notepad.model.Note;
import com.noahwie.notepad.repository.FolderRepository;
import com.noahwie.notepad.repository.NoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final FolderRepository folderRepository;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper, FolderRepository folderRepository) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
        this.folderRepository = folderRepository;
    }

    // Get all Notes inside a folder
    public List<NoteDto> getNotesInFolder(Long folderId) {
        return noteRepository.findByFolderId(folderId)
                .stream()
                .map(noteMapper::toDto)
                .toList();
    }

    // create a Note to folder
    public NoteDto createNote(Long folderId, NoteDto noteDto) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));


        Note note = noteMapper.toEntity(noteDto);
        note.setCreatedAt(LocalDateTime.now());
        note.setFolder(folder);

        Note savedNote = noteRepository.save(note);
        return noteMapper.toDto(savedNote);
    }

    // Get a note Id
    public NoteDto getNoteById(long id) {
        return noteRepository.findById((long) id)
                .map(noteMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    // Edit Note
    public NoteDto updateNoteById(long id, NoteDto noteDto) {
        Note existing = noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));

        existing.setTitle(noteDto.title);
        existing.setContent(noteDto.content);

        Note updated = noteRepository.save(existing);
        return noteMapper.toDto(updated);
    }

    // Delete Note
    public void deleteNote(long id) {
        if(!noteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
        }
        noteRepository.deleteById(id);
    }



}
