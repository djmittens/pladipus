package me.ngrid.hackerrank.fractals

/**
  * Draws a size x size spiral using recursion
  */
object RecursiveSpiral extends App{
  val `_` = ' '
  val `*` = '*'
  def spiral (size: Int): Seq[Seq[Char]] = {
    if(size == 4) return Seq(
      Seq(`*`, `*`, `*`, `*`),
      Seq(`_`, `_`, `_`, `*`),
      Seq(`_`, `*`, `_`, `*`),
      Seq(`_`, `*`, `*`, `*`)
    )
    else if(size == 3) return Seq(
      Seq( `*`, `*`, `*`),
      Seq( `_`, `_`, `*`),
      Seq( `_`, `*`, `*`)
    )
    else if(size == 2) return Seq(
      Seq( `*`, `*`),
      Seq( `_`, `*`)
    )
    else if(size == 1) return Seq(
      Seq( `*`)
    )

    val r = (spiral(size-4) :+ Seq.fill(size-4)(`_`)).map(x=> Seq(`_`,`*`) ++ x ++ Seq(`_`,`*`))
    Seq.fill(size)(`*`) +: (Seq.fill(size - 1)(`_`) :+ `*`) +: r :+ (`_` +: Seq.fill(size - 1)(`*`))
  }

  spiral(12).foreach(x => println(x.mkString))
}
