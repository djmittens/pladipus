package me.ngrid.hackerrank.blackrockcodesprint

/**
  *
  */
object FixedIncomeSecurity {

  case class Portfolio(id: String, ordered: Int, allocated: Int = 0) {
    def amend(ordered: Int) = copy(ordered = ordered)

    def allocate(amt: Int) = copy(ordered = ordered - amt, allocated = allocated + amt)

    def propAllocation(totalOrdered: Int, incomeSecurity: IncomeSecurity): Double =
      ordered.toDouble / totalOrdered.toDouble * incomeSecurity.available.toDouble
  }

  case class IncomeSecurity(minTradeSize: Int, increment: Int, available: Int) {
    //    def tradeableAmount(n: Int) = minTradeSize + (increment * n)
    //    def isTradeableAmount(n: Int):Boolean = (n - minTradeSize) % increment == 0

    def isTradeable(n: Int) = (n >= minTradeSize) && ((n - minTradeSize) % increment == 0)

    //    @scala.annotation.tailrec
    def findTradeableAmount(n: Int, portfolio: Portfolio): Int = {
      val amt = n - ((n - minTradeSize) % increment)
      if (amt > 0 && isTradeable(portfolio.ordered - amt)) amt else 0
    }

    def allocate(a: Int) = copy(available = available - a)
  }


  def allocateSecurity(security: IncomeSecurity, orders: Seq[Portfolio]): Seq[Portfolio] = {

    def doAllocate(security: IncomeSecurity): PartialFunction[(Portfolio, Double), (Portfolio, Int)] = {
      // Oh man if this thing sucks then let it suck

      // Give it another spin, maybe we can just give them the tradesize
      case (portfolio, propAlloc) if propAlloc < security.minTradeSize && propAlloc >= (security.minTradeSize / 2) =>
        doAllocate(security)(portfolio, security.minTradeSize)

      case (portfolio, propAlloc) if propAlloc >= security.minTradeSize =>
        val amt =
          if (propAlloc >= portfolio.ordered) portfolio.ordered
          else security.findTradeableAmount(propAlloc.toInt, portfolio)
        (portfolio.allocate(amt), amt)

      case (portfolio, propAlloc) => (portfolio, 0)
    }

    //      .map(o => o -> o.propAllocation(orderedTotal, security.available))
    val sorted = orders.sortBy(x => (x.ordered, x.id))
    val (o, s) = sorted
      .zip(sorted.tails.toSeq.map(_.map(_.ordered).sum))
      .foldLeft((Seq[Portfolio](), security))((res, p) => {
        val (a, amt) = doAllocate(res._2)(p._1, p._1.propAllocation(p._2, res._2))
        (a +: res._1, res._2.allocate(amt))
      })

    o
  }

  def main(args: Array[String]) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution */
    val n = io.StdIn.readInt

    val security = io.StdIn.readLine.split(' ').map(_.toInt) match {
      case Array(a, b, c) => IncomeSecurity(a, b, c)
    }

    val orders = (0 until n).map(_ => io.StdIn.readLine.split(' ')).map({
      case Array(name, order) => Portfolio(name, order.toInt)
    })

    allocateSecurity(security, orders).sortBy(_.id).foreach(x => println(s"${x.id} ${x.allocated}"))
  }
}

