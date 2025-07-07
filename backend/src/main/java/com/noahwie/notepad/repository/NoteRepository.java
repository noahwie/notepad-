package com.noahwie.notepad.repository;

import com.noahwie.notepad.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByFolderId(Long folderId);
}
