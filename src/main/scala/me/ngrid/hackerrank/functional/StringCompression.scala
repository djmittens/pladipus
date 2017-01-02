package me.ngrid.hackerrank.functional

import scala.collection.mutable.ArrayBuffer

object StringCompression {
  @scala.annotation.tailrec
  def compress(p: ArrayBuffer[Char], s: Seq[Char]): ArrayBuffer[Char] = {
    if (s.isEmpty) return p

    val (c, l, r) = countOff(s)
    val pre = l.map { len =>
      p += c
      p ++= len.toString
    } getOrElse {p += c}

    compress(pre, r)
  }

  def countOff(s: Seq[Char]): (Char, Option[Int], Seq[Char]) = {
    val (a, b) = s.span(_ == s.head)
    val length = if (a.length > 1) Some(a.length) else None
    (a.head, length, b)
  }

  def main(args: Array[String]) {
    compress(ArrayBuffer(), scala.io.StdIn.readLine()).foreach(print)
  }
}

