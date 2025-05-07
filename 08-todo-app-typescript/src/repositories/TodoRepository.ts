import { type TodoList } from '../types'

const API_URL = 'https://api.jsonbin.io/v3/b/63ff3a52ebd26539d087639c'

export class TodoRepository {
  static async fetchTodos(): Promise<TodoList> {
    const res = await fetch(API_URL)
    if (!res.ok) {
      console.error('Error fetching todos')
      return []
    }
    const { record: todos } = await res.json() as { record: TodoList }
    return todos
  }

  static async updateTodos(todos: TodoList): Promise<boolean> {
    const res = await fetch(API_URL, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'X-Master-Key': import.meta.env.VITE_API_BIN_KEY
      },
      body: JSON.stringify(todos)
    })
    return res.ok
  }
}