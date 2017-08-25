package org.vs.algorithms.w2

trait Stack[T] extends Iterable[T] {

  def push(item: T): Unit
  def pop(): Option[T]
  def isEmpty: Boolean
  def size: Int

}
