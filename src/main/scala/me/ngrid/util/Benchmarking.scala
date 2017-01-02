package me.ngrid.util

import java.io._
import java.nio.file.Files

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.util.Try

trait Benchmarking extends LazyLogging with MonadicFutures{

  def timeSystemTask(stdIn: InputStream, op: => Unit, timeout: Duration)
                    (implicit ec: ExecutionContext): (Long, File) =  {
    val tmp = createTempFile()
    tmp.deleteOnExit()
    val outstr = new PrintStream(tmp)
    try {
      logger.info(s"Created a temporary file for output in ${tmp.getAbsolutePath}")
      timeSystemTask(stdIn, outstr, op, timeout) -> tmp
    } finally {
      outstr.close()
    }
  }

  def timeSystemTask(stdIn: InputStream, stdOut: PrintStream, op: => Unit, timeout: Duration)
                    (implicit ec: ExecutionContext): Long = {
    logger.info("Waiting to perform application timing ...")
    Benchmarking.synchronized {
      val in = System.in
      val out = System.out
      try {
        logger.info(s"Beginning timing of an application, reassigning global stdin / stdout")
        System.setIn(stdIn)
        System.setOut(stdOut)
        scala.Console.withIn(stdIn) {
         scala.Console.withOut(stdOut) {
           Await.result(time(op), timeout)
         }
        }
      } finally {
        System.setIn(in)
        System.setOut(out)
        logger.info("Finished timing, restoring stdin / stdout")
      }
    }
  }

  def createTempFile(): File = {
    Files.createTempFile("nicksbench", ".txt").toFile
  }

  def time(op: => Unit)(implicit ec: ExecutionContext): Future[Long] = {
    val p = Promise[Long]()
    ec.prepare().execute(new Runnable {
      override def run(): Unit = {
        val start = System.nanoTime()
        val res = Try(op)
        val end = System.nanoTime()
        p.complete(res.map(_ => end - start))
      }
    })
    p.future
  }
}

object Benchmarking {}
