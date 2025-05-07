import { useEffect, useState } from 'react'
import { TodoObserver } from '../observers/TodoObserver'
import { type Todo } from '../types'

export const TodoStats: React.FC = () => {
  const [todos, setTodos] = useState<Todo[]>([])

  useEffect(() => {
    const listener = (updatedTodos: Todo[]) => {
      setTodos(updatedTodos)
    }

    TodoObserver.subscribe(listener)

    return () => {
      TodoObserver.unsubscribe(listener) // Limpiar la suscripción
    }
  }, [])

  const completedCount = todos.filter(todo => todo.completed).length
  const activeCount = todos.length - completedCount

  return (
    <div>
      <p>Tareas activas: {activeCount}</p>
      <p>Tareas completadas: {completedCount}</p>
    </div>
  )
}