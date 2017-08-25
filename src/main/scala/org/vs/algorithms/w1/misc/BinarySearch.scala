package org.vs.algorithms.w1.misc

import scala.annotation.tailrec

object BinarySearch {

  @tailrec
  private def search(key: Int, array: Array[Int],
                     low: Int, high: Int): Int = low + (high - low) / 2 match {
    case _ if low >= high => -1
    case mid if key < array(mid) => search(key, array, low, high = mid - 1)
    case mid if key > array(mid) => search(key, array, low = mid + 1, high)
    case mid => mid
  }

  def search(key: Int, array: Array[Int]): Int =
    search(key, array, 0, array.length - 1)

}
