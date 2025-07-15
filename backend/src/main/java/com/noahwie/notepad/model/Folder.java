package com.noahwie.notepad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Entity representing a folder that groups related notes.
 * A folder contains a name, creation timestamp, and a list of notes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Folder {
    /** Unique identifier of the folder (primary key). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the folder defined by the user. */
    private String name;

    /** Timestamp when the folder was created. */
    private LocalDateTime createdAt = LocalDateTime.now();

    /** List of notes that belong to this folder. */
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;
}
