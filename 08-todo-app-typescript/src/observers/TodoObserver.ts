import { type Todo } from '../types'

type Listener = (todos: Todo[]) => void

export class TodoObserver {
  private static listeners: Listener[] = []

  static subscribe(listener: Listener): void {
    this.listeners.push(listener)
  }

  static unsubscribe(listener: Listener): void {
    this.listeners = this.listeners.filter(l => l !== listener)
  }

  static notify(todos: Todo[]): void {
    this.listeners.forEach(listener => listener(todos))
  }
}