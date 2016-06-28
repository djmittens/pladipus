package me.ngrid.cake

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
  */
@RunWith(classOf[JUnitRunner])
class ProductOfIntsExceptAtIndex$Test extends FlatSpec with Matchers{
  it should "return a sequence with multiples" in {
    ProductOfIntsExceptAtIndex.memoizing(IndexedSeq(1, 2, 6, 5, 9)) should equal(Seq(540, 270, 90, 108, 60))
    ProductOfIntsExceptAtIndex.arrayed(IndexedSeq(1, 2, 6, 5, 9)) should equal(Seq(540, 270, 90, 108, 60))
  }
}
