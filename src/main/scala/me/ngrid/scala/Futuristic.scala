package me.ngrid.scala

import scala.concurrent.{Await, ExecutionContext, Future, Promise}
import scala.util.{Failure, Success, Try}
import scala.concurrent.duration._
import scala.util.control.NonFatal


object Futuristic extends App with MonadicFutures {
  import ExecutionContext.Implicits.global

  val f1 = Future {
    Thread.sleep(100)
    1
  }

  val f2 = Future {
    Thread.sleep(400)
    2
  }

  val f3 = Future {
    Thread.sleep(600)
    3
  }
  val f4 = Future {
    Thread.sleep(300)
    4
  }

  val res = f1 > { _ => 1 } >* { _ > 4 } <| println <* { _ => Some(20) } >< {
    case Success(20) =>
      println("moo")
      f2
  } <| println <>> { _ => f4 } <-> f3 >| println

  Await.result(res, 1.seconds)
}

object MonadicFutures extends MonadicFutures

trait MonadicFutures {

  import scala.language.implicitConversions

  implicit class MonadicFuture[T](self: Future[T]) {

    /**
      * Foreach on success
      * @param f
      * @param ec
      * @return
      */
    def >|(f: (T) => Unit)(implicit ec: ExecutionContext): Future[T] = {
      self.map{x => f(x); x}
    }

    /**
      * Map successful value of this future.
      * @param f
      * @param ec
      * @tparam U
      * @return
      */
    def >[U](f: (T) => U)(implicit ec: ExecutionContext): Future[U] = {
      self.map(f)
    }

    /**
      * Filter (turn into failure)
      * @param f
      * @param ec
      * @return
      */
    def >*(f: (T) => Boolean)(implicit ec: ExecutionContext): Future[T] = {
      self.filter(f)
    }

    /**
      * Flat Map successfull value
      * @param f
      * @param ec
      * @tparam U
      * @return
      */
    def >>[U](f: (T) => Future[U])(implicit ec: ExecutionContext): Future[U] = {
      self.flatMap(f)
    }

    /**
      * Flat Map the complete result, success and failure
      * @param f
      * @param ec
      * @tparam U
      * @return
      */
    def ><[U](f: Try[T] => Future[U])(implicit ec: ExecutionContext): Future[U] = {
      promiseOnComplete[U] { (t, p) =>
        f(t).onComplete(p.complete)
      }.future
    }

    /**
      * Join self to another future,
      * Resolves only when both futures resolve, only return the result of the other future
      *
      * @param other
      * @tparam U
      * @return
      */
    def <->[U](other: Future[U])(implicit ec: ExecutionContext): Future[U] = {
      promiseOnComplete[U] { (t, p) =>
        other.onComplete(p.complete)
      }.future
    }

    /**
      * ForEach on failure
      * @param f
      * @param ec
      * @return
      */
    def <|(f: (Throwable) => Unit)(implicit ec: ExecutionContext): Future[T] = {
      promiseOnFailure {
        case (e, p) => f(e); p.failure(e)
      }.future
    }

    /**
      * Filter on failure
      * @param f
      * @param ec
      * @return
      */
    def <*(f: (Throwable) => Option[T])(implicit ec: ExecutionContext): Future[T] = {
      promiseOnFailure { (e, p) =>
        p.complete(Try({f(e).get}).orElse(Failure(e)))
      }.future
    }

    /**
      * Map on a failure
      * @param f
      * @param ec
      * @tparam U
      * @return
      */
    def <>[U <: Throwable](f: (Throwable) => U)(implicit ec: ExecutionContext): Future[T] = {
      promiseOnFailure { (e, p) =>
        p.failure(f(e))
      }.future
    }

    /**
      * Flat Map on failure
      * @param f
      * @param ec
      * @return
      */
    def <>>(f: (Throwable) => Future[T])(implicit ec: ExecutionContext): Future[T] = {
      promiseOnFailure { (e, p) => f(e).onComplete(p.complete) }.future
    }

    private def promiseOnFailure(f: (Throwable, Promise[T]) => Unit)
                                      (implicit ec: ExecutionContext): Promise[T] = {
      promiseOnComplete[T] { (t, p) =>
        t match {
          case Success(v) => p.success(v)
          case Failure(e) => f(e, p)
        }
      }
    }

    private def promiseOnComplete[U](f: (Try[T], Promise[U]) => Unit)
                                          (implicit ec: ExecutionContext): Promise[U] = {
      val p = Promise[U]
      self.onComplete { v =>
        try {
          f(v, p)
        } catch {
          case NonFatal(e) => p.failure(e)
        }
      }
      p
    }
  }

}
