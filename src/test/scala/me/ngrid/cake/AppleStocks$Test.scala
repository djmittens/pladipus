package me.ngrid.cake

import org.scalatest.{FlatSpec, Matchers}

/**
  */
class AppleStocks$Test extends FlatSpec with Matchers {
  it should "compute maximum value" in {
    AppleStocks.getMaxExchange(Seq(10, 7, 5, 8, 11, 9)) should equal (6)
    AppleStocks.getMaxExchangeRec(Seq(10, 7, 5, 8, 11, 9)) should equal (6)
  }

  it should "report least losess" in {
    AppleStocks.getMaxExchange(Seq(15, 11, 8, 4, 1)) should equal (-3)
    AppleStocks.getMaxExchangeRec(Seq(15, 11, 8, 4, 1)) should equal (-3)
  }
}
