package org.vs.algorithms.w2

import org.vs.algorithms.w2.LinkedQueue.Node

object LinkedQueue {
  private case class Node[T](item: T, var next: Option[Node[T]] = None)
}

class LinkedQueue[T] extends Queue[T] {

  private var first: Option[Node[T]] = None
  private var last: Option[Node[T]] = None

  override def enqueue(item: T): Unit = {
    val oldLast = last
    last = Option(Node(item = item))

    if (isEmpty) first = last
    else oldLast.foreach {
      _.next = last
    }
  }

  override def dequeue(): Option[T] = {
    val oldFirst = first
    first = oldFirst.flatMap(_.next)

    if (isEmpty) last = None
    oldFirst.map(_.item)
  }

  override def isEmpty: Boolean = first.isEmpty

  override def size: Int = ???
}
