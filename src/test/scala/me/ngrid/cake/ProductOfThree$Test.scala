package me.ngrid.cake

import org.scalatest.{FlatSpec, Matchers}

/**
  */
class ProductOfThree$Test extends FlatSpec with Matchers{
  it should "Find the basic product" in {
    ProductOfThree.naive(IndexedSeq(9, 10, 12, 4, 2, 11)) should be (1320)
    ProductOfThree.memoized(IndexedSeq(9, 10, 12, 4, 2, 11)) should be (1320)
    ProductOfThree.memoized(IndexedSeq(-10, -10, 1, 3, 2)) should be (300)
    ProductOfThree.memoized(IndexedSeq(-10, 10, 1, -3, 2)) should be (300)

    ProductOfThree.memoized(IndexedSeq(-10, 1, -10, 1, 10 )) should be (1000)
  }
}
