import React from "react";

function FolderItem({ folder, onClick, onDelete }) {
  return (
    <div className="folder-item" style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
      <span onClick={onClick} style={{ flexGrow: 1, cursor: "pointer" }}>{folder.name}</span>
      <button onClick={() => onDelete(folder)} className="delete-btn">X</button>
    </div>
  );
}


export default FolderItem;