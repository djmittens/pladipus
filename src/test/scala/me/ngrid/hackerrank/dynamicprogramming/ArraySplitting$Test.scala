package me.ngrid.hackerrank.dynamicprogramming

import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
class ArraySplitting$Test extends FlatSpec with Matchers {

  import ArraySplitting._

  behavior of "Nikitas Game"
  it should "split your basic array" in {
    maxSplits(Array(4, 4, 4, 4)) should be(2)
    maxSplits(Array(4, 1, 0, 1, 1, 0, 1)) should be(3)
    maxSplits(Array(3, 3, 3)) should be(0)
  }

//  it should "pass test case # 2" in {
//    maxSplits(getArray(test2_1)) should be(0)
//    maxSplits(getArray(test2_2)) should be(0)
//    maxSplits(getArray(test2_3)) should be(12)
//  }


  def getArray(s: String) = s.split(' ').map(_.toLong)
}

