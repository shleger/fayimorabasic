package despat.creational

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Prototype extends App {

  val cookie = new CoconutCookie
  val machine = new CookieMachine(cookie)
  for (a <- 1 to 10){
    println(" cloned " + machine.makeCookie())
  }
}

class Cookie extends Cloneable {
  var weight: Int = 0

  override def clone(): Cookie = {
    val copy = new Cookie()
    copy.weight = this.weight
    return copy
  }
}


class CookieMachine(cookie: Cookie) {

  def makeCookie(): Cookie = {
    return cookie.clone()
  }
}


class CoconutCookie extends Cookie {}
