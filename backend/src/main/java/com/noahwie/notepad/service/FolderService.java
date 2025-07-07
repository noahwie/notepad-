package com.noahwie.notepad.service;

import com.noahwie.notepad.dto.FolderDto;
import com.noahwie.notepad.mapper.FolderMapper;
import com.noahwie.notepad.model.Folder;
import com.noahwie.notepad.repository.FolderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FolderService {
    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;

    public FolderService(FolderRepository folderRepository, FolderMapper folderMapper) {
        this.folderRepository = folderRepository;
        this.folderMapper = folderMapper;
    }

    // Get all Folders
    public List<FolderDto> getAllFolders() {
        return folderRepository.findAll()
                .stream()
                .map(folderMapper::toDto)
                .toList();
    }

    // Get folder by Id
    public FolderDto getFolderById(long id) {
        return folderRepository.findById((long) id)
                .map(folderMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Folder not found"));
    }

    // Create new folder
    public FolderDto createFolder(FolderDto folderDto) {
        Folder folder = folderMapper.toEntity(folderDto);
        folder.setCreatedAt(LocalDateTime.now());
        Folder savedFolder = folderRepository.save(folder);
        return folderMapper.toDto(savedFolder);
    }

    // Delete folder by id
    public void deleteFolderById(long id) {
        if (!folderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found");
        }
        folderRepository.deleteById(id);
    }
}
