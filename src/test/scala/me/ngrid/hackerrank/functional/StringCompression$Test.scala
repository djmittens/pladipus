package me.ngrid.hackerrank.functional

import me.ngrid.util.{SystemTask, SystemTaskSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContext

@RunWith(classOf[JUnitRunner])
class StringCompression$Test extends FlatSpec with SystemTaskSpec with Matchers {
  implicit val ec: ExecutionContext = ExecutionContext.global

  val algorithm: TestSubjectTask = scalaTask(){ () =>
    StringCompression.main(Array())
  }

  it should "work for the example code" in {
    algorithm {
      "/hackerrank/stringcompression/input-small.txt"
    } validateTime { t =>
      assert(t > 1000)
      logger.info(s"Completed with times ${timeFromNano(t)}")
    } validateOutput {
      compareWithFile("/hackerrank/stringcompression/output-small.txt")
    }
  }

  it should "work for large sample" in {
    algorithm {
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
