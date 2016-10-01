package me.ngrid.hackerrank.warmup

object Staircase extends App {
  val n = scala.io.StdIn.readInt()
  (1 to n).map(x => Seq.fill(n-x)(' ') ++ Seq.fill(x)('#')).
    map(_.mkString).
    foreach(println)
}
