package me.ngrid.hackerrank.fractals

import scala.collection.mutable

/**
  * Draws an n x n spiral by building individual segments and mutating an 2D array.
  */
object ImperativeSpiral extends App {
  val `_` = ' '
  val `*` = '*'

  type Spiral = mutable.ArraySeq[mutable.ArraySeq[Char]]

  case class Position(r: Int, c: Int) {
    def up(n: Int) = copy(r = r - n)
    def down(n: Int) = copy(r = r + n)
    def left(n: Int) = copy(c = c - n)
    def right(n: Int) = copy(c = c + n)
  }

  def spiral(size: Int): (Seq[Position], Spiral) = {
    val spiral = mutable.ArraySeq.fill(size, size)(`_`)

    def draw(position: Position): Unit = spiral(position.r).update(position.c, `*`)

    def loop(n: Int, s: Position): Seq[Position] = {
      def segment(n: Int, t: (Int) => Position): Option[(Seq[Position], Position)]= {
        val o = (0 until n).map(t(_))
        o.lastOption.map((o, _))
      }

      val a = segment(n, s.right)
      val b = a.flatMap(x => segment(n - 1 , x._2.down(1).down))
      val c = b.flatMap(x => segment(n - 2, x._2.left(1).left))
      val d = c.flatMap(x => segment(n - 3, x._2.up(1).up))

      Seq(a, b, c, d).filter(_.nonEmpty).flatMap(_.get._1)
    }

    val (segs, _) = (size % 4).to(size, 4).foldRight((Seq[Position](), Position(0, 0)))((n, r) => {
      val (o, pos) = r
      val a = loop(n, pos)
      a.foreach(draw)

      (o ++ a, a.lastOption.
        map(_.right(1)).
        getOrElse(pos))
    })

    segs -> spiral
  }

  val (s, sp) = spiral(12)
  println(s.map(Position.unapply(_).get).mkString(", "))
  sp.foreach(x => println(x.mkString))
}
