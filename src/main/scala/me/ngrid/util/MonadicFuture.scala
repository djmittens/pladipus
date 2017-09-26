package me.ngrid.util

import java.util.concurrent.TimeoutException

import scala.concurrent.duration.Duration
import scala.concurrent._
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}


object MonadicFuture {
  import scala.language.implicitConversions

  implicit def apply[T](f: Future[T]): MonadicFuture[T] = new MonadicFuture(f)
  implicit def toFuture[T](f: MonadicFuture[T]): Future[T] = f.self
}

trait MonadicFutures {
  import scala.language.implicitConversions

  implicit def apply[T](f: Future[T]): MonadicFuture[T] = MonadicFuture(f)
  implicit def toFuture[T](f: MonadicFuture[T]): Future[T] = MonadicFuture.toFuture(f)
}

class MonadicFuture[T](private val self: Future[T]) extends Future[T] {

  def each(f: (T) => Unit)(implicit ec: ExecutionContext): MonadicFuture[T] = {
    self.map{x => f(x); x}
  }

  def fmap[U](f: (T) => Future[U])(implicit ec: ExecutionContext): MonadicFuture[U] = {
    self.flatMap(f)
  }

  def fmapResult[U](f: Try[T] => Future[U])(implicit ec: ExecutionContext): MonadicFuture[U] = {
    promiseOnComplete[U] { (t, p) =>
      f(t).onComplete(p.complete)
    }.future
  }

  /**
    * Join self to another future,
    * Resolves only when both futures resolve, only return the result of the other future
    */
  def join[U](other: Future[U])(implicit ec: ExecutionContext): MonadicFuture[U] = {
    promiseOnComplete[U] { (t, p) =>
      other.onComplete(p.complete)
    }.future
  }

  def eachFailure(f: (Throwable) => Unit)(implicit ec: ExecutionContext): MonadicFuture[T] = {
    promiseOnFailure {
      case (e, p) => f(e); p.failure(e)
    }.future
  }

  def filterFailure(f: (Throwable) => Option[T])(implicit ec: ExecutionContext): MonadicFuture[T] = {
    promiseOnFailure { (e, p) =>
      p.complete(Try({f(e).get}).orElse(Failure(e)))
    }.future
  }

  def mapFailure[U <: Throwable](f: (Throwable) => U)(implicit ec: ExecutionContext): MonadicFuture[T] = {
    promiseOnFailure { (e, p) =>
      p.failure(f(e))
    }.future
  }

  def fmapFailure(f: (Throwable) => Future[T])(implicit ec: ExecutionContext): MonadicFuture[T] = {
    promiseOnFailure { (e, p) => f(e).onComplete(p.complete) }.future
  }

  def promiseOnFailure(f: (Throwable, Promise[T]) => Unit)
                      (implicit ec: ExecutionContext): Promise[T] = {
    promiseOnComplete[T] { (t, p) =>
      t match {
        case Success(v) => p.success(v)
        case Failure(e) => f(e, p)
      }
    }
  }

  def promiseOnComplete[U](f: (Try[T], Promise[U]) => Unit)
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

  override def onComplete[U](f: (Try[T]) => U)(implicit executor: ExecutionContext): Unit = self.onComplete(f)

  override def isCompleted: Boolean = self.isCompleted

  override def value: Option[Try[T]] = self.value

  @scala.throws[InterruptedException](classOf[InterruptedException])
  @scala.throws[TimeoutException](classOf[TimeoutException])
  override def ready(atMost: Duration)(implicit permit: CanAwait): MonadicFuture.this.type = {
    self.ready(atMost)
    this
  }

  @scala.throws[Exception](classOf[Exception])
  override def result(atMost: Duration)(implicit permit: CanAwait): T = self.result(atMost)

  override def transform[S](f: Try[T] => Try[S])(implicit executor: ExecutionContext): Future[S] = self.transform(f)

  def transformWith[S](f: Try[T] => Future[S])(implicit executor: ExecutionContext): Future[S] = self.transformWith(f)
}
