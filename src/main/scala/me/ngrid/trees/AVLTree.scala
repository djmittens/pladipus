package me.ngrid.trees

import scala.collection.mutable

object AVLTree {
  def apply(i: Int*) = {
    var out = new AVLTree(AVLNode(i.head), size = 1)
    i.tail.foreach(out += _)
    out
  }

}

case class AVLTree (var root: AVLNode, var size: Int) {
  def +=(i: Int): this.type = {
    root = root.insert(i)
    size += 1
    this
  }

  def foreach(f: (Int) => Unit): Unit = {
    root.inOrder(f)
  }

//  override def toString: String = {
//
//  }
}

case class AVLNode(value: Int) {
  private var height: Int = 0
  private var parent: Option[AVLNode] = None
  private var left: Option[AVLNode] = None
  private var right: Option[AVLNode] = None

  def insert(n: Int): AVLNode = {
    var node = this
    var next: Option[AVLNode] = Some(this)
    while (next.nonEmpty) {
      node = next.get
      if(n > node.value){
        next = node.right
      } else if(n == node.value) {
        return this
      } else {
        next = node.left
      }
    }

    node.setChild(AVLNode(n))

    reBalance(node)
  }

  def reBalance(n: AVLNode) : AVLNode = {
    var node = n
    var next: Option[AVLNode] = Some(n)
    while(next.nonEmpty) {
      node = next.get
      height(left) - height(right) match {
        case x if x > 1 =>
          val p = node.parent
          node = rotateRight(node)
          p.foreach(_.setChild(node))
          node.parent = p
        case x if x == -1 =>
          val p = node.parent
          node = rotateLeft(node)
          p.foreach(_.setChild(node))
          node.parent = p
        case _ => // Dont touch this pretty unicorn its purrfect
      }
      next = node.parent
    }

    node
  }

  def rotateRight(y: AVLNode): AVLNode = {
    val x = y.left.get
    val T2 = x.right

    y.setLeft(T2)
    x.setRight(Some(y))

    x
  }

  def rotateLeft(x: AVLNode): AVLNode = {
    val y = x.right.get
    val T2 = y.left

    x.setRight(T2)
    y.setLeft(Some(x))

    y
  }

  def setRight(node: Option[AVLNode]) = {
    node.foreach(_.parent = Some(this))
    this.right = node
    this.recalculateHeight()
    node
  }

  def setLeft(node: Option[AVLNode]) = {
    node.foreach(_.parent = Some(this))
    this.left = node
    this.recalculateHeight()
    node
  }

  def setChild(node: AVLNode) = {
    if(node.value == value) this
    else if(node.value > value) setRight(Some(node))
    else if(node.value < value) setLeft(Some(node))
  }

  def inOrder[T](f: (Int) => T): Seq[T] = {
    val out = mutable.ArrayBuffer[T]()
    val stack = mutable.Stack[AVLNode](this)
    var node = this.left
    while(stack.nonEmpty || node.nonEmpty) {
      if(node.nonEmpty) {
        stack.push(node.get)
        node = node.flatMap(_.left)
      } else {
        node = Some(stack.pop)
        node.map(_.value).foreach(out += f(_))
        node = node.flatMap(_.right)
      }
    }
    out
  }

  def recalculateHeight(): Unit = height = Math.max(height(left), height(right)) + 1

  def height(n: Option[AVLNode]): Int = n.map(_.height).getOrElse(-1)
}
