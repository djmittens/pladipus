package me.ngrid.trees

import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
class AVLTreeNodeTest extends FlatSpec with Matchers {
  behavior of  "An AVL tree"
  it should "Produce a sweet tree" in {
    AVLTree(66, 77, 78, 79).foreach(println)
  }
}
