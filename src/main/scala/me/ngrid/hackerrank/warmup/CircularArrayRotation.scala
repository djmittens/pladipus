package me.ngrid.hackerrank.warmup

object CircularArrayRotation extends App {

  import scala.io.StdIn

  val Array(n, k, q) = StdIn.readLine.split(' ')
  val arr = StdIn.readLine().split(' ')

  val queries = (1 to q.toInt).
    map(_ => StdIn.readLine.toInt - k.toInt)

}
