import db from "../config/db.js";

// Obtener todos los todos
export const getAllTodosRepository = async () => {
  const query = "SELECT * FROM todos";
  return await db.query(query);
};

// Crear un nuevo todo
export const createTodoRepository = async (title, completed = 0) => {
  const query = "INSERT INTO todos (title, completed) VALUES (?, ?)";
  return await db.query(query, [title, completed]);
};

// Actualizar un todo
export const updateTodoRepository = async (id, title, completed) => {
  const query = "UPDATE todos SET title = ?, completed = ? WHERE id = ?";
  return await db.query(query, [title, completed, id]);
};

// Eliminar un todo
export const deleteTodoRepository = async (id) => {
  const query = "DELETE FROM todos WHERE id = ?";
  return await db.query(query, [id]);
};