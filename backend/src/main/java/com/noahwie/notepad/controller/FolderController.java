package com.noahwie.notepad.controller;

import com.noahwie.notepad.dto.FolderDto;
import com.noahwie.notepad.service.FolderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @GetMapping
    public List<FolderDto> getAllFolders() {
        return folderService.getAllFolders();
    }

    @GetMapping("/{id}")
    public FolderDto getFolderById(@PathVariable long id) {
        return folderService.getFolderById(id);
    }

    @PostMapping
    public FolderDto createFolder(@RequestBody @Valid FolderDto folderDto) {
        return folderService.createFolder(folderDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFolderById(@PathVariable long id) {
       folderService.deleteFolderById(id);
    }
}
