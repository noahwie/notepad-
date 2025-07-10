// src/components/NoteCard.jsx
import React from "react";

function NoteCard({ note, onClick }) {
  return (
    <div className="note-card" onClick={() => onClick(note)}>
      <div className="note-title">
        {note.title || "Untitled"}
      </div>
    </div>
  );
}

export default NoteCard;