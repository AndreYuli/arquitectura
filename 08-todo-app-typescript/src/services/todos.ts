import { TodoRepository } from '../repositories/TodoRepository'

// Reexportar las funciones del repositorio
export const fetchTodos = TodoRepository.fetchTodos
export const updateTodos = TodoRepository.updateTodos
