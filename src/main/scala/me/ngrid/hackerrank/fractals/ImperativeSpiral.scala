package me.ngrid.hackerrank.fractals

import scala.collection.mutable

/**
  *
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

  def spiral(size: Int): Spiral = {
    val spiral = mutable.ArraySeq.fill(size, size)(`_`)

    def drawSegment(n: Int, t: (Int) => Position): Option[Position] = {
      def draw(position: Position): Option[Position] = {spiral(position.r).update(position.c, `*`); Option(position)}

      (0 until n).foldLeft[Option[Position]](None) {(pos, x) => draw(t(x))}
    }

    (size % 4).to(size, 4).foldRight[Option[Position]](Some(Position(0, 0)))((n, pos) => {
      for {
        start <- pos
        p1 <- drawSegment(n, start.right)
        p2 <- drawSegment(n, p1.down)
        p3 <- drawSegment(n - 1, p2.left)
        p4 <- drawSegment(n - 2, p3.up)
      } yield p4.right(1)
    })

    spiral
  }

  spiral(12).foreach(x => println(x.mkString))
}
