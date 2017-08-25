package org.vs.algorithms.w1.union_find

import scala.language.postfixOps

class QuickFindUF(n: Int) extends UF {

  private var id = Array.range(0, n)

  override def connected(p: Int, q: Int): Boolean =
    id(p) == id(q)

  // n^2
  override def union(p: Int, q: Int): Unit = {
    val pid = id(p)
    val qid = id(q)

    id = id map {
      case v if v == pid => qid
      case v => v
    }
  }
}
