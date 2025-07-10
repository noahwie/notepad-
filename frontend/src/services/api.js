import axios from 'axios';

const API = axios.create({
  baseURL: 'http://localhost:8080',
});

export const getFolders = () => API.get('/folders');
export const createFolder = (data) => API.post('/folders', data);
export const deleteFolder = (id) => API.delete(`/folders/${id}`);

export const getNotesInFolder = (id) => API.get(`/folders/${id}/notes`);
export const createNote = (id, data) => API.post(`/folders/${id}/notes`, data);
export const getNote = (id) => API.get(`/notes/${id}`);
export const updateNote = (id, data) => API.put(`/notes/${id}`, data);
export const deleteNote = (id) => API.delete(`/notes/${id}`);