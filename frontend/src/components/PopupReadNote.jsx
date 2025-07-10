// src/components/PopupReadNote.jsx
import React, { useState } from "react";
import { deleteNote, updateNote } from "../services/api";

function PopupReadNote({ note, onClose, onUpdate }) {
  const [isEditing, setIsEditing] = useState(false);
  const [title, setTitle] = useState(note.title);
  const [content, setContent] = useState(note.content);

  const handleDelete = async () => {
    if (!window.confirm("Delete this note?")) return;

    try {
      await deleteNote(note.id);
      onUpdate();
    } catch (error) {
      console.error("Fehler beim Löschen:", error);
    }
  };

  const handleUpdate = async () => {
    try {
      await updateNote(note.id, { title, content });
      setIsEditing(false);
      onUpdate();
    } catch (error) {
      console.error("Fehler beim Aktualisieren:", error);
    }
  };

  return (
    <div className="popup">
      <div className="popup-content">
        <button className="close-btn" onClick={onClose}>X</button>

        {isEditing ? (
          <>
            <input
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
            <textarea
              rows={6}
              value={content}
              onChange={(e) => setContent(e.target.value)}
            />
            <button onClick={handleUpdate}>Done Editing</button>
          </>
        ) : (
          <>
            <h3>{note.title}</h3>
            <p>{note.content}</p>
            <button onClick={() => setIsEditing(true)}>Edit Note</button>
          </>
        )}

        <button onClick={handleDelete} className="delete-btn">Delete Note</button>
      </div>
    </div>
  );
}

export default PopupReadNote;
