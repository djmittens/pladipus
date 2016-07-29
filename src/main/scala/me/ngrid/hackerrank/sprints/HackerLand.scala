package me.ngrid.hackerrank.sprints


object HackerLand {

  import scala.io.StdIn
  import scala.collection.mutable
  import java.util.{Comparator, PriorityQueue}

  def main(args: Array[String]): Unit = {
    val counts = StdIn.readLine().split(' ').map(_.toInt)
    val country = Country(counts(0), counts(1))

    for (i <- 0 until country.numEdges) {
      val road = StdIn.readLine().split(' ').map(_.toInt)
      country.addRoad(road(0), road(1), road(2))
    }

    val sum = sumsOfShortestRoads(country)
    println(java.lang.Long.toBinaryString(sum))
//    println(sum)
  }

  def sumsOfShortestRoads(c: Country): Long = {
    val soFar = mutable.HashSet[Int]()
    var sum = 0l
    for (start <- c.cities.valuesIterator) {
      sum += shortestPaths(start, soFar)
      soFar.add(start.id)
    }
    sum
  }

  def shortestPaths(start: City, exclude: mutable.HashSet[Int]): Long = {
    class Node(var weight: Long, val city: City, var visited: Boolean = false) extends Comparable[Node] {
      override def compareTo(o: Node): Int = city.id.compareTo(o.city.id)

      override def equals(obj: scala.Any): Boolean = {
        obj match {
          case node: Node => node.city.id == city.id
          case c: City => city.id == c.id
          case _ => false
        }
      }

      override def toString: String = {
        s"${city.id}) - $weight"
      }
    }

    //    val processing = mutable.PriorityQueue[Node](new Node(0, start))
    val processing = new PriorityQueue[Node](64, new Comparator[Node] {
      override def compare(o1: Node, o2: Node): Int = o1.weight.compareTo(o2.weight)
    })
    val visited = mutable.HashMap[Int, Node]()

    processing.offer(new Node(0, start))

    while (!processing.isEmpty) {
      val node = processing.poll()

      visited.put(node.city.id, node)

      for (road <- node.city.roads) {

        val o = visited.getOrElse(road.otherCity.id, {
          new Node(node.weight + road.length, road.otherCity)
        })

        o.weight = Math.min(node.weight + road.length, o.weight)


        if (processing.contains(road.otherCity)) {
          processing.remove(road.otherCity)
          processing.offer(o)
        }

        else if (!visited.contains(o.city.id)) {
          processing.offer(o)
        }

        visited.put(o.city.id, o)
      }
    }

    var sum = 0l
    for (node <- visited.values) {
      if (!exclude.contains(node.city.id)) {
        sum += node.weight
//        println(s"(${start.id}, $node")
      }
    }

    sum
  }


  case class Country(numNodes: Int, numEdges: Int) {
    val cities = mutable.HashMap[Int, City]()

    def addRoad(city1: Int, city2: Int, ci: Long): Unit = {
      val c1 = cities.getOrElseUpdate(city1, {
        new City(city1)
      })
      val c2 = cities.getOrElseUpdate(city2, {
        new City(city2)
      })

      val length = if(ci == 0) 1 else 2 << (ci - 1)

      c1.roads += Road(length, c2)
      c2.roads += Road(length, c1)
    }

    def getCity(i: Int) = cities(i)
  }

  case class Road(length: Long, otherCity: City)

  class City(val id: Int) {
    val roads = mutable.ArrayBuffer[Road]()
  }

}
