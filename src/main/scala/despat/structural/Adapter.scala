package despat.structural

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Adapter extends App {

  val chief = new ChiefAdapter
  val key = chief.makeDinner

}


trait Chief {

  def makeBreakfast: AnyRef

  def makeDinner: AnyRef

  def makeSupper: AnyRef

}


class Plumber {
  def getPipe = new AnyRef

  def getKey = new AnyRef

  def getScrewDriver = new AnyRef
}

class ChiefAdapter extends Chief {
  val plumber = new Plumber

  def makeBreakfast: AnyRef = plumber.getKey

  def makeDinner: AnyRef = { println("plumber make dinner")  ; plumber.getPipe   }

  def makeSupper: AnyRef = plumber.getScrewDriver
}