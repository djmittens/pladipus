package me.ngrid.cake

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers, Outcome}

/**
  *
  */
@RunWith(classOf[JUnitRunner])
class TemperatureTrackerTest extends FlatSpec with Matchers with BeforeAndAfterEach{

  var tracker = new TemperatureTracker()

  override def beforeEach(): Unit = {
    tracker = new TemperatureTracker()
    tracker.insert(8)
    tracker.insert(4)
    tracker.insert(10)
    tracker.insert(11)
    tracker.insert(8)
    tracker.insert(11)
    super.beforeEach()
  }

  behavior of "A Temperature tracker"
  it should "return the correct mean" in {
    tracker.getMean should equal(7 / 6)
  }

  it should "return the correct mode" in {
    tracker.getMode should equal (8)
  }
}
