package me.ngrid.hackerrank.strings

import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by grimm on 7/23/16.
  */
class ReducedString$Test extends FlatSpec with Matchers{
  behavior of "String Reducer"
  it should "reduce a basic case" in {
    ReducedString.reduce("aaabccddd") should equal ("abd")
  }

  it should "still work with empty string" in {
    ReducedString.reduce("") should equal ("")
  }

  it should "reduce the unreduceable" in {
    ReducedString.reduce("aaabbbccb") should equal ("a")
  }
}
