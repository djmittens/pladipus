package me.ngrid.hackerrank.dynamicprogramming

import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  * Created by grimm on 7/22/16.
  */
@RunWith(classOf[JUnitRunner])
class MaxSubarray$Test extends FlatSpec with Matchers {

  behavior of "Max Subarrays"
  it should "Find the max subarray of a simple array" in {
    MaxSubarray.dp(Array(1,-2,2,3,-1,4)) should equal((8, 10))
    MaxSubarray.dp2(Array(1,-2,2,3,-1,4)) should equal((8, 10))
  }

  it should "Find the right subarrays with negatives" in {
    MaxSubarray.dp(Array(2,-1,2,3,4,-5)) should equal((10, 11))
    MaxSubarray.dp2(Array(2,-1,2,3,4,-5)) should equal((10, 11))
  }

  it should "Find negative max too" in {
    MaxSubarray.dp(Array(-1,-2,-3,-4,-5,-6)) should equal((-1, -1))
    MaxSubarray.dp2(Array(-1,-2,-3,-4,-5,-6)) should equal((-1, -1))
  }

  it should "Not break in this case" in {
    MaxSubarray.dp(Array[Long](-3, -2, -1)) should equal((-1, -1))
    MaxSubarray.dp2(Array[Long](-3, -2, -1)) should equal((-1, -1))
  }

  it should "handle an empty array" in {
    MaxSubarray.dp(Array[Long]()) should equal((0, 0))
    MaxSubarray.dp2(Array[Long]()) should equal((0, 0))
  }

  it should "handle an array with 1 element" in {
    MaxSubarray.dp(Array[Long](1)) should equal((1, 1))
    MaxSubarray.dp2(Array[Long](1)) should equal((1, 1))
  }

  it should "handle "
}
