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
/**
 * Service class responsible for handling note-related logic.
 * Supports creating, updating, retrieving and deleting notes within folders.
 */
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

    /**
     * Retrieves all notes belonging to a given folder ID.
     * @param folderId the ID of the folder
     * @return list of notes as DTOs
     */
    public List<NoteDto> getNotesInFolder(Long folderId) {
        return noteRepository.findByFolderId(folderId)
                .stream()
                .map(noteMapper::toDto)
                .toList();
    }

    /**
     * Creates a new note and links it to the specified folder.
     * @param folderId the ID of the folder
     * @param noteDto note data (title, content)
     * @return the created note as DTO
     * @throws ResponseStatusException if folder not found
     */
    public NoteDto createNote(Long folderId, NoteDto noteDto) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));


        Note note = noteMapper.toEntity(noteDto);
        note.setCreatedAt(LocalDateTime.now());
        note.setFolder(folder);

        Note savedNote = noteRepository.save(note);
        return noteMapper.toDto(savedNote);
    }

    /**
     * Retrieves a note by its ID.
     * @param id the note ID
     * @return note as DTO
     * @throws RuntimeException if note not found
     */
    public NoteDto getNoteById(long id) {
        return noteRepository.findById((long) id)
                .map(noteMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    /**
     * Updates the title and content of a note by ID.
     * @param id the note ID
     * @param noteDto updated note data
     * @return updated note as DTO
     * @throws ResponseStatusException if note not found
     */
    public NoteDto updateNoteById(long id, NoteDto noteDto) {
        Note existing = noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));

        existing.setTitle(noteDto.getTitle());
        existing.setContent(noteDto.getContent());

        Note updated = noteRepository.save(existing);
        return noteMapper.toDto(updated);
    }

    /**
     * Deletes a note by its ID.
     * @param id the note ID
     * @throws ResponseStatusException if note not found
     */
    public void deleteNote(long id) {
        if(!noteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
        }
        noteRepository.deleteById(id);
    }



}
