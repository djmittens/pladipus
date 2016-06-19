package me.ngrid.hackerrank.fractals

/**
  *
  */
object StreamingSpiral extends App {
  val `_` = ' '
  val `*` = '*'
  type Spiral = Seq[Seq[Char]]

  def spirals(seed: Spiral): Stream[Spiral] = {
    def nextSpiral (s: Spiral): Spiral = {
      val size = s.length + 4
      val r = (s :+ Seq.fill(size-4)(`_`)).map(x=> Seq(`_`,`*`) ++ x ++ Seq(`_`,`*`))
      Seq.fill(size)(`*`) +: (Seq.fill(size - 1)(`_`) :+ `*`) +: r :+ (`_` +: Seq.fill(size - 1)(`*`))
    }

    Stream.cons(seed, spirals(nextSpiral(seed)))
  }

  val minSpiral: PartialFunction [Int, Spiral] = {
    case 4 => Seq(
      Seq(`*`, `*`, `*`, `*`),
      Seq(`_`, `_`, `_`, `*`),
      Seq(`_`, `*`, `_`, `*`),
      Seq(`_`, `*`, `*`, `*`)
    )
    case 3 => Seq(
      Seq( `*`, `*`, `*`),
      Seq( `_`, `_`, `*`),
      Seq( `_`, `*`, `*`)
    )
    case 2 => Seq(
      Seq( `*`, `*`),
      Seq( `_`, `*`)
    )
    case 1 => Seq(
      Seq( `*`)
    )
  }

  val getSpiral: PartialFunction[Int, Spiral] = {
    case size if (size % 4) == 0 =>
      spirals(minSpiral(4))(size / 4 - 1)
    case size =>
      val min = size % 4
      spirals(minSpiral(min))(size / 4)
  }

  def drawSpiral(s: Spiral) = s.foreach(x => println(x.mkString))

  drawSpiral(getSpiral(12))

  spirals(minSpiral(4)).take(2).reverse.foreach(drawSpiral)
}
