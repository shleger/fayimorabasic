package despat.structural

import scala.collection.mutable.ListBuffer

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Composite extends App{

  val e1 = new Ellipse
  val e2 = new Ellipse
  val e3 = new Ellipse

  val mainGr = new CompositeGraphic

  val g1 = new CompositeGraphic
  val g2 = new CompositeGraphic
  val g3 = new CompositeGraphic

  g1.add(e1)
  g2.add(e2)
  g3.add(e3)

  mainGr.add(g1)
  mainGr.add(g2)
  mainGr.add(g3)


  mainGr.print

}

trait Graphic{
  def print
}

class  Ellipse extends Graphic{
  def print: Unit = println("painted " + this)
}

class CompositeGraphic extends Graphic{
  private val mChildGraphics  = new ListBuffer[Graphic]

  def print: Unit =  mChildGraphics.foreach(x => x.print)

  def add(g: Graphic) = mChildGraphics += g
  def remove(g: Graphic) = mChildGraphics -= g
}