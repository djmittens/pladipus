package me.ngrid.cake

import scala.collection.mutable
import scala.util.Try
/**
  * ****
  *   *****
  *           ******
  *       *****
  *  ===
  * *****************
  */
object HiCal {
  type Segment = (Int, Int)

  def isIntersecting(a: Segment, b: Segment): Boolean = {
    val(x1, y1) = a
    val(x2, y2) = b

    if(x1 > y2 ) return false
    else if (x2 > y1) return false

    true
  }

  def merge(a: Segment, b: Segment): Segment = {
    val(x1, y1) = a
    val(x2, y2) = b

    (Math.min(x1, x2), Math.max(y1, y2))
  }

  def condenseMeetingTimes(meetings: Seq[Segment]): Seq[Segment] = {
    val o = mutable.Stack[Segment]()

    def getNextSegment(a: Segment, b: Segment): Segment = {
      if(isIntersecting(a, b)) merge(a, b)
      else {o.push(a); b}
    }

    meetings.sortBy(_._1).foreach(b => {
      val x = Try(o.pop()).map(getNextSegment(_, b))

      o.push(x.getOrElse(b))
    })

    o.reverse
  }
}
