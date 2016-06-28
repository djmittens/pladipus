package me.ngrid.cake

import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  *
  */
@RunWith(classOf[JUnitRunner])
class HiCalTest extends FlatSpec with Matchers {
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

}
