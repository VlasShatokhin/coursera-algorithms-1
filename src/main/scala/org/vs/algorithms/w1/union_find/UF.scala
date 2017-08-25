package org.vs.algorithms.w1.union_find

trait UF {

  def connected(p: Int, q: Int): Boolean

  def union(p: Int, q: Int): Unit
}
