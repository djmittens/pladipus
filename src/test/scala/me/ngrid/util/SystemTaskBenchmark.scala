package me.ngrid.util

import java.io.{File, FileInputStream, InputStream}
import java.util.Scanner

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{Duration, _}

trait SystemTaskBenchmark extends Benchmarking with LazyLogging {
  def timeSystemTask(op: => Unit, timeout: Duration = 180.seconds)(stdInPath: String)
                    (implicit ec: ExecutionContext): TimingResult = {
    val file = Option(this.getClass.getResourceAsStream(stdInPath)).get
    try {
      TimingResult.tupled(timeSystemTask(file, op, timeout))
    } catch {
      case e: Throwable =>
        throw new Exception("Encountered a problem executing the given task: ", e)
    } finally {
      file.close()
    }
  }

  def compareWithFile(outPath: String)(is: InputStream): Unit = {
    val s1 = new Scanner(getClass.getResourceAsStream(outPath))
    val s2 = new Scanner(is)
    try {
      val res = compareOutput(s1, s2)
      if(res.nonEmpty)
        throw new Exception(res.get.toString())
    } finally {
      s1.close()
    }
  }

  @scala.annotation.tailrec
  final def compareOutput(f1: Scanner, f2: Scanner, line: Int = 1): Option[(Int, String)] = {
    if(!f1.hasNext && !f2.hasNext) return None
    if(!f1.hasNext) return Some(line -> s"Unexpected termination in expected output: ${f2.nextLine()}")
    if(!f2.hasNext) return Some(line -> s"Unexpected termination in actual output: ${f1.nextLine()}")

    if(f1.nextLine() != f2.nextLine()) return Some(line -> s"Inequality found")

    compareOutput(f1, f2, line + 1)
  }

  case class TimingResult(time: Long, output: File) {
    def validateTime(op: Long => Unit): TimingResult = {
      op(time)
      this
    }

    def validateOutput(op: InputStream => Unit): TimingResult = {
      val stream = new FileInputStream(output)
      try op(stream) finally stream.close()
      this
    }
  }
}
