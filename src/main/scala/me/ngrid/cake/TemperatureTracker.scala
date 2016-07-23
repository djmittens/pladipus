package me.ngrid.cake

import scala.collection.mutable

class TemperatureTracker {
  private val temps = mutable.ArrayBuffer.fill[Int](110)(0)
  private var min = 0
  private var max = 0
  private var count = 0

  private var mean = 0
  private var mode = 0

  def insert(t: Int): Unit = {
    temps(t) += 1
    count += 1

    min = Math.min(min, t)
    max = Math.max(max, t)

    mean = (max - min) / count

    mode = if(temps(t) - temps(mode) > 0) t else mode
  }

  def getMax = max
  def getMin = min
  def getMean = mean
  def getMode = mode
}
