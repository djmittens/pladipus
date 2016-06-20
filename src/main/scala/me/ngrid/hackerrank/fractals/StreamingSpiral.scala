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

  val getSpiral: PartialFunction[Int, Stream[Spiral]] = {
    case size if (size % 4) == 0 =>
      Seq(Seq[Char]()) #:: spirals(minSpiral(4))
    case size =>
      val min = size % 4
      spirals(minSpiral(min))
  }

  def drawSpiral(s: Spiral) = s.foreach(x => println(x.mkString))

  val size = 12
  val sp = getSpiral(size)

  drawSpiral(sp(size / 4))

  sp.take(size/4 + 1).reverse.tail.foreach(drawSpiral)
}
