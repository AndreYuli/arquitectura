import { type Todo } from '../types'

export class TodoFactory {
  static create(title: string): Todo {
    return {
      id: crypto.randomUUID(),
      title,
      completed: false
    };
  }
}