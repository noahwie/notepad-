package com.noahwie.notepad.repository;

import com.noahwie.notepad.model.Folder;
import org.springframework.data.repository.CrudRepository;

public interface FolderRepository extends CrudRepository<Folder, Long> {
}
