package me.ngrid.hackerrank.dynamicprogramming

import scala.io.StdIn

/**
  *
  */
object ModifiedFibonacci {

  def fib(t1: BigInt, t2: BigInt, n: Int): BigInt = {
    val memo = new Array[BigInt](n)
    memo(0) = t1
    memo(1) = t2
    for(i <- 2 until n){
      memo(i) = memo(i - 1).pow(2) + memo(i - 2)
    }
    memo(memo.length - 1)
  }

  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine().split(' ').map(_.toLong)
    println(fib(input(0), input(1), input(2).toInt))
  }
}
