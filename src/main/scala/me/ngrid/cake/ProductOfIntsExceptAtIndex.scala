package me.ngrid.cake

import scala.collection.mutable

/**
  */
object ProductOfIntsExceptAtIndex {
  /**
    *  * => i
    *  _, 2, 3, 4, 5
    *  1, _, 3, 4, 5
    *  1, 2, _, 4, 5
    *  1, 2, 3, _, 5
    *  1, 2, 3, 4, _
    *
    *  out = left(i - 1) * right (i + 1)
    *  right (n) = right right(n) * (n + 1)
    *  left (n) = left(n) * left(n - 1)
   */
  def memoizing(a: IndexedSeq[Int]): Seq[Int] = {

    // Store the result of computation here.
    val leftMemo =  mutable.HashMap[Int, Int]()
    val rightMemo = mutable.HashMap[Int, Int]()

    // Seed the memos with boundary conditions.
    leftMemo.put(-1, 1)
    rightMemo.put(a.length, 1)

    def memo(n: Int, m: mutable.HashMap[Int, Int], fN: () => Int): Int = {
      val out = m.getOrElse(n, a(n) * fN())
      m.put(n, out)
      out
    }

    def left(n: Int): Int = {
      println(s"left memoing $n")
      memo(n, leftMemo, () => left(n - 1))
    }

    def right(n: Int): Int = {
      println(s"right memoing $n")
      memo(n, rightMemo, () => right(n + 1))
    }

    a.indices.map(i => left(i - 1) * right(i + 1))
  }

  def arrayed(a: IndexedSeq[Int]): Seq[Int] = {
    val o = mutable.ArrayBuffer.fill[Int](a.length)(1)

    for(i <- o.indices.reverse.tail) {
      o.update(i, a(i + 1) * o(i + 1))
    }

    a.indices.foldLeft(1) ((left, i) => {
      o.update(i, left * o(i))
      left * a(i)
    })

    o
  }
}
