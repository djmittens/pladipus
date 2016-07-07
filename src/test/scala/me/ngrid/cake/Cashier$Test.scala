package me.ngrid.cake

import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
class Cashier$Test extends FlatSpec with Matchers {
  it should "return some numbers" in {
    Cashier.naive(4, Seq(1, 2, 3)) should be(4)
    Cashier.memoized(4, Seq(1, 2, 3)) should be(4)
    Cashier.iterative(4, Seq(1, 2, 3)) should be(4)
  }
}
