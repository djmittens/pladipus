package me.ngrid.util

import java.io.{File, FileInputStream, InputStream}
import java.util.Scanner

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

trait SystemTaskSpec extends LazyLogging {

  def javaTask(timeout: FiniteDuration = defaultTimeout)(op: () => Unit)
              (implicit ec: ExecutionContext): TestSubjectTask = {
    new TestSubjectTask(SystemTask.java(timeout)(op))
  }
  def scalaTask(timeout: FiniteDuration = defaultTimeout)(op: () => Unit)
               (implicit ec: ExecutionContext): TestSubjectTask = {
    new TestSubjectTask(SystemTask.scala(timeout)(op))
  }

  def defaultTimeout: FiniteDuration = 180.seconds

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
  final def compareOutput(expected: Scanner, actual: Scanner, line: Int = 1): Option[(Int, String)] = {
    if(!expected.hasNext && !actual.hasNext) return None
    if(!expected.hasNext) return Some(line -> s"Unexpected termination in expected output: ${actual.nextLine()}")
    if(!actual.hasNext) return Some(line -> s"Unexpected termination in actual output: ${expected.nextLine()}")

    if(expected.nextLine() != actual.nextLine()) return Some(line -> s"Inequality found")

    compareOutput(expected, actual, line + 1)
  }

  class TestSubjectTask (task: SystemTask) {
    def apply(stdinPath: String): TimingResult = {
      val file = Option(this.getClass.getResourceAsStream(stdinPath)).get
      try {
        TimingResult.tupled(task.run(file))
      } catch {
        case e: Throwable =>
          throw new Exception("Encountered a problem executing the given task: ", e)
      } finally {
        file.close()
      }
    }
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
