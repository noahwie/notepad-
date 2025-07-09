package com.noahwie.notepad.mapper;

import com.noahwie.notepad.dto.FolderDto;
import com.noahwie.notepad.model.Folder;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = NoteMapper.class)
public interface FolderMapper {

    FolderDto toDto(Folder folder);
    Folder toEntity(FolderDto dto);
}
