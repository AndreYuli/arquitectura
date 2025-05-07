import express from "express";
import {
  getTodos,
  createTodo,
  updateTodo,
  deleteTodo,
} from "../controllers/todos.controllers.js";

const api = express.Router();

// Rutas para la tabla "todos"
api.get("/todos", getTodos); // Obtener todos los todos
api.post("/todos", createTodo); // Crear un nuevo todo
api.put("/todos/:id", updateTodo); // Actualizar un todo por ID
api.delete("/todos/:id", deleteTodo); // Eliminar un todo por ID

export default api;
