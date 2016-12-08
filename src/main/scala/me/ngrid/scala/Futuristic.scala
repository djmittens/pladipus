package me.ngrid.scala

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.duration._

trait MonadicFutures {
  import scala.language.implicitConversions

  implicit class MonadicFuture[T](val self: Future[T]) {
    def #>[U] (f: (T) => U)(implicit ec: ExecutionContext): Future[U] = {
      self.map(f)
    }

    def ~>[U] (f: (T) => Unit)(implicit ec: ExecutionContext): Future[T] = {
      self.foreach(f)
      self
    }

    def #>>[U] (f: (T) => Future[U])(implicit ec: ExecutionContext): Future[U] = {
      self.flatMap(f)
    }

    def >>[U] (f: Future[U])(implicit ec: ExecutionContext): Future[U] = {
      self.flatMap(_ => f)
    }

    def #>>>[U] (f: Try[T] => Future[U])(implicit ec: ExecutionContext): Future[U] = {
      self.flatMap { x =>
        f(Success[T](x))
      } recoverWith {
        case e =>
          try {
            f(Failure(e))
          } catch {
            case e: Throwable => Future.failed[U](e)
          }
      }
    }

    def ?> (f: PartialFunction[Throwable, T])(implicit ec: ExecutionContext): Future[T] = {
      self.recover(f)
    }

    def ?> (v: T)(implicit ec: ExecutionContext): Future[T] = {
      self.recover(PartialFunction(_ => v))
    }

    def ?>>> (f: PartialFunction[Throwable, Future[T]])(implicit ec: ExecutionContext): Future[T] = {
      self.recoverWith(f)
    }

    def ?>> (f: Future[T])(implicit ec: ExecutionContext): Future[T] = {
      self.recoverWith(PartialFunction(_ => f))
    }
  }
}

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

  val res = f1 #> { x: Int =>
    x * 2
  } #>>> {
    case Success(4) => f2
  } ~> println >> f1

  Await.result(res, 1.seconds)
}
