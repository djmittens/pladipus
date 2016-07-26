package me.ngrid.hackerrank.strings

/**
  * Created by grimm on 7/23/16.
  */
object Pangram {
  import scala.collection.mutable
  import scala.io.StdIn

  def isPangram(s: String): Boolean = {
    val m = mutable.Set[Char]()
    for(c <- s) {
      if(c.isLetter) m += c.toLower
    }
    m.size == 26
  }

  def main(args: Array[String]): Unit = {
    val o = if(isPangram(StdIn.readLine())) "pangram" else "not pangram"
    println(o)
  }
}
