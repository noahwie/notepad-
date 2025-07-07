package com.noahwie.notepad.repository;

import com.noahwie.notepad.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
