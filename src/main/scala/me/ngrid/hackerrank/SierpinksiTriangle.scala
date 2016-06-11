package me.ngrid.hackerrank

/**
  * Draws an ascii Sierpinski triangle.
  */
object SierpinksiTriangle extends App{

  def getTriangle(base: Int): Seq[String] = 1.to(base, 2).map(x => pad(Seq.fill(x)('1').mkString, (base - x)/2))

  def padRectangle (d: Seq[String], to: Int): Seq[String] = d.map(x=> pad(x, to - x.length))

  def pad(o: String, n: Int) = {
    def padding(len: Int): String = Seq.fill(len)('_').mkString
    s"${padding(n)}$o${padding(n)}"
  }

  def drawTriangles(base: Int = 63, n: Int = 1000): Unit = {

    def calculate(b: Int, n: Int): Seq[String] = {
      if(n == 0 || b <= 1)  return getTriangle(b)
      packTriangles(calculate(b / 2, n - 1))
    }

    def packTriangles(t: Seq[String]): Seq[String] = {
      padRectangle(t, t.head.length + t.head.length/2 + 1) ++ t.map(x => x ++ "_" ++ x)
    }

    calculate(base, n).foreach(println)
  }

  drawTriangles(base = 20 , n = 20)
}
