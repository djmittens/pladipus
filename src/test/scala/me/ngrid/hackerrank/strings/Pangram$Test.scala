package me.ngrid.hackerrank.strings

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by grimm on 7/23/16.
  */
class Pangram$Test extends FlatSpec with Matchers{
  behavior of "Pangram"
  it should "totally detect a pangram when it sees one, eg a fox, cmon just a fox" in {
    Pangram.isPangram("The quick brown fox jumps over the lazy dog") should be (true)
  }

  it should "detect a sample pangram" in {
    Pangram.isPangram("We promptly judged antique ivory buckles for the next prize") should be (true)
  }
}
