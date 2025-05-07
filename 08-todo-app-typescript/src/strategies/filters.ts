import { type Todo } from '../types'

export interface FilterStrategy {
  filter(todos: Todo[]): Todo[]
}

export class AllFilter implements FilterStrategy {
  filter(todos: Todo[]): Todo[] {
    return todos
  }
}

export class ActiveFilter implements FilterStrategy {
  filter(todos: Todo[]): Todo[] {
    return todos.filter(todo => !todo.completed)
  }
}

export class CompletedFilter implements FilterStrategy {
  filter(todos: Todo[]): Todo[] {
    return todos.filter(todo => todo.completed)
  }
}

export const getFilterStrategy = (filter: string): FilterStrategy => {
  switch (filter) {
    case 'active':
      return new ActiveFilter()
    case 'completed':
      return new CompletedFilter()
    default:
      return new AllFilter()
  }
}