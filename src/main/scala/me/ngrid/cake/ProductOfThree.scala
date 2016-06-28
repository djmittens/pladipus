package me.ngrid.cake

/**
  */
object ProductOfThree {
  def naive(in: IndexedSeq[Int]): Int = {
    var largest = -1
    for(i <- 0 until in.length - 2) {
      for(j <- i + 1 until in.length - 1) {
        for(k <- j + 1 until in.length ) {
          largest = Math.max(largest, in(i) * in(j) * in(k))
        }
      }
    }
    largest
  }

  def memoized(in: IndexedSeq[Int]): Int = {
    var g2 = 0
    var g3 = 0

    for(i <- 2 until in.length) {
      g2 = Math.max(g2, in(i - 2) * in(i - 1))
      g3 = Math.max(g3, g2 * in(i))
    }

    g3
  }
}
