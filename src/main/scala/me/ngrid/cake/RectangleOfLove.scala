package me.ngrid.cake

/**
  */
object RectangleOfLove {

  def intersect(s1: (Int, Int), s2:(Int, Int)): Option[(Int, Int)] = {
    Seq(s1, s2).sortBy(_._1) match {
      case Seq(v1, v2) if v1._2 - v2._1 > 0 => Some((v2._1, Math.max(v2._2, v1._2)))
      case _ => None
    }
  }

  def naive(r1: Rectangle, r2: Rectangle): Option[Rectangle] = {
    for {
      x <- intersect(r1.xSegment, r2.xSegment)
      y <- intersect(r1.ySegment, r2.ySegment)
    } yield Rectangle(x, y)
  }
}

object Rectangle {
  def apply(x: (Int, Int), y: (Int, Int)): Rectangle = Rectangle(x._1, y._1, x._2 - x._1, y._2 - y._1)
}

case class Rectangle (x: Int, y: Int, width: Int, height: Int) {
  def xSegment: (Int, Int) = (x, x + width)
  def ySegment: (Int, Int) = (y, y + height)
}
