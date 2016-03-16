package me.ngrid.akka

import akka.actor.{Actor, ActorSystem, Props}

// Define Actor Messages
case class Greet(who: String)

// Define the actor that will do the greeting.
class Greeter extends Actor {
  override def receive = {
    case Greet(who) => println(s"Hello $who")
  }
}

object HelloAkka extends App{
  val system = ActorSystem("Hello-Actors")

  val greeter = system.actorOf(Props[Greeter], "Greeter-Actor")

  greeter ! Greet("Nick")

  system.terminate()
}
