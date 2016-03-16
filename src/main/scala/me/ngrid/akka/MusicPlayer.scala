package me.ngrid.akka

import akka.actor.{ActorSystem, Props, Actor}
import me.ngrid.akka.MusicController.{Stop, Play}
import me.ngrid.akka.MusicPlayer.{StopMusic, StartMusic}

object MusicController {
  sealed trait ControllerMsg

  case object Play extends ControllerMsg
  case object Stop extends ControllerMsg
}

class MusicController extends Actor {
  override def receive = {
    case Play =>
      println ("Music Started ....")
    case Stop =>
      println ("Music Stopped ....")
  }
}
object MusicPlayer {
  sealed trait PlayMsg
  case object StartMusic extends PlayMsg
  case object StopMusic extends PlayMsg
}

class MusicPlayer extends Actor {
  def receive = {
    case StopMusic =>
      println("I dont want to stop music")
    case StartMusic =>
      val controller = context.actorOf(Props[MusicController], "controller")
      controller ! Play
  }
}

object MusicApp extends App {
  val system = ActorSystem("music-player")

  val player = system.actorOf(Props[MusicPlayer], "music-player")

  player ! StartMusic

  player ! StopMusic

  system.terminate()
}
