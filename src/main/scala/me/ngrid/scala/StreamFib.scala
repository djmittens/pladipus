package me.ngrid.scala


import scala.math.BigInt
object StreamFib extends App {
  lazy val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map {
    case (a, b) => a + b
  }


  fibs take 5 foreach println
}
