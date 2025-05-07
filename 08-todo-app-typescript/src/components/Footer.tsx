import { type FilterValue } from '../types'
import { Filters } from './Filters'

interface Props {
  handleFilterChange: (filter: FilterValue) => void
  activeCount: number
  completedCount: number
  onClearCompleted: () => void
  filterSelected: FilterValue
}

export const Footer: React.FC<Props> = ({
  activeCount,
  completedCount,
  onClearCompleted,
  filterSelected,
  handleFilterChange
}) => {
  const singleActiveCount = activeCount === 1
  const activeTodoWord = singleActiveCount ? 'tarea' : 'tareas'

  return (
    <footer className="footer">
      {/* Mostrar el conteo de tareas activas */}
      <span className="todo-count">
        <strong>{activeCount}</strong> {activeTodoWord} pendientes
      </span>

      {/* Renderizar los filtros */}
      <Filters
        filterSelected={filterSelected}
        handleFilterChange={handleFilterChange}
      />

      {/* Botón para limpiar tareas completadas */}
      {completedCount > 0 && (
        <button
          className="clear-completed"
          onClick={onClearCompleted}
        >
          Limpiar completados
        </button>
      )}
    </footer>
  )
}
