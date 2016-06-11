package me.ngrid.hackerrank


/**
  */
object SimpleFunctionalProbs {

  def reverseList(arr: List[Int]): List[Int] = {
    arr.foldLeft(List[Int]())((a, b) => b :: a)
  }

  def eToThe(x: Float): Float = {
    def fac(n: Int): Double = (1 to n).product

    def doTheE(ex: Double, n: Int = 2): Stream[Double] = {
      Stream.cons(ex, doTheE((Math.pow(x, n) / fac(n)) + ex, n + 1))
    }

    1f + doTheE(x)(8).toFloat
  }

  @scala.annotation.tailrec
  def gcd(x: Int, y: Int): Int = {
    val rem = y % x
    if (rem == 0)
      return x
    gcd(rem, x)
  }

  def fibonacci(x: Int): Int = {
    // Fill Up this function body
    // You can add another function as well, if required
    def doTheFib(c: Int = 0, p: Int = 1): Stream[Int] = {
      val k = c + p
      Stream.cons(k, doTheFib(p, k))
    }
    (0 #:: 0 #:: 1 #:: doTheFib()) (x)
  }

  //  @scala.annotation.tailrec
  def pascalsTriangle(n: Int): Unit = {
    def fac(n: Int): Int = (1 to n).product
    def calcValue(r: Int): Int = fac(n) / (fac(r) * fac(n - r))

    lazy val k = (0 to n).map(calcValue).mkString(" ")

    if (n > 0) pascalsTriangle(n - 1)
    println(k)
  }

  def stringMingling(a: String, b: String): Unit = a.zipAll(b, "", ""). foreach({case (a, b) => print(a); print(b)})

  def stringRotations(str: String)(n: Int = str.length): Seq[String] = {
    def doRotate(s: String): Stream[String] = {
      val h = s.tail + s.head
      Stream.cons(h, doRotate(h))
    }

    doRotate(str).take(n)
  }

//  val k = (0 until io.StdIn.readInt).map(_ => io.StdIn.readLine).map(x => x.split(' ')).map({case Array(a, b) => (a.toInt, b.toInt)})
  def polygonPerimeter(points: Seq[(Int, Int)]): Double = {
    val first = points.head

    @scala.annotation.tailrec
    def doCalc(p: Seq[(Int, Int)], perimeter: Double = 0): Double = {
      if(p.length == 1) return perimeter + distance(first, p.head)
      doCalc(p.tail, perimeter + distance(p.head, p.tail.head))
    }
    def distance (a: (Int, Int), b: (Int, Int)) = {
      val o = Math.pow(a._1 - b._1, 2) + Math.pow(a._2 - b._2, 2)
      Math.sqrt(o)
    }

    doCalc(points)
  }

  def polygonArea(points: Seq[(Int, Int)]): Double = {
    def doCalc(a: Seq[Int], b: Seq[Int]): Int = {
      a.zip(b.tail).map({case (i, j) => i * j}).sum
    }

    val (x, y) = (points ++ Seq(points.head)).unzip
    (doCalc(x, y) - doCalc(y, x)) / 2
  }
}
