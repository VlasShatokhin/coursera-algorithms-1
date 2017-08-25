package org.vs.algorithms.w2

import scala.annotation.tailrec


object LinkedStack {
  private case class Node[T](item: T, next: Option[Node[T]])

  class StackIterator[T](item: Node[T]) extends Iterator[T] {

    private var current: Option[Node[T]] = Option(item)

    def hasNext: Boolean = current.isDefined
    def remove(): Unit = throw new NotImplementedError("not supported")
    def next(): T = {
      val item = current.map(_.item)
      current = current.flatMap(_.next)
      item.head
    }
  }
}

class LinkedStack[T] extends Stack[T] {
  import LinkedStack.{Node, StackIterator}

  private var first: Option[Node[T]] = None

  override def push(item: T): Unit = first = Some {
    Node[T](item = item, next = first)
  }

  override def pop(): Option[T] = {
    val oldFirst = first
    first = first.flatMap(_.next)
    oldFirst.map(_.item)
  }

  override def isEmpty: Boolean = first.isEmpty

  override def size: Int = count(first)

  @tailrec
  private def count(node: Option[Node[T]], accum: Int = 0): Int = node match {
    case Some(n) => count(n.next, accum + 1)
    case None => accum
  }

  override def iterator: Iterator[T] = new StackIterator(first.orNull)
}
