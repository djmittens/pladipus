package me.ngrid.hackerrank.blackrockcodesprint

/**
  *
  */
object FixedIncomeSecurity {
  case class Portfolio(id: String, ordered: Int, allocated: Double = 0) {
    def allocate(amt: Int) = copy(allocated = amt)
    def propAllocation(total: Int, available: Int): Int = ordered / total * available
  }

  case class FixedIncomeSecurity(minTradeSize: Int, increment: Int, available: Int) {
    def tradeableAmount(n: Int) = minTradeSize + (increment * n)
    def updateAvailable(a: Int) = copy(available = a)
  }

  def allocateSecurity(security: FixedIncomeSecurity, orders: Seq[Portfolio]): Seq[Portfolio] = {
    if(security.available < security.minTradeSize || orders.isEmpty) return  orders


    def allocateAll(orders: Seq[(Portfolio, Int)], available: Int): (Seq[Portfolio], Int)= {
      orders.foldLeft((Seq[Portfolio](), available))((res, o) => {
        val k = Option(o).
          filter({case (ord, alloc) => alloc > ord.ordered }).
          map({
            case (ord, alloc) => ord.allocate(alloc)
          }).
          getOrElse(o._1)
        res match {
          case (orders, available) =>
            (k +: orders, available - k.ordered)
        }
      })
    }

    val orderedTotal = orders.map(_.ordered).sum
    val (ready, unAllocated) = orders.sortBy(x => (x.ordered, x.id))
      .map( o => o -> o.propAllocation(orderedTotal, security.available))
      .partition(_._2 > security.minTradeSize)

    allocateAll(ready, security.available)
  }
}

