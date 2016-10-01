package me.ngrid.hackerrank.warmup

object TimeConversion extends App {
  import scala.io.StdIn

  val rgxp = "^(\\d\\d)(.*)(AM|PM)$".r
  val o = StdIn.readLine() match {
    case rgxp("12", rest, "AM") =>
      "00" + rest
    case rgxp(hour, rest, "AM") =>
      hour + rest
    case rgxp("12", rest, "PM") =>
      "12" + rest
    case rgxp(hour, rest, "PM") =>
      val h = hour.toInt + 12
      h + rest
  }

  println(o)
}
