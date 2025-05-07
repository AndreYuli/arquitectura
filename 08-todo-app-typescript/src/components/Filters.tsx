import { TODO_FILTERS } from '../consts.js'
import { type FilterValue } from '../types.js'

const FILTERS_BUTTONS = {
  [TODO_FILTERS.ALL]: { literal: 'All', href: `/?filter=${TODO_FILTERS.ALL}` },
  [TODO_FILTERS.ACTIVE]: { literal: 'Active', href: `/?filter=${TODO_FILTERS.ACTIVE}` },
  [TODO_FILTERS.COMPLETED]: { literal: 'Completed', href: `/?filter=${TODO_FILTERS.COMPLETED}` }
} as const

interface Props {
  handleFilterChange: (filter: FilterValue) => void
  filterSelected: FilterValue
}

export const Filters: React.FC<Props> = ({ filterSelected, handleFilterChange }) => {
  return (
    <ul className="filters">
      {Object.entries(FILTERS_BUTTONS).map(([key, { href, literal }]) => {
        const isSelected = key === filterSelected
        const className = isSelected ? 'selected' : ''

        return (
          <li key={key}>
            <a
              href={href}
              className={className}
              onClick={(e) => {
                e.preventDefault()
                handleFilterChange(key as FilterValue)
              }}
            >
              {literal}
            </a>
          </li>
        )
      })}
    </ul>
  )
}
