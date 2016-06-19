package me.ngrid.hackerrank.fractals

/**
  * draw a tree structure for dem peeps
  */
object Tree extends App {

  def getV(height: Int): Seq[String] = {
    def getScore(r: Int, n: Int): String = {
      Seq.fill(r)('_').updated(n, '1').mkString
    }

    (0 until height).
      map(getScore(height, _)).
      map(x => s"${x}_${x.reverse}")
  }

  def getI(height: Int): Seq[String] = {
    Seq.fill(height, height * 2 + 1)('_').map(_.updated(height, '1').mkString)
  }

   def drawTree(height: Int, maxIt: Int): Unit = {
     if(maxIt == 0) getV(height) ++ getI(height)
   }
  getV(11).foreach(println)
  getI(11).foreach(println)
}
