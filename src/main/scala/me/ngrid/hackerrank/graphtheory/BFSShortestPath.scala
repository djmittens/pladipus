package me.ngrid.hackerrank.graphtheory


/**
  * Created by grimm on 7/25/16.
  */
object BFSShortestPath {

  import scala.collection.mutable
  import scala.io.StdIn

  def shortestPaths(s: Int, numNodes: Int, neighbors: mutable.HashMap[Int, mutable.HashSet[Int]]): Array[Int] = {
    val output = Array.fill(numNodes)(Int.MaxValue)

    var distance: Int = 0

    var level = 0
    var lastFrontier = mutable.HashSet[Int]()
    var frontier = mutable.HashSet[Int](s)

    while (frontier.nonEmpty) {
      val nextFrontier = mutable.HashSet[Int]()

      for(i <- frontier) {
        val nodes = neighbors.getOrElse(i, Nil)
        for(node <- nodes) {
          if(!lastFrontier.contains(node) && !frontier.contains(node)) {
            nextFrontier.add(node)
          }
        }
        output(i - 1) = Math.min(output(i - 1), level)
      }

      level += 1
      lastFrontier = frontier
      frontier = nextFrontier
    }

    output
  }

  def main(args: Array[String]): Unit = {
    var n = StdIn.readInt()


    (0 until n).foreach(_ => {
      val input = StdIn.readLine().split(' ').map(_.toInt)
      val nodes = mutable.HashMap[Int, mutable.HashSet[Int]]()

      val numNodes = input(0)

      for (_ <- 0 until input(1)) {

        val input = StdIn.readLine().split(' ').map(_.toInt)
        val n1 = nodes.get(input(0)).orElse({Some(mutable.HashSet[Int]())}).get
        val n2 = nodes.get(input(1)).orElse({Some(mutable.HashSet[Int]())}).get

        n1.add(input(1))
        n2.add(input(0))

        nodes(input(0)) = n1
        nodes(input(1)) = n2
      }

      for (i <- shortestPaths(StdIn.readLine().trim.toInt, numNodes, nodes)) {
        if (i == Int.MaxValue) {
          print("-1 ")
        } else if (i != 0) {
          print(s"${i * 6} ")
        }
      }
      print ("\n")
    })
  }
}
