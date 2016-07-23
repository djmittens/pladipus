package me.ngrid.cake

import scala.collection.mutable

/**
  *
  */
object BalancedBinaryTree {
  def naive (node: BinaryTreeNode): Boolean =  {
    def dfs(node: BinaryTreeNode, depth: Int = 0): (Int, Int) = {
      if(node.isLeaf) return (depth, depth)

      val l = node.left.map(dfs(_, depth + 1)).getOrElse((Int.MaxValue, Int.MinValue))
      val r = node.right.map(dfs(_, depth + 1)).getOrElse((Int.MaxValue, Int.MinValue))
      (Math.min(l._1, r._1), Math.max(l._2, r._2))
    }

    val(min, max) = dfs(node)

    !(max - min > 1)
  }

  def iterative(node: BinaryTreeNode): Boolean = {
    var min = 0
    var max = 0

//    val s = mutable.Stack[(Int, BinaryTreeNode)](0, node)

//    while(s.nonEmpty) {
//      val (d, n) = s.pop()
//      if(d - min > 1) return false
//
//      if(n.isLeaf) {
//        min = Math.min(min, d)
//        max = Math.max(max, d)
//      } else {
//        n.left.foreach(x => s.push((d + 1, x)))
//        n.right.foreach(x => s.push((d + 1, x)))
//      }
//    }

    true
  }
}

object BSTChecker {
//  def naive(node: BinaryTreeNode): Boolean = {
//
//  }
}

case class BinaryTreeNode(value: Int, left: Option[BinaryTreeNode] = None, right: Option[BinaryTreeNode] = None) {
  def updateRight(n: Option[BinaryTreeNode]) = copy(right = n)
  def updateLeft(n: Option[BinaryTreeNode]) = copy(left = n)

  def isLeaf = left.isEmpty && right.isEmpty
}

