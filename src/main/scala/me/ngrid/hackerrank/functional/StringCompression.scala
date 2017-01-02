package me.ngrid.hackerrank.functional

import scala.collection.mutable.ArrayBuffer

object StringCompression {
  @scala.annotation.tailrec
  def compress(p: ArrayBuffer[Char], s: Seq[Char]): ArrayBuffer[Char] = {
    if (s.isEmpty) return p
    val (c, l, r) = countOff(s)
    p += c
    if(l > 1) p ++= l.toString
    compress(p, r)
  }

  def countOff(s: Seq[Char]): (Char, Int, Seq[Char]) = {
    val (a, b) = s.span(_ == s.head)
    (a.head, a.length, b)
  }

  def main(args: Array[String]) {
    compress(ArrayBuffer(), scala.io.StdIn.readLine()).foreach(print)
  }
}

