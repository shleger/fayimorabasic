package despat.creational

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Factory  extends App{

  val list: List[Creator] = List(new CreatorA, new CreatorB )

  list.foreach(f=>{
    val q = f.factory()
    println(q.getClass)
  })

}


abstract class Product{}

class ProductA extends Product{print("productA ")}
class ProductB extends Product{print("productB ")}

abstract class Creator{
   def factory() : Product
}

class CreatorA extends Creator{
  def factory(): Product = new ProductA
}

class CreatorB extends Creator{
  def factory(): Product = new ProductB
}

