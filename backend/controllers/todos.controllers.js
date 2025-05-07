import {
  getAllTodosRepository,
  createTodoRepository,
  updateTodoRepository,
  deleteTodoRepository,
} from "../repository/todos.repository.js";
import { Response } from "../utils/response.js";

// Obtener todos los todos
export const getTodos = async (req, res) => {
  let responseObj = { ...Response };

  try {
    const todos = await getAllTodosRepository();
    responseObj.status = 200;
    responseObj.message = "Todos obtenidos correctamente";
    responseObj.result = todos;
    res.status(200).json(responseObj);
  } catch (error) {
    console.error("Error al obtener todos:", error);
    responseObj.status = 500;
    responseObj.message = "Error al obtener todos";
    responseObj.result = error.message;
    res.status(500).json(responseObj);
  }
};

// Crear un nuevo todo
export const createTodo = async (req, res) => {
  const { title, completed } = req.body;
  let responseObj = { ...Response };

  if (!title) {
    responseObj.status = 400;
    responseObj.message = "El campo 'title' es obligatorio";
    return res.status(400).json(responseObj);
  }

  try {
    const result = await createTodoRepository(title, completed || 0);
    responseObj.status = 201;
    responseObj.message = "Todo creado correctamente";
    responseObj.result = result;
    res.status(201).json(responseObj);
  } catch (error) {
    console.error("Error al crear todo:", error);
    responseObj.status = 500;
    responseObj.message = "Error al crear todo";
    responseObj.result = error.message;
    res.status(500).json(responseObj);
  }
};

// Actualizar un todo
export const updateTodo = async (req, res) => {
  const { id } = req.params;
  const { title, completed } = req.body;
  let responseObj = { ...Response };

  if (!id || !title) {
    responseObj.status = 400;
    responseObj.message = "Faltan datos requeridos: id y title son obligatorios";
    return res.status(400).json(responseObj);
  }

  try {
    await updateTodoRepository(id, title, completed);
    responseObj.status = 200;
    responseObj.message = "Todo actualizado correctamente";
    res.status(200).json(responseObj);
  } catch (error) {
    console.error("Error al actualizar todo:", error);
    responseObj.status = 500;
    responseObj.message = "Error al actualizar todo";
    responseObj.result = error.message;
    res.status(500).json(responseObj);
  }
};

// Eliminar un todo
export const deleteTodo = async (req, res) => {
  const { id } = req.params;
  let responseObj = { ...Response };

  if (!id) {
    responseObj.status = 400;
    responseObj.message = "El ID es obligatorio";
    return res.status(400).json(responseObj);
  }

  try {
    await deleteTodoRepository(id);
    responseObj.status = 200;
    responseObj.message = "Todo eliminado correctamente";
    res.status(200).json(responseObj);
  } catch (error) {
    console.error("Error al eliminar todo:", error);
    responseObj.status = 500;
    responseObj.message = "Error al eliminar todo";
    responseObj.result = error.message;
    res.status(500).json(responseObj);
  }
};