package me.ngrid.hackerrank.functional

import me.ngrid.util.SystemTaskBenchmark
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext

@RunWith(classOf[JUnitRunner])
class StringCompression$Test extends FlatSpec with SystemTaskBenchmark with Matchers {
  implicit val ec: ExecutionContext = ExecutionContext.global

  def timeAlgorithm: (String) => TimingResult =
    timeSystemTask(StringCompression.main(Array()))

  behavior of "blah"
  it should "work for the example code" in {
    timeAlgorithm {
      "/hackerrank/stringcompression/input-small.txt"
    } validateTime { t =>
      assert(t > 1000)
      logger.info(s"Completed with times ${timeFromNano(t)}")
    } validateOutput {
      compareWithFile("/hackerrank/stringcompression/output-small.txt")
    }
  }

  it should "work for large sample" in {
    timeAlgorithm {
      "/hackerrank/stringcompression/input-large.txt"
    } validateTime { t =>
      assert(t > 1000)
      logger.info(s"Completed with times ${timeFromNano(t)}")
    } validateOutput {
      compareWithFile("/hackerrank/stringcompression/output-large.txt")
    }
  }

  def timeFromNano(t: Long) = s"$t [ns] ${t/1000000} [ms] ${t/1000000000} [s]"
}
