package me.ngrid.hackerrank.blackrockcodesprint

/**
  *
  */
object Arbitrage {
  def arbitrate(uE:BigDecimal, eG: BigDecimal, gU: BigDecimal, usd:BigDecimal = 100000): BigDecimal = {
    Option(usd).
      map(_ / uE / eG / gU).
      map(_ - usd).
      map(_.setScale(0, BigDecimal.RoundingMode.FLOOR)).
      filter(_ > 0).
      getOrElse(0)
  }
}
