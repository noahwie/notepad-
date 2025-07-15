package com.noahwie.notepad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
/**
 * Entity representing a single note inside a folder.
 * A note includes a title, content, creation timestamp, and a reference to its folder.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Note {
    /** Unique identifier of the note (primary key). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Title of the note, shown as label on the frontend. */
    private String title;

    /** The main text content of the note. */
    @Column(columnDefinition = "TEXT")
    private String content;

    /** Timestamp when the note was created. */
    private LocalDateTime createdAt = LocalDateTime.now();

    /** Reference to the folder that owns this note. */
    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;
}
