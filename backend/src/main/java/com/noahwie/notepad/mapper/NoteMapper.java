package com.noahwie.notepad.mapper;

import com.noahwie.notepad.dto.NoteDto;
import com.noahwie.notepad.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteMapper {


    NoteDto toDto(Note note);
    Note toEntity(NoteDto dto);
}