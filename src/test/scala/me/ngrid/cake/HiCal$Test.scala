package me.ngrid.cake

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
@RunWith(classOf[JUnitRunner])
class HiCal$Test extends FlatSpec with Matchers{
  "isIntersecting" should "true, when first segment contains the second" in {
    HiCal.isIntersecting((0, 3), (1, 2)) should be(true)
  }

  "isIntersecting" should "be false, when right segment is to the right" in {
    HiCal.isIntersecting((0, 1), (2, 3)) should be(false)
  }

  "isIntersecting" should "be false, when left segment is to the right" in {
    HiCal.isIntersecting((0, 1), (2, 3)) should be(false)
  }


  "isIntersecting" should "be true, when there is small overlap" in {
    HiCal.isIntersecting((0, 1), (1, 3)) should be(true)
  }

  "condenseMeetingTimes" should "condense out of order meetings" in {
    HiCal.condenseMeetingTimes(Seq((0, 1), (3, 5), (4, 8), (10, 12), (9, 10))) should be (Seq((0, 1), (3, 8), (9, 12)))
    HiCal.condenseMeetingTimes(Seq((1, 2), (2, 3))) should be (Seq((1,3)))
    HiCal.condenseMeetingTimes(Seq((1, 5), (2, 3))) should be (Seq((1,5)))
    HiCal.condenseMeetingTimes(Seq((1, 10), (2, 6), (3, 5), (7, 9))) should be (Seq((1,10)))
  }
}
