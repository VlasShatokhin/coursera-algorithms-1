package org.vs.algorithms.w2

trait Queue[T] {

  def enqueue(item: T): Unit

  def dequeue(): Option[T]

  def isEmpty: Boolean

  def size: Int
}
