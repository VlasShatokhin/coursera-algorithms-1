package org.vs.algorithms.w1.union_find

import scala.annotation.tailrec

class WeightedQuickUnion(n: Int) extends UF {

  private val id = Array.range(0, n)
  private var size = Array.fill(n)(1)

  override def connected(p: Int, q: Int): Boolean =
    root(p) == root(q)

  override def union(p: Int, q: Int): Unit = {
    val pr = root(p)
    val qr = root(q)

    if (pr != qr) {
      if (size(pr) < size(qr)) {
        id(pr) = qr
        size(qr) += size(pr)
        size = size drop pr
      } else {
        id(qr) = pr
        size(pr) += size(qr)
        size = size drop qr
      }
    }
  }

  def getNumberOfGroups: Int = size.length

  @tailrec
  private def root(i: Int): Int = id(i) match {
    case j if j == i => i
    case j => root {
      id(j)
    }
  }

}