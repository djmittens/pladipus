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
    var l2 = 0
    var g3 = 0

    for(i <- 2 until in.length) {
      val p = in(i - 2) * in(i - 1)
      g2 = Math.max(g2, p)
      l2 = Math.min(l2, p)
      g3 = Math.max(g3, Math.max(in(i) * g2, in(i) * l2))
    }

    g3
  }
}
