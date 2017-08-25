package org.vs.algorithms.w2

class ArrayStack[T] { /*extends Stack[T] {

  var N = 0
  var s = new Array[T](1)

  override def push(item: T): Unit = {
    if (N == s.length) resize(2 * s.length)
    s(N += 1) = item
  }

  override def pop(): Some[T] = {
    N -= 1
    val item: T = s(N)
    s(N) = null
    if (N > 0 && N == s.length / 4) resize(s.length / 2)
    Some(item)
  }

  override def isEmpty: Boolean = N == 0

  override def size: Int = N

  private def resize(capacity: Int): Unit = {
    val copy = new Array[T](capacity)
    var i = 0
    while (i < N) {
      copy(i) = s(i)
      i += 1
    }
  }*/
}
