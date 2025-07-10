// src/components/Sidebar.jsx
import React, { useState, useEffect } from "react";
import { getFolders } from "../services/api";
import FolderItem from "./FolderItem";
import PopupFolder from "./PopupFolder";

function Sidebar({ onFolderSelect }) {
  const [folders, setFolders] = useState([]);
  const [showPopup, setShowPopup] = useState(false);

  // Holt die Folder beim ersten Laden
  useEffect(() => {
    fetchFolders();
  }, []);

  const fetchFolders = async () => {
    try {
      const response = await getFolders();
      setFolders(response.data);
    } catch (error) {
      console.error("Fehler beim Laden der Folder:", error);
    }
  };

  const handleFolderClick = (folder) => {
    onFolderSelect(folder); // gibt den gewählten Ordner an die Main-Komponente weiter
  };

  const handleCreateSuccess = () => {
    setShowPopup(false);     // Popup schliessen
    fetchFolders();          // Folder-Liste neu laden
  };

  return (
    <div className="sidebar">
      <button onClick={() => setShowPopup(true)} className="new-folder-btn">
        New Folder +
      </button>

      {folders.map((folder) => (
        <FolderItem
          key={folder.id}
          folder={folder}
          onClick={() => handleFolderClick(folder)}
        />
      ))}

      {showPopup && (
        <PopupFolder
          onClose={() => setShowPopup(false)}
          onCreate={handleCreateSuccess}
        />
      )}
    </div>
  );
}

export default Sidebar;