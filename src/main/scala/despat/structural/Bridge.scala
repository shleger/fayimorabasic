package despat.structural

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Bridge extends App {
  println("start " + this)

  val shapes  = List[Shape](
                            new Circle(5,10,10, new LargeCircleDrawer()),
                            new Circle(20,30,100, new SmallCircleDrawer())
                            )
  shapes.foreach(
    x => x.draw
  )



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


 class Circle(x: Int, y: Int, radius: Int, _drawer: Draw) extends Shape(_drawer){

   def draw: Unit = _drawer.drawCircle(x,y,radius)

   def enlargeRadius(multiplier: Int): Unit = radius*multiplier
 }