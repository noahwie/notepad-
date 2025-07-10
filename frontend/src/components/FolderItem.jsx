// src/components/FolderItem.jsx
import React from "react";

function FolderItem({ folder, onClick }) {
  return (
    <div className="folder-item" onClick={onClick}>
      {folder.name}
    </div>
  );
}

export default FolderItem;