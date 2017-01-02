package me.ngrid.util

import java.io.{File, InputStream, PrintStream}
import java.nio.file.Files

import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.util.Try

object SystemTask extends LazyLogging {
  def java(timeout: Duration)(op: () => Unit)(implicit ec: ExecutionContext): SystemTask = {
    new SystemTask(timeout, (stdIn: InputStream, stdOut: PrintStream) => {

      logger.info("Waiting to perform application timing ...")
      SystemTask.synchronized {
        val in = System.in
        val out = System.out
        try {
          logger.info(s"Beginning timing of an application, reassigning global stdin / stdout")
          System.setIn(stdIn)
          System.setOut(stdOut)
          Await.result(time{op()}, timeout)
        } finally {
          System.setIn(in)
          System.setOut(out)
          logger.info("Finished timing, restoring stdin / stdout")
        }
      }
    })
  }

  def scala(timeout: Duration)(op: () => Unit)(implicit ec: ExecutionContext): SystemTask = {
    new SystemTask(timeout, (stdIn: InputStream, stdOut: PrintStream) => {
      Await.result(time {
        Console.withIn(stdIn) {
          Console.withOut(stdOut) { op() }
        }
      }, timeout)
    })
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

class SystemTask(timeout: Duration, op: (InputStream, PrintStream) => Long)
  extends LazyLogging with MonadicFutures {

  def run (stdIn: InputStream): (Long, File) = {
    val tmp = createTempFile()
    tmp.deleteOnExit()
    val outstr = new PrintStream(tmp)
    try {
      logger.info(s"Created a temporary file for output in ${tmp.getAbsolutePath}")
      op(stdIn, outstr) -> tmp
    } finally {
      outstr.close()
    }
  }

  def run (stdIn: InputStream, stdOut: PrintStream): Long = {
    op(stdIn, stdOut)
  }

  def createTempFile(): File = {
    Files.createTempFile("nicksbench", ".txt").toFile
  }
}

