package despat.creational

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Builder extends App {
  val waiter: Waiter = new Waiter()

  val pb = new SpicyPizzaBuilder()
  waiter.setPizzaBuilder(pb)
  waiter.constructPizza()

  val pizza: Pizza = waiter.getPizza()

}

class Pizza() {
  var dough: String = null
  var sauce: String = null
  var topping: String = null

  override def toString: String = dough + " " + sauce + " " + topping
}

abstract class PizzaBuilder() {
  def buildDough()

  def buildSauce()

  def buildTopping()

  protected var pizza: Pizza = null

  def createNewPizzaProduct: Unit = {
    pizza = new Pizza()
  }

  def getPizza():Pizza = pizza

}

class MushroomPizzaBuilder extends PizzaBuilder {

  def buildDough(): Unit = pizza.dough = "d1"

  def buildSauce(): Unit = pizza.sauce = "s1"

  def buildTopping(): Unit = pizza.topping = "t1"
}

class SpicyPizzaBuilder extends PizzaBuilder {
  def buildDough(): Unit = pizza.dough = "d2"

  def buildSauce(): Unit = pizza.sauce = "s2"

  def buildTopping(): Unit = pizza.topping = "t2"

}

class Waiter {
  private var pizzaBuilder: PizzaBuilder = null

  def getPizza() :Pizza = {

    println("Pizza on table: " + pizzaBuilder.getPizza())
    pizzaBuilder.getPizza()

  }

  def setPizzaBuilder(it: PizzaBuilder) = pizzaBuilder = it

  def constructPizza() = {
    pizzaBuilder.createNewPizzaProduct
    pizzaBuilder.buildDough()
    pizzaBuilder.buildSauce()
    pizzaBuilder.buildTopping()
  }
}