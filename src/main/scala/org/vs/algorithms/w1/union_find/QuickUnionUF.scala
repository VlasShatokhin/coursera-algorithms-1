package org.vs.algorithms.w1.union_find

import scala.annotation.tailrec
import scala.language.postfixOps

class QuickUnionUF(n: Int) extends UF {

  private val id = Array.range(0, n)

  override def connected(p: Int, q: Int): Boolean =
    root(p) == root(q)

  override def union(p: Int, q: Int): Unit = {
    val pr = root(p)
    val qr = root(q)

    id(pr) = qr
  }

  @tailrec
  private def root(i: Int): Int = id(i) match {
    case j if j == i => i
    case j => root(j)
  }

}
