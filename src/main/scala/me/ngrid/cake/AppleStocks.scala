package me.ngrid.cake

/**
  * Fun problem, to discover maximum profit that could be made from a time sequence of stock prices from previous day.
  */
object AppleStocks {

  def getMaxExchange(y: Seq[Int]) = {
    var max = y(1) - y.head
    var min = y.head

    for(i <- y.tail) {
      max = Math.max(max, i - min)
      min = Math.min(min, i)
    }

    max
  }

  def getMaxExchangeRec(y: Seq[Int]) = {

    @scala.annotation.tailrec
    def gME(s: Seq[Int], min: Int, max: Int): Int = {
      if (s.isEmpty) return max
      gME(s.tail, Math.min(min, s.head), Math.max(max, s.head - min))
    }

    gME(y.tail, y.head, y(1) - y.head)
  }
}
