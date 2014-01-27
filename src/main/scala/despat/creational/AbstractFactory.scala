package despat.creational


/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
object Factory extends App {

  val code = Country.US


  var factory: AbstractFinancialToolFactory = null

  code match {
    case Country.US => factory = new AmericanFinancialToolFactory()
    case Country.RU => factory = new RussianFinancialToolFacory()
    case _ => throw new Exception("set country code")

  }

  val inpProc = new InputProcessor(factory)
  inpProc.processInput(new Input)

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

class InputProcessor(iaxProc: AbstractFinancialToolFactory) {
  val processor = iaxProc.createTaxProcessor()


  def processInput(inp: Input) = {
    processor.calcTax(inp)
  }
}

object Country extends Enumeration {
  val US, RU = Value
}




