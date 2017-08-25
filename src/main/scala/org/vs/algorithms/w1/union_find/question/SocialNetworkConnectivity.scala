package org.vs.algorithms.w1.union_find.question

import java.time.LocalDateTime

import org.vs.algorithms.w1.union_find.WeightedQuickUnion

import scala.annotation.tailrec
import scala.io.Source
import scala.language.postfixOps

object SocialNetworkConnectivity {

  private type Friendship = (Int, Int, LocalDateTime)

  private val fileName = "friendship.txt"
  private val numOfUsers = 100

  def main(args: Array[String]): Unit = {

    val formationDt = formTheGroup(
      friendships = Source.fromFile(fileName).getLines.toIterable map parseLine,
      uf = new WeightedQuickUnion(numOfUsers))

    println(s"group formed at $formationDt")
  }

  @tailrec
  private def formTheGroup(friendships: Iterable[Friendship], uf: WeightedQuickUnion): LocalDateTime = friendships match {
    case Nil => throw new RuntimeException("group is not formed")
    case (user1, user2, dt) :: tail =>
      uf.union(user1, user2)
      if (uf.getNumberOfGroups > 1) formTheGroup(tail, uf) else dt
  }

  private def parseLine(line: String): Friendship = line split(" ", 2) match {
    case Array(id1String, id2String, timeString) => (
      id1String.toInt,
      id2String.toInt,
      LocalDateTime.parse(timeString)
    )
  }


}

