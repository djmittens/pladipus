package me.ngrid.hackerrank.dynamicprogramming


/**
  * Created by grimm on 7/22/16.
  */
object MaxSubarray {

  import scala.io.StdIn

  def dp(data: Array[Long]): (Long, Long) = {
    if(data == null || data.isEmpty) return (0, 0)

    def sum(i: Int, j: Int) = {
      var sum: Long = 0
      for(k <- i to j)
        sum += data(k)
      sum
    }

    val cmemo = new Array[Long](data.length)

    var holyMax = data(0)

    for (i <- data.indices) {
      for (j <- i until data.length) {
        val s = data.slice(i, j + 1)

        val suffix = if(i == 0) data(i) else cmemo(j)
        val prefix = if(j == 0) Long.MinValue else cmemo(j - 1)
        cmemo(j) = Math.max(prefix, Math.max(suffix, s.sum))
      }

      if(i != 0) holyMax = Math.max(holyMax + data(i), Math.max(holyMax, data(i)))
    }

    (cmemo.last, holyMax)
  }

  def dp2(data: Array[Long]): (Long, Long) = {
    if(data == null || data.isEmpty) return (0, 0)

    var holyMax = data(0)

    var maxSoFar, maxEndingHere = data(0)
    for (i <- data.indices.tail) {
      maxEndingHere = Math.max(data(i), data(i) + maxEndingHere)
      maxSoFar = Math.max(maxSoFar, maxEndingHere)
      holyMax = Math.max(holyMax + data(i), Math.max(holyMax, data(i)))
    }
    (maxSoFar, holyMax)
  }


  def main(args: Array[String]): Unit = {
    val cases = StdIn.readInt()
    for (i <- 0 until cases) {
      StdIn.readInt()
      val (c, m) = dp2(StdIn.readLine().split(' ').map(_.toLong))
      println(s"$c $m")
    }
  }
}
