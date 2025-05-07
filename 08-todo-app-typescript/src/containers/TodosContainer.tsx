import { Todos } from '../components/Todos'
import { useTodos } from '../hooks/useTodos'

export const TodosContainer: React.FC = () => {
  const {
    todos,
    handleCompleted,
    handleRemove,
    handleUpdateTitle
  } = useTodos()

  return (
    <Todos
      todos={todos}
      setCompleted={handleCompleted}
      setTitle={handleUpdateTitle}
      removeTodo={handleRemove}
    />
  )
}