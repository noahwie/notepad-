// src/components/PopupNote.jsx
import React, { useState } from "react";
import { createNote } from "../services/api";

function PopupNote({ folderId, onClose, onCreate }) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!title.trim()) return;

    try {
      await createNote(folderId, { title, content });
      onCreate(); // löst Note-Reload aus
    } catch (error) {
      console.error("Fehler beim Erstellen der Notiz:", error);
    }
  };

  return (
    <div className="popup">
      <div className="popup-content">
        <h3>Create New Note</h3>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Note title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <textarea
            placeholder="Note content..."
            value={content}
            onChange={(e) => setContent(e.target.value)}
            rows={6}
          />

          <div className="popup-buttons">
            <button type="submit" className="create-btn">Create Note</button>
            <button type="button" className="cancel-btn" onClick={onClose}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default PopupNote;
