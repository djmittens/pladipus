package me.ngrid.hackerrank.dynamicprogramming

/**
  *
  */
object ArraySplitting {
  import scala.io.StdIn
  import scala.collection.mutable

  case class State(data: Array[Long], lmemo: Array[BigInt], rmemo: Array[BigInt]) {
    val lastIndex = data.length - 1
    calcSums()

    def calcSums(): Unit = {

      var leftSum: BigInt = 0
      var rightSum: BigInt = 0

      for(k <- 0 to lastIndex) {
        leftSum += data(k)
        rightSum += data(lastIndex - k)

        lmemo(k) = leftSum
        rmemo(lastIndex - k) = rightSum
      }
      rightSum = 0
    }

    def recalcSums(split: Int, i: Int, j: Int): Unit = {
      var leftSum: BigInt = 0
      var rightSum: BigInt = 0

      // update left
      for(k <- split + 1 to j) {
        leftSum += data(k)
        lmemo(k) = leftSum
      }

      // update right
      for(k <- (i to split).reverse) {
        rightSum += data(k)
        rmemo(k) = rightSum
      }
      rightSum = 0
    }

    def split(i: Int, j: Int): Int = {
      var split = -1
      for(k <- i until j) {
        if(lmemo(k) == rmemo(k + 1)) {
          split = k
        }
      }

      split
    }
  }

  def maxSplits(data: Array[Long]): Int = {
    val state = State(data, new Array(data.length), new Array(data.length))

    val divs = mutable.Stack[(Int, Int, Int)]()
    divs.push((0, state.lastIndex, 0))
    var maxSoFar = 0

    while(divs.nonEmpty) {
      val (i, j, max) = divs.pop()
      val s = state.split(i, j)

      if(s >= 0) {
        divs.push((i, s, max + 1))
        divs.push((s + 1, j, max + 1))
        maxSoFar = Math.max(maxSoFar, max + 1)
        state.recalcSums(s, i, j)
      }
    }

    maxSoFar
  }


  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    for(i <- 0 until n) {
      StdIn.readLine()
      println(maxSplits(StdIn.readLine().split(' ').map(_.toLong)))
    }
  }
}
