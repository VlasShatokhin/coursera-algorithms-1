package org.vs.algorithms.w2

object Evaluate {

  private val `*` = (x: Double, y: Double) => x * y
  private val `-` = (x: Double, y: Double) => x - y
  private val `+` = (x: Double, y: Double) => x + y
  private val `/` = (x: Double, y: Double) => x / y
  private val `%` = (x: Double, y: Double) => x % y

  def main(args: Array[String]): Unit = {
    val values: Stack[Double] = new LinkedStack
    val ops: Stack[(Double, Double) => Double] = new LinkedStack

    args.foreach {
      case "(" =>
      case ")" => ops.pop() match {
        case Some(op) =>
          val right = values.pop()
          val left = values.pop()
          if (left.isDefined && right.isDefined)
            values push {
              op apply (left.get, right.get)
            }
          else throw new IllegalArgumentException
        case None => throw new IllegalArgumentException
      }
      case "*" => ops.push(`*`)
      case "-" => ops.push(`-`)
      case "+" => ops.push(`+`)
      case "%" => ops.push(`%`)
      case "/" => ops.push(`/`)
      case n => values.push(n.toDouble)
    }

    println(values.pop())
  }

}
