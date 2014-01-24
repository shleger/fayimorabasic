package despat.creational


/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Factory extends App {

  val code = Country.US


//  val factory : AbstractFinancialToolFactory

  code match {
    case Country.US => new AmericanFinancialToolFactory()
    case Country.RU => new RussianFinancialToolFacory()
    case _ => throw new Exception("set country code")

  }


}


abstract class AbstractFinancialToolFactory {
   def createTaxProcessor(): TaxProcessor

}


class AmericanFinancialToolFactory extends AbstractFinancialToolFactory {
  def createTaxProcessor(): TaxProcessor = {
    return new AmericanTaxProcessor()
  }
}

class RussianFinancialToolFacory extends AbstractFinancialToolFactory {
   def createTaxProcessor(): TaxProcessor = {
    return new RussianTaxProcessor()
  }
}

abstract class TaxProcessor {
  def calcTax(inp: Input): Unit
}

class AmericanTaxProcessor extends TaxProcessor {
  def calcTax(inp: Input): Unit = {
    println("calc American taxes")
  }
}

class RussianTaxProcessor extends TaxProcessor {
  def calcTax(inp: Input): Unit = {
    println("calc Russian taxes")
  }
}

class Input {}

object Country extends Enumeration {
  val US, RU = Value
}




