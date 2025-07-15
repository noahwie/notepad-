package com.noahwie.notepad.service;

import com.noahwie.notepad.dto.NoteDto;
import com.noahwie.notepad.mapper.NoteMapper;
import com.noahwie.notepad.model.Folder;
import com.noahwie.notepad.model.Note;
import com.noahwie.notepad.repository.FolderRepository;
import com.noahwie.notepad.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NoteServiceTest {

    private NoteRepository noteRepository;
    private NoteMapper noteMapper;
    private FolderRepository folderRepository;
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        noteRepository = mock(NoteRepository.class);
        noteMapper = mock(NoteMapper.class);
        folderRepository = mock(FolderRepository.class);
        noteService = new NoteService(noteRepository, noteMapper, folderRepository);
    }

    @Test
    void getNotesInFolder_returnsList() {
        Note note = new Note();
        NoteDto noteDto = new NoteDto();

        when(noteRepository.findByFolderId(1L)).thenReturn(List.of(note));
        when(noteMapper.toDto(note)).thenReturn(noteDto);

        List<NoteDto> result = noteService.getNotesInFolder(1L);

        assertEquals(1, result.size());
        verify(noteRepository).findByFolderId(1L);
    }

    @Test
    void getNoteById_validId_returnsDto() {
        Note note = new Note();
        note.setId(10L);
        NoteDto dto = new NoteDto();
        dto.setId(10L);

        when(noteRepository.findById(10L)).thenReturn(Optional.of(note));
        when(noteMapper.toDto(note)).thenReturn(dto);

        NoteDto result = noteService.getNoteById(10L);

        assertEquals(10L, result.getId());
    }

    @Test
    void createNote_validInput_returnsDto() {
        Long folderId = 1L;
        NoteDto input = new NoteDto();
        input.setTitle("Test Note");
        input.setContent("Test Content");

        Folder folder = new Folder();
        folder.setId(folderId);

        Note noteEntity = new Note();
        noteEntity.setTitle("Test Note");
        noteEntity.setContent("Test Content");

        Note savedNote = new Note();
        savedNote.setId(5L);
        savedNote.setTitle("Test Note");

        NoteDto outputDto = new NoteDto();
        outputDto.setId(5L);
        outputDto.setTitle("Test Note");

        when(folderRepository.findById(folderId)).thenReturn(Optional.of(folder));
        when(noteMapper.toEntity(input)).thenReturn(noteEntity);
        when(noteRepository.save(any(Note.class))).thenReturn(savedNote);
        when(noteMapper.toDto(savedNote)).thenReturn(outputDto);

        NoteDto result = noteService.createNote(folderId, input);

        assertEquals("Test Note", result.getTitle());
        verify(noteRepository).save(noteEntity);
    }

    @Test
    void updateNote_existingNote_returnsUpdatedDto() {
        Note existing = new Note();
        existing.setId(1L);
        existing.setTitle("Old");
        existing.setContent("Old");

        NoteDto update = new NoteDto();
        update.setTitle("New Title");
        update.setContent("New Content");

        Note updated = new Note();
        updated.setId(1L);
        updated.setTitle("New Title");

        NoteDto updatedDto = new NoteDto();
        updatedDto.setId(1L);
        updatedDto.setTitle("New Title");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(noteRepository.save(existing)).thenReturn(updated);
        when(noteMapper.toDto(updated)).thenReturn(updatedDto);

        NoteDto result = noteService.updateNoteById(1L, update);

        assertEquals("New Title", result.getTitle());
    }

    @Test
    void deleteNote_existing_deletesSuccessfully() {
        when(noteRepository.existsById(1L)).thenReturn(true);

        noteService.deleteNote(1L);

        verify(noteRepository).deleteById(1L);
    }

    @Test
    void deleteNote_notExisting_throws() {
        when(noteRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> noteService.deleteNote(99L));
    }
}
