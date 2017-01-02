package me.ngrid.hackerrank.functional

import java.util.Scanner

object StringCompression {
  @scala.annotation.tailrec
  def compress(p: String, s: String): String = {
    if (s.isEmpty) return p

    val (c, l, r) = countOff(s)
    val pre = l.map { len =>
      s"$p$c$len"
    } getOrElse s"$p$c"

    compress(pre, r)
  }

  def countOff(s: String): (Char, Option[Int], String) = s match {
    case "" => ('\0', None, "")
    case _ =>
      val (a, b) = s.span(_ == s.head)
      val length = if (a.length > 1) Some(a.length) else None
      (a.head, length, b)
  }

  def main(args: Array[String]) {
//    val sc = new Scanner(System.in)
//    System.out.println(compress("", sc.nextLine))
    println(compress("", scala.io.StdIn.readLine()))
  }
}

