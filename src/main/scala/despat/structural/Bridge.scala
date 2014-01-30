package despat.structural

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Bridge extends App {

}

trait Draw {
  def drawCircle(x: Int, y: Int, radius: Int)
}

class SmallCircleDrawer extends Draw {
  val radiusMultiplier: Double = 0.25

  def drawCircle(x: Int, y: Int, radius: Int): Unit = {
    println("Small circle center = " + x + "," + y + " radius = " + radius * radiusMultiplier)
  }
}

class LargeCircleDrawer extends Draw {
  val radiusMultiplier: Int = 10

  def drawCircle(x: Int, y: Int, radius: Int): Unit = {
    println("Large circle center  = " + x + "," + y + " radius = " + radius * radiusMultiplier)
  }
}


abstract class Shape(_drawer: Draw) {
  protected val drawer = _drawer
  def draw
  def enlargeRadius(multiplier: Int)


}


abstract class Circle(_x: Int, _y: Int, _radius: Int, _drawer: Draw) extends Shape(_drawer: Draw){
  private var x: Int = _x
  private var y: Int = _y
  private var radius: Int = _radius

}