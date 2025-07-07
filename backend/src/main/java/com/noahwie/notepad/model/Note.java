package com.noahwie.notepad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Note {
    // Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

    // Konstruktor

    public Note(Long id, String title, String content, LocalDateTime createdAt, Folder folder) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.folder = folder;
    }

}
