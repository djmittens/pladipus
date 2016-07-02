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
    var g = Math.max(in(0), in(1))
    var l = Math.min(in(0), in(1))
    var g2 = in(0) * in(1)
    var l2 = g2
    var g3 = g2 * in(2)

    for(i <- 2 until in.length) {
      g3 = Math.max(g3, Math.max(in(i) * g2, in(i) * l2))
      g2 = Math.max(g2, Math.max(in(i) * g, in(i) * l))
      l2 = Math.min(l2, Math.min(in(i) * g, in(i) * l))
      l = Math.min(in(i), l)
      g = Math.max(in(i), g)
    }

    g3
  }
}
