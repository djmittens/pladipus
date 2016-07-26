package me.ngrid.hackerrank.strings

import scala.collection.mutable.ArrayBuffer


/**
  * Created by grimm on 7/23/16.
  */
object ReducedString {
  import scala.io.StdIn
  import scala.collection.mutable

  def reduce(s: String): String = {
    val o = mutable.Stack[Char]()
    for(c <- s) {
      if(o.isEmpty || o.head != c) o.push(c)
      else o.pop
    }

    new String(o.toArray.reverse)
  }

  def main(args: Array[String]): Unit = {
    val s = reduce(StdIn.readLine())

    if(s.isEmpty) println("Empty String")
    else println(s)
  }
}
