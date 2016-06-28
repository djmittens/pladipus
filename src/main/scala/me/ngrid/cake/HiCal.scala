package me.ngrid.cake

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

  def condenseMeetingTimes(meetings: IndexedSeq[Segment]): Seq[Segment] = {
    Seq.empty
  }
}
