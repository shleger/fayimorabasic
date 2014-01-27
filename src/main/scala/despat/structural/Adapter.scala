package despat.structural

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Adapter extends App{

  val initOneObj = new FirstObject("qqq")
}


class FirstObject(str: String){
  println("constructor vars: " + str)
}