package me.ngrid.trees

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
  *
  */
@RunWith(classOf[JUnitRunner])
class AVLTreeNodeTest extends FlatSpec with Matchers {
  behavior of  "An AVL tree"
  it should "Produce a sweet tree" in {
    AVLTree(66, 77, 78, 79).foreach(println)
  }
}
