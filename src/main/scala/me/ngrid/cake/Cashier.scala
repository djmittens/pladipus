package me.ngrid.cake

import scala.collection.mutable

/**
  *
  */
object Cashier {
  def naive(amount: Int, denominations: Seq[Int]): Int = {
    if(amount == 0 ) return 1
    else if (amount < 0) return 0

    if(denominations.isEmpty) return 0
    println(s"naive $amount : $denominations")

    denominations.zip(denominations.tails.toSeq.tail).flatMap({ z =>
      val (x, left) = z
      (1 to amount / x).map(y => {
        naive(amount - (y * x), left)
      })
    }).sum
  }

  def memoized(amount: Int, denominations: Seq[Int]): Int = {
    val m = mutable.HashMap[(Int, Int), Int]()

    def fire(a:Int, d: Seq[Int]): Int = {
      if(a == 0 ) return 1
      else if (a < 0) return 0
      if(d.isEmpty) return 0

      println(s"memo $a : $d")

      d.zip(d.tails.toSeq.tail).flatMap({ z =>
        val (x, left) = z
        (1 to a / x).map(y => {
          val rem = a - (y * x)
          lazy val res = fire(rem, left)
          m.getOrElseUpdate((x, y), res)
        })
      }).sum
    }

    fire(amount, denominations)
  }

  def iterative(amount: Int, denoms: Seq[Int]): Int = {
    val m = mutable.ArrayBuffer.fill[Int](amount)(1)
    m(denoms.head) = 1

    def getCount(i: Int): Int = if (i > 0) m(i) else 0

    for {
      d <- denoms
      i <- m.indices.drop(2)
    } {
      m(i) += getCount(i - d)
    }

    m.last
  }
}
